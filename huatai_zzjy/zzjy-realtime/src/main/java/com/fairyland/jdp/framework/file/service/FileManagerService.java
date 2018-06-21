package com.fairyland.jdp.framework.file.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

import com.fairyland.jdp.framework.file.domain.Attach;

public interface FileManagerService {
	  /**
     * 根据文件路径判断文件是否已经写入
     * 
     * @param absolutePath
     */
    public boolean isExistFileByPath(String absolutePath) throws IOException;

    /**
     * 更新文件信息
     * 
     * @param absolutePath
     *            上传文件地址
     * @param in
     *            临时文件输入流
     */
    public Map<String, Object> updateFile(Map<String, Object> paraMap) throws IOException;
    
    /**
     * 手机端上传文件
     * @param paraMap
     * @return
     * @throws IOException
     */
    public Map<String, Object> updateFileMobile (Map<String, Object> paraMap) throws IOException;

    /**
     * 根据功能id和功能type获取附件list
     * @param map
     * functionId functionType
     * @return
     */
    public List<Attach> getAttachListByIdAndType(Map<String, Object> map);
    
    /**
     * 
     * @param absolutePath
     *            要保存到的本地地址
     * @param file
     *            临时文件
     * 
     */
    public void writeFile(String absolutePath, File file) throws IOException;

    /**
     * 
     * @param absolutePath
     *            要保持到的本地地址
     * @param in
     *            临时文件输入流
     */
    public void writeFile(String absolutePath, InputStream in) throws IOException;

    /**
     * 
     * @param out
     *            保存文件的输入流
     * @param in
     *            临时文件输入流
     */
    public void writeFile(OutputStream out, InputStream in) throws IOException;

    /**
     * @param remotePath
     *            远程文件地址
     * @param localPath
     *            下载地址
     */
    public void readFile(String remotePath, String localPath) throws IOException;

    /**
     * 
     * @param remotePath
     *            远程文件地址
     * @param out
     *            下载文件的输出流
     */
    public void readFile(String remotePath, OutputStream out) throws IOException;

    /**
     * 根据文件类型模糊查询保存在数据库中的信息
     * 
     * @param fileType
     *            文件类型
     * @throws IOException
     */
    public Map<String, Object> readFileByFileType(String fileType) throws IOException;

    public InputStream readFile(String remotePath) throws IOException;
    
    public Blob getInputStream(String remotePath) throws IOException;
    
    public void addAttach(Attach attach);
    
    public void delAttach(Long attachId);
    
    public void delAttachByFunTypeAndFunId (String functionType, String functionId);
    
    public Attach getAttachById(Long attachId);
    
    public void updateAttach(String fileIds,String functionId);
    
    /**
     * 获取附件和备注
     * @param map
     * @return
     */
    public List<Attach> getRemarkAttachList(Map<String, Object> map);
    
}
