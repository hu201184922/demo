package com.fairyland.jdp.framework.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;

import com.fairyland.jdp.orm.util.common.StringUtil;
import com.fairyland.jdp.orm.util.regexp.RegexpUtil;

public class FileUtil {
	private static final Log logger = LogFactory.getLog(FileUtil.class);

	public static String readFile(File file) {
		return readFile(file, "UTF-8");
	}

	public static String readFile(File file, String charset) {
		String line = null;
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), charset);
			BufferedReader reader = new BufferedReader(read);
			StringBuffer buf = new StringBuffer();

			while ((line = reader.readLine()) != null) {
				buf.append(line + "\n");
			}
			reader.close();
			return buf.toString();
		} catch (FileNotFoundException ex) {
			throw new RuntimeException("没有找到文件:" + file.getAbsolutePath());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}

	public static String readFile(String file) {
		return readFile(new File(file));
	}

	public static String readFile(InputStream in) throws IOException {
		return readFile(in, "UTF-8");
	}

	public static String readFile(InputStream in, String charset)
			throws IOException {
		BufferedReader reader = null;
		StringBuffer html = new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(in, charset));
			String line;
			while ((line = reader.readLine()) != null) {
				html.append(line).append(System.getProperty("line.separator"));
			}
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(reader);
		}
		return html.toString();
	}

	public static void writeFile(String content, String file) {
		try {
			File f = new File(file).getParentFile();
			if (!f.exists()) {
				f.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(file);
			Writer out = new OutputStreamWriter(fos, "utf-8");

			out.flush();
			out.write(content);
			out.close();
		} catch (IOException ex) {
			logger.error(ex);
			throw new RuntimeException(ex);
		}
		logger.debug("Write File:" + file);
	}

	public static void writeFile(byte[] content, String file) {
		try {
			File f = new File(file).getParentFile();
			if (!f.exists()) {
				f.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(content, 0, content.length);
			fos.flush();
			fos.close();
		} catch (IOException ex) {
			logger.error(ex);
			throw new RuntimeException(ex);
		}
		logger.debug("Write File:" + file);
	}

	public static void writeW3cDocument(Document doc, String fileName)
			throws Exception {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult("c:/doc.xml");
		transformer.transform(source, result);
	}

	public static InputStream getResource(String fileName) {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		return classLoader.getResourceAsStream(fileName);
	}

	public static String rename(String path, String newName) {
		path = path.replaceAll("\\\\", "/");
		String folder = path.substring(0, path.lastIndexOf("/") + 1);
		String extName = getFileExtName(path);
		return folder + newName + "." + extName;
	}

	public static String getFileExtName(String fileName) {
		int pos = fileName.lastIndexOf(".");
		if (pos == -1) {
			return "NO_EXT_NAME";
		}
		return fileName.substring(pos + 1);
	}

	public static String getFileName(String path) {
		path = path.replaceAll("\\\\", "/");
		String fileName = path.substring(path.lastIndexOf("/") + 1);
		return fileName;
	}

	public static String getFileBaseName(String path) {
		path = path.replaceAll("\\\\", "/");
		String fileName = path.substring(path.lastIndexOf("/") + 1);
		int pos = fileName.lastIndexOf(".");
		if (pos == -1) {
			return fileName;
		}
		return fileName.substring(0, pos);
	}

	public static File rename(File file) {
		String path = file.getParent();
		String name = file.getName();
		int pos = name.lastIndexOf(".");
		String extName = getFileExtName(name);
		if (pos > 0) {
			name = name.substring(0, pos);
		}
		File newFile = new File(path + "/" + name + "-s." + extName);
		return newFile;
	}

	public static File createFolder(String path) {
		path = path.replaceAll("\\\\", "/");
		int n = path.lastIndexOf("/");
		int m = path.lastIndexOf(".");
		if (m > n) {
			path = path.substring(0, n);
		}
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file;
	}

	public static void createFolder(File file) {
		if (!file.isDirectory()) {
			createFolder(file.getParentFile());
		}
		if (!file.exists())
			file.mkdirs();
	}

	public static File createFile(String filePath) {
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		return file;
	}

	public static boolean exists(String folderName) {
		File folder = new File(folderName);

		return folder.exists();
	}

	public static File[] listFolders(String folderName) {
		File folder = new File(folderName);
		if (!folder.exists()) {
			throw new RuntimeException("(目录不存在。)folder [" + folder
					+ "]not exist。");
		}
		File[] files = folder.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
		return files;
	}

	/**
	 * 列出文件夹下面所有扩展名相同的文件名.jpg等
	 * 
	 * @param folderName
	 *            文件名
	 * @param extName
	 *            扩展名
	 * @return
	 */
	public static File[] listFiles(String folderName, final String extName) {
		File folder = new File(folderName);
		if (!folder.exists()) {
			throw new RuntimeException("(目录不存在。)folder [" + folder
					+ "]not exist。");
		}
		File[] files = folder.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().toLowerCase()
						.lastIndexOf("." + extName.toLowerCase()) != -1;
			}
		});
		return files;
	}

	public static void gbk2utf8(File file, String[] exts)
			throws UnsupportedEncodingException {
		if (file.isFile()) {
			for (int i = 0; i < exts.length; i++)
				if (file.getAbsolutePath().toLowerCase().endsWith(exts[i])) {
					String gbkContent = readFile(file);
					byte[] utf8Content = gbk2utf8(gbkContent);

					writeFile(utf8Content, file.getAbsolutePath());
					break;
				}
		} else {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++)
				gbk2utf8(files[i], exts);
		}
	}

	public static void compressionGZIP(String oldPath, String newPath) {
		try {
			FileInputStream fin = new FileInputStream(oldPath);

			createFolder(RegexpUtil.replace(newPath,
					"(([a-zA-Z0-9]|([(]|[)]|[ ]))+)[.]([a-zA-Z0-9]+)$", ""));
			logger.debug("创建文件 ："
					+ RegexpUtil.replace(newPath,
							"(([a-zA-Z0-9]|([(]|[)]|[ ]))+)[.]([a-zA-Z0-9]+)$",
							"") + "|" + newPath);
			FileOutputStream fout = new FileOutputStream(newPath);

			GZIPOutputStream gzout = new GZIPOutputStream(fout);
			byte[] buf = new byte[1024];
			int num;
			while ((num = fin.read(buf)) != -1) {
				gzout.write(buf, 0, num);
			}
			gzout.close();
			fout.close();
			fin.close();
		} catch (IOException e) {
			logger.debug(e);
		}
	}

	public static void extractGzip(String fileUrl) {
		try {
			FileInputStream fin = new FileInputStream(fileUrl);

			GZIPInputStream gzin = new GZIPInputStream(fin);

			FileOutputStream fout = new FileOutputStream(
					StringUtil.replaceLast(fileUrl, ".gz", ""));
			byte[] buf = new byte[1024];
			int num;
			while ((num = gzin.read(buf, 0, buf.length)) != -1) {
				fout.write(buf, 0, num);
			}
			gzin.close();
			fout.close();
			fin.close();
		} catch (IOException e) {
			logger.debug(e);
		}
	}

	public static byte[] gbk2utf8(String chenese)
			throws UnsupportedEncodingException {
		char[] c = chenese.toCharArray();
		byte[] fullByte = new byte[3 * c.length];
		for (int i = 0; i < c.length; i++) {
			int m = c[i];
			String word = Integer.toBinaryString(m);

			StringBuffer sb = new StringBuffer();
			int len = 16 - word.length();
			for (int j = 0; j < len; j++) {
				sb.append("0");
			}
			sb.append(word);
			sb.insert(0, "1110");
			sb.insert(8, "10");
			sb.insert(16, "10");

			String s1 = sb.substring(0, 8);
			String s2 = sb.substring(8, 16);
			String s3 = sb.substring(16);

			byte b0 = Integer.valueOf(s1, 2).byteValue();
			byte b1 = Integer.valueOf(s2, 2).byteValue();
			byte b2 = Integer.valueOf(s3, 2).byteValue();
			byte[] bf = new byte[3];
			bf[0] = b0;
			fullByte[(i * 3)] = bf[0];
			bf[1] = b1;
			fullByte[(i * 3 + 1)] = bf[1];
			bf[2] = b2;
			fullByte[(i * 3 + 2)] = bf[2];
		}

		return fullByte;
	}

	public static boolean moveFile(File sourceFile, File targetFile) {
		if (sourceFile.isFile()) {
			return moveOnlyFile(sourceFile, targetFile);
		}
		File[] files = sourceFile.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				String newName = targetFile.getAbsolutePath() + "/"
						+ files[i].getName();
				moveFile(files[i], new File(newName));
			}
		}

		sourceFile.delete();
		return true;
	}

	private static boolean moveOnlyFile(File sourceFile, File targetFile) {
		targetFile.getParentFile().mkdirs();
		if (targetFile.exists())
			targetFile.delete();
		logger.debug("Move File. sourceFile:" + sourceFile
				+ ", targetFile:" + targetFile.getPath());
		boolean flag = sourceFile.renameTo(targetFile);
		if (!flag) {
			try {
				copyFile(sourceFile, targetFile);

				if (sourceFile.exists())
					logger.debug("delete file:" + sourceFile + ":"
							+ sourceFile.delete());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return false;
	}

	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		if (sourceFile.isFile()) {
			copyOnlyFile(sourceFile, targetFile);
		} else {
			File[] files = sourceFile.listFiles();
			if (files != null)
				for (int i = 0; i < files.length; i++) {
					String newName = targetFile.getAbsolutePath() + "/"
							+ files[i].getName();
					copyFile(files[i], new File(newName));
				}
		}
	}

	private static void copyOnlyFile(File sourceFile, File targetFile)
			throws IOException {
		logger.debug("copy from:" + sourceFile);
		logger.debug("copy to:" + targetFile);
		targetFile.getParentFile().mkdirs();

		FileInputStream fis = new FileInputStream(sourceFile);
		FileOutputStream fos = new FileOutputStream(targetFile);

		byte[] buf = new byte[1024];
		int n = 0;
		while ((n = fis.read(buf)) != -1) {
			fos.write(buf, 0, n);
		}

		fis.close();
		fos.flush();
		fos.close();
	}

	public static URL generate(URL url, File file) throws Exception {
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				httpConn.getInputStream()));
		StringBuffer buf = new StringBuffer();
		String line;
		while ((line = in.readLine()) != null) {
			buf.append("\n" + line);
		}
		in.close();
		FileWriter fw = new FileWriter(file);
		fw.write(buf.toString());
		fw.flush();
		fw.close();
		return url;
	}

	public static void delFile(File file) {
		if (file.exists())
			if (file.isFile()) {
				delOnlyFile(file);
			} else {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					delFile(files[i]);
				}
				file.delete();
			}
	}

	private static void delOnlyFile(File file) {
		if (file.exists())
			file.delete();
	}

	public static void delFile(String filePath) {
		File file = new File(filePath);
		logger.debug(filePath);
		delFile(file);
	}

	public static String loadResponseByUrl(String urlName) throws IOException {
		URL url = new URL(urlName);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection
				.addRequestProperty("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.0.3705)");
		connection.addRequestProperty("Cache-Control", "no-cache");
		connection.addRequestProperty("Accept", "*/*");
		connection.addRequestProperty("Connection", "Keep-Alive");
		connection.connect();
		connection.disconnect();
		return connection.getURL().toString();
	}

	public static void replaceInFolder(File file, String oldStr, String newStr) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++)
				replaceInFolder(files[i], oldStr, newStr);
		} else {
			String content = readFile(file);
			if ((file.getName().endsWith(".html"))
					&& (content.indexOf(oldStr) != -1)) {
				writeFile(content, file.getAbsolutePath());
			}
		}
	}

	public static Date lastModified(String filePath) {
		File file = new File(filePath);
		return new Date(file.lastModified());
	}

	public static String getExtension(File file) {
		return getExtension(file.getName());
	}

	public static String getExtension(String fileName) {
		String[] ress = fileName.split("\\.");
		return ress.length < 2 ? "" : ress[(ress.length - 1)];
	}

	public static void writeFile(InputStream in, String filePath)
			throws IOException {
		OutputStream out = new FileOutputStream(getFile(filePath));
		byte[] buffer = new byte[1024];
		while (in.read(buffer) != -1)
			out.write(buffer);
		out.close();
		in.close();
	}

	public static File getFile(String pathname) {
		createFolder(pathname);
		File file = new File(pathname);
		return file;
	}

	public void writeFile(OutputStream out, InputStream in) throws IOException {
		byte[] buffer = new byte[1024];
		while (in.read(buffer) != -1)
			out.write(buffer);
		out.close();
		in.close();
	}
}
