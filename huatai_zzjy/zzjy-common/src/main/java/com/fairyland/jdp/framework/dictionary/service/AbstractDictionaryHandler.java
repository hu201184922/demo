package com.fairyland.jdp.framework.dictionary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.view.DictItemModel;

public abstract class AbstractDictionaryHandler implements DictionaryHandler {

	@Autowired
	private DictionaryService dictionaryService;

	/**
	 * 清空指定字典的字典项设置缓存
	 * 
	 * @param dictCode
	 *            (字典编码)
	 */
	public void clearCache(String dictCode) {

	}

	/**
	 * 清空所有字典缓存
	 */
	public void clearCache() {
		// dictionaryDao.deleteAll();
	}

	/**
	 * 根据字典编码获取字典项Map
	 * 
	 * @param dictCode
	 *            (字典编码)
	 * @return
	 */
	abstract Map<String, DictItemModel> getDictionary(String dictCode);

	/**
	 * 获取指定字典中的字典项
	 * 
	 * 
	 * @param dictCode
	 *            (字典编码，唯一标识)
	 * @return
	 */
	@Cacheable(value = "DictCache", key = "#dictCode")
	public List<DictItemModel> getDictItems(String dictCode) {
		List<DictItemModel> list = new ArrayList<DictItemModel>();

		if (StringUtils.isNotEmpty(dictCode)) {
			List<DictionaryItem> items = dictionaryService
					.getItemsByDictCode(dictCode);
			for (DictionaryItem item : items) {
				list.add(new DictItemModel(item));
			}
		}

		return list;
	}

	/**
	 * 根据字典项编码获取CodeName对象
	 * 
	 * @param itemCode
	 *            (字典项编码)
	 * @return
	 */
//	public DictItemModel getDictItem(String itemCode) {
//		return new DictItemModel(dictionaryService.getDictItemByCode(itemCode));
//	}

	/**
	 * 根据字典编码和字典项名称获取CodeName对象 (字典项编码和字典项名称)
	 * 
	 * @param dictCode
	 *            (字典编码)
	 * @param itemName
	 *            (字典项名称)
	 * @return
	 */
	public DictItemModel getDictItem(String dictCode, String itemName) {
		List<DictItemModel> dictItems = getDictItems(dictCode);
		for (DictItemModel dictItem : dictItems) {
			if (dictItem.getName().equals(itemName)) {
				return dictItem;
			}
		}
		return null;
	}
	
	public DictItemModel getDictItemByItemCode(String dictCode, String ItemCode) {
		List<DictItemModel> dictItems = getDictItems(dictCode);
		for (DictItemModel dictItem : dictItems) {
			if (dictItem.getCode().equals(ItemCode)) {
				return dictItem;
			}
		}
		return null;
	}
	
	//@Cacheable(value = "DictCache", key = "#dictCode")
	public List<DictItemModel> getDictItems(String dictCode,Long pid) {
		List<DictItemModel> list = new ArrayList<DictItemModel>();

		if (StringUtils.isNotEmpty(dictCode)) {
			List<DictionaryItem> items = dictionaryService
					.getItemsByDictCodeAndPid(dictCode,pid);
			for (DictionaryItem item : items) {
				list.add(new DictItemModel(item));
			}
		}

		return list;
	}
	public List<DictItemModel> getDictItemsByPids(String dictCode,Long pid,Long pids) {
		List<DictItemModel> list = new ArrayList<DictItemModel>();
		
		if (StringUtils.isNotEmpty(dictCode)) {
			List<DictionaryItem> items = dictionaryService
					.getItemsByDictCodeAndPids(dictCode,pid,pids);
			for (DictionaryItem item : items) {
				list.add(new DictItemModel(item));
			}
		}
		
		return list;
	}
}
