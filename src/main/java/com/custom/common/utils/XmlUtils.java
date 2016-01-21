package com.custom.common.utils;

import com.custom.mail.MailCreator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

/**
 * xml schema校验
 * <功能详细描述>
 *
 * @author zhangmeng
 * @date 2015/12/26,16:26
 */
@Component
public class XmlUtils {
    @Value("${error.to}")
    private String errorTo;

    private static String mailTo;

    @PostConstruct
    public void init() {
        mailTo = errorTo;
    }

    private final static Logger logger = LoggerFactory.getLogger(XmlUtils.class);

    public static void validateXml(String xml, String encoding) throws IOException, SAXException, ParserConfigurationException {
        String xsd = "";
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new ByteArrayInputStream(xml.getBytes(encoding)));
            String schemaLocation = doc.getElementsByTagName("Message").item(0).getAttributes().getNamedItem("xsi:schemaLocation").getNodeValue();
            xsd = schemaLocation.split("\\s+")[1];

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            URL url = XmlUtils.class.getClass().getResource("/xsd/" + xsd);
            if (null != url) {
                Schema schema = schemaFactory.newSchema(url);
                Validator validator = schema.newValidator();
                InputSource inputSource = new InputSource(new StringReader(xml));
                Source source = new SAXSource(inputSource);
                validator.validate(source);
            }
        } catch (Exception e) {
            if (!StringUtils.isEmpty(mailTo)) {
                try {
                    MailCreator.createAndSendMail(xsd + "校验失败;" + e.getMessage(), "报文xsd校验失败", "", mailTo);
                } catch (Exception e1) {
                    logger.error("发送邮件失败", e1);
                }
            }
            throw e;
        }

    }
}

