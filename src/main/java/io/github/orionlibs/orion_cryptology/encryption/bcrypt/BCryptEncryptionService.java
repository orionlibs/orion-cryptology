package io.github.orionlibs.orion_cryptology.encryption.bcrypt;

import io.github.orionlibs.orion_assert.Assert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncryptionService
{
    public static String encrypt(String data)
    {
        if(data != null && !data.isEmpty())
        {
            return new BCryptPasswordEncoder().encode(data);
        }
        else
        {
            return "";
        }
    }


    public static boolean matches(String rawData, String encodedData)
    {
        Assert.notEmpty(rawData, "Input rawData is null/empty.");
        Assert.notEmpty(encodedData, "Input encodedData is null/empty.");
        return new BCryptPasswordEncoder().matches(rawData, encodedData);
    }
}