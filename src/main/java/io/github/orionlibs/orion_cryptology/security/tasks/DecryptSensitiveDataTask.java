package io.github.orionlibs.orion_cryptology.security.tasks;

import io.github.orionlibs.orion_assert.Assert;
import io.github.orionlibs.orion_cryptology.encoding.EncodingAlgorithm;
import io.github.orionlibs.orion_cryptology.encoding.alphabetical.AlphabeticalEncodingService;
import io.github.orionlibs.orion_cryptology.encoding.ascii.ASCIIEncodingService;
import io.github.orionlibs.orion_cryptology.encoding.base64.Base64EncodingService;
import io.github.orionlibs.orion_cryptology.encryption.EncryptionAlgorithm;
import io.github.orionlibs.orion_cryptology.encryption.rsa.RSAEncryptionException;
import io.github.orionlibs.orion_cryptology.encryption.rsa.RSAEncryptionService;
import io.github.orionlibs.orion_cryptology.hashing.md5.MD5HashingException;
import io.github.orionlibs.orion_enumeration.OrionEnumeration;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class DecryptSensitiveDataTask
{
    public static String run(String encryptedData, List<OrionEnumeration> decodingAndDecryptionAlgorithmsToBeUsedInOrder) throws MD5HashingException, RSAEncryptionException
    {
        if(encryptedData == null || encryptedData.isEmpty())
        {
            return encryptedData;
        }
        else
        {
            Assert.notEmpty(decodingAndDecryptionAlgorithmsToBeUsedInOrder, "The decodingAndDecryptionAlgorithmsToBeUsedInOrder input cannot be null/empty.");
            String rawData = encryptedData;
            for(OrionEnumeration algorithm : decodingAndDecryptionAlgorithmsToBeUsedInOrder)
            {
                if(algorithm instanceof EncodingAlgorithm)
                {
                    rawData = decodeData(algorithm, rawData);
                }
                else if(algorithm instanceof EncryptionAlgorithm)
                {
                    rawData = decryptData(algorithm, rawData);
                }
            }
            return rawData;
        }
    }


    private static String decodeData(OrionEnumeration algorithm, String data) throws MD5HashingException
    {
        EncodingAlgorithm encodingAlgorithm = (EncodingAlgorithm)algorithm;
        if(encodingAlgorithm.is(EncodingAlgorithm.ALPHABETICAL))
        {
            return AlphabeticalEncodingService.decodeAlphanumericForNumber(data);
        }
        else if(encodingAlgorithm.is(EncodingAlgorithm.ASCII))
        {
            return ASCIIEncodingService.decodeASCII(data);
        }
        else if(encodingAlgorithm.is(EncodingAlgorithm.BASE64_FOR_STRING))
        {
            return Base64EncodingService.decodeBase64ForString(data);
        }
        else if(encodingAlgorithm.is(EncodingAlgorithm.BASE64_FOR_URL))
        {
            return Base64EncodingService.decodeBase64ForURL(data);
        }
        return data;
    }


    private static String decryptData(OrionEnumeration algorithm, String data) throws RSAEncryptionException
    {
        EncryptionAlgorithm encryptionAlgorithm = (EncryptionAlgorithm)algorithm;
        if(encryptionAlgorithm.is(EncryptionAlgorithm.RSA))
        {
            try
            {
                return RSAEncryptionService.decryptFromRSA(data);
            }
            catch(InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | ClassNotFoundException | InvalidKeySpecException | IOException e)
            {
                throw new RSAEncryptionException(e, "Problem with the decryption of the data.");
            }
        }
        else if(encryptionAlgorithm.is(EncryptionAlgorithm.RSANoPadding))
        {
            try
            {
                return RSAEncryptionService.decryptFromRSAWithoutPadding(data);
            }
            catch(InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | ClassNotFoundException | InvalidKeySpecException | IOException e)
            {
                throw new RSAEncryptionException(e, "Problem with the decryption of the data.");
            }
        }
        return data;
    }
}