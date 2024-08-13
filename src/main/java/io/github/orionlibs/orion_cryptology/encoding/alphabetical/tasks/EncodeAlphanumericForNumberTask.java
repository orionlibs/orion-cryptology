package io.github.orionlibs.orion_cryptology.encoding.alphabetical.tasks;

import io.github.orionlibs.orion_assert.Assert;
import io.github.orionlibs.orion_cryptology.encoding.alphabetical.AlphabeticalEncodingService;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class EncodeAlphanumericForNumberTask
{
    public static String run(String data) throws NumberFormatException
    {
        Assert.notEmpty(data, "The given data is null/empty.");
        BigInteger dataBigInteger = new BigInteger(data);
        List<Character> result = new ArrayList<>();
        BigInteger base = new BigInteger("" + AlphabeticalEncodingService.characters.length);
        int exponent = 1;
        BigInteger remaining = dataBigInteger;
        while(true)
        {
            BigInteger a = base.pow(exponent);
            BigInteger b = remaining.mod(a);
            BigInteger c = base.pow(exponent - 1);
            BigInteger d = b.divide(c);
            result.add(AlphabeticalEncodingService.characters[d.intValue()]);
            remaining = remaining.subtract(b);
            if(remaining.compareTo(BigInteger.ZERO) == 0)
            {
                break;
            }
            exponent++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = result.size() - 1; i >= 0; i--)
        {
            sb.append(result.get(i));
        }
        return sb.toString();
    }
}