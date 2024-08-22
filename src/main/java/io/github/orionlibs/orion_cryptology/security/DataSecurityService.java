package io.github.orionlibs.orion_cryptology.security;

import io.github.orionlibs.orion_configuration.InMemoryConfigurationService;
import io.github.orionlibs.orion_cryptology.encryption.rsa.RSAEncryptionException;
import io.github.orionlibs.orion_cryptology.hashing.md5.MD5HashingException;
import io.github.orionlibs.orion_cryptology.security.annotations.service.DecodeBase64ForStringAnnotationService;
import io.github.orionlibs.orion_cryptology.security.annotations.service.DecodeBase64ForURLAnnotationService;
import io.github.orionlibs.orion_cryptology.security.annotations.service.DecodeXSSAnnotationService;
import io.github.orionlibs.orion_cryptology.security.annotations.service.DecryptAsDataAnnotationService;
import io.github.orionlibs.orion_cryptology.security.annotations.service.DecryptAsUsernameAnnotationService;
import io.github.orionlibs.orion_cryptology.security.annotations.service.EncodeBase64ForStringAnnotationService;
import io.github.orionlibs.orion_cryptology.security.annotations.service.EncodeBase64ForURLAnnotationService;
import io.github.orionlibs.orion_cryptology.security.annotations.service.EncodeXSSAnnotationService;
import io.github.orionlibs.orion_cryptology.security.annotations.service.EncryptAsDataAnnotationService;
import io.github.orionlibs.orion_cryptology.security.annotations.service.EncryptAsUsernameAnnotationService;
import io.github.orionlibs.orion_cryptology.security.tasks.DecryptSensitiveDataTask;
import io.github.orionlibs.orion_cryptology.security.tasks.EncryptSensitiveDataTask;
import io.github.orionlibs.orion_cryptology.security.tasks.ExtractEncodingAndDecodingAndHashingAndEncryptionAndDecryptionAlgorithmsTask;
import io.github.orionlibs.orion_enumeration.OrionEnumeration;
import io.github.orionlibs.orion_reflection.variable.access.ReflectionInstanceVariablesAccessService;
import io.github.orionlibs.orion_reflection.variable.retrieval.ReflectionInstanceVariablesRetrievalService;
import java.lang.reflect.Field;
import java.util.List;

public class DataSecurityService
{
    public static List<OrionEnumeration> getEncodingAndEncryptionAlgorithmsForUsername() throws NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException
    {
        String[] algorithmsToBeUsedInOrder = InMemoryConfigurationService.getProp("data.security.encoding.and.encryption.algorithms.to.use.for.username").split(",");
        return extractEncodingAndEncryptionAlgorithms(algorithmsToBeUsedInOrder);
    }


    public static List<OrionEnumeration> getDecodingAndDecryptionAlgorithmsForUsername() throws NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException
    {
        String[] algorithmsToBeUsedInOrder = InMemoryConfigurationService.getProp("data.security.decoding.and.decryption.algorithms.to.use.for.username").split(",");
        return extractDecodingAndDecryptionAlgorithms(algorithmsToBeUsedInOrder);
    }


    public static List<OrionEnumeration> getEncodingAndHashingAndEncryptionAlgorithmsForSensitiveUserData() throws NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException
    {
        String[] algorithmsToBeUsedInOrder = InMemoryConfigurationService.getProp("data.security.encoding.and.encryption.algorithms.to.use.for.sensitive.user.data").split(",");
        return extractEncodingAndEncryptionAlgorithms(algorithmsToBeUsedInOrder);
    }


    public static List<OrionEnumeration> getDecodingAndDecryptionAlgorithmsForSensitiveUserData() throws NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException
    {
        String[] algorithmsToBeUsedInOrder = InMemoryConfigurationService.getProp("data.security.decoding.and.decryption.algorithms.to.use.for.sensitive.user.data").split(",");
        return extractDecodingAndDecryptionAlgorithms(algorithmsToBeUsedInOrder);
    }


    public static String encryptUsername(String rawData) throws MD5HashingException, RSAEncryptionException, NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException
    {
        return encryptUsername(rawData, getEncodingAndEncryptionAlgorithmsForUsername());
    }


    public static String encryptUsername(String rawData, List<OrionEnumeration> encodingAndEncryptionAlgorithmsForUsername) throws MD5HashingException, RSAEncryptionException
    {
        return encryptData(rawData, encodingAndEncryptionAlgorithmsForUsername);
    }


