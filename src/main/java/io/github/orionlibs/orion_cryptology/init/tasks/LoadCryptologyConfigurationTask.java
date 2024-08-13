package io.github.orionlibs.orion_cryptology.init.tasks;

import io.github.orionlibs.orion_configuration.InMemoryConfigurationService;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

public class LoadCryptologyConfigurationTask
{
    public static void run() throws ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException, IOException
    {
        try
        {
            InputStream publicRSAKeyStream = LoadCryptologyConfigurationTask.class.getResourceAsStream("/configuration/cryptology/RSAKeys/publicRSAKey");
            PublicKey publicRSAKey = ReadPublicRSAKeyFromFileStreamTask.run(publicRSAKeyStream);
            InMemoryConfigurationService.registerObjectProp("public.rsa.key", publicRSAKey);
            InputStream privateRSAKeyStream = LoadCryptologyConfigurationTask.class.getResourceAsStream("/configuration/cryptology/RSAKeys/privateRSAKey");
            PrivateKey privateRSAKey = ReadPrivateRSAKeyFromFileStreamTask.run(privateRSAKeyStream);
            InMemoryConfigurationService.registerObjectProp("private.rsa.key", privateRSAKey);
        }
        catch(ClassNotFoundException e)
        {
            throw e;
        }
        catch(NoSuchAlgorithmException e)
        {
            throw e;
        }
        catch(InvalidKeySpecException e)
        {
            throw e;
        }
        catch(IOException e)
        {
            throw e;
        }
    }
}