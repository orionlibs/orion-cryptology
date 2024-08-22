package io.github.orionlibs.orion_cryptology.security.annotations.service;

import io.github.orionlibs.orion_assert.InaccessibleException;
import io.github.orionlibs.orion_cryptology.encryption.rsa.RSAEncryptionException;
import io.github.orionlibs.orion_cryptology.hashing.md5.MD5HashingException;
import io.github.orionlibs.orion_cryptology.security.annotations.EncryptAsUsername;
import io.github.orionlibs.orion_enumeration.OrionEnumeration;
import java.lang.reflect.Field;
import java.util.List;

public class EncryptAsUsernameAnnotationService extends EncryptionDecryptionAnnotationService
{
    public void encryptObject(Object objectToEncrypt, List<Field> instanceVariablesOfObject, List<OrionEnumeration> encodingAndEncryptionAlgorithmsForUsernameToBeUsedInOrder)
    {
        processInputs(objectToEncrypt, encodingAndEncryptionAlgorithmsForUsernameToBeUsedInOrder);
        instanceVariablesOfObject.forEach(field ->
        {
            try
            {
                processInstanceVariableForEncryption(field);
            }
            catch(IllegalArgumentException e)
            {
                throw e;
            }
            catch(IllegalAccessException e)
            {
                //throw new InaccessibleException("The instance variable is inaccessible.");
            }
            catch(InaccessibleException e)
            {
                //
            }
            catch(MD5HashingException e)
            {
                //
            }
            catch(RSAEncryptionException e)
            {
                //
            }
        });
    }


    private void processInstanceVariableForEncryption(Field instanceVariable) throws IllegalArgumentException, IllegalAccessException, InaccessibleException, MD5HashingException, RSAEncryptionException
    {
        if(instanceVariable.getAnnotation(EncryptAsUsername.class) != null)
        {
            encryptData(instanceVariable);
        }
    }
}