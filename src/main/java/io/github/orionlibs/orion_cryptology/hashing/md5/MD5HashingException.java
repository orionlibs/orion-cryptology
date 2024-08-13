package io.github.orionlibs.orion_cryptology.hashing.md5;

import io.github.orionlibs.orion_assert.OrionCheckedException;

public class MD5HashingException extends OrionCheckedException
{
    private static final String DefaultErrorMessage = "Problem with MD5 encoding/decoding.";


    public MD5HashingException()
    {
        super(DefaultErrorMessage);
    }


    public MD5HashingException(String message)
    {
        super(message);
    }


    public MD5HashingException(String errorMessage, Object... arguments)
    {
        super(String.format(errorMessage, arguments));
    }


    public MD5HashingException(Throwable cause, String errorMessage, Object... arguments)
    {
        super(String.format(errorMessage, arguments), cause);
    }


    public MD5HashingException(Throwable cause)
    {
        super(cause, DefaultErrorMessage);
    }
}