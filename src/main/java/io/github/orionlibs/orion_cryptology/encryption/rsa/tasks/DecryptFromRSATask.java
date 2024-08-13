package io.github.orionlibs.orion_cryptology.encryption.rsa.tasks;

import io.github.orionlibs.orion_assert.Assert;
import io.github.orionlibs.orion_configuration.InMemoryConfigurationService;
import io.github.orionlibs.orion_cryptology.encryption.EncryptionAlgorithm;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class DecryptFromRSATask
{
    public static String run(String data) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        return decryptData(data, (PrivateKey)InMemoryConfigurationService.getObjectProp("private.rsa.key"), null);
    }


    public static String run(String data, EncryptionAlgorithm encryptionAlgorithmToUse) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        return decryptData(data, (PrivateKey)InMemoryConfigurationService.getObjectProp("private.rsa.key"), encryptionAlgorithmToUse);
    }


    private static synchronized String decryptData(String data, PrivateKey privateKey, EncryptionAlgorithm encryptionAlgorithmToUse) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        Assert.notEmpty(data, "input data cannot be null/empty.");
        byte[] decryptedDataBytes = null;
        String decryptedData = "";
        String[] dataBytesTemp = data.split("__");
        byte[] dataBytes = new byte[dataBytesTemp.length];
        for(int i = 0; i < dataBytesTemp.length; i++)
        {
            dataBytes[i] = Byte.parseByte(dataBytesTemp[i]);
        }
        Cipher cipher = Cipher.getInstance(getEncryptionAlgorithmToUse(encryptionAlgorithmToUse));
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        decryptedDataBytes = cipher.doFinal(dataBytes);
        decryptedData = "";
        for(int i = 0; i < decryptedDataBytes.length; i++)
        {
            decryptedData += new String(new byte[]
                            {(byte)decryptedDataBytes[i]}, StandardCharsets.UTF_8);
        }
        return decryptedData.trim();
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