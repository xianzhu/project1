package com.cv.peseer.conf;

public abstract class AbstractConfigurationNode implements ConfigurationNode
{
    protected static ConfigurationNode[] emptyNodeArray;

    static
    {
        emptyNodeArray = new ConfigurationNode[0];
    }

    @Override
    public String toString()
    {
        return "{name:" + getName() + ", value:" + getValue() + "}";
    }
}