package com.mockCommon.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mockCommon.constant.LogConstant;
import com.netease.common.util.StringUtil;

public class ParseXmlUtil {
	public static Map<String, String> parseXml(byte[] xmlBytes) {
		Map<String, String> params = new HashMap<String, String>();
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(new ByteArrayInputStream(xmlBytes));
		} catch (DocumentException e) {
			LogConstant.debugLog.info("解析xml请求异常！");
			e.printStackTrace();
		}
		// 获取根元素
		if(document == null){
			LogConstant.debugLog.info("解析xml文件时Document为空！");
			return params;
		}
		Element root = document.getRootElement();
		LogConstant.debugLog.info("解析xml请求开始：");
		xmlTraverse(root, params);
		LogConstant.debugLog.info("解析xml请求完成。");
		LogConstant.debugLog.info("xml的请求参数为：" + params);
		return params;
	}

	@SuppressWarnings("rawtypes")
	private static void xmlTraverse(Element ele, Map<String, String>params) {
		for (Iterator i = ele.elementIterator(); i.hasNext();) {
			Element node = (Element) i.next();
			if (node.attributes() != null && node.attributes().size() > 0) {
				for (Iterator j = node.attributeIterator(); j.hasNext();) {
					Attribute item = (Attribute) j.next();
					LogConstant.debugLog.info("属性名：" + item.getName() + "\t属性值：" + item.getValue());
				}
			}
			if (node.getText().length() > 0) {
				if(!StringUtil.isEmpty(node.getText())){
					params.put(node.getName(), node.getText());
				}
				//LogConstant.debugLog.info("节点名:" + node.getName() + "      节点值：" + node.getText());
			}
			if (node.elementIterator().hasNext()) {
				xmlTraverse(node, params);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Element> queryElementsByXPath(String xml, String xpath){
		
		List<Element> list = new ArrayList<Element>();
		Document doc = null;
		if(StringUtils.isEmpty(xpath)){
			return list;
		}
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			LogConstant.debugLog.error("parse xml error", e);
		}
		if(doc == null){
			return list;
		}
		Element root = doc.getRootElement();
		list = root.selectNodes(xpath);
		return list;
	}
	
	public static Element queryElementByXPath(String xml, String xpath){
		
		Document doc = null;
		if(StringUtils.isEmpty(xpath)){
			return null;
		}
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			LogConstant.debugLog.error("parse xml error", e);
		}
		if(doc == null){
			return null;
		}
		Element root = doc.getRootElement();
		return (Element) root.selectSingleNode(xpath);
	}
	
	//解析某个节点下的所有input节点
	public static Map<String, String> parseXmlNodes(String xml, String xPath){
		Map<String, String> map = new HashMap<String, String>();
		List<Element> listEle = queryElementsByXPath(xml, xPath);
		for(Element element : listEle){
			map.put(element.attributeValue("name"), element.getTextTrim());
		}
		return map;
	}
	
	public static void main(String[] args) {
		String path = "C:\\Users\\kanghuaisong\\Desktop\\optional.xml";
		List<String> list = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		try {
			list = Files.readAllLines(Paths.get(path), Charset.forName("utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(String str : list){
			builder.append(str).append("\n");
		}
		String xmlStr = builder.toString();
		Element eleImmeValid = ParseXmlUtil.queryElementByXPath(xmlStr, 
				"/PackageList/Package/Request/InputsList/Inputs[@type=\"immevalid\"]/Input[@name=\"immeValidHoursStart\"]");
		/*for(Element element : listEle){
			System.out.println(element.getTextTrim());
		}*/
		System.out.println(eleImmeValid.getTextTrim());
		System.out.println("parse over");
	}
}