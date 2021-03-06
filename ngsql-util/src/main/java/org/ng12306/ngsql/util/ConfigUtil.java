package org.ng12306.ngsql.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ConfigUtil {
	public static Document getDocument(final InputStream dtd, InputStream xml) 
			throws ParserConfigurationException, SAXException, IOException
	{
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      factory.setValidating(true);
	      factory.setNamespaceAware(false);
	      DocumentBuilder builder = factory.newDocumentBuilder();
	              
		  return builder.parse(xml);	
	}
	
	  public static Map<String, Object> loadAttributes(Element e) {
	        Map<String, Object> map = new HashMap<String, Object>();
	        NamedNodeMap nm = e.getAttributes();
	        for (int j = 0; j < nm.getLength(); j++) {
	            Node n = nm.item(j);
	            if (n instanceof Attr) {
	                Attr attr = (Attr) n;
	                map.put(attr.getName(), attr.getNodeValue());
	            }
	        }
	        return map;
	    }
	  
	  public static Element loadElement(Element parent, String tagName) {
	        NodeList nodeList = parent.getElementsByTagName(tagName);
	        if (nodeList.getLength() > 1) {
	            throw new ConfigException(tagName + " elements length  over one!");
	        }
	        if (nodeList.getLength() == 1) {
	            return (Element) nodeList.item(0);
	        } else {
	            return null;
	        }
	    }
	  
	    public static Map<String, Object> loadElements(Element parent) {
	        Map<String, Object> map = new HashMap<String, Object>();
	        NodeList children = parent.getChildNodes();
	        for (int i = 0; i < children.getLength(); i++) {
	            Node node = children.item(i);
	            if (node instanceof Element) {
	                Element e = (Element) node;
	                String name = e.getNodeName();
	                if ("property".equals(name)) {
	                    String key = e.getAttribute("name");
	                    String value = e.getTextContent();
	                    map.put(key, value);
	                }
	            }
	        }
	        return map;
	    }
}
