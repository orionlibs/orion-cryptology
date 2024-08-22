package io.github.orionlibs.orion_cryptology.security;

import io.github.orionlibs.orion_assert.OrionCheckedException;

public class NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException extends OrionCheckedException
{
    private static final String DefaultErrorMessage = "No encoding/decoding and encryption/decryption algorithms provided. Please check the system configuration.";


    public NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException()
    {
        super(DefaultErrorMessage);
    }


    public NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException(String message)
    {
        super(message);
    }


    public NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException(String errorMessage, Object... arguments)
    {
        super(String.format(errorMessage, arguments));
    }


    public NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException(Throwable cause, String errorMessage, Object... arguments)
    {
        super(String.format(errorMessage, arguments), cause);
    }


    public NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException(Throwable cause)
    {
        super(cause, DefaultErrorMessage);
    }
}