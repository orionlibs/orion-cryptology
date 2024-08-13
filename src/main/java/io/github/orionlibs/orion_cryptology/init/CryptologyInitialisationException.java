package io.github.orionlibs.orion_cryptology.init;

import io.github.orionlibs.orion_assert.OrionCheckedException;

public class CryptologyInitialisationException extends OrionCheckedException
{
    private static final String DefaultErrorMessage = "There was an error during the initialisation of the Orion Cryptology project.";


    public CryptologyInitialisationException(String message)
    {
        super(message);
    }


    public CryptologyInitialisationException(String errorMessage, Object... arguments)
    {
        super(String.format(errorMessage, arguments));
    }


    public CryptologyInitialisationException(Throwable cause, String errorMessage, Object... arguments)
    {
        super(String.format(errorMessage, arguments), cause);
    }


    public CryptologyInitialisationException(Throwable cause)
    {
        super(cause, DefaultErrorMessage);
    }
}