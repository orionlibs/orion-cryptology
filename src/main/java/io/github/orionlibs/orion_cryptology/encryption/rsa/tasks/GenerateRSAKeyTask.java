package io.github.orionlibs.orion_cryptology.encryption.rsa.tasks;

import io.github.orionlibs.orion_cryptology.encryption.EncryptionAlgorithm;
import io.github.orionlibs.orion_cryptology.encryption.rsa.RSAEncryptionService;
import io.github.orionlibs.orion_object.ResourceCloser;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class GenerateRSAKeyTask
{
    public static void run(int keySize)
    {
        KeyPairGenerator keyPairGenerator = null;
        String RSAString = EncryptionAlgorithm.RSA.get();
        try
        {
            keyPairGenerator = KeyPairGenerator.getInstance(RSAString);
            keyPairGenerator.initialize(keySize);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            KeyFactory keyFactory = KeyFactory.getInstance(RSAString);
            RSAPublicKeySpec rsaPubKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
            RSAPrivateKeySpec rsaPrivKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
            System.out.println("Public Key Modulus : " + rsaPubKeySpec.getModulus());
            System.out.println("Public Key Exponent : " + rsaPubKeySpec.getPublicExponent());
            System.out.println("Private Key Modulus : " + rsaPrivKeySpec.getModulus());
            System.out.println("Private Key Exponent : " + rsaPrivKeySpec.getPrivateExponent());
            saveKeys(RSAEncryptionService.publicKeyFile, rsaPubKeySpec.getModulus(), rsaPubKeySpec.getPublicExponent());
            saveKeys(RSAEncryptionService.privateKeyFile, rsaPrivKeySpec.getModulus(), rsaPrivKeySpec.getPrivateExponent());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(InvalidKeySpecException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }


    private static void saveKeys(String fileName, BigInteger modulus, BigInteger exponent) throws IOException
    {
        OutputStream fileWriter = null;
        ObjectOutputStream objectWriter = null;
        try
        {
            fileWriter = Files.newOutputStream(Paths.get(fileName));
            objectWriter = new ObjectOutputStream(new BufferedOutputStream(fileWriter));
            objectWriter.writeObject(modulus);
            objectWriter.writeObject(exponent);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ResourceCloser.closeResource(objectWriter);
            ResourceCloser.closeResource(fileWriter);
        }
    }
}