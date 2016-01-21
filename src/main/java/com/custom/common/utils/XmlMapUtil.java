package com.custom.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map与xml转换工具类
 * by zhangmeng
 */
public class XmlMapUtil {

    private final static Logger logger = LoggerFactory.getLogger(XmlMapUtil.class);

    /**
     * 字符串转map
     *
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static Map xml2map(String xml) throws DocumentException {
        Document doc = DocumentHelper.parseText(xml);
        Element rootElement = doc.getRootElement();
        return ele2map(rootElement);
    }

    /***
     * dom元素转map
     *
     * @param srcElement
     */
    public static Map ele2map(Element srcElement) {
        Map map = new HashMap();
        if (0 == srcElement.elements().size()) {
            map.put(srcElement.getName(), srcElement.getTextTrim());
        } else {
            Map elementsMap = new HashMap();
            List<Element> elements = srcElement.elements();
            if (!CollectionUtils.isEmpty(elements)) {
                for (Element ele : elements) {
                    Object o = elementsMap.get(ele.getName());
                    if (null == o) {
                        elementsMap.putAll(procOneEle(ele));
                    } else {
                        if (!(o instanceof List)) {
                            List list = new ArrayList();
                            list.add(elementsMap.get(ele.getName()));
                            elementsMap.put(ele.getName(), list);
                        }
                        ((List) elementsMap.get(ele.getName())).add(ele2map(ele).get(ele.getName()));
                    }
                }

            }
            map.put(srcElement.getName(), elementsMap);
        }
        return map;
    }

    /**
     * 处理一个元素
     *
     * @param ele
     * @return
     */
    private static Map procOneEle(Element ele) {
        Map map = new HashMap();
        if (ele.elements().size() == 0) {
            map.put(ele.getName(), ele.getTextTrim());
        } else {
            map.putAll(ele2map(ele));
        }
        return map;
    }

    /**
     * 读取xml报文放入map
     *
     * @param in
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Map<String, Object> readXml(InputStream in, String code)
            throws ParserConfigurationException, SAXException, IOException, DocumentException {
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        try {
            rtnMap = xml2map(inputStream2String(in, code));
        } finally {
            if (null != in) {
                in.close();
            }
        }
        return rtnMap;
    }

    /**
     * 读取指定文件放入map，自动猜测文件的编码格式
     *
     * @param fileName
     * @return
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws DocumentException
     */
    public static Map<String, Object> readXml(String fileName) throws IOException, ParserConfigurationException, SAXException, DocumentException {
        String encoding = EncodingDetect.getJavaEncode(fileName);
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        rtnMap = readXml(new FileInputStream(new File(fileName)), encoding);
        return rtnMap;
    }

    /**
     * input 转字符串
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static String inputStream2String(InputStream in, String code) throws IOException {
        return inputStream2SrcString(in, code).replaceFirst("[^<]*<", "<");
    }

    /**
     * input 转字符串,不进行任何处理
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static String inputStream2SrcString(InputStream in, String code) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, code));

        StringBuffer out = new StringBuffer();
        char[] b = new char[4096];
        for (int n; (n = reader.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    /**
     * 将document 生成文档
     *
     * @param document
     * @throws IOException
     */
    public static void createXml(Document document, String fileName, String encoding) throws IOException {
        //把生成的xml文档存放在硬盘上  true代表是否换行
        OutputFormat format = new OutputFormat("    ", false);
        format.setEncoding(encoding);//设置编码格式
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileName), format);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    /**
     * 合并map到document中
     *
     * @param document
     * @param map
     * @param prefix
     * @param suffix
     */
    public static void mergeDocument(Document document, Map<String, String> map, String prefix, String suffix) {
        if (null != document) {
            mergeElement(document.getRootElement(), map, prefix, suffix);
        }
    }

    /**
     * 合并map到element中
     *
     * @param element
     * @param map
     * @param prefix
     * @param suffix
     */
    public static void mergeElement(Element element, Map<String, String> map, String prefix, String suffix) {
        if (null != element) {
            if (element.elements().size() == 0) {
                if (map.containsKey(element.getTextTrim())) {
                    element.setText(prefix + map.get(element.getTextTrim()) + suffix + "|" + element.getTextTrim());
                }
            } else {
                for (Element child : (List<Element>) element.elements()) {
                    mergeElement(child, map, prefix, suffix);
                }
            }
        }
    }

    /**
     * 用dom读取文档
     *
     * @param filePath
     * @throws IOException
     */
    public static Document readDocXml(String filePath) throws IOException, DocumentException {
        String encoding = EncodingDetect.getJavaEncode(filePath);
        String strXml = inputStream2String(new FileInputStream(new File(filePath)), encoding);
        return DocumentHelper.parseText(strXml);
    }

    /**
     * 读取java代码中的注释以及字段与xml文件合并
     *
     * @param xmlPath
     * @param javaPaths
     * @param prefix
     * @param suffix
     * @throws IOException
     * @throws DocumentException
     */
    public static void readAndMergeXml(String xmlPath, List<String> javaPaths, String prefix, String suffix) throws IOException, DocumentException {
        if (StringUtils.isNotEmpty(xmlPath) && !CollectionUtils.isEmpty(javaPaths)) {
            Document xmlDoc = readDocXml(xmlPath);
            Map<String, String> mapFields = new HashMap<String, String>();
            for (String javaPath : javaPaths) {
                mapFields.putAll(ZzStringUtil.getFieldsAnnotation(javaPath, "utf8"));
            }
            mergeDocument(xmlDoc, mapFields, prefix, suffix);
            createXml(xmlDoc, "d:/test", "utf8");
        }
    }

    public static void main(String args[]) throws IOException, DocumentException, ParserConfigurationException, SAXException {
        //System.out.println(readXml("F:\\meng\\EPORT_DOC\\三个一预录入组件\\上报结果记录\\CN_CSM002_1P0_785239774_20150728122128439.xml"));
    }

}
