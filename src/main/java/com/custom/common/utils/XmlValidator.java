package com.custom.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * 校验xml工具类
 *
 * @author zhangmeng
 */
public class XmlValidator {

    private final static Logger logger = LoggerFactory.getLogger(XmlValidator.class);

    /**
     * 获取xml schema文件
     *
     * @param xmlPath
     * @return
     */
    public static String getXmlSchema(String xmlPath) {

        return "";
    }

    /**
     * 校验xml文件有效性
     *
     * @param xmlPath
     * @return
     * @throws IOException
     */
    public static boolean validateXml(String xmlPath)
        throws IOException {
        return validateXml(getXmlSchema(xmlPath), xmlPath);
    }

    /**
     * 校验xml文件有效性
     *
     * @param xsdPath
     * @param xmlPath
     * @return
     * @throws IOException
     */
    public static boolean validateXml(String xsdPath, String xmlPath)
        throws IOException {
        try {
            SchemaFactory schemaFactory = SchemaFactory
                .newInstance("http://www.w3.org/2001/XMLSchema");
            File schemaFile = new File(xsdPath);
            Schema schema = null;
            schema = schemaFactory.newSchema(schemaFile);

            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlPath);
            validator.validate(source);
        } catch (SAXException e) {
            logger.error(xmlPath + "校验失败", e);
            return false;
        }
        return true;
    }
}

