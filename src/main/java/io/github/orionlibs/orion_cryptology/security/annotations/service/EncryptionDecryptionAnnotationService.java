package io.github.orionlibs.orion_cryptology.security.annotations.service;

import io.github.orionlibs.orion_assert.Assert;
import io.github.orionlibs.orion_assert.InaccessibleException;
import io.github.orionlibs.orion_cryptology.encryption.rsa.RSAEncryptionException;
import io.github.orionlibs.orion_cryptology.hashing.md5.MD5HashingException;
import io.github.orionlibs.orion_cryptology.security.DataSecurityService;
import io.github.orionlibs.orion_enumeration.OrionEnumeration;
import io.github.orionlibs.orion_reflection.variable.access.ReflectionInstanceVariablesAccessService;
import java.lang.reflect.Field;
import java.util.List;

class EncryptionDecryptionAnnotationService
{
    private List<OrionEnumeration> algorithmsForUsernameToBeUsedInOrder;
    private Object objectToEncryptOrDecrypt;


    protected void processInputs(Object objectToEncryptOrDecrypt, List<OrionEnumeration> algorithmsForUsernameToBeUsedInOrder)
    {
        Assert.notNull(objectToEncryptOrDecrypt, "The objectToEncryptOrDecrypt input cannot be null.");
        Assert.notEmpty(algorithmsForUsernameToBeUsedInOrder, "The algorithmsForUsernameToBeUsedInOrder input cannot be null/empty.");
        this.algorithmsForUsernameToBeUsedInOrder = algorithmsForUsernameToBeUsedInOrder;
        this.objectToEncryptOrDecrypt = objectToEncryptOrDecrypt;
    }


    protected void encryptData(Field instanceVariable) throws IllegalArgumentException, IllegalAccessException, InaccessibleException, MD5HashingException, RSAEncryptionException
    {
        String data = (String)instanceVariable.get(objectToEncryptOrDecrypt);
        String encryptedData = DataSecurityService.encryptData(data, algorithmsForUsernameToBeUsedInOrder);
        ReflectionInstanceVariablesAccessService.injectStringToInstanceVariable(objectToEncryptOrDecrypt, encryptedData, instanceVariable);
    }


    protected void decryptData(Field instanceVariable) throws IllegalArgumentException, IllegalAccessException, InaccessibleException, MD5HashingException, RSAEncryptionException
    {
        ReflectionInstanceVariablesAccessService.makeInstanceVariableAccessible(instanceVariable);
        String data = (String)instanceVariable.get(objectToEncryptOrDecrypt);
        String decryptedData = DataSecurityService.decryptData(data, algorithmsForUsernameToBeUsedInOrder);
        ReflectionInstanceVariablesAccessService.injectStringToInstanceVariable(objectToEncryptOrDecrypt, decryptedData, instanceVariable);
    }
}