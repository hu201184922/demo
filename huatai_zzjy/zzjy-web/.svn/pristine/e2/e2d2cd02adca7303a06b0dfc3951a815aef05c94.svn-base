package com.fairyland.jdp.framework.dictionary.service;

import java.util.List;
import java.util.Map;

import com.fairyland.jdp.framework.dictionary.domain.Dictionary;
import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;

public interface DictionaryService {
	void createDictionary(Dictionary dictionary);
	
	Dictionary readDictionary(Long dictionaryId);

	void updateDictionary(Dictionary dictionary);

	void deleteDictionary(Long dictionaryid);

	void deleteDictionary(Dictionary dictionary);
	
	//字典项的实现接口
	void createDictionaryItem(DictionaryItem dictionaryItem);

	DictionaryItem readDictionaryItem(Long dictionaryItemId);

	void updateDictionaryItem(DictionaryItem dictionaryItem);

	void deleteDictionaryItem(Long dictionaryItemid);

	void deleteDictionaryItem(DictionaryItem dictionaryItem);
	
	Dictionary getDictByCode(String code);
	
	DictionaryItem getDictItemByCodeAndDictId(String code, Long dictId);
	
	List<DictionaryItem> getItemsByDictCode(String dictCode);
	
	Map<String,Object> getItemsByDictCodeForMap(String dictCode);
	
	/**
	 * 获得所有数据字典
	 * @return
	 */
	public List<Dictionary> findAllDictionary();
	
	/**
	 * 根据字典id获得所属的字典项
	 * @param id
	 * @return
	 */
	public List<DictionaryItem> findDictionaryItemByDicId(Long id);

	List<DictionaryItem> getDictionaryItem(Long id);

	Long getDictIdByCode(String code);

	List<Long> findByPid(Long id);

	List<Dictionary> findParentIdNull();

	List<DictionaryItem> getItemsByDictCodeAndPid(String dictCode,Long id);
	
	List<DictionaryItem> getItemsByDictCodeAndPids(String dictCode,Long pid,Long pids);
	
	void createDictionaryItemByCode(DictionaryItem dictionaryItem);
	
	List<DictionaryItem> findByDictionaryCodeOrderByCode(String code);
	
	DictionaryItem findByDictCodeAndDictItemCode(String dictCode,String dictItemCode);
	
	DictionaryItem findByDictCodeAndDictItemName(String dictCode,String dictItemName);
}
