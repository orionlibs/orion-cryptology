package io.github.orionlibs.orion_cryptology.encryption;

import io.github.orionlibs.orion_enumeration.OrionEnumeration;

public enum EncryptionAlgorithm implements OrionEnumeration
{
    RSA("RSA"),
    RSANoPadding("RSA/ECB/NOPADDING"),
    BCrypt("BCrypt");
    private String name;


    private EncryptionAlgorithm(String name)
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
        return other instanceof EncryptionAlgorithm && this == other;
    }


    @Override
    public boolean isNot(OrionEnumeration other)
    {
        return other instanceof EncryptionAlgorithm && this != other;
    }


    public static boolean valueExists(String other)
    {
        EncryptionAlgorithm[] values = values();
        for(EncryptionAlgorithm value : values)
        {
            if(value.get().equals(other))
            {
                return true;
            }
        }
        return false;
    }


    public static EncryptionAlgorithm getEnumForValue(String other)
    {
        EncryptionAlgorithm[] values = values();
        for(EncryptionAlgorithm value : values)
        {
            if(value.get().equals(other))
            {
                return value;
            }
        }
        return null;
    }
}