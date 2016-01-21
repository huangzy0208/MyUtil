package com.custom.common.utils;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author rocky
 * @version v1.0
 * @date 2015/5/14,22:44
 * @since IOT-OMP 1.0
 */

public class MD5Encrypt {

    MD5Encrypt() {
    }

    public static final String getMD5(String pwd) {
        if (StringUtils.isEmpty(pwd)) {
            return "";
        }
        char[] md5String = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] e = pwd.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(e);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for (int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = md5String[byte0 >>> 4 & 15];
                str[k++] = md5String[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }
}

