package io.github.orionlibs.orion_cryptology.encoding.alphabetical;

import io.github.orionlibs.orion_cryptology.encoding.alphabetical.tasks.DecodeAlphanumericForNumberTask;
import io.github.orionlibs.orion_cryptology.encoding.alphabetical.tasks.EncodeAlphanumericForNumberTask;
import io.github.orionlibs.orion_cryptology.encoding.alphabetical.tasks.GenerateRandomAlphabeticalStringTask;

public class AlphabeticalEncodingService
{
    public static final char[] characters = new char[]
                    {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};


    public static String generateRandomAlphabeticalString(int desiredStringLength)
    {
        return GenerateRandomAlphabeticalStringTask.run(desiredStringLength);
    }


    //examples=====
    //9 => 9
    //10 => a
    //35 => z
    //36 => 10
    //37 => 11
    //70 => 1y
    //71 => 1z
    //72 => 20
    public static String encodeAlphanumericForNumber(String data)
    {
        return EncodeAlphanumericForNumberTask.run(data);
    }


    public static String decodeAlphanumericForNumber(String data)
    {
        return DecodeAlphanumericForNumberTask.run(data);
    }
}