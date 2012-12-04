package com.vangent.tap.sys.service.encrypt.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.vangent.formengine.sys.exception.SystemException;

/**
 * Encrypts a file use open PGP libraries
 */
public class PGPEncryptServiceImpl implements EncryptService, InitializingBean {
    private static Logger log = Logger.getLogger(PGPEncryptServiceImpl.class);
            
    private final int BUFFER_SIZE = 1<<16;
    //configurable public key file location.  Default location is this.classpath\pubring.pgp
    private String publicKeyLocation;
    //The public key
    private PGPPublicKey publicKey; 
    
    //configurable private key file location.  Default location is this.classpath\secring.pgp
    private String privateKeyLocation;
    //the private key
    private PGPPrivateKey privateKey; 
    
    private char[] password = "PGS-date:120103-LHW".toCharArray();
    
    /*
     * (non-Javadoc)
     * @see com.vangent.tap.sys.service.encrypt.service.EncryptService#generatePublicKey(java.io.InputStream)
     */
    @SuppressWarnings("unchecked")
    public PGPPublicKey findPublicKey(InputStream keyFileStream) throws IOException, PGPException 
    {
        InputStream decoder = PGPUtil.getDecoderStream(keyFileStream);
        PGPPublicKeyRingCollection rings = new PGPPublicKeyRingCollection(decoder);
        
        Iterator ringIter = rings.getKeyRings();
        while (ringIter.hasNext()) {
            PGPPublicKeyRing ring = (PGPPublicKeyRing)ringIter.next();
            Iterator keyIter = ring.getPublicKeys();
            while (keyIter.hasNext()) {
                PGPPublicKey key = (PGPPublicKey)keyIter.next();
                if (key.isEncryptionKey()) {
                    return key;
                }
            }
        }
        throw new PGPException("Failed to generate public PGP encryption key.");
    }
    
    /*
     * (non-Javadoc)
     * @see com.vangent.tap.sys.service.encrypt.service.EncryptService#encryptFile(java.lang.String, java.lang.String)
     */
    public void encryptFile(String inputFilePath, String outputFilePath) throws SystemException
    {
        this.encryptFile(inputFilePath, outputFilePath, this.publicKey);
    }
    
    /*
     * (non-Javadoc)
     * @see com.vangent.tap.sys.service.encrypt.service.EncryptService#encryptFile(java.lang.String, java.lang.String, org.bouncycastle.openpgp.PGPPublicKey)
     */
    public void encryptFile(String inputFilePath, String outputFilePath, PGPPublicKey publicKey) throws SystemException
    {
        InputStream in = null;
        OutputStream  out = null;
        try
        {
            in = new FileInputStream(inputFilePath);
            out = new FileOutputStream(outputFilePath);
            
            this.encryptFile(in, out, publicKey);
        }
        catch (FileNotFoundException e)
        {
           throw new SystemException(e);
        }
        catch (IOException e)
        {
           throw new SystemException(e);
        }
        catch (PGPException e)
        {
           throw new SystemException(e);
        }
        catch (NoSuchProviderException e)
        {
           throw new SystemException(e);
        }
        finally {
            try
            {
                if(in != null)
                {
                    in.close(); 
                }
                if(out != null)
                {
                    out.close(); 
                }
            }
            catch (IOException e)
            {
              throw new SystemException("Fail to close input/output stream");
            }
        }
    }
   
    
    /*
     * (non-Javadoc)
     * @see com.vangent.tap.sys.service.encrypt.service.EncryptService#encryptFile(java.io.InputStream, java.io.OutputStream)
     */
    public void encryptFile(InputStream in, OutputStream out) throws IOException, PGPException, NoSuchProviderException{
        this.encryptFile(in, out, this.publicKey);
    }
    
    /*
     * (non-Javadoc)
     * @see com.vangent.tap.sys.service.encrypt.service.EncryptService#encryptFile(java.io.InputStream, java.io.OutputStream, org.bouncycastle.openpgp.PGPPublicKey)
     */
    public void encryptFile(InputStream in, OutputStream out, PGPPublicKey publicKey) throws IOException, PGPException, NoSuchProviderException
    {
        Security.addProvider(new BouncyCastleProvider());
       
        
        PGPEncryptedDataGenerator pk = new PGPEncryptedDataGenerator(PGPEncryptedData.CAST5, true, new SecureRandom(), "BC");
   
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        PGPUtil.writeFileToLiteralData(pk.open(bOut, buffer), PGPLiteralData.TEXT, new File("C:\\apps\\hesc\\tap\\jobManager\\cycle11\\ackExtract\\HE8346Y11.1.txt"));
        
        pk.addMethod(publicKey);
        OutputStream encryptedOut = pk.open(bOut, BUFFER_SIZE);
        try {
            //byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                encryptedOut.write(buffer, 0, bytesRead);
            }
            encryptedOut.flush();
            out.flush();
        }
        finally {
            encryptedOut.close();
        }
    }
    
    
    /**
     * @return The location of the public key on disk
     */
    public String getPublicKeyLocation() {
        return publicKeyLocation;
    }

    /**
     * Sets the location of the public key on disk
     * @param publicKeyLocation the public key location
     */
    public void setPublicKeyLocation(String publicKeyLocation) {
        this.publicKeyLocation = publicKeyLocation;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception
    {
        InputStream publicKeyFileStream = null;
        if(this.publicKeyLocation == null)
        {
            log.debug("get default public key file. pubring.pgp");
            publicKeyFileStream = this.getClass().getResourceAsStream("pubring.pgp"); 
        }
        else
        {
            publicKeyFileStream = new FileInputStream(this.publicKeyLocation);
        }
        this.publicKey = this.findPublicKey(publicKeyFileStream);
        
        InputStream privateKeyFileStream = null;
        if(this.privateKeyLocation == null)
        {
            log.debug("get default private key file. secring.pgp");
            publicKeyFileStream = this.getClass().getResourceAsStream("secring.pgp"); 
        }
        else
        {
            privateKeyFileStream = new FileInputStream(this.privateKeyLocation);
        }
        
        this.privateKey = this.findPrivateKey(privateKeyFileStream, this.publicKey.getKeyID(), this.password);
        
        
        Assert.notNull(this.publicKey, "Default publicKey must not be null");
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public PGPPrivateKey findPrivateKey(InputStream keyIn, Long keyId, char[] password) throws IOException, PGPException, NoSuchProviderException
    {
        Security.addProvider(new BouncyCastleProvider());
        
        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(keyIn));
        if(keyId == null)
        {
            keyId = this.publicKey.getKeyID();
        }
        PGPSecretKey pgpSecKey = pgpSec.getSecretKey(keyId);
        
        if (pgpSecKey == null) 
        {
            throw new PGPException("Failed to find PGP secret key.");
        }
        
        return pgpSecKey.extractPrivateKey(password, "BC");

    }
    
    public void decrypt(InputStream in, OutputStream out) throws Exception {
        in = PGPUtil.getDecoderStream(in);
        try {
                PGPObjectFactory pgpF = new PGPObjectFactory(in);
                PGPEncryptedDataList enc;
                Object o = pgpF.nextObject();
                //
                // the first object might be a PGP marker packet.
                //
                if (o instanceof PGPEncryptedDataList) {
                        enc = (PGPEncryptedDataList) o;
                } else {
                        enc = (PGPEncryptedDataList) pgpF.nextObject();
                }
              
                Iterator it = enc.getEncryptedDataObjects();
              
                PGPPublicKeyEncryptedData pbe = null;
                while (pbe == null && it.hasNext()) {
                        pbe = (PGPPublicKeyEncryptedData) it.next();
                        System.out.println("pbe id=" + pbe.getKeyID());
                }
                
               
                InputStream clear = pbe.getDataStream(this.privateKey, "BC");
       
                System.out.println("available=="+clear.available());
             
      
                int bytesRead;
                while ((bytesRead = clear.read()) !=0) {
                    out.write(bytesRead);
                }
                
                
        } catch (PGPException e) {
            System.out.println("helo");
                System.err.println(e);
                if (e.getUnderlyingException()!= null) {
                        e.getUnderlyingException().printStackTrace();
                }
        }

    }
    
    
    public static String _decrypt(InputStream in, InputStream keyIn,
            char[] passwd) throws Exception {
    in = PGPUtil.getDecoderStream(in);
    try {
            PGPObjectFactory pgpF = new PGPObjectFactory(in);
            PGPEncryptedDataList enc;
            Object o = pgpF.nextObject();
            //
            // the first object might be a PGP marker packet.
            //
            if (o instanceof PGPEncryptedDataList) {
                    enc = (PGPEncryptedDataList) o;
            } else {
                    enc = (PGPEncryptedDataList) pgpF.nextObject();
            }
            //
            // find the secret key
            //
            
            InputStream decodedSecretKeyIn = PGPUtil.getDecoderStream(keyIn);
            
            Iterator it = enc.getEncryptedDataObjects();
            PGPPrivateKey sKey = null;
            PGPPublicKeyEncryptedData pbe = null;
            while (sKey == null&&it.hasNext()) {
                    pbe = (PGPPublicKeyEncryptedData) it.next();
                    System.out.println("pbe id=" + pbe.getKeyID());
                    sKey = findSecretKeyX(decodedSecretKeyIn, pbe.getKeyID(), passwd);
            }
            if (sKey == null) {
                   throw new IllegalArgumentException("secret key for message not found.");
            }
            
            InputStream clear = pbe.getDataStream(sKey, "BC");
            
           int data;
            while((data=clear.read() )!=-1){
                
            }
            
          
            
            
            return "";
            
//            PGPObjectFactory plainFact = new PGPObjectFactory(clear);
//            Object message = plainFact.nextObject();
//            if (message instanceof PGPCompressedData) {
//                    PGPCompressedData cData = (PGPCompressedData) message;
//                    PGPObjectFactory pgpFact = new PGPObjectFactory(cData.getDataStream());
//                    message = pgpFact.nextObject();
//            }
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            if (message instanceof PGPLiteralData) {
//                    PGPLiteralData ld = (PGPLiteralData) message;
//                    InputStream unc = ld.getInputStream();
//                    int ch;
//                    while ((ch = unc.read())>= 0) {
//                            baos.write(ch);
//                    }
//            } else if (message instanceof PGPOnePassSignatureList) {
//                    throw new PGPException("encrypted message contains a signed message - not literal data.");
//            } else {
//                    throw new PGPException("message is not a simple encrypted file - type unknown.");
//            }
//            if (pbe.isIntegrityProtected()) {
//                    if (!pbe.verify()) {
//                            System.err.println("message failed integrity check");
//                    } else {
//                            System.err.println("message integrity check passed");
//                    }
//            } else {
//                    System.err.println("no message integrity check");
//            }
//            return baos.toString();
    } catch (PGPException e) {
            System.err.println(e);
            if (e.getUnderlyingException()!= null) {
                    e.getUnderlyingException().printStackTrace();
            }
    }
    return null;
}
    
    private static PGPPrivateKey findSecretKeyX(InputStream keyIn, long keyID,
            char[] pass) throws IOException, PGPException,
            NoSuchProviderException {
    PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(keyIn));
    PGPSecretKey pgpSecKey = pgpSec.getSecretKey(keyID);
    if (pgpSecKey == null) {
            return null;
    }
    return pgpSecKey.extractPrivateKey(pass, "BC");
}
    
    public static void main(String [] args)
    {
        
        PGPEncryptServiceImpl enc = new PGPEncryptServiceImpl();
        try
        {
            enc.publicKey = enc.findPublicKey(new FileInputStream("C:\\Documents and Settings\\luoxja\\Desktop\\PGP Test keys\\pubring.pgp"));
            
            enc.privateKey = enc.findPrivateKey(new FileInputStream("C:\\Documents and Settings\\luoxja\\Desktop\\PGP Test keys\\secring.pgp"), null, "PGS-date:120103-LHW".toCharArray());
            
            
            enc.decrypt(new FileInputStream("C:\\apps\\hesc\\tap\\jobManager\\cycle11\\ackExtract\\test.txt"), new FileOutputStream("C:\\apps\\hesc\\tap\\jobManager\\cycle11\\ackExtract\\test.clear"));
           
            //enc.encryptFile("C:\\apps\\hesc\\tap\\jobManager\\cycle11\\ackExtract\\HE8346Y11.1.txt", "C:\\apps\\hesc\\tap\\jobManager\\cycle11\\ackExtract\\test.txt");
        
            //PGPEncryptServiceImpl._decrypt(new FileInputStream("C:\\apps\\hesc\\tap\\jobManager\\cycle11\\ackExtract\\test.txt"), new FileInputStream("C:\\Documents and Settings\\luoxja\\Desktop\\PGP Test keys\\secring.pgp"),"PGS-date:120103-LHW".toCharArray());
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (PGPException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NoSuchProviderException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //enc.setPublicKeyLocation("C:\\Documents and Settings\\luoxja\\Desktop\\PGP Test keys\\pubring.pgp");
        //enc.encryptFile("C:\\apps\\hesc\\tap\\jobManager\\cycle11\\ackExtract\\HE8346Y11.1.txt", "C:\\apps\\hesc\\tap\\jobManager\\cycle11\\ackExtract\\test.txt");
    }


}
