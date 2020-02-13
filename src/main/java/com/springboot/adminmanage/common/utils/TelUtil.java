package com.springboot.adminmanage.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: ouyang
 * Date:2020/1/28 14:34
 **/
public class TelUtil {

    /**
            * 验证是否是正确合法的手机号码
     *
             * @param telephone
     *            需要验证的打手机号码
     * @return 合法返回true，不合法返回false
     * */
    public static boolean isCellPhoneNo(String telephone) {
        if (StringUtils.isBlank(telephone)) {
            return false;
        }
        if (telephone.length() != 11) {
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3,5]\\d{9}||18[6,8,9]\\d{8}$");
        Matcher matcher = pattern.matcher(telephone);

        if (matcher.matches()) {
            return true;
        }
        return false;

    }


}
