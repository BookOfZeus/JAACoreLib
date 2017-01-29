package com.corelib;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * XMLParser.java
 *
 * XML manipulation.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
class XMLParser
{
	/**
	 * The class tag name
	 */
	final static private String TAG = "XMLParser";

	/**
	 * Constructor
	 *
	 */
	public XMLParser()
	{
	}

	/**
	 * Get the DOM of the XML file
	 *
	 * @param xml The content of the XML file
	 * @return The document
	 * @throws javax.xml.parsers.ParserConfigurationException Bad XML/Format
	 * @throws org.xml.sax.SAXException SAX Error
	 * @throws java.io.IOException File not found
	 */
	public Document getDomElement(String xml) throws ParserConfigurationException, SAXException, IOException
	{
		if(xml.equals("")) {
			return null;
		}
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xml));
		return db.parse(is);
	}

	/**
	 * Get the value of a node
	 *
	 * @param item The element
	 * @return The element value
	 */
	public String getValue(Element item, String str)
	{
		if(item == null || str.equals("")) {
			return "";
		}
		NodeList n = item.getElementsByTagName(str);
		if(n == null || n.getLength() == 0) {
			return "";
		}

		if(str.equals("media:content")) {
			Element media_content = (Element) n.item(0);
			return media_content.getAttribute("url");
		}

		Node child, elem = n.item(0);
		if (elem == null || !elem.hasChildNodes()) {
			return "";
		}
		for(child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
			if(child.getNodeType() == Node.TEXT_NODE){
				return child.getNodeValue().trim();
			}
		}
		return "";
	}
}