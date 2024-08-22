package io.github.orionlibs.orion_cryptology.security.annotations.service;

import io.github.orionlibs.orion_assert.InaccessibleException;
import io.github.orionlibs.orion_cryptology.encoding.xss.XSSEncodingService;
import io.github.orionlibs.orion_cryptology.security.annotations.DecodeXSS;
import io.github.orionlibs.orion_reflection.variable.access.ReflectionInstanceVariablesAccessService;
import java.lang.reflect.Field;
import java.util.List;

public class DecodeXSSAnnotationService
{
    public static void decryptObject(Object objectToDecrypt, List<Field> instanceVariablesOfObject)
    {
        instanceVariablesOfObject.forEach(field ->
        {
            try
            {
                processInstanceVariable(field, objectToDecrypt);
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
        });
    }


    private static void processInstanceVariable(Field instanceVariable, Object object) throws IllegalArgumentException, IllegalAccessException, InaccessibleException
    {
        if(instanceVariable.getAnnotation(DecodeXSS.class) != null)
        {
            decryptData(instanceVariable, object);
        }
    }


    private static void decryptData(Field instanceVariable, Object object) throws IllegalArgumentException, IllegalAccessException, InaccessibleException
    {
        ReflectionInstanceVariablesAccessService.makeInstanceVariableAccessible(instanceVariable);
        String data = (String)instanceVariable.get(object);
        String encryptedData = XSSEncodingService.decodeFromXSS(data);
        ReflectionInstanceVariablesAccessService.injectStringToInstanceVariable(object, encryptedData, instanceVariable);
    }
}