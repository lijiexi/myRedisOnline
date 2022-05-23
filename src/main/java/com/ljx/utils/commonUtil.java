package com.ljx.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class commonUtil {
    /**
     * 判断参数是否为数字
     * @param str string
     * @return
     */
    public static boolean isNumber (String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
