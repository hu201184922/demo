package com.fairyland.jdp.framework.dictionary.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.dictionary.dao.DictionaryDao;
import com.fairyland.jdp.framework.dictionary.dao.DictionaryItemDao;
import com.fairyland.jdp.framework.dictionary.domain.Dictionary;
import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;

@Service
public class DictionaryServiceImpl implements DictionaryService {
	@Autowired
	private DictionaryDao dictionaryDao;
	@Autowired 
	private DictionaryItemDao dictionaryItemDao;
	
	@Override
	public void createDictionary(Dictionary dictionary) {
		if(dictionary.getParent()!=null && dictionary.getParent().getId()==null){
			dictionary.setParent(null);
		}
		dictionaryDao.save(dictionary);
	}

	@Override
	public Dictionary readDictionary(Long dictionaryId) {
		return dictionaryDao.findOne(dictionaryId);
	}

	@Override
	public void updateDictionary(Dictionary dictionary) {
		if(dictionary.getParent()!=null && dictionary.getParent().getId()==null){
			dictionary.setParent(null);
		}
		 dictionaryDao.save(dictionary);
	}

	@Override
	@CacheEvict(value="DictCache", key="#dictionaryId.toString()")
	public void deleteDictionary(Long dictionaryId) {
		dictionaryDao.delete(dictionaryId);
		
	}

	@Override
	@CacheEvict(value="DictCache", key="#dictionary.id.toString()")
	public void deleteDictionary(Dictionary dictionary) {
		dictionaryDao.delete(dictionary);
	}
	//=====================================字典项的crud方法=======================
	@Override
	@CacheEvict(value="DictCache", key="#dictionaryItem.dictionary.code")
	public void createDictionaryItem(DictionaryItem dictionaryItem) {
		//add by xiongmiao 
		//重新获得dictionary，用来清除缓存
		Dictionary dictionary = dictionaryDao.findOne(dictionaryItem.getDictionary().getId());
		dictionaryItem.setDictionary(dictionary);
		dictionaryItemDao.save(dictionaryItem);
	}

	
	@Override
	@CacheEvict(value="DictCache", key="#dictionaryItem.dictionary.code")
	public void createDictionaryItemByCode(DictionaryItem dictionaryItem) {
		//add by xiongmiao 
		//重新获得dictionary，用来清除缓存
		Dictionary dictionary = dictionaryDao.findByCode(dictionaryItem.getDictionary().getCode());
		dictionaryItem.setDictionary(dictionary);
		dictionaryItemDao.save(dictionaryItem);
	}
	
	@Override
	public DictionaryItem readDictionaryItem(Long dictionaryItemId) {
		return dictionaryItemDao.findOne(dictionaryItemId);
	}

	@Override
	@CacheEvict(value="DictCache", key="#dictionaryItem.dictionary.code")
	public void updateDictionaryItem(DictionaryItem dictionaryItem) {
		//add by xiongmiao 
		//重新获得dictionary，用来清除缓存
		Dictionary dictionary = dictionaryDao.findOne(dictionaryItem.getDictionary().getId());
		dictionaryItem.setDictionary(dictionary);
		dictionaryItemDao.save(dictionaryItem);
	}

	@Override
	public void deleteDictionaryItem(Long dictionaryItemid) {
		DictionaryItem dictionaryItem=readDictionaryItem(dictionaryItemid);
		deleteDictionaryItem(dictionaryItem);
	}

	@Override
	@CacheEvict(value="DictCache", key="#dictionaryItem.dictionary.code")
	public void deleteDictionaryItem(DictionaryItem dictionaryItem) {
		dictionaryItemDao.delete(dictionaryItem);
	}

	@Override
	public Dictionary getDictByCode(String code) {
		return dictionaryDao.findByCode(code);
	}

	@Override
	public DictionaryItem getDictItemByCodeAndDictId(String code,Long dictId) {
		return dictionaryItemDao.getDictItemByCodeAndDictId(code,dictId);
	}

	@Override
	@Cacheable(value="DictCache", key="#dictCode")
	public List<DictionaryItem> getItemsByDictCode(String dictCode) {
		System.out.println("-----------------"+dictCode+"--------------------");
		return dictionaryItemDao.findByDictionaryCode(dictCode);
	}

	@Override
	public List<Dictionary> findAllDictionary() {
		return dictionaryDao.findAllDictionary();
	}

	@Override
	public List<DictionaryItem> findDictionaryItemByDicId(Long id) {
		return dictionaryDao.findDictionaryItemByDicId(id);
	}

	@Override
	public List<DictionaryItem> getDictionaryItem(Long id) {
		return dictionaryItemDao.findByDictionaryItemIdOrderBySortIndexAsc(id);
	}

	@Override
	public Long getDictIdByCode(String code) {
		return dictionaryItemDao.findDIctIdByCode(code);
	}

	@Override
	public List<Long> findByPid(Long id) {
		return dictionaryDao.findByPid(id);
	}

	@Override
	public List<Dictionary> findParentIdNull() {
		return dictionaryDao.findParentIdNull();
	}

	@Override
	public List<DictionaryItem> getItemsByDictCodeAndPid(String dictCode,
			Long id) {
		if(id==null)return dictionaryItemDao.findByDictionaryCodeAndPidisNull(dictCode);
		return dictionaryItemDao.findByDictionaryCodeAndPid(dictCode, id);
	}
	
	@Override
	public List<DictionaryItem> getItemsByDictCodeAndPids(String dictCode,Long pid,Long pids) {
		return dictionaryItemDao.findByDictionaryCodeAndPids(dictCode, pid,pids);
	}

	public List<DictionaryItem> findByDictionaryCodeOrderByCode(String code){
		return dictionaryItemDao.findByDictionaryCodeOrderByCode(code);
	}

	@Override
	@Cacheable(value="DictCache", key="#dictCode")
	public Map<String, Object> getItemsByDictCodeForMap(String dictCode) {
		System.out.println("---------------dictCode-------------");
		List<DictionaryItem> list = getItemsByDictCode(dictCode);
		Map<String,Object> params = new HashMap<String, Object>();
		for(DictionaryItem d:list){
			params.put(d.getCode(), d.getName());
		}
		return params;
	}

	@Override
	public DictionaryItem findByDictCodeAndDictItemCode(String dictCode, String dictItemCode) {
		return dictionaryItemDao.findByDictCodeAndDictItemCode(dictCode, dictItemCode);
	}

	@Override
	public DictionaryItem findByDictCodeAndDictItemName(String dictCode, String dictItemName) {
		return dictionaryItemDao.findByDictCodeAndDictItemName(dictCode, dictItemName);
	}

}
