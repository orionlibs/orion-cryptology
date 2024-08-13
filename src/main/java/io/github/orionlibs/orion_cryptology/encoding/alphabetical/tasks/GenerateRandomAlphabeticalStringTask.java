package io.github.orionlibs.orion_cryptology.encoding.alphabetical.tasks;

import io.github.orionlibs.orion_assert.Assert;
import io.github.orionlibs.orion_simple_math.RandomNumberGenerationService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GenerateRandomAlphabeticalStringTask
{
    public static final char[] capitalLetters = new char[]
                    {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static ConcurrentMap<Integer, Character> numbersToCapitalLettersMapper;

    static
    {
        numbersToCapitalLettersMapper = new ConcurrentHashMap<Integer, Character>();
        numbersToCapitalLettersMapper.put(1, 'A');
        numbersToCapitalLettersMapper.put(2, 'B');
        numbersToCapitalLettersMapper.put(3, 'C');
        numbersToCapitalLettersMapper.put(4, 'D');
        numbersToCapitalLettersMapper.put(5, 'E');
        numbersToCapitalLettersMapper.put(6, 'F');
        numbersToCapitalLettersMapper.put(7, 'G');
        numbersToCapitalLettersMapper.put(8, 'H');
        numbersToCapitalLettersMapper.put(9, 'I');
        numbersToCapitalLettersMapper.put(10, 'J');
        numbersToCapitalLettersMapper.put(11, 'K');
        numbersToCapitalLettersMapper.put(12, 'L');
        numbersToCapitalLettersMapper.put(13, 'M');
        numbersToCapitalLettersMapper.put(14, 'N');
        numbersToCapitalLettersMapper.put(15, 'O');
        numbersToCapitalLettersMapper.put(16, 'P');
        numbersToCapitalLettersMapper.put(17, 'Q');
        numbersToCapitalLettersMapper.put(18, 'R');
        numbersToCapitalLettersMapper.put(19, 'S');
        numbersToCapitalLettersMapper.put(20, 'T');
        numbersToCapitalLettersMapper.put(21, 'U');
        numbersToCapitalLettersMapper.put(22, 'V');
        numbersToCapitalLettersMapper.put(23, 'W');
        numbersToCapitalLettersMapper.put(24, 'X');
        numbersToCapitalLettersMapper.put(25, 'Y');
        numbersToCapitalLettersMapper.put(26, 'Z');
    }

    public static String run(int desiredStringLength)
    {
        Assert.isNonNegative(desiredStringLength, "The given desiredStringLength cannot be negative.");
        StringBuilder sb = new StringBuilder();
        if(desiredStringLength > 0)
        {
            for(int i = 1; i <= desiredStringLength; i++)
            {
                int randomNumber = RandomNumberGenerationService.getRandomIntegerExceptZero(numbersToCapitalLettersMapper.size());
                sb.append(numbersToCapitalLettersMapper.get(randomNumber));
            }
        }
        return sb.toString();
    }
}