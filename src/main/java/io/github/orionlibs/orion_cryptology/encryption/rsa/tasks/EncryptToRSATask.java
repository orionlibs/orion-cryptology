package io.github.orionlibs.orion_cryptology.encryption.rsa.tasks;

import io.github.orionlibs.orion_assert.Assert;
import io.github.orionlibs.orion_configuration.InMemoryConfigurationService;
import io.github.orionlibs.orion_cryptology.encryption.EncryptionAlgorithm;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EncryptToRSATask
{
    public static String run(String data) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException, InvalidKeySpecException
    {
        return encryptData(data, (PublicKey)InMemoryConfigurationService.getObjectProp("public.rsa.key"), null);
    }


    public static String run(String data, EncryptionAlgorithm encryptionAlgorithmToUse) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException, InvalidKeySpecException
    {
        return encryptData(data, (PublicKey)InMemoryConfigurationService.getObjectProp("public.rsa.key"), encryptionAlgorithmToUse);
    }


    private static synchronized String encryptData(String data, PublicKey publicRSAKey, EncryptionAlgorithm encryptionAlgorithmToUse) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        Assert.notEmpty(data, "input data cannot be null/empty.");
        String encryptedData = null;
        byte[] dataToEncrypt = data.getBytes();
        byte[] encryptedDataTemp = null;
        Cipher cipher = Cipher.getInstance(getEncryptionAlgorithmToUse(encryptionAlgorithmToUse));
        cipher.init(Cipher.ENCRYPT_MODE, publicRSAKey);
        encryptedDataTemp = cipher.doFinal(dataToEncrypt);
        encryptedData = "";
        for(int i = 0; i < encryptedDataTemp.length; i++)
        {
            encryptedData += encryptedDataTemp[i];
            if(i < encryptedDataTemp.length - 1)
            {
                encryptedData += "__";
            }
        }
        return encryptedData;
    }


    private static String getEncryptionAlgorithmToUse(EncryptionAlgorithm encryptionAlgorithmToUse)
    {
        if(encryptionAlgorithmToUse != null)
        {
            return encryptionAlgorithmToUse.get();
        }
        else
        {
            return EncryptionAlgorithm.RSA.get();
        }
    }
}