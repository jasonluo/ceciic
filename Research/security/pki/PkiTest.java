package com.vangent.tap.sys;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



//import com.ibm.crypto.fips.provider.IBMJCEFIPS;
//import com.ibm.misc.BASE64Decoder;



public class PkiTest {
		private static Logger log = Logger.getLogger(PkiTest.class);
		public static void main(String [] args) throws Exception{
			/**
			 * step 1
			 * 
			 */
			//String paddedSSN = "372199999" + BigInteger.valueOf(4958667).toString();
			
			/**
			 * step 2
			 * 
			 */
			//Sign the Hash with Private Key and store in SigField
//			Signature sign = Signature.getInstance("SHA256withRSA", "IBMJCEFIPS");
//			sign.initSign(privateKey);
//			sign.update(paddedSSN.getBytes());
//			byte[] byteSignedData = sign.sign();
//			sigField = new BASE64Encoder().encode(byteSignedData);

			//Provider provider = com.ibm.crypto.fips.provider.IBMJCEFIPS;
			//java.security.Security.addProvider(provider);
			
//			String sigPad = "144003201690333657877258490262838693978";
//			String paddedSSN = "455010001" + sigPad.toString();
//			
//			String sigField = "LBHFr+Hg2J30MySOAOeu7MwOgQ9XUYMaOPrrlUfbYeg1b+RQGWFtYu/nKxGKc5nZqgrNDKhVmvyyHBaltmdKbuZp5xD2VdkFeqnnISwcORijVi/Mvvaz9DwD4lzwX0ys2kFnfnOPRVntnq/vAStKfLuVkBsHpMVVUNkYmMSBAs4/TFZS23eMPplbEvVMDljADX1XmReY4cOI7CWiqtGlaGO/2UVbn5gpfUII4BY+sGCczDIOC6JDj2xDnZVnN22IQnOKNiQQOStE2n7NPnSGsXzt1rRzHF7KsU1IgVNNkwwXkaxz1MYZC7waGx8zCLvAsuXRn5DAeOJ0GjcUjXGCjg==";
//			
			String sigPad = "4958667";
			String paddedSSN = "372199999" + sigPad.toString();
			String sigField = "MSvWRz5CydUqcCTz4xw8nWTKKutgSRGKNM/k5K1tCGlWyEFeJAAlUOL4Cj1H9zKPCNI3DX+nfBV5zlRaeFkqcv8b4zfWCLLMCopLdG3yWcdfdfZKhhO5hWnj9cZq1UjyoO11Hznzrx5VP/59uCBSIcyxIpLey7f08ThNhtw32hUgwR4uJxMXsPxC+kKLbhM4oQtEaXlKpGmHl4/klIN8JsSIX5caSbYyhPftht46yqICCpBCy+2x7jPZrQHecrXhXafPT5A76ki4ZL4bb7FfHym1TCPzyuDcKsUyU7SqN4fjEZaHGCAX/0L7+TFgYZAF+MR77BvcokjHnJ3nSZulwQ==";
			/**
			 * step 3
			 * 
			 */
			//Extract the FOTW Public Key from their certificate
			FileInputStream certfis = new FileInputStream("C:\\TAP_II\\Year 12-13\\Development\\FafsaApi\\testfafsa.arm");
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			System.out.println("==="+certfis.available());
			Certificate cert = cf.generateCertificate(certfis);
			PublicKey publicKey = getPublicKey();//cert.getPublicKey();
		
			
			//Sign the Hash with Public Key
			//Signature sign = Signature.getInstance("SHA256withRSA", "SunPKCS11-NSS");
			//	Signature sign = Signature.getInstance("SHA256withRSA", "IBMJCEFIPS");
			Signature sign = Signature.getInstance("SHA256withRSA", "SunRsaSign");
			
			sign.initVerify(publicKey);
			sign.update(paddedSSN.getBytes());

			/**
			 * step 4
			 * 
			 */
			//Compare FOTW signed Hash with State signed Hash sent via sigField
			Boolean verifySign = sign.verify(new BASE64Decoder().decodeBuffer(sigField));

			if (verifySign) { 
			//Digital Signature matches
				System.out.println("matched");
			} else {
				//Digital Signature does not match
				System.out.println("no match");
			}
			
			sign();
		}
		
		public static PrivateKey getPrivateKey()
		{
			try {
			    KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			    //this doesn't seem right - see Pat w/questions
			    FileInputStream is = new FileInputStream("C:\\TAP_II\\Year 12-13\\Development\\FafsaApi\\keystore.ImportKey");
			    keystore.load(is, "importkey".toCharArray());
	
			    return (PrivateKey) keystore.getKey("importkey", "importkey".toCharArray());
				
			} catch (java.security.NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (java.security.KeyStoreException e) {
				e.printStackTrace();
			} catch (java.io.IOException e) {
				e.printStackTrace();
			} catch (java.security.cert.CertificateException e) {
				e.printStackTrace();
			} catch (java.security.UnrecoverableKeyException e) {
				e.printStackTrace();
			}
			return null;

		}
		
		public static void sign(){
			/**
			 * step 1
			 * 
			 */
			String paddedSSN = "372199999" + BigInteger.valueOf(4958667).toString();
			
			/**
			 * step 2
			 * 
			 */
			//Sign the Hash with Private Key and store in SigField
			try{
				Signature sign = Signature.getInstance("SHA256withRSA", "SunRsaSign");
				PrivateKey privateKey = getPrivateKey();
				sign.initSign(privateKey);
				sign.update(paddedSSN.getBytes());
				byte[] byteSignedData = sign.sign();
				String sigField = new BASE64Encoder().encode(byteSignedData);
				System.out.println(sigField);
				//System.out.println("sigpad=="+BigInteger.valueOf(4958667).toString());
				
			}
			catch(Exception e){
				e.printStackTrace();
			}

		}
		
		public static PublicKey getPublicKey(){
			try {
			    KeyStore keystore;
				
				keystore = KeyStore.getInstance("JKS", "SUN");
				
			    //this doesn't seem right - see Pat w/questions
			    FileInputStream is = new FileInputStream("C:\\TAP_II\\Year 12-13\\Development\\FafsaApi\\keystore.ImportKey");
			    keystore.load(is, "importkey".toCharArray());
			   
			    Certificate cert = keystore.getCertificate("importkey");
				
			    return cert.getPublicKey();
				
			} catch (java.security.NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (java.security.KeyStoreException e) {
				e.printStackTrace();
			} catch (java.io.IOException e) {
				e.printStackTrace();
			} catch (java.security.cert.CertificateException e) {
				e.printStackTrace();
			} 
			 catch (NoSuchProviderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			return null;
		}

}
