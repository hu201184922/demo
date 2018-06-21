package com.huatai.web.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.fairyland.jdp.framework.util.PropsUtil;

public class CephUtil {
//	private static String accessKey = "accesskey";
//	private static String secretKey = "secretkey";
//	private static String endpoint = "http://192.168.88.144:7000";
//	private static String accessKey = "NKQC99N0448DIUMFXQXB";
//	private static String secretKey = "a7zBAgjJmnFxY1qusPKiVwpwGO85cQ7eNghk4HKx";
//	private static String endpoint = "http://10.100.251.3:20121";
	
//	private static String defaultBucketName = "default";
	private static String accessKey = PropsUtil.get("ceph.accessKey");
	private static String secretKey = PropsUtil.get("ceph.secretKey");
//	private static String endpoint = "http://10.100.242.7:9000";
//	private static String endpoint = "http://10.100.252.182:31002/ceph";
//	private static String endpoint = "http://123.127.120.126:31003/ceph";
//	private static String endpoint = "http://127.0.0.1/ceph";
	private static String endpoint = PropsUtil.get("ceph.endpoint");
	public static String defaultBucketName = "ceph";
	private static AmazonS3 connection;
	public static AmazonS3 getConnection(){
		if(connection == null){
			AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

			ClientConfiguration clientConfig = new ClientConfiguration();
			clientConfig.setProtocol(Protocol.HTTP);
			
			AmazonS3 conn = new AmazonS3Client(credentials,clientConfig);
			conn.setEndpoint(endpoint);
			conn.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).build());
			connection = conn;
		}
		return connection;
	}
	public static void test(){
		AmazonS3 conn = getConnection();
//		conn.createBucket(bucketName);
//		List<Bucket> buckets = conn.listBuckets();
//		for (Bucket bucket : buckets) {
//		        System.out.println(bucket.getName() + "\t" +
//		                StringUtils.fromDate(bucket.getCreationDate()));
//		}
		conn.setObjectAcl(defaultBucketName, "1.txt", CannedAccessControlList.PublicRead);
//		ObjectListing listing = conn.listObjects("testbk");
//		System.out.println(listing);
	}
	public static void createBucket(String bucketName){
		AmazonS3 conn = getConnection();
		conn.createBucket(bucketName);
	}
//	public static void deleteBucket(String bucketName){
//		AmazonS3 conn = getConnection();
//		conn.deleteBucket(bucketName);
//	}
	public static void deleteFile(String bucketName,String key){
		if(bucketName==null)
			bucketName = defaultBucketName;
		AmazonS3 conn = getConnection();
		conn.deleteObject(bucketName,key);
	}
	public static List<String> listBuckets(){
		AmazonS3 conn = getConnection();
		List<String> list = new ArrayList<String>();
		List<Bucket> buckets = conn.listBuckets();
		for (Bucket bucket : buckets) {
			list.add(bucket.getName());
		}
		return list;
	}
	public static boolean isBucketExists(String bucketName){
		AmazonS3 conn = getConnection();
		return conn.doesBucketExist(bucketName);
	}
	public static boolean isFileExists(String bucketName,String key){
		if(bucketName==null)
			bucketName = defaultBucketName;
		AmazonS3 conn = getConnection();
		return conn.doesObjectExist(bucketName, key);
	}
	public static void uploadFile(MultipartFile file,String bucketName,String key) throws FileNotFoundException, IOException{
		uploadFile(file.getInputStream(),bucketName,key,file.getSize());
	}
	public static void uploadFile(InputStream in,String bucketName,String key,long contentLength) throws FileNotFoundException{
		if(bucketName==null)
			bucketName = defaultBucketName;
		AmazonS3 conn = getConnection();
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(contentLength);
//		Map<String,String> userMetadata = new HashMap<String,String>();
//		userMetadata.put("filename", filename);
//		metadata.setUserMetadata(userMetadata);
		if(!isBucketExists(bucketName))
			createBucket(bucketName);
		conn.putObject(bucketName, key, in, metadata);
		conn.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
	}
	public static void uploadFile(String filePath,String bucketName,String key) throws FileNotFoundException{
		BufferedInputStream input = new BufferedInputStream(new FileInputStream(filePath));
		File file = new File(filePath);
//		InputStream input = new FileInputStream(file);
		uploadFile(input,bucketName,key,file.length());
	}
	public static void downloadFile(String localFilePath,String bucketName,String key){
		if(bucketName==null)
			bucketName = defaultBucketName;
		AmazonS3 conn = getConnection();
		ObjectMetadata metadata = conn.getObject(new GetObjectRequest(bucketName, key),
		        new File(localFilePath));
//		System.out.println(metadata.getUserMetaDataOf("filename"));
	}
	public static String generateDownloadUrl(String bucketName,String key){
		if(bucketName==null)
			bucketName = defaultBucketName;
//		AmazonS3 conn = getConnection();
//		GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key);
//		request.setExpiration(DateUtil.getNextDate());
//		URL url = conn.generatePresignedUrl(request);
//		System.out.println(url.toString());
//		return url.toString();
		String url = endpoint+"/"+bucketName+"/"+key;
		return url;
	}
	public static List<String> listObj(String bucketName){
		if(bucketName==null)
			bucketName = defaultBucketName;
		AmazonS3 conn = getConnection();
		List<String> fileList = new ArrayList<String>();
		ObjectListing objects = conn.listObjects(bucketName);
		do {
	        for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
	        	fileList.add(objectSummary.getKey());
	        }
	        objects = conn.listNextBatchOfObjects(objects);
		} while (objects.isTruncated());
		return fileList;
	}
