package com.asteroid.expression.common.util.generate;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUtil {
	private static Map<Character, String> xmlCharMap=new HashMap<Character, String>();
	
	static {
		xmlCharMap.put(Character.valueOf('<'), "&lt;");
		xmlCharMap.put(Character.valueOf('>'), "&gt;");
		xmlCharMap.put(Character.valueOf('&'), "&amp;");
		xmlCharMap.put(Character.valueOf('\''), "&apos;");
		xmlCharMap.put(Character.valueOf('\"'), "&quot;");
	}
    /**
     * 从本地的XML文件生成XML Document
     *
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static Document getXMLDocFromFile(String path)
            throws SAXException, IOException, ParserConfigurationException {
        File xmlFile=new File(path);
        return getXMLDocFromFile(xmlFile);
    }

    /**
     * 创建空文档
     *
     * @return document对象
     * @throws ParserConfigurationException
     */
    public static Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.newDocument();
    }

    public static Document getXMLDocFromFile(File xmlFile)
            throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(xmlFile);
    }
    
    /**
     * 从IO流生成XML Document
     *
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static Document getXMLDocFromStream(InputStream is)
            throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(is);
    }

    /**
     * 获取标签元素内容
     *
     * @param doc
     * @param xPath ： root/jdbc <root> <jdbc>aaa</jdbc> <jdbc>bbb</jdbc> </root>
     * @return {aaa,bbb}的集合
     */
    public static List<String> getTxts(Document doc, String xPath) throws Exception {
        List<String> list = new ArrayList<>();
        NodeList nodes = getNodeList(xPath, doc);
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            list.add(node.getTextContent());
        }
        return list;
    }

    /**
     * 标签所在xml中的位置 0开始
     *
     * @param doc
     * @param xPath
     * @param index
     * @return
     * @throws Exception
     */
    public static String getTxt(Document doc, String xPath, int index) throws Exception {
        // TODO添加异常，如果list的size< index
        List<String> txt = getTxts(doc, xPath);
        if (0 == txt.size()) {
            return null;
        }
        if (txt.size() < index) {
            throw new Exception("index超出list大小");
        }
        return txt.get(index);
    }

    public static String getTxt(Document doc, String xPath) throws Exception {
        return getTxt(doc, xPath, 0);
    }

    /**
     * 获取标签元素个数
     *
     * @param doc
     * @param xPath
     * @return
     * @throws Exception
     */
    public static int countTag(Document doc, String xPath) throws Exception {
        NodeList nodes = getNodeList(xPath, doc);
        return nodes.getLength();
    }

    /**
     * 获取元素属性值
     *
     * @param doc
     * @param xPath
     * @return Map中的key为属性名称，value为属性值
     * @throws Exception
     */
    public static List<Map<String, String>> getProps(Document doc, String xPath) throws Exception {
        List<Map<String, String>> proplist = new ArrayList<>();
        NodeList nodes = getNodeList(xPath, doc);
        for (int i = 0; i < nodes.getLength(); i++) {
            Map<String, String> propMap = new HashMap<>(16);
            Node node = nodes.item(i).getFirstChild();
            propMap.put(nodes.item(i).getNodeName(), node.getTextContent());
            proplist.add(propMap);
        }
        return proplist;
    }

    /**
     * 获取元素属性值
     *
     * @param doc
     * @param xPath
     * @param index 所在位置 从0开始
     * @return Map中的key为属性名称，value为属性值
     * @throws Exception
     */
    public static Map<String, String> getProp(Document doc, String xPath, int index) throws Exception {
        Map<String, String> propMap = new HashMap<>(16);
        NodeList nodes = getNodeList(xPath, doc);
        if (index > nodes.getLength()) {
            throw new Exception("index大小超限");
        }
        Node node = nodes.item(index).getFirstChild();
        propMap.put(nodes.item(index).getNodeName(), node.getTextContent());
        return propMap;
    }

    /**
     * 获取元素属性值
     *
     * @param doc
     * @param xPath
     * @return Map中的key为属性名称，value为属性值 如果有多个 则会被后面的覆盖
     */
    public static Map<String, String> getProp(Document doc, String xPath) throws Exception {
        Map<String, String> propMap = new HashMap<>(16);
        NodeList nodes = getNodeList(xPath, doc);
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node;
            node = nodes.item(i).getFirstChild();
            propMap.put(nodes.item(i).getNodeName(), node.getTextContent());
        }
        return propMap;
    }

    /**
     * 获取节点数据
     *
     * @param xPath
     * @param doc
     * @return NodeList
     * @throws Exception
     */
    private static NodeList getNodeList(String xPath, Document doc) throws Exception {
        XPathFactory factory = XPathFactory.newInstance();
        // 多核循环读取，属性读取
        XPath mulXpath = factory.newXPath();
        Object o = mulXpath.evaluate(xPath, doc, XPathConstants.NODESET);
        return (NodeList) o;
    }

    /**
     * 通过xpath取得节点列表
     *
     * @param node       节点
     * @param expression XPath表达式
     * @return NodeList
     * @throws XPathExpressionException XPath表达式异常
     */
    public static NodeList selectNodes(Node node, String expression) {
        if (node == null)
            return null;
        try {
            XPathExpression xpexpreesion = getXPath().compile(expression);
            Object object = xpexpreesion.evaluate(node, XPathConstants.NODESET);
            return (NodeList) object;
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过xpath取得单个节点
     *
     * @param node       节点
     * @param expression XPath表达式
     * @return Node
     * @throws XPathExpressionException XPath表达式异常
     */
    public static Node selectSingleNode(Node node, String expression) {
        if (node == null)
            return null;
        try {
            XPathExpression xpexpreesion = getXPath().compile(expression);
            Object object = xpexpreesion.evaluate(node, XPathConstants.NODE);
            return (Node) object;
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据xpath取得节点的文本值
     *
     * @param node       节点
     * @param expression XPath表达式
     * @return String
     * @throws XPathExpressionException XPath表达式异常
     */
    public static String getNodeStringValue(Node node, String expression) {
        if (node == null)
            return "";
        try {
            XPathExpression xpexpreesion = getXPath().compile(expression);
            Object object = xpexpreesion.evaluate(node, XPathConstants.STRING);
            return (String) object;
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 把普通str转换成xml支持的str
     * @param str
     * @return
     */
    public static String toXmlStr(String str) {
    	if(StringUtils.isBlank(str))return str;
    	StringBuffer sb=new StringBuffer();
    	for(char c: str.toCharArray()) {
    		if(xmlCharMap.keySet().contains(c)) {
    			sb.append(xmlCharMap.get(c));
    		}else {
    			sb.append(c);
    		}
    	}
        return sb.toString();
    }
    
   /**
    * 把xml中的合法str转换成
    * @param xmlStr
    * @return
    */
    public static String toStr(String xmlStr) {
    	
    	//TODO
    	return null;
    }



    private static XPath getXPath() {
        XPathFactory xpfactory = XPathFactory.newInstance();
        return xpfactory.newXPath();
    }

}
