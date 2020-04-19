package com.lnr.android.base.framework.uitl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zwb on.
 * Des: 正则表达式
 * Date: 2017/6/14
 */

public class RegularExpressionsUtils {

    public static boolean matchingNickName(String name) {
        Pattern pattern = Pattern.compile("^[a-z0-9A-Z\\u4e00-\\u9fa5]+$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }


    public static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }


    public static boolean hasEmoji(String content) {
        Pattern pattern = Pattern.compile("[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]");
        Matcher matcher = pattern.matcher(content);
        return matcher.find();
    }

    //中文的字数
    public static int matchingChineseCharactersCount(String name) {
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher matcher = pattern.matcher(name);
        int count = 0;
        boolean b = matcher.find();
        while (b) {
            count++;
            b = matcher.find();
        }
        return count;
    }

    //返回字母的个数
    public static int matchingLetterCount(String name) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(name);
        int count = 0;
        boolean b = matcher.find();
        while (b) {
            count++;
            b = matcher.find();
        }
        return count;
    }


    //返回不是字母数字汉字中文的个数
    public static int misMatchingCount(String name) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9\\u4e00-\\u9fa5]");
        Matcher matcher = pattern.matcher(name);
        StringBuffer buffer = new StringBuffer();
        boolean b = matcher.find();
        while (b) {
            //把符合规则的都替换成空字符
            matcher.appendReplacement(buffer, "");
            b = matcher.find();
        }
        //添加不符合规则的
        matcher.appendTail(buffer);
        return buffer.length();
    }

}