//	@RequestMapping("/ceph/{key:[a-zA-Z0-9\\.]+}")
//	public void ceph(HttpServletRequest request,HttpServletResponse response,@PathVariable("key") String key) throws Exception {
//		String cephServer = PropsUtil.get("ceph.endpoint")+"/"+CephUtil.defaultBucketName+"/";
//		String url = cephServer+key;
//		Object[] obj = getInputFromUrl(url);
//		InputStream in = (InputStream)obj[0];
//		long length = (Long)obj[1];
//		transfer(request,response,"application/octet-stream",key,in,length);
//	}
	public static InputStream getInputFromCeph(String bucketName,String key) throws IOException{
		if(bucketName==null)
			bucketName = defaultBucketName;
		String cephServer = PropsUtil.get("ceph.endpoint")+"/"+bucketName+"/";
		String urlKey = URLEncoder.encode(key, "utf-8");
		String url = cephServer+urlKey;
		Object[] obj = getInputFromUrl(url);
		InputStream in = (InputStream)obj[0];
		return in;
	}
	public static Object[] getInputFromUrl(String urlStr) throws IOException{  
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
                //设置超时间为3秒  
        conn.setConnectTimeout(3*1000);  
        //防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
        long length=conn.getContentLengthLong(); 
        //得到输入流  
        InputStream inputStream = conn.getInputStream();
        BufferedInputStream in = new BufferedInputStream(inputStream);
        Object[] obj = new Object[2];
        obj[0] = inputStream;
        obj[1] = length;
        return obj;
    }  
  
    public static void transfer(HttpServletRequest request,  
            HttpServletResponse response, String contentType,  
            String realName,InputStream in,long length) throws Exception {  
//        response.setContentType("text/html;charset=UTF-8"); 
//    	response.setContentType("text/html;charset=UTF-8"); 
        request.setCharacterEncoding("UTF-8");  
        BufferedOutputStream bos = null;  
  
  
        response.setContentType(contentType);  
        response.setHeader("Content-disposition", "attachment; filename="  
                + new String(realName.getBytes("utf-8"), "ISO8859-1"));  
        response.setHeader("Content-Length", String.valueOf(length));  
  
        bos = new BufferedOutputStream(response.getOutputStream());  
        byte[] buff = new byte[2048];  
        int bytesRead;  
        while (-1 != (bytesRead = in.read(buff, 0, buff.length))) {  
            bos.write(buff, 0, bytesRead);  
        }  
        in.close();
        bos.close();
    }  
	public static void main(String[] args) throws Exception {
//		System.setProperty("http.proxySet", "true");    //设置使用网络代理  
//		System.setProperty("http.proxyHost", "10.100.11.1");  //设置代理服务器地址  
//		System.setProperty("http.proxyPort", "8080");
//		test();
//		uploadFile("C:/Users/jinchenfly/Desktop/1.txt","default","2.txt");
//		System.out.println(generateDownloadUrl(null, "testVideo.mp4"));
//		downloadFile("C:/Users/jinchenfly/Desktop/2.txt","","1.txt");
//		Object[] obj = getInputFromUrl("http://10.100.251.3:3308/ceph/用户更换10.mp4");
//		System.out.println(obj[0]);
		List<String> list = listObj("uploadt");
		for (String string : list) {
			System.out.println(string);
		}
//		List<String> list = listBuckets();
//		for (String string : list) {
//			System.out.println(string);
//		}
//		System.out.println(generateDownloadUrl("2.txt"));
//		System.out.println(isBucketExists("default"));
//		System.out.println(isBucketExists("testbk"));
	}
}
