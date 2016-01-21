package com.sdeport.common.utils;

import com.sdeport.common.pojo.HostInfo;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

/**
 * FreeMarkerUtil工具类
 *
 * @author zhangmeng
 */
public class FreeMarkerUtil {
    private final static Logger logger = LoggerFactory.getLogger(FreeMarkerUtil.class);

    private FreeMarkerUtil() {
    }

    private Configuration config;
    private static FreeMarkerUtil freeMarkerUtil;

    /**
     * 初始化freemarker配置
     *
     * @return
     * @throws IOException
     */
    private static Configuration getConfiguration() throws IOException {
        if (null == freeMarkerUtil) {
            freeMarkerUtil = new FreeMarkerUtil();
            freeMarkerUtil.config = new Configuration();
            freeMarkerUtil.config.setLocale(Locale.CHINA);
            freeMarkerUtil.config.setDefaultEncoding("utf-8");
            freeMarkerUtil.config.setDefaultEncoding("utf-8");
            freeMarkerUtil.config.setEncoding(Locale.CHINA, "utf-8");
            ClassTemplateLoader ftl2 = new ClassTemplateLoader(FreeMarkerUtil.class, "/template");
            TemplateLoader[] loaders = {ftl2};
            MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
            freeMarkerUtil.config.setTemplateLoader(mtl);
            freeMarkerUtil.config.setObjectWrapper(new DefaultObjectWrapper());
        }
        return freeMarkerUtil.config;
    }

    /**
     * 处理模板
     *
     * @param templateName 模板名字
     * @param root         模板根 用于在模板内输出结果集
     * @param out          输出对象 具体输出到哪里
     */
    public static <T> void processTemplate(String templateName, T root, Writer out)
            throws IOException, TemplateException {
        try {
            //获得模板
            Template template = getConfiguration().getTemplate(templateName, "utf-8");
            template.process(root, out);
            out.flush();
        } catch (IOException | TemplateException e) {
            logger.error("生成报文失败", e);
            throw e;
        } finally {
            try {
                out.close();
                out = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理模板
     *
     * @param root 模板根 用于在模板内输出结果集
     */
    public static <T> String processTemplate(String templateName, T root) throws IOException, TemplateException {
        final Writer out = new StringWriter();
        processTemplate(templateName, root, out);
        return out.toString();
    }

    /**
     * @param root 模板根 用于在模板内输出结果集
     */
    public static <T> String processTemplate(T root) throws IOException, TemplateException {
        return processTemplate(root.getClass().getSimpleName(), root);
    }

    public static void main(String[] args) throws IOException, TemplateException {
        System.out.println(processTemplate("CN_CSM101.json",new HostInfo()));
    }
}
