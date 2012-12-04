package com.vangent.tap.sys.service.encrypt.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchProviderException;

import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;

import com.vangent.formengine.sys.exception.SystemException;

public interface EncryptService
{
    /**
     * find a public key from key file
     * @param keyFileStream
     * @return
     * @throws IOException
     * @throws PGPException
     */
    public PGPPublicKey findPublicKey(InputStream keyFileStream) throws IOException, PGPException;
    
    /**
     * Encrypts using default key
     * 
     * @param inputFilePath
     * @param outputFilePath
     * @throws SystemException
     */
    public void encryptFile(String inputFilePath, String outputFilePath) throws SystemException;
    
    /**
     * Encrypts the given file, writing the encrypted output to the given stream
     * 
     * @param inputFilePath
     * @param outputFilePath
     * @param publicKey
     * @throws SystemException
     */
    public void encryptFile(String inputFilePath, String outputFilePath, PGPPublicKey publicKey) throws SystemException;
    
   
    /**
     * Encrypts using default key
     * 
     * @param in
     * @param out
     * @throws IOException
     * @throws PGPException
     * @throws NoSuchProviderException
     */
    public void encryptFile(InputStream in, OutputStream out) throws IOException, PGPException, NoSuchProviderException;
    
    /**
     * Encrypts the given file, writing the encrypted output to the given stream
     * 
     * @param in
     * @param out
     * @param publicKey
     * @throws IOException
     * @throws PGPException
     * @throws NoSuchProviderException
     */
    public void encryptFile(InputStream in, OutputStream out, PGPPublicKey publicKey) throws IOException, PGPException, NoSuchProviderException;
    
    /**
     * find a private key from key file
     * 
     * @param keyIn
     * @param keyID
     * @param password
     * @return
     * @throws IOException
     * @throws PGPException
     * @throws NoSuchProviderException
     */
    public PGPPrivateKey findPrivateKey(InputStream keyIn, Long keyId, char[] password) throws IOException, PGPException, NoSuchProviderException;
    
}
