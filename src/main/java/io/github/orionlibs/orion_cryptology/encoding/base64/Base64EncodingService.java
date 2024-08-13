package io.github.orionlibs.orion_cryptology.encoding.base64;

import java.util.Base64;

public class Base64EncodingService
{
    public static String encodeBase64ForURL(String data)
    {
        if(data != null && !data.isEmpty())
        {
            return encodeBase64ForURL(data.getBytes());
        }
        else
        {
            return "";
        }
    }


    public static String encodeBase64ForURL(byte[] data)
    {
        if(data != null && data.length > 0)
        {
            return new String(Base64.getUrlEncoder().encodeToString(data));
        }
        else
        {
            return "";
        }
    }


    public static String encodeBase64ForString(String data)
    {
        if(data != null && !data.isEmpty())
        {
            return new String(Base64.getEncoder().encodeToString(data.getBytes()));
        }
        else
        {
            return "";
        }
    }


    public static String encodeBase64ForString(byte[] data)
    {
        if(data != null && data.length > 0)
        {
            return new String(Base64.getEncoder().encodeToString(data));
        }
        else
        {
            return "";
        }
    }


    public static String decodeBase64ForURL(String encodedData)
    {
        if(encodedData != null && !encodedData.isEmpty())
        {
            return new String(Base64.getUrlDecoder().decode(encodedData));
        }
        else
        {
            return "";
        }
    }


    public static String decodeBase64ForURL(byte[] encodedData)
    {
        if(encodedData != null && encodedData.length > 0)
        {
            return new String(Base64.getUrlDecoder().decode(encodedData));
        }
        else
        {
            return "";
        }
    }


    public static String decodeBase64ForString(String encodedData)
    {
        if(encodedData != null && !encodedData.isEmpty())
        {
            return new String(Base64.getDecoder().decode(encodedData));
        }
        else
        {
            return "";
        }
    }


    public static String decodeBase64ForString(byte[] encodedData)
    {
        if(encodedData != null && encodedData.length > 0)
        {
            return new String(Base64.getDecoder().decode(encodedData));
        }
        else
        {
            return "";
        }
    }


    public static byte[] decodeBase64ForUrlAndGetBytes(String encodedData)
    {
        if(encodedData != null && !encodedData.isEmpty())
        {
            return Base64.getUrlDecoder().decode(encodedData);
        }
        else
        {
            return new byte[0];
        }
    }


    public static byte[] decodeBase64ForUrlAndGetBytes(byte[] encodedData)
    {
        if(encodedData != null && encodedData.length > 0)
        {
            return Base64.getUrlDecoder().decode(encodedData);
        }
        else
        {
            return new byte[0];
        }
    }


    public static byte[] decodeBase64ForStringAndGetBytes(String encodedData)
    {
        if(encodedData != null && !encodedData.isEmpty())
        {
            return Base64.getDecoder().decode(encodedData);
        }
        else
        {
            return new byte[0];
        }
    }


    public static byte[] decodeBase64ForStringAndGetBytes(byte[] encodedData)
    {
        if(encodedData != null && encodedData.length > 0)
        {
            return Base64.getDecoder().decode(encodedData);
        }
        else
        {
            return new byte[0];
        }
    }
}