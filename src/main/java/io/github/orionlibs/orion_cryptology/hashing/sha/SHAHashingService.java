package io.github.orionlibs.orion_cryptology.hashing.sha;

import io.github.orionlibs.orion_cryptology.hashing.HashingAlgorithm;
import io.github.orionlibs.orion_cryptology.hashing.sha.tasks.EncodeDataToSHATask;

public class SHAHashingService
{
    public static String encodeDataToSHA1(String data)
    {
        return EncodeDataToSHATask.run(data, HashingAlgorithm.SHA1);
    }


    public static String encodeDataToSHA256(String data)
    {
        return EncodeDataToSHATask.run(data, HashingAlgorithm.SHA256);
    }


    public static String encodeDataToSHA512(String data)
    {
        return EncodeDataToSHATask.run(data, HashingAlgorithm.SHA512);
    }
}