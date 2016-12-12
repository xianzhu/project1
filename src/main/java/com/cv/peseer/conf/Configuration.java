package com.cv.peseer.conf;

import java.io.InputStream;

public interface Configuration
{
    boolean load(InputStream is);
    boolean load(String path);
    void unload();
    boolean isValid();
    ConfigurationNode getNode(String name);
}
