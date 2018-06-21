package com.fairyland.jdp.framework.dictionary.service;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.dictionary.view.DictItemModel;
@Service
public class DefaultDictionaryHandler extends AbstractDictionaryHandler {

	/**
	 * 根据字典编码获取字典项Map
     * 实现父类抽象方法，根据JDP平台设计的缓存机制实现字典项的缓存读写
	 */
	@Override
	  Map<String, DictItemModel> getDictionary(String dictCode){
			List<DictItemModel> list=(List<DictItemModel>) getDictItems(dictCode);
	        Map<String,DictItemModel> map=new HashMap<String, DictItemModel>();
	        for(DictItemModel  itemModel:list){
	        	if(itemModel==null){
	        		continue;
	        	}
	        	map.put(itemModel.getCode(), itemModel);
	        }
	        return map;
	    }
}
