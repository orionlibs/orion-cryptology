package io.github.orionlibs.orion_cryptology.encoding;

import io.github.orionlibs.orion_enumeration.OrionEnumeration;

public enum EncodingAlgorithm implements OrionEnumeration
{
    ALPHABETICAL("alphabetical"),
    ASCII("ASCII"),
    BASE64_FOR_STRING("BASE64ForString"),
    BASE64_FOR_URL("BASE64ForURL"),
    XSS("XSS");
    private String name;


    private EncodingAlgorithm(String name)
    {
        setName(name);
    }


    @Override
    public String get()
    {
        return getName();
    }


    public String getName()
    {
        return this.name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    @Override
    public boolean is(OrionEnumeration other)
    {
        return other instanceof EncodingAlgorithm && this == other;
    }


    @Override
    public boolean isNot(OrionEnumeration other)
    {
        return other instanceof EncodingAlgorithm && this != other;
    }


    public static boolean valueExists(String other)
    {
        EncodingAlgorithm[] values = values();
        for(EncodingAlgorithm value : values)
        {
            if(value.get().equals(other))
            {
                return true;
            }
        }
        return false;
    }


    public static EncodingAlgorithm getEnumForValue(String other)
    {
        EncodingAlgorithm[] values = values();
        for(EncodingAlgorithm value : values)
        {
            if(value.get().equals(other))
            {
                return value;
            }
        }
        return null;
    }
}