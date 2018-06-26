package com.tools.string;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.tools.utils.RegexpUtil;

public class StringUtil {
    public static int PASSWORD_LEVEL_LOW = 1;

    public static int PASSWORD_LEVEL_MIDDLE = 2;

    public static int PASSWORD_LEVEL_HIGH = 3;

    public static String ellipsis(String value, int len, String word) {
	if (length(value) <= len) {
	    return value;
	}
	len -= length(word);
	do
	    value = value.substring(0, value.length() - 1);
	while (length(value) > len);
	return value + word;
    }

    public static int length(String str) {
	return str.replaceAll("[^\\x00-\\xff]", "rr").length();
    }

    public static boolean isChinese(String input) {
	return RegexpUtil.isMatch(input, "[^\\x00-\\xff]");
    }

//    public static void main(String[] args) throws Exception {
//	log.debug(isChinese("sss"));
//
//	String ts = "上海市人民政府办公厅关于加强全市性重要经济社会统计数据管理与发布工作的通知";
//	String en = "Rights and Interests of the Counterpart of Administrative Law Enforcement of the Statistics Organs";
//	String c_ts = ellipsis(ts, 39, "...");
//	String c_en = ellipsis(en, 39, "...");
//	log.debug(c_ts);
//	log.debug(c_en);
//	log.debug(ellipsis(ts, 39, "..."));
//	log.debug(ellipsis(en, 39, "..."));
//	log.debug(ellipsis("海市人民政府办公厅关于于rb", 25, "..."));
//	log.debug(ellipsis("海市人民政府办公厅关于于o", 25, "..."));
//    }

    public static String trim(String s) {
	if (s == null) {
	    return "";
	}
	s = s.replaceAll("　", " ");
	s = s.replaceAll("\\s+", " ");
	return s.trim();
    }

    /**
     * 以\s+格式截取字符串返回list
     * 
     * @param s
     *            字符串
     * @return
     */
    public static List<String> split(String s) {
	List<String> list = new ArrayList<String>();
	s = trim(s);
	if (s == null) {
	    return list;
	}
	String[] rs = s.split("\\s+");
	for (int i = 0; i < rs.length; i++) {
	    if (rs[i].trim().length() > 0) {
		list.add(rs[i]);
	    }
	}
	return list;
    }

    public static List<String> split(String s, String delim) {
	List<String> list = new ArrayList<String>();
	s = trim(s);
	if (s == null) {
	    return list;
	}
	String[] rs = s.split(delim);
	for (int i = 0; i < rs.length; i++) {
	    if (rs[i].trim().length() > 0) {
		list.add(rs[i]);
	    }
	}
	return list;
    }

    public static int occurTimes(String string, String a) {
	int pos = -2;
	int n = 0;

	while (pos != -1) {
	    if (pos == -2) {
		pos = -1;
	    }
	    pos = string.indexOf(a, pos + 1);
	    if (pos != -1) {
		n++;
	    }
	}
	return n;
    }

    public static boolean nullOrSpace(String s) {
	if (s == null) {
	    return true;
	}

	return s.trim().length() == 0;
    }

    public static boolean isNull(Object s) {
	return isBlank(nullValue(s));
    }

    public static boolean isNotNull(Object s) {
	return !isNull(s);
    }

    public static String nullValue(String s) {
	return s == null ? "" : s.trim();
    }

    public static String nullValue(Object s, String defaultValue) {
	return s == null ? defaultValue : s.toString();
    }

    public static String defaultValue(String s, String defaultValue) {
	return isNull(s) ? defaultValue : s;
    }

    public static String nullValue(Object s) {
	return s == null ? "" : s.toString();
    }

    public static String LongValue(Long s) {
	return (s == null) || (s.intValue() <= 0) ? "" : s.toString();
    }

    public static String LongValueZero(Long s) {
	return (s == null) || (s.intValue() <= 0) ? "0" : s.toString();
    }

    public static String nullValue(long s) {
	return s < 0L ? "" : String.valueOf(s);
    }

    public static String nullValue(int s) {
	return s < 0 ? "" : "" + s;
    }

    public static String isSelected(String arg, String selectedValue) {
	return (arg != null) && (arg.equals(selectedValue)) ? "selected" : "";
    }

    public static String isChecked(String arg, String checkedValue) {
	return (arg != null) && (arg.equals(checkedValue)) ? "checked" : "";
    }

    public static String noNull(String s) {
	return s == null ? "" : s;
    }

    public static boolean isBlank(Object s) {
	return (s == null) || (nullValue(s).trim().length() == 0);
    }

    public static boolean isNotBlank(String s) {
	return !isBlank(s);
    }

    public static String upperCaseFirst(String s) {
	return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String lowerCaseFirst(String s) {
	return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    public static boolean in(String url, String[] allUrl) {
	for (int i = 0; i < allUrl.length; i++) {
	    if (allUrl[i].equalsIgnoreCase(url)) {
		return true;
	    }
	}
	return false;
    }

    public static boolean inWithCase(String url, String[] allUrl) {
	for (int i = 0; i < allUrl.length; i++) {
	    if (allUrl[i].equals(url)) {
		return true;
	    }
	}
	return false;
    }

    public static String getChar(int n) {
	return String.valueOf((char) n);
    }

    public static String getCol(int n) {
	return String.valueOf((char) (n + 65));
    }

    public static String escapeSql(String sql) {
	if (sql == null) {
	    return null;
	}

	sql = sql.replaceAll("'", "''");
	sql = sql.replaceAll("_", "\\_");
	sql = sql.replaceAll("%", "\\%");
	sql = sql.replaceAll("\\(", "\\\\(");
	sql = sql.replaceAll("\\)", "\\\\)");
	return sql;
    }

    public static String firstIndent(String text) {
	text = text.trim();
	text = text.replaceAll("&nbsp;", "");
	text = text.replaceAll("　", "");
	text = text.replaceAll("　", "");
	text = text.replaceAll("\\s+", "");
	return "&nbsp;&nbsp;&nbsp;&nbsp;" + text;
    }

    /**
     * 
     * @param password
     * @param intensity
     * @param pwdLength
     * @return 密码强度 1 为低等强度 2为中等强度 3为高等强度
     */
    public static int validPassword(String password, String intensity, int pwdLength) {
	String charGroup[][] = { { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" }, { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }, { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" } };
	int level = 0;
	boolean flagGroup[] = { false, false, false };
	for (int i = 0; i < charGroup.length; i++) {
	    String charSmallGroup[] = charGroup[i];
	    for (int j = 0; j < password.length(); j++) {
		if (flagGroup[i] == true) {
		    continue;
		}
		String str = password.substring(j, j + 1);
		if (inWithCase(str, charSmallGroup)) {
		    level++;
		    flagGroup[i] = true;
		}
	    }
	}
	return level;
    }

    /**
     * 左边补零以满足长度要求
     * 
     * resultLength 最终长度
     */
    public static String addZeroLeft(String arg, int resultLength) {
	if (arg == null)
	    return "";
	String result = arg;
	if (result.length() < resultLength) {
	    for (int i = result.length(); i < resultLength; i++) {
		result = "0" + result;
		if (result.length() == resultLength)
		    break;
	    }
	}
	return result;
    }

    public static int getSameCharCount(String str1, String str2) {
	int count = 0;
	int start = str2.length();
	int i = 0;
	if ((StringUtil.isNotNull(str1))) {
	    while ((i < str1.length()) && (count < 5)) {
		if (str1.indexOf(str2, i) != -1) {
		    count++;
		    i += start;
		} else {
		    i++;
		    if ((count == 0) && (i >= 50)) {
			break;
		    }
		}
	    }
	}
	return count;
    }

    public static boolean startsWith(String source, String prefix) {
	return source.startsWith(prefix);
    }

    public static boolean endsWith(String source, String prefix) {
	return source.endsWith(prefix);
    }

    public static String replaceFirst(String source, String regex, String prefix) {
	return source.replaceFirst(regex, prefix);
    }

    public static String replaceLast(String source, String regex, String prefix) {
	int index = lastIndexOf(source, regex);
	if (index <= 0) {
	    return source;
	}
	StringBuffer buffer = new StringBuffer();
	buffer.append(source.substring(0, index - 1)).append(prefix).append(source.substring(index - 1 + regex.length()));
	return buffer.toString();
    }

    static int lastIndexOf(String source, String str) {
	int i = 0;
	int indexof = 0;
	while (indexof >= 0) {
	    i = indexof + 1;
	    indexof = source.indexOf(str, i);
	}
	return i;
    }

    public static boolean isNumber(String curPage) {
	return RegexpUtil.isMatch(curPage, RegexpUtil.getPattern("^[0-9]+$"));
    }

    public static Object equals(String s1, String s2, Object v1, Object v2) {
	return s1.equals(s2) ? v1 : v2;
    }

    public static String capitalize(String field) {
	return field.substring(0, 1).toUpperCase().concat(field.substring(1));
    }

    public static String escapeHtml(String html) {
	String htmlStr = html;
	String textStr = "";
	try {
	    String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";

	    String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";

	    String regEx_html = "<[^>]+>";
	    String regEx_html1 = "<[^>]+";
	    Pattern p_script = Pattern.compile(regEx_script, 2);
	    Matcher m_script = p_script.matcher(htmlStr);
	    htmlStr = m_script.replaceAll("");

	    Pattern p_style = Pattern.compile(regEx_style, 2);
	    Matcher m_style = p_style.matcher(htmlStr);
	    htmlStr = m_style.replaceAll("");

	    Pattern p_html = Pattern.compile(regEx_html, 2);
	    Matcher m_html = p_html.matcher(htmlStr);
	    htmlStr = m_html.replaceAll("");

	    Pattern p_html1 = Pattern.compile(regEx_html1, 2);
	    Matcher m_html1 = p_html1.matcher(htmlStr);
	    htmlStr = m_html1.replaceAll("");

	    htmlStr = htmlStr.replace("\"", "“");
	    textStr = htmlStr;
	} catch (Exception e) {
	    System.err.println("Html2Text: " + e.getMessage());
	}

	return textStr;
    }

    public static String encodeURI(String s, String enc) throws UnsupportedEncodingException {
	return isBlank(s) ? s : URLEncoder.encode(s, enc);
    }

    public static String decodeURI(String s, String enc) throws UnsupportedEncodingException {
	return isBlank(s) ? s : URLDecoder.decode(s, enc);
    }

    public static String escapeSpecialSign(String condition) {
	String bb = StringUtils.replace(condition, "/", "//");
	bb = StringUtils.replace(bb, "%", "/%");
	bb = StringUtils.replace(bb, "_", "/_");
	return bb;
    }
}
