package com.cv.kdata.conf;

public interface ConfigurationNode
{
    String getName();
    String getValue();
    boolean hasChildren();
    ConfigurationNode[] getChildren();
}

