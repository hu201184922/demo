package com.fairyland.jdp.framework.file.mapper;

import java.util.List;
import java.util.Map;

import com.fairyland.jdp.framework.file.domain.Attach;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;

@MyBatisRepository
public interface FileManagerMapper {
    
    public void addAttach(Attach attach);

    public void delAttach(Long attachId);
    
    public void delAttachByFunTypeAndFunId (Map<String, Object> map);
    
    public void updateAttach(Map<String, Object> map);
    /**
     * 根据功能id和功能type获取附件list
     * @param map
     * @return
     */
    public List<Attach> getAttachListByIdAndType(Map<String, Object> map);
    
    public Attach getAttachById(Long attachId);
    
    /**
     * 获取附件和备注
     * @param map
     * @return
     */
    public List<Attach> getRemarkAttachList(Map<String, Object> map);
}
