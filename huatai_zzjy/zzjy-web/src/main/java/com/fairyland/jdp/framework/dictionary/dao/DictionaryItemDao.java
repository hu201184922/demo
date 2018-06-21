package com.fairyland.jdp.framework.dictionary.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;


public interface DictionaryItemDao extends CrudRepository<DictionaryItem, Long> ,JpaSpecificationExecutor<DictionaryItem>{
	
	@Query("select pd from DictionaryItem pd where pd.code = ?1 and pd.dictionary.id=?2")
	DictionaryItem getDictItemByCodeAndDictId(String code,Long id);

	// 根据字典code获取CodeName对象
	@Query("select pd from DictionaryItem pd where pd.dictionary.code = ? order by pd.sortIndex")
	public List<DictionaryItem> findByDictionaryCode(String dictCode);

	@Query("select pd from DictionaryItem pd where pd.dictionary.id = ?1 order by pd.sortIndex")
	public List<DictionaryItem> findByDictionaryItemIdOrderBySortIndexAsc(Long id);

	@Query("select pd.dictionary.id from DictionaryItem pd where pd.code = ?1")
	Long findDIctIdByCode(String code);
	
	@Query("select pd from DictionaryItem pd where pd.dictionary.code = ?1 and pd.pid=?2 order by pd.sortIndex")
	List<DictionaryItem> findByDictionaryCodeAndPid(String dictCode,Long pid);
	
	@Query("select pd from DictionaryItem pd where pd.dictionary.code = ?1 and pd.pid=?2 or pd.pid=?3 order by pd.sortIndex")
	List<DictionaryItem> findByDictionaryCodeAndPids(String dictCode,Long pid,Long pids);
	
	@Query("select pd from DictionaryItem pd where pd.dictionary.code = ?1 and pd.pid = null order by pd.sortIndex")
	List<DictionaryItem> findByDictionaryCodeAndPidisNull(String dictCode);
	
	@Query("select pd from DictionaryItem pd where pd.dictionary.code = ?1  order by pd.code desc")
	List<DictionaryItem> findByDictionaryCodeOrderByCode(String dictCode);
	
	// 根据字典code获取CodeName对象
	@Query("select pd from DictionaryItem pd where pd.dictionary.code = ?1 and pd.code=?2 order by pd.sortIndex")
	public DictionaryItem findByDictCodeAndDictItemCode(String dictCode,String dictItemCode);
	
	@Query("select pd from DictionaryItem pd where pd.dictionary.code = ?1 and pd.name=?2 order by pd.sortIndex")
	public DictionaryItem findByDictCodeAndDictItemName(String dictCode,String dictItemName);
}
