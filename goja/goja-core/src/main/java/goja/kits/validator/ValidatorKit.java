/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package goja.kits.validator;

import goja.app.StringPool;
import com.google.common.base.Strings;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static goja.Constants.FLOAT_NEGATIVE;
import static goja.Constants.FLOAT_NORMAL;
import static goja.Constants.FLOAT_POSITIVE;
import static goja.Constants.FLOAT_UNNEGATIVE;
import static goja.Constants.FLOAT_UNPOSITIVE;
import static goja.Constants.INTEGER_NEGATIVE;
import static goja.Constants.INTEGER_NORMAL;
import static goja.Constants.INTEGER_POSITIVE;
import static goja.Constants.INTEGER_UNNEGATIVE;
import static goja.Constants.INTEGER_UNPOSITIVE;
import static goja.Constants.ValidatorRegex.*;

/**
 * <p>
 * .
 * </p>
 *
 * @author sagyf yang
 * @version 1.0 2014-02-22 15:09
 * @since JDK 1.6
 */
@SuppressWarnings("UnusedDeclaration")
public class ValidatorKit {

    private ValidatorKit() {

    }


    /**
     * 验证字符串中是否含有某个字符
     *
     * @param str 要验证的字符串
     * @param c   要验证的某个字符
     * @return boolean 如果含有该字符返回true，否则返回false
     */
    public static boolean isContainChar(String str, String c) {
        return !Strings.isNullOrEmpty(str) && (str.contains(c));
    }

    /**
     * 验证字符串是否包含英文
     *
     * @param str 要验证的字符串
     * @return boolean 如果含有英文返回true，否则返回false
     */
    public static boolean isContainEnglish(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(CONTAINENGLISH);
    }

    /**
     * 验证字符串是否包含数字
     *
     * @param str 要验证的字符串
     * @return boolean 如果含有数字返回true，否则返回false
     */
    public static boolean isContainInteger(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(CONTAININTEGER);
    }

    /**
     * 验证字符串是否全为半角
     *
     * @param str 要验证的字符串
     * @return boolean 如果全为半角返回true，否则返回false
     */
    public static boolean isAllDBCCase(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(FULLDBC);
    }

    /**
     * 验证字符串是否包含半角
     *
     * @param str 要验证的字符串
     * @return boolean 如果包含半角返回true，否则返回false
     */
    public static boolean isContainDBCCase(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.valueOf(str.charAt(i)).toString().matches(CONTAINDBC)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将字符串全部转换为大写
     *
     * @param str 要转换的字符
     * @return String 全部为大写的字符串
     */
    public static String toUpperCase(String str) {
        return str.toUpperCase();
    }

    /**
     * 将字符串全部转换为小写
     *
     * @param str 要转换的字符
     * @return String 全部为小写的字符串
     */
    public static String toLowerCase(String str) {
        return str.toLowerCase();
    }

    /**
     * 判断字符串是否全为大写字母
     *
     * @param str 要验证的字符串
     * @return Boolean 如果全为大写返回trur，否则返回FALSE
     */
    public static boolean isUpperCase(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(ALLUPPERCASE);
    }

    /**
     * 判断字符串是否全为小写字母
     *
     * @param str 要验证的字符串
     * @return Boolean 如果全为小写返回trur，否则返回FALSE
     */
    public static boolean isLowerCase(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(ALLLOWERCASE);
    }

    /**
     * 验证字符串中是否含有中文
     *
     * @param str 要验证的字符串
     * @return 如果含有中文返回 true， 否则返回false
     */
    public static boolean isContainChinese(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(CONTAINCHINESE);
    }

    /**
     * 验证字符串中不能含有中文
     *
     * @param str
     *            要验证的字符串
     * @return 如果不含有中文返回 true， 否则返回false
     * @author dingfenghua
     */
    /*
     * public static boolean isNotContainChinese(String str) { return
	 * !(isContainChinese(str)); }
	 */

    /**
     * 验证字符串中是否含有全角
     *
     * @param str
     *            要验证的字符串
     * @return 如果含有全角返回 true，否则返回false
     * @author YangLijuan
     */
    /*
     * public static boolean isContainFullShaped(String str) { return
	 * Strings.isNullOrEmpty(str) ? false : str.matches(FULLSHAPED); }
	 */

    /**
     * 验证字符串中是否含有空格
     *
     * @param str
     *            要验证的字符串
     * @return 如果字符串中含有返回 true，否则返回 false
     * @author Yang Lijuan
     */
    /*
     * public static boolean isContainSpace(String str) { return Strings.isNullOrEmpty(str) ?
	 * false : str.matches(CONTAINSPACE); }
	 */

    /**
     * 验证字符串中不含有空格
     *
     * @param str
     *            要验证的字符串
     * @return 如果字符串中不含有返回 true，否则返回 false
     * @author Yang Lijuan
     */
    /*
     * public static boolean isNotContainSpace(String str) { return
	 * !(isContainSpace(str)); }
	 */

    /**
     * 验证字符串中是否含有特殊字符
     *
     * @param str 要验证的字符串
     * @return 如果字符串中含有特殊字符返回 true，否则返回 false
     */
    public static boolean isContainSpecial(String str) {
        boolean tag = false;
        for (int i = 0; i < str.length(); i++) {
            String c = Character.valueOf(str.charAt(i)).toString();
            if (isAllNumber(c) || isAllEnglish(c) || isAllChinese(c) || isAllDBCCase(c))
                continue;
            else
                tag = true;
            break;
        }
        return tag;
        //return Strings.isNullOrEmpty(str) ? false : str.matches(CONTAINSPECIAL);
    }

    /**
     * 验证字符串中是否含有特殊字符
     *
     * @param str 要验证的字符串
     * @return 如果字符串中含有特殊字符返回 true，否则返回 false
     */
    public static boolean isNotContainSpecial(String str) {
        return !(isContainSpecial(str));
    }

    /**
     * 验证字符串是否是标准字符串 （格式：包含数字，字母，和下划线的字符串）
     *
     * @param str 要验证的字符串
     * @return 验证通过返回 true，否则返回 false
     */
    public static boolean isStandardString(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(STANDARSTRING);
    }

    /**
     * 验证字符串是否为合法的网址 isStrict为true时：
     * （格式：开头"http://"或"https://"，"http://"或"https://"后一位不得为"." ;倒数第四位或第五位为".";
     * 以"/"结尾）
     * <p/>
     * isStrict为false时: （格式：开头"www."倒数第四位或第五位为".";）
     *
     * @param url      要验证的字符串
     * @param isStrict 是否为严格验证合法网址
     * @return 验证通过返回 true，否则返回 false
     */
    public static boolean isUrl(String url, boolean isStrict) {
        if (!Strings.isNullOrEmpty(url)) {
            if (isStrict) {
                return url.matches(URLSTRICT);
            }
            return url.matches(URL);
        }
        return false;
    }

    /**
     * 验证字符串是否为合法的IP （只验证IP4标准：15位数字与"."组合，连续数字不得超过3位，连续两位不能为".",开头和结尾不能为"."）
     *
     * @param str 要验证的字符串
     * @return 验证通过返回 true，否则返回 false
     */
    public static boolean isIP(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(IP);
    }

    /**
     * 验证是否有值（验证的字符串先trim之后长度大于等于1）
     *
     * @param str 要被验证的字符串
     * @return 如果验证通过返回 true，否则返回 false
     */
    public static boolean isNotEmpty(String str) {
        return !Strings.isNullOrEmpty(str) && str.trim().length() > 0;
    }

    /**
     *验证字符串的的长度是否在指定大小内
     *
     * @param str
     *            要被验证的字符串
     * @param size
     *            指定的最大长度
     * @return 长度在指定范围内返回 true，否则返回false
     * @author Yang Lijuan
     */
    /*
     * public static boolean isInLength(String str, int size) { return
	 * Strings.isNullOrEmpty(str) ? false : size >= getLength(str); }
	 */

    /**
     * 获取字符串长度，中文为两位以gbk读取，gbk码读取失败的以str.length()返回长度
     *
     * @param str 要分析的字符串
     * @return 字符串的长度
     */
    public static int getLength(String str) {
        int length = 0;
        try {
            String s = new String(str.getBytes(StringPool.UTF_8), StringPool.UTF_8);
            for (int i = 0; i < str.length(); i++) {
                String ss = Character.valueOf(str.charAt(i)).toString();
                if (ss.matches(FULLSHAPED)) {
                    length += 2;
                } else {
                    length += 1;
                }
            }

        } catch (UnsupportedEncodingException e) {
            return str.length();
        }
        return length;
    }

    /**
     * 验证字符串的长度（汉字长度为2）是不是在两个长度（包括最大最小值点）之间
     *
     * @param str    要被验证的字符串
     * @param minLen 要被验证的区域的最小值
     * @param maxLen 要被验证的区域的最大值
     * @return 返回验证的结果，不通过返回false，通过返回true
     */
    public static boolean isFitableLength(String str, int minLen, int maxLen) {
        return !(Strings.isNullOrEmpty(str) || maxLen < 0) && (getLength(str) >= minLen && (getLength(str)) <= maxLen);
    }

    /**
     * 验证字符串是否为合法的整数类型 （valueRange值不同时判断不同的整数类型， valueRange值不输入时默认为0, 0
     * 代表正整数、负整数和0, 1代表正整数, -1代表负整数, 10代表非负整数, -10 代表非正整数）
     *
     * @param str        要验证的字符
     * @param valueRange 要验证的取值范围
     * @return 通过验证返回true, 验证失败、空值、null值或取值范围不正确则返回false
     */
    public static boolean isInteger(String str, int valueRange) {
        if (!Strings.isNullOrEmpty(str)) {
            switch (valueRange) {
                case INTEGER_NORMAL:
                    return str.matches(INTEGER);
                case INTEGER_POSITIVE:
                    return str.matches(POSITIVEINTEGER);
                case INTEGER_UNPOSITIVE:
                    return str.matches(UNPOSITIVEINTEGER);
                case INTEGER_NEGATIVE:
                    return str.matches(NEGATIVEINTEGER);
                case INTEGER_UNNEGATIVE:
                    return str.matches(UNNEGATIVEINTEGER);
                default:
                    return false;
            }
        }
        return false;
    }

    /**
     * 验证字符串是否为合法的整数，并且在两个整数范围之内,含边界值
     *
     * @param str    要被验证的字符串
     * @param minLen 要被验证的区域的最小值
     * @param maxLen 要被验证的区域的最大值
     * @return 返回验证的结果，不通过返回false，通过返回true
     */
    public static boolean isIntegerInLength(String str, int minLen, int maxLen) {
        return !Strings.isNullOrEmpty(str) && str.matches(INTEGER)
                && ((Integer.parseInt(str) >= minLen && Integer
                .parseInt(str) <= maxLen));

    }

    /**
     * 验证字符串是否为合法的浮点类型，并且在两个整数范围之内,含边界值
     *
     * @param str    要被验证的字符串
     * @param minLen 要被验证的区域的最小值
     * @param maxLen 要被验证的区域的最大值
     * @return 返回验证的结果，不通过返回false，通过返回true
     */
    public static boolean isFloatInLength(String str, int minLen, int maxLen) {
        return !Strings.isNullOrEmpty(str) && isFloat(str)
                && ((Float.parseFloat(str) >= minLen && Float
                .parseFloat(str) <= maxLen));
    }

    /**
     * 验证字符串是否为合法的浮点数类型 （valueRange值不同时判断不同的整数类型， valueRange值不输入时默认为0, 0
     * 代表正浮点数负浮点数和0， 1代表正浮点数, -1代表负浮点数, 10代表非负浮点数, -10 代表非正浮点数）
     *
     * @param str        要验证的字符
     * @param valueRange 要验证的浮点数类型
     * @return 通过验证返回true, 验证失败、空值、null值或取值范围不正确则返回false
     */
    public static boolean isFloat(String str, int valueRange) {
        if (!Strings.isNullOrEmpty(str)) {
            switch (valueRange) {
                case FLOAT_NORMAL:
                    return str.equals("0") || str.equals("-0") || str.equals("+0") || str.matches(FLOAT);
                case FLOAT_POSITIVE:
                    return str.equals("0") || str.endsWith("+0") || str.matches(POSITIVEFLOAT);
                case FLOAT_UNPOSITIVE:
                    return str.equals("0") || str.equals("-0") || str.matches(UNPOSITIVEFLOAT);
                case FLOAT_NEGATIVE:
                    return str.equals("0") || str.equals("-0") || str.matches(NEGATIVEFLOAT);
                case FLOAT_UNNEGATIVE:
                    return str.equals("0") || str.equals("-0") || str.matches(UNNEGATIVEFLOAT);
                default:
                    return false;
            }
        }
        return false;
    }

    /**
     * 验证字符串是否为浮点数
     *
     * @param str 字符串
     * @return 如果是浮点数，则返回true。
     */
    public static boolean isFloat(String str) {
        return isFloat(str, 0);
    }

    /**
     * 验证两个字符串是否相同(两个字符串均不能为空)
     *
     * @param strSrc 要验证的字符串
     * @param strDes 被验证的字符串
     * @return 如果两个字符串相同返回 true，不同或任意一字符串为null时返回false isSame 1为不区分大小写 0 区分大小写
     */
    public static boolean isSameValue(String strSrc, String strDes, int isSame) {
        if (!Strings.isNullOrEmpty(strSrc) && !Strings.isNullOrEmpty(strDes) && !strSrc.isEmpty()) {

            if (isSame == 1) {
                return strSrc.equals(strDes);
            } else {
                return toLowerCase(strDes).equals(toLowerCase(strSrc));
            }

        }
        return false;
    }

    /**
     * 验证两个字符串不能相同(两个字符串均不能为空)
     *
     * @param strSrc
     *            要验证的字符串
     * @param strDes
     *            被验证的字符串
     * @return 如果两个字符串相同返回 true，不同或任意一字符串为null时返回false
     * @author hhd
     */
    /*
     * public static boolean isNotSameValue(String strSrc, String strDes) {
	 * if(Strings.isNullOrEmpty(strSrc)&&Strings.isNullOrEmpty(strDes)){ return false; }else{ return
	 * !(isSameValue(strSrc,strDes)); } }
	 */

    /**
     * 验证字符串是否为合法的Email （Email格式是指：字母、数字、下划线与'@'和'.'的组合，'@'数量不得超过1；连续两位字符不能为'_'
     * 或'@'与'.'；不得以'@'或'.'或'_'开头；倒数第三位或第四位必须为'.'）
     *
     * @param email 要验证的字符串
     * @return 验证通过返回 true，否则返回 false
     */
    public static boolean isEmail(String email) {
        return !Strings.isNullOrEmpty(email) && email.matches(EMAIL);
    }

    /**
     * 验证字符串是否为合法的属性名 （格式：英文字符，可以用 '.'进行连接，不得包含其他字符或符号）
     *
     * @param str 要验证的字符串
     * @return 验证通过返回 true，否则返回 false
     */
    public static boolean isAttributeName(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(ATTRIBUTENAME);
    }

    /**
     * 验证字符串是为合法的英文姓名 （格式：英文字符，空格，'.'组合）
     *
     * @param str 要验证的字符串
     * @return 验证通过返回 true，否则返回 false
     */
    public static boolean isEnglishName(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(ENGLISHNAME);
    }

    /**
     * 验证字符串是否为合法的中国的身份证号 （validateLevel值为false时简单验证：15位或18位数字字符非0开头的组合，
     * 最后一位可以为大写'X'。 validateLevel值为true时严格验证：只适合18位身份证，简单验证通过后，
     * 并对18位身份证校验位进行校验）
     *
     * @param str           要验证的字符串
     * @param validateLevel 验证程序，false为简单验证，true为严格验证
     * @return 验证通过返回 true，验证不通过或是严格验证中身份号不是18位的返回 false
     */
    public static boolean isChinesePID(String str, boolean validateLevel) {
        if (!Strings.isNullOrEmpty(str)) {
            if (!validateLevel) {
                return str.matches(PID);
            } else if (str.length() == 18 && str.matches(STANDARDPID)) {

                int ai[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4,
                        2, 1};
                int i = 0;
                for (int k = 0; k < 18; k++) {
                    char c = str.charAt(k);
                    int j;
                    if (c == 'X') {
                        j = 10;
                    } else if (c <= '9' || c >= '0') {
                        j = c - 48;
                    } else {
                        return false;
                    }
                    i += j * ai[k];
                }
                return i % 11 == 1;
            }
        }

        return false;
    }

    /**
     * 验证字符串是否为合法的电话号码 格式: 数字开头'-'分割,'-'不连续,不能以'-'结尾,最少3位数字，最多30个字符
     *
     * @param str 要验证的字符串
     * @return 验证通过返回 true，否则返回 false
     */
    public static boolean isTel(String str) {
        if (Strings.isNullOrEmpty(str) || str.length() < 3 || str.length() > 30) {
            return false;
        }
        if (str.length() == 3) {
            return isAllNumber(str);
        }
        return str.matches(TEL);
    }

    /**
     * 验证字符串是否为合法的手机号码(长度不小于11位不大于20位，可以用'+'开始,不能存在连续”-”)
     *
     * @param str 要验证的字符串
     * @return 验证通过返回 true，否则返回 false
     */
    public static boolean isMobile(String str) {
        return !(Strings.isNullOrEmpty(str) || str.length() < 11 || str.length() > 20) && str.matches(MOBILE);
    }

    /**
     * 验证字符串是否为合法的登录名称 （格式：字母，数字，下划线组合，以字母开头）
     *
     * @param str 要验证的字符串
     * @return 验证通过返回 true，否则返回 false
     */
    public static boolean isLoginID(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(LOGINID);
    }

    /**
     * 验证字符串是否为合法的密码格式 （格式：英文输入法下的所有字符，空格可以输入，验证时不trim，长度为6-18位）
     *
     * @param str 要验证的字符串
     * @return 验证通过返回 true，否则返回 false
     */
    public static boolean isPassWordFormat(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(PWFORMAT);
    }

    /**
     * 验证字符串是不是全部由26个英文字母组成的字符串
     *
     * @param str 要被验证的字符串
     * @return 如果验证通过返回 true，否则返回 false
     */
    public static boolean isAllEnglish(String str) {
        return !Strings.isNullOrEmpty(str) && str.trim().matches(ALLENGLISH);
    }

    /**
     * 验证字符串是否全部是汉字(不能有空格)
     *
     * @param str 要被验证的字符串
     * @return 如果验证通过返回 true，否则返回 false
     */
    public static boolean isAllChinese(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(ALLCHINESE);
    }

    /**
     * 验证字符串是否全部是数字
     *
     * @param str 要被验证的字符串
     * @return 如果验证通过返回 true，否则返回 false
     */
    public static boolean isAllNumber(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(ALLNUMBER);
    }

    /**
     * 验证字符串是否为合法的邮编格式 （格式：6位数字组成的）
     *
     * @param str 要被验证的字符串
     * @return 如果验证通过返回 true，否则返回 false
     */
    public static boolean isPostCode(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(POSTCODE);
    }

    /**
     * 验证字符串是否为合法的电话区号 （格式：3位或4位的数字组成）
     *
     * @param str 要被验证的字符串
     * @return 如果验证通过返回 true，否则返回 false
     */
    public static boolean isCityCode(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(CITYCODE);
    }

    /**
     * 验证字符串是否为合法的QQ号 （QQ号是指：1-9开头，大于4的数字字符组合）
     *
     * @param str 要被验证的字符串
     * @return 如果验证通过返回 true，否则返回 false
     */
    public static boolean isQQ(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(QQ);
    }

    /**
     * 验证字符串是否为合法的时间格式 格式：HH:mm:ss或HH:mm秒位可省，范围：HH(0-23)，mm(0-59)，ss(0-59)
     *
     * @param str 要被验证的字符串
     * @return 如果验证通过返回 true，否则返回 false
     */
    public static boolean isTime(String str) {
        return !Strings.isNullOrEmpty(str) && str.matches(TIME);
    }

    /**
     * 验证字符串是否为合法的日期格式 （正确的日期格式是指：yyyy-MM-dd，范围为当前时间前后50年）
     *
     * @param str 要被验证的字符串
     * @return 如果验证通过返回 true，否则返回 false
     */
    public static boolean isDate(String str) {
        if (!Strings.isNullOrEmpty(str) && str.matches(DATE)) {

            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(java.sql.Date.valueOf(str));
            cal2.setTimeInMillis(new java.util.Date().getTime());

            return Math.abs(cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) <= 50;
        }

        return false;
    }

    /**
     * 验证字符串是否全是大写字母或全是小写字母（可含有空格，upperOrLower为true时判断全大写字母,false时判断全小写字母）
     *
     * @param str          要被验证的字符串
     * @param upperOrLower true时判断全大写字母,false时判断全小写字母
     * @return 如果验证通过返回 true，否则返回 false
     */
    public static boolean isCaseSensitive(String str, boolean upperOrLower) {

        if (upperOrLower) {
            return !Strings.isNullOrEmpty(str) && str.matches(ALLUPPERCASE);
        } else {
            return !Strings.isNullOrEmpty(str) && str.matches(ALLLOWERCASE);
        }
    }

    /**
     * 验证姓名是否为中文或者英文
     *
     * @param str 要验证的字符串
     * @return 通过返回 true,否则返回false
     */
    public static boolean isChinaOrEnName(String str) {
        if (!Strings.isNullOrEmpty(str)) {
            if (str.matches(ENGLISHNAME)) {
                return true;
            }

            if (str.matches(ALLCHINESE)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证字符串是否为数字或者英文
     *
     * @param str 要验证的字符串`
     * @return 通过返回 true,否则返回false
     */
    public static boolean isNumOrEn(String str) {
        if (!Strings.isNullOrEmpty(str)) {
            if (str.matches(ALLENGLISH)) {
                return true;
            }

            if (str.matches(ALLNUMBER)) {
                return true;
            }
        }
        return false;
    }


}
