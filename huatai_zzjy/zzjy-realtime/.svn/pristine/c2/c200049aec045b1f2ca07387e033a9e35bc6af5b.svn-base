package com.fairyland.jdp.framework.dictionary.service;

import java.util.List;

import com.fairyland.jdp.framework.dictionary.view.DictItemModel;
public interface DictionaryHandler {
	//清空指定字典的字典项设置缓存
	public void clearCache(String dictCode);
	
	//清空所有字典缓存
	public void clearCache();
	
	//根据指定字典的编码获取它的字典项
	public List<DictItemModel> getDictItems(String dictCode);
	
	//根据字典项编码获取CodeName对象
//	public DictItemModel getDictItem(String itemCode);
	
	//根据字典项编码获取CodeName对象
	public DictItemModel getDictItemByItemCode(String dictCode,String itemCode);
	
	//根据字典编码和字典项名称获取CodeName对象
	public DictItemModel getDictItem(String dictCode,String itemName);
	
	List<DictItemModel> getDictItems(String dictCode,Long pid);

	List<DictItemModel> getDictItemsByPids(String dictCode,Long pid,Long pids);
}