    public static String encryptData(String rawData, List<OrionEnumeration> encodingAndHashingAndEncryptionAlgorithmsToBeUsedInOrder) throws MD5HashingException, RSAEncryptionException
    {
        return EncryptSensitiveDataTask.run(rawData, encodingAndHashingAndEncryptionAlgorithmsToBeUsedInOrder);
    }


    public static String decryptUsername(String encryptedData) throws MD5HashingException, RSAEncryptionException, NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException
    {
        return decryptData(encryptedData, getDecodingAndDecryptionAlgorithmsForUsername());
    }


    public static String decryptData(String encryptedData, List<OrionEnumeration> decodingAndDecryptionAlgorithmsToBeUsedInOrder) throws MD5HashingException, RSAEncryptionException
    {
        return DecryptSensitiveDataTask.run(encryptedData, decodingAndDecryptionAlgorithmsToBeUsedInOrder);
    }


    public static void encryptObject(Object objectToEncrypt, List<OrionEnumeration> encodingAndEncryptionAlgorithmsForUsernameToBeUsedInOrder, List<OrionEnumeration> encodingAndHashingAndEncryptionAlgorithmsForDataToBeUsedInOrder)
    {
        List<Field> instanceVariables = ReflectionInstanceVariablesRetrievalService.getDeclaredInstanceVariables(objectToEncrypt);
        instanceVariables.forEach(instanceVariable -> ReflectionInstanceVariablesAccessService.makeInstanceVariableAccessible(instanceVariable));
        new EncryptAsUsernameAnnotationService().encryptObject(objectToEncrypt, instanceVariables, encodingAndEncryptionAlgorithmsForUsernameToBeUsedInOrder);
        new EncryptAsDataAnnotationService().encryptObject(objectToEncrypt, instanceVariables, encodingAndHashingAndEncryptionAlgorithmsForDataToBeUsedInOrder);
        EncodeBase64ForStringAnnotationService.encryptObject(objectToEncrypt, instanceVariables);
        EncodeBase64ForURLAnnotationService.encryptObject(objectToEncrypt, instanceVariables);
        EncodeXSSAnnotationService.encryptObject(objectToEncrypt, instanceVariables);
    }


    public static void encryptObject(Object objectToEncrypt) throws NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException
    {
        encryptObject(objectToEncrypt, getEncodingAndEncryptionAlgorithmsForUsername(), getEncodingAndHashingAndEncryptionAlgorithmsForSensitiveUserData());
    }


    public static void decryptObject(Object objectToDecrypt, List<OrionEnumeration> decodingAndDecryptionAlgorithmsForUsernameToBeUsedInOrder, List<OrionEnumeration> decodingAndDecryptionAlgorithmsForDataToBeUsedInOrder)
    {
        List<Field> instanceVariables = ReflectionInstanceVariablesRetrievalService.getDeclaredInstanceVariables(objectToDecrypt);
        instanceVariables.forEach(instanceVariable -> ReflectionInstanceVariablesAccessService.makeInstanceVariableAccessible(instanceVariable));
        new DecryptAsUsernameAnnotationService().decryptObject(objectToDecrypt, instanceVariables, decodingAndDecryptionAlgorithmsForUsernameToBeUsedInOrder);
        new DecryptAsDataAnnotationService().decryptObject(objectToDecrypt, instanceVariables, decodingAndDecryptionAlgorithmsForDataToBeUsedInOrder);
        DecodeBase64ForStringAnnotationService.decryptObject(objectToDecrypt, instanceVariables);
        DecodeBase64ForURLAnnotationService.decryptObject(objectToDecrypt, instanceVariables);
        DecodeXSSAnnotationService.decryptObject(objectToDecrypt, instanceVariables);
    }


    public static void decryptObject(Object objectToDecrypt) throws NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException
    {
        decryptObject(objectToDecrypt, getDecodingAndDecryptionAlgorithmsForUsername(), getDecodingAndDecryptionAlgorithmsForSensitiveUserData());
    }


    public static List<OrionEnumeration> extractEncodingAndEncryptionAlgorithms(String[] encodingAndEncryptionAlgorithmsToBeUsedInOrder) throws NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException
    {
        return ExtractEncodingAndDecodingAndHashingAndEncryptionAndDecryptionAlgorithmsTask.run(encodingAndEncryptionAlgorithmsToBeUsedInOrder);
    }


    public static List<OrionEnumeration> extractDecodingAndDecryptionAlgorithms(String[] decodingAndDecryptionAlgorithmsToBeUsedInOrder) throws NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException
    {
        return ExtractEncodingAndDecodingAndHashingAndEncryptionAndDecryptionAlgorithmsTask.run(decodingAndDecryptionAlgorithmsToBeUsedInOrder);
    }
}