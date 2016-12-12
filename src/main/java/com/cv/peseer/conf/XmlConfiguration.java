package com.cv.peseer.conf;

import java.lang.reflect.Array;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlConfiguration extends AbstractConfiguration
{
    private static Pair<String, String>[] dummy = null;

    private Document doc;

    private static Log log = LogFactory.getLog(XmlConfiguration.class);

    static
    {
        Pair<String, String> x = new Pair<String, String>();
        @SuppressWarnings("unchecked")
        Pair<String, String>[] dummy2 = (Pair<String, String>[])Array.newInstance(x.getClass(), 0);
        dummy = dummy2;
    }

    public XmlConfiguration()
    {
    }

    public boolean load(InputStream is)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try
        {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(is);
            doc.normalize();
        }
        catch(Exception e)
        {
            log.error("XmlConfiguration load method got Exception");
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean isValid()
    {
        return doc != null;
    }

    public void unload()
    {
        doc = null;
    }

    synchronized Element[] getElementsByName(String name)
    {
        String segment = null;

        int startpos = 0;
        int len = name.length();
        int pos = 0;

        Element root = doc.getDocumentElement();
        Element element = null;
        NodeList nodes = null;
        Node node = null;
        Queue<Element> queue = new LinkedList<Element>();
        queue.add(root);
        do
        {
            pos = name.indexOf('.', startpos);
            pos = (pos == -1) ? len : pos;
            segment = name.substring(startpos, pos);
            List<Element> list = new ArrayList<Element>();
            while((element = queue.poll()) != null)
            {
                nodes = element.getChildNodes();
                for(int i = 0; i < nodes.getLength(); ++i)
                {
                    node = nodes.item(i);
                    if(node.getNodeType() == Node.ELEMENT_NODE)
                    {
                        if(segment.equalsIgnoreCase(((Element)node)
                                    .getTagName()))
                        {
                            list.add((Element)node);
                        }
                    }
                }
            }
            for(Element e : list)
            {
                queue.offer(e);
            }
            startpos = pos + 1;
        } while(startpos < len && !queue.isEmpty());
        Element[] foundElements = new Element[((LinkedList<Element>)queue).size()];
        for(int i = 0; i < foundElements.length; ++i)
        {
            foundElements[i] = queue.poll();
        }
        return foundElements;
    }

    Element getElementByName(String name)
    {
        Element[] elements = getElementsByName(name);
        if(elements != null && elements.length > 0)
        {
            return elements[0];
        }
        return null;
    }

    public ConfigurationNode getNode(String name)
    {
        if(StringUtils.isEmpty(name))
        {
            throw new IllegalArgumentException("argument 'name' is empty.");
        }
        if(!isValid())
        {
            throw new ConfigurationException("configuration is invalid");
        }
        Element node = getElementByName(name);
        if(node == null)
        {
            return null;
        }
        return new XmlConfigurationNode(node);
    }

    public synchronized Pair<String,String>[] getNodePairs(String name)
    {
        Element[] elements = getElementsByName(name);
        List<Pair<String, String>> pairs = new ArrayList<Pair<String, String>>();
        for(Element element : elements)
        {
            NodeList nodes = element.getChildNodes();
            for(int i = 0; i < nodes.getLength(); ++i)
            {
                Node node = nodes.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE && ((Element)node).getTagName() == "add")
                {
                    Pair<String, String> pair = new Pair<String, String>();
                    Element addElement = (Element)node;
                    pair.name = addElement.getAttribute("name");
                    pair.value = addElement.getAttribute("value");
                    pairs.add(pair);
                }
            }
        }
        return pairs.toArray(dummy);
    }
    
    public synchronized HashMap<String, HashMap<String, String>> getNodeValues(String name)
    {
    	HashMap<String, HashMap<String, String>> nodeValues = new HashMap<String, HashMap<String, String>>();
    	Element[] elements = getElementsByName(name);
    	int counter=1;
        for(Element element : elements)
        {
        	if (element.getNodeType() == Node.ELEMENT_NODE)
        	{
                NodeList nodes = element.getChildNodes();
                HashMap<String, String> items = new HashMap<String, String>();
                for(int index = 0; index < nodes.getLength(); ++index)
                {
                    Node node = nodes.item(index);
                    if(node.getNodeType() == Node.ELEMENT_NODE)
                    {
                        Element addElement = (Element)node;
                        items.put(addElement.getNodeName(), addElement.getTextContent());
                    }
                }
                nodeValues.put(String.format("%s%d", element.getNodeName(), counter), items);
                counter++;
        	}
        } 
        return nodeValues;
    }

    
}
