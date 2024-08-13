package io.github.orionlibs.orion_cryptology.hashing.sha.tasks;

import io.github.orionlibs.orion_assert.Assert;
import io.github.orionlibs.orion_cryptology.hashing.HashingAlgorithm;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeDataToSHATask
{
    public static String run(String data, HashingAlgorithm hashingAlgorithm)
    {
        Assert.notEmpty(data, "The given data is null/empty.");
        String encodedData = null;
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance(hashingAlgorithm.get());
            md.update(data.getBytes());
            byte[] byteData = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < byteData.length; i++)
            {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            encodedData = sb.toString();
        }
        catch(NoSuchAlgorithmException e)
        {
        }
        return encodedData;
    }
}