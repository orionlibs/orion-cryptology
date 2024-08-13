package io.github.orionlibs.orion_cryptology.encryption.rsa;

import io.github.orionlibs.orion_assert.OrionCheckedException;

public class RSAEncryptionException extends OrionCheckedException
{
    private static final String DefaultErrorMessage = "There was an error with the RSA encryption service.";


    public RSAEncryptionException(String message)
    {
        super(message);
    }


    public RSAEncryptionException(String errorMessage, Object... arguments)
    {
        super(String.format(errorMessage, arguments));
    }


    public RSAEncryptionException(Throwable cause, String errorMessage, Object... arguments)
    {
        super(String.format(errorMessage, arguments), cause);
    }


    public RSAEncryptionException(Throwable cause)
    {
        super(cause, DefaultErrorMessage);
    }
}