package com.cv.peseer.conf;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlConfigurationNode extends AbstractConfigurationNode
{

    Element element;

    protected XmlConfigurationNode(Element element)
    {
        if(element == null)
        {
            throw new IllegalArgumentException();
        }
        this.element = element;
    }

    public String getName()
    {
        return element.getTagName();
    }
    public String getValue()
    {
        return element.getTextContent();
    }

    public boolean hasChildren()
    {
        return element.hasChildNodes();
    }

    public ConfigurationNode[] getChildren()
    {
        List<ConfigurationNode> cfgNodes = new ArrayList<ConfigurationNode>();
        NodeList nodes = element.getChildNodes();
        Node node = null;
        int len = nodes.getLength();
        for(int i = 0; i < len; ++i)
        {
            node = nodes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE)
            {
                cfgNodes.add(new XmlConfigurationNode((Element)node));
            }
        }
        ConfigurationNode[] array = (ConfigurationNode[])cfgNodes.toArray();
        cfgNodes.clear();
        return array;
    }
}