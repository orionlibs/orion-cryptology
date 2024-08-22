package io.github.orionlibs.orion_cryptology.security.annotations.service;

import io.github.orionlibs.orion_assert.InaccessibleException;
import io.github.orionlibs.orion_cryptology.encoding.xss.XSSEncodingService;
import io.github.orionlibs.orion_cryptology.security.annotations.EncodeXSS;
import io.github.orionlibs.orion_reflection.variable.access.ReflectionInstanceVariablesAccessService;
import java.lang.reflect.Field;
import java.util.List;

public class EncodeXSSAnnotationService
{
    public static void encryptObject(Object objectToEncrypt, List<Field> instanceVariablesOfObject)
    {
        instanceVariablesOfObject.forEach(field ->
        {
            try
            {
                processInstanceVariable(field, objectToEncrypt);
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
        if(instanceVariable.getAnnotation(EncodeXSS.class) != null)
        {
            encryptData(instanceVariable, object);
        }
    }


    private static void encryptData(Field instanceVariable, Object object) throws IllegalArgumentException, IllegalAccessException, InaccessibleException
    {
        ReflectionInstanceVariablesAccessService.makeInstanceVariableAccessible(instanceVariable);
        String data = (String)instanceVariable.get(object);
        String encryptedData = XSSEncodingService.encodeWithXSS(data);
        ReflectionInstanceVariablesAccessService.injectStringToInstanceVariable(object, encryptedData, instanceVariable);
    }
}