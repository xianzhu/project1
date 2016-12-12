package com.cv.peseer.conf;

public interface ConfigurationNode
{
    String getName();
    String getValue();
    boolean hasChildren();
    ConfigurationNode[] getChildren();
}

