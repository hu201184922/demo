package com.fairyland.jdp.orm.util.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptHelp {
	private static Logger log = LoggerFactory.getLogger(ScriptHelp.class);
    private static final String ENCODE = "UTF-8";
    public static List<String> fileList = new ArrayList<String>();

    public static void batchCompressJS(String baseScriptPath) {
	List<String> fileList = getListFiles(baseScriptPath, "js", true);

	for (Iterator<String> i = fileList.iterator(); i.hasNext();) {
	    String fromFile = (String) i.next();
	    String toFile = StringUtil.replaceLast(fromFile, ".js", ".min").concat(".js");
	    compressSingleJS(fromFile, toFile);
	}
    }

    public static void compressSingleJS(String fromFile, String toFile) {
	String content = readFile(fromFile);
	writeFile(compressJS(content), toFile);
    }

    public static void mergeJS(List<String> fileList, String toFile, Boolean isCompress) {
	String content = "";
	for (Iterator<String> i = fileList.iterator(); i.hasNext();) {
	    String fromFile = (String) i.next();
	    content = content + readFile(fromFile);
	}
	if (isCompress.booleanValue())
	    writeFile(compressJS(content), toFile);
	else
	    writeFile(content, toFile);
    }

    public static String compressJS(String content) {
	content = content.replaceAll("([^\"])\\/\\*([^\\*^\\/]*|[\\*^\\/*]*[^\\**\\/]*)*\\*\\/", "$1").replaceAll("\\/\\/[^\\n]*", "");

	if (content.indexOf("/*") == 0)
	    content = content.substring(content.indexOf("*/") + 2, content.length());
	content = content.replaceAll("\\s{2,}", " ").replaceAll("\r\n", "").replaceAll("\n", "");

	return content;
    }

    public static void writeFile(String content, String comspec) {
	try {
	    FileOutputStream fos = new FileOutputStream(comspec);
	    Writer out = new OutputStreamWriter(fos, ENCODE);
	    out.write(content);
	    log.debug("成功输出文件：" + comspec);
	    out.close();
	    fos.close();
	} catch (IOException e) {
	    log.debug("写文件操作出错！");
	    log.error(e.getMessage());
	}
    }

    public static String readFile(String filePath) {
	StringBuilder sb = new StringBuilder();
	try {
	    File file = new File(filePath);
	    InputStreamReader read = new InputStreamReader(new FileInputStream(file), ENCODE);
	    BufferedReader reader = new BufferedReader(read);
	    String s = reader.readLine();
	    while (s != null) {
		sb.append(s);
		sb.append("\r\n");
		s = reader.readLine();
	    }
	    reader.close();
	} catch (IOException e) {
	    log.error(e.getMessage());
	}
	return sb.toString();
    }

    public static List<String> getListFiles(String path, String suffix, boolean isdepth) {
	File file = new File(path);
	return listFile(path, file, suffix, isdepth);
    }

    public static List<String> listFile(String path, File f, String suffix, boolean isdepth) {
	String temp = path.replaceAll("/", "\\\\");
	if (((f.isDirectory()) && (isdepth)) || (temp.equals(f.getAbsolutePath()))) {
	    File[] t = f.listFiles();
	    for (int i = 0; i < t.length; i++)
		listFile(path, t[i], suffix, isdepth);
	} else {
	    addFilePath(f, suffix, isdepth);
	}
	return fileList;
    }

    public static List<String> addFilePath(File f, String suffix, boolean isdepth) {
	String filePath = f.getAbsolutePath().replaceAll("\\\\", "/");
	if (suffix != null) {
	    int begIndex = filePath.lastIndexOf(".");
	    String tempsuffix = "";

	    if (begIndex != -1) {
		tempsuffix = filePath.substring(begIndex + 1, filePath.length());
	    }
	    if (tempsuffix.equals(suffix))
		fileList.add(filePath);
	} else {
	    fileList.add(filePath);
	}
	return fileList;
    }
}