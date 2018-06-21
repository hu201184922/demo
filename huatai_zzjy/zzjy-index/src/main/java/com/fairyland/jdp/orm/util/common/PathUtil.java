package com.fairyland.jdp.orm.util.common;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathUtil {
	private static Logger log = LoggerFactory.getLogger(PathUtil.class);
    private static String WEBCLASSES_PATH = null;
    private static String WEBINF_PATH = null;
    private static String WEBROOT_PATH = null;

    static {
	StringBuffer path = new StringBuffer(PathUtil.getClassLocationURL(PathUtil.class).getPath());
	File file = new File(path.replace(path.lastIndexOf("classes"), path.length(), "").append("classes").toString());
	PathUtil.WEBCLASSES_PATH = file.getPath();
	PathUtil.WEBINF_PATH = file.getParentFile().getPath();
	PathUtil.WEBROOT_PATH = file.getParentFile().getParentFile().getPath();
	//扫描WebRoot下有没有WEB-INF目录,以适应jetty
	File webroot = new File(WEBROOT_PATH);
	if(webroot.list(new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			if(name.equalsIgnoreCase("web-inf")){
				return true;
			}
			return false;
		}
	}).length==0){
		File newWebRoot = TextSearchFile.searchOneFileUpper(webroot, "web-inf", 2);
		if(newWebRoot!=null){
			PathUtil.WEBINF_PATH = newWebRoot.getPath();
			PathUtil.WEBROOT_PATH = newWebRoot.getParentFile().getPath();
		}
	}
//	WEBCLASSES_PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//	WEBINF_PATH = WEBCLASSES_PATH.substring(0, WEBCLASSES_PATH.lastIndexOf("classes"));
//	WEBROOT_PATH = WEBCLASSES_PATH.substring(0, WEBCLASSES_PATH.lastIndexOf("WEB-INF"));
    }

    public static String getFileName(String path) {
	String[] ress = path.split("/");
	return ress[(ress.length - 1)];
    }

    public static String getFilePath(String path, String fileName) {
	return path.replaceAll("/" + fileName, "").replaceFirst("/", "");
    }

    public static String getFilePostfix(String uri) {
	String[] ress = uri.split("\\.");
	return ress.length < 2 ? "" : ress[(ress.length - 1)];
    }

    public static String getWebClassesPath() {
	return WEBCLASSES_PATH;
    }

    public static String getWebInfPath() {
	return WEBINF_PATH;
    }

    public static String getWebRoot() {
	return WEBROOT_PATH;
    }

    public static String getPathFromClass(Class<?> cls) {
	String path = null;
	if (cls == null) {
	    throw new NullPointerException();
	}
	URL url = getClassLocationURL(cls);
	if (url != null) {
	    path = url.getPath();
	    if ("jar".equalsIgnoreCase(url.getProtocol())) {
		try {
		    path = new URL(path).getPath();
		} catch (MalformedURLException localMalformedURLException) {
		}
		int location = path.indexOf("!/");
		if (location != -1) {
		    path = path.substring(0, location);
		}
	    }
	    File file = new File(path);
	    try {
		path = file.getCanonicalPath();
	    } catch (IOException e) {
		log.error(e.getMessage());
	    }
	}
	return path;
    }

    public static String getFullPathRelateClass(String relatedPath, Class<?> cls) throws IOException {
	String path = null;
	if (relatedPath == null) {
	    throw new NullPointerException();
	}
	String clsPath = getPathFromClass(cls);
	File clsFile = new File(clsPath);
	String tempPath = clsFile.getParent() + File.separator + relatedPath;
	File file = new File(tempPath);
	path = file.getCanonicalPath();
	return path;
    }

    private static URL getClassLocationURL(Class<?> cls) {
	if (cls == null)
	    throw new IllegalArgumentException("null input: cls");
	URL result = null;
	String clsAsResource = cls.getName().replace('.', '/').concat(".class");
	ProtectionDomain pd = cls.getProtectionDomain();
	if (pd != null) {
	    CodeSource cs = pd.getCodeSource();
	    if (cs != null)
		result = cs.getLocation();
	    if ((result != null) && ("file".equals(result.getProtocol())))
		try {
		    if ((result.toExternalForm().endsWith(".jar")) || (result.toExternalForm().endsWith(".zip")))
			result = new URL("jar:".concat(result.toExternalForm()).concat("!/").concat(clsAsResource));
		    else if (new File(result.getFile()).isDirectory())
			result = new URL(result, clsAsResource);
		} catch (MalformedURLException localMalformedURLException) {
		}
	}
	if (result == null) {
	    ClassLoader clsLoader = cls.getClassLoader();
	    result = clsLoader != null ? clsLoader.getResource(clsAsResource) : ClassLoader.getSystemResource(clsAsResource);
	}
	return result;
    }

    public static String getFilePath(String path) {
	StringBuffer result = new StringBuffer();
	if (path.endsWith(".xml"))
	    result.append(getWebClassesPath()).append("/").append(path.replace(".xml", "").replace('.', '/').concat(".xml"));
	else if (path.endsWith(".class"))
	    result.append(getWebClassesPath()).append("/").append(path.replace(".class", "").replace('.', '/').concat(".class"));
	else if ((path.endsWith(".js")) || (path.endsWith(".css")) || (path.endsWith(".jpg")))
	    result.append(getWebRoot()).append("/").append(path);
	else
	    result.append(getWebRoot()).append("/").append(path);
	return result.toString();
    }

    public static String getClassFilePath(Class<?> classes) {
	return classes.getName().replaceAll("\\.", "/").concat(".class");
    }

    public static String getPageTemplateDir(Class<?> classes) {
	return "/".concat(getClassFilePath(classes)).replaceAll("/".concat(classes.getSimpleName()).concat("\\.class$"), "");
    }

    public static String[] getPackagePath(String packages) {
	if (packages == null)
	    return null;
	List<String> pathPrefixes = new ArrayList<String>();
	String pathPrefix;
	for (StringTokenizer st = new StringTokenizer(packages, ", \n\t"); st.hasMoreTokens(); pathPrefixes.add(pathPrefix)) {
	    pathPrefix = st.nextToken().replace('.', '/');
	    if (!pathPrefix.endsWith("/"))
		pathPrefix = pathPrefix + "/";
	}
	return (String[]) pathPrefixes.toArray(new String[pathPrefixes.size()]);
    }

    public static String getActionConfigPath(String defaultActionConfig) {
	return getWebClassesPath() + "/" + defaultActionConfig.replace(".xml", "").replace('.', '/').concat(".xml");
    }

    public static String getTmpdir() {
	return System.getProperty("java.io.tmpdir");
    }
}