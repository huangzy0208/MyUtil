package com.sdeport.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 文件工具类
 * Created by zhangmeng on 2015/10/21.
 */
public class FileUtils {

    private final static Logger log = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 获取指定路径下的指定范式的文件列表
     *
     * @param dir
     * @param regFile
     * @return
     */
    public static List<String> getFileList(String dir, String regFile) {
        List<String> listFile = new ArrayList<>();
        List<String> listFileAll = getFileList(dir);
        if (!CollectionUtils.isEmpty(listFileAll)) {
            for (String file : listFileAll) {
                if (file.matches(regFile)) {
                    listFile.add(file);
                }
            }
        }
        return listFile;
    }

    /**
     * 获取指定路径下的所有文件列表
     *
     * @param dir 要查找的目录
     * @return
     */
    public static List<String> getFileList(String dir) {
        List<String> listFile = new ArrayList<>();
        File dirFile = new File(dir);
        //如果不是目录文件，则直接返回
        if (dirFile.isDirectory()) {
            //获得文件夹下的文件列表，然后根据文件类型分别处理
            File[] files = dirFile.listFiles();
            if (null != files && files.length > 0) {
                //根据时间排序
                Arrays.sort(files, new Comparator<File>() {
                    public int compare(File f1, File f2) {
                        return (int) (f1.lastModified() - f2.lastModified());
                    }

                    public boolean equals(Object obj) {
                        return true;
                    }
                });
                for (File file : files) {
                    //如果不是目录，直接添加
                    if (!file.isDirectory()) {
                        listFile.add(file.getAbsolutePath());
                    } else {
                        //对于目录文件，递归调用
                        listFile.addAll(getFileList(file.getAbsolutePath()));
                    }
                }
            }
        }
        return listFile;
    }

    /**
     * 将文件移动至指定地址
     *
     * @param srcFileName
     * @param targetFileName
     * @return
     */
    public static boolean moveFile(String srcFileName, String targetFileName) {
        if (StringUtils.isNotEmpty(srcFileName) && StringUtils.isNotEmpty(targetFileName) && !srcFileName.equals(
                targetFileName)) {
            File src = new File(srcFileName);
            File target = new File(targetFileName);
            if (src.exists()) {
                if (target.exists()) {
                    target.delete();
                }
                String targetPath = getFilePath(targetFileName, System.getProperties().getProperty("file.separator"));
                if (!new File(targetPath).exists()) {
                    new File(targetPath).mkdirs();
                }
                if (!src.renameTo(target)) {
                    copyDelete(srcFileName, targetFileName);
                }
            }
        }
        return true;
    }

    public static void copyDelete(String srcFile, String destFile) {
        copy(srcFile, destFile);
        File dest = new File(destFile);
        if (dest.exists()) {
            File src = new File(srcFile);
            if (src.exists()) {
                src.delete();
            }
        }
    }


    public static void copy(String srcFile, String destFile) {
        InputStream in = null;
        OutputStream out = null;
        try {
            String targetPath = getFilePath(destFile, "/");
            if (!new File(targetPath).exists()) {
                new File(targetPath).mkdirs();
            }
            int byteread = 0;
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];
            while ((byteread = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteread);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != out) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String args[]) {
        log.info("public static boolean moveFile", getFileList("f:\\"));
    }

    public static String getFilePath(String remoteFileName, String fileSplit) {

        return remoteFileName.substring(0, remoteFileName.lastIndexOf(fileSplit));
    }
}
