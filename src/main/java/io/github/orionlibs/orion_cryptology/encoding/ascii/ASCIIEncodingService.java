package io.github.orionlibs.orion_cryptology.encoding.ascii;

import io.github.orionlibs.orion_cryptology.encoding.ascii.tasks.DecodeASCIITask;
import io.github.orionlibs.orion_cryptology.encoding.ascii.tasks.EncodeToASCIITask;

public class ASCIIEncodingService
{
    public static String encodeToASCII(String data)
    {
        return EncodeToASCIITask.run(data);
    }


    public static String decodeASCII(String data)
    {
        return DecodeASCIITask.run(data);
    }
}