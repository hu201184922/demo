package com.fairyland.jdp.orm.mybatis.dialect;

import java.util.regex.Matcher;

import com.fairyland.jdp.orm.util.regexp.RegexpUtil;


public class DialectUtil {

	private static String[] keywords = new String[] { "select", "top", "count", "from", "order", "by", "asc", "desc",
			"group", "where", "and" };

	private static String keywordRegexp = null;
	static {
		StringBuffer buffer = new StringBuffer("^(");
		for (int i = 0; i < keywords.length; i++) {
			String keyword = keywords[i];
			buffer.append("(" + keyword + ")");
			if (i != keywords.length - 1) {
				buffer.append("|");
			}
		}
		buffer.append(")$");
		keywordRegexp = buffer.toString();
	};

	private static RegexpUtil.ReplaceCallBack antonymReplaceCallBack = new RegexpUtil.AbstractReplaceCallBack() {

		@Override
		public String doReplace(String text, int index, Matcher matcher) {
			if (RegexpUtil.find(text.toLowerCase(), "^((asc)|(desc))$")) {
				return text.toLowerCase().equals("asc") ? "desc" : "asc";
			}
			return text;
		}

	};

	public static String antonymOver(String over) {
		return RegexpUtil.replace(over, "[a-zA-Z0-9_]{1,}", antonymReplaceCallBack);
	}

	public static String pretty(String sql) {
		return RegexpUtil.replace(sql, "[a-zA-Z0-9_]{1,}", new RegexpUtil.AbstractReplaceCallBack() {

			@Override
			public String doReplace(String text, int index, Matcher matcher) {
				if (RegexpUtil.find(text.toLowerCase(), keywordRegexp)) {
					return text.toUpperCase();
				}
				return text;
			}

		});
	}

}
