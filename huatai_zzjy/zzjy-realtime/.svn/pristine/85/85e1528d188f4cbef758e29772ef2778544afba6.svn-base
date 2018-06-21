package com.fairyland.jdp.framework.file.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fairyland.jdp.framework.file.domain.Attach;
import com.fairyland.jdp.framework.file.mapper.FileManagerMapper;

@Repository
public class FileManagerDao {
	@Autowired
	private FileManagerMapper fileMapper;
    
    public void addAttach(Attach attach) {
    	this.fileMapper.addAttach(attach);
    }
    
    public void delAttach(Long attachId) {
    	this.fileMapper.delAttach(attachId);
    }
    
    public void delAttachByFunTypeAndFunId (Map<String, Object> map) {
    	this.fileMapper.delAttachByFunTypeAndFunId(map);
    }
    
    public void updateAttach(Map<String, Object> map) {
    	this.fileMapper.updateAttach(map);
    }
    /**
     * 根据功能id和功能type获取附件list
     * @param map
     * @return
     */
    public List<Attach> getAttachListByIdAndType(Map<String, Object> map){
    	return fileMapper.getAttachListByIdAndType(map);
    }
    
    public Attach getAttachById(Long attachId){
    	return fileMapper.getAttachById(attachId);
    }
    
    /**
     * 获取附件和备注
     * @param map
     * @return
     */
    public List<Attach> getRemarkAttachList(Map<String, Object> map){
    	return fileMapper.getRemarkAttachList(map);
    }
}
