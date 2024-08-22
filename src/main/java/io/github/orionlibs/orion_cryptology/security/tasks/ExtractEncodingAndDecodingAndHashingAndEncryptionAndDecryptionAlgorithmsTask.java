package io.github.orionlibs.orion_cryptology.security.tasks;

import io.github.orionlibs.orion_cryptology.encoding.EncodingAlgorithm;
import io.github.orionlibs.orion_cryptology.encryption.EncryptionAlgorithm;
import io.github.orionlibs.orion_cryptology.hashing.HashingAlgorithm;
import io.github.orionlibs.orion_cryptology.security.NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException;
import io.github.orionlibs.orion_enumeration.OrionEnumeration;
import java.util.ArrayList;
import java.util.List;

public class ExtractEncodingAndDecodingAndHashingAndEncryptionAndDecryptionAlgorithmsTask
{
    public static List<OrionEnumeration> run(String[] algorithmsToBeUsedInOrder) throws NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException
    {
        List<OrionEnumeration> algorithmsToBeUsedInOrderList = new ArrayList<>();
        for(String algorithm : algorithmsToBeUsedInOrder)
        {
            EncodingAlgorithm encodingDecodingAlgorithm = EncodingAlgorithm.getEnumForValue(algorithm);
            if(encodingDecodingAlgorithm != null)
            {
                algorithmsToBeUsedInOrderList.add(encodingDecodingAlgorithm);
            }
            else
            {
                HashingAlgorithm hashingAlgorithm = HashingAlgorithm.getEnumForValue(algorithm);
                if(hashingAlgorithm != null)
                {
                    algorithmsToBeUsedInOrderList.add(hashingAlgorithm);
                }
                else
                {
                    EncryptionAlgorithm encryptionDecryptionAlgorithm = EncryptionAlgorithm.getEnumForValue(algorithm);
                    if(encryptionDecryptionAlgorithm != null)
                    {
                        algorithmsToBeUsedInOrderList.add(encryptionDecryptionAlgorithm);
                    }
                }
            }
        }
        if(algorithmsToBeUsedInOrderList.isEmpty())
        {
            throw new NoEncodingAndEncryptionAlgorithmsForUsernameProvidedException();
        }
        return algorithmsToBeUsedInOrderList;
    }
}