package com.fairyland.jdp.framework.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.dictionary.service.DictionaryHandler;
import com.fairyland.jdp.framework.dictionary.view.DictItemModel;
@Component
public class DictionaryUtils {
	
	static private DictionaryHandler dictionaryHandler;

	static{
		DictionaryUtils.dictionaryHandler =SpringUtil.getBean(DictionaryHandler.class);
	}
	/**
	 * 依赖注入（注意注入方法不能为static,否则Spring IOC初始化会抛出异常）
	 * 
	 * @param dictionaryHandler
	 */
	@Autowired
	public void setDictionaryHandler(DictionaryHandler dictionaryHandler) {
		DictionaryUtils.dictionaryHandler = dictionaryHandler;
	}

	/**
	 * 获取字典项中指定编码对应的名称
	 * 
	 * @param itemCode
	 *            (字典项编码)
	 * @return
	 */
	static public String codeToName(String dictCode,String itemCode) {
		//DictItemModel codename = dictionaryHandler.getDictItemByItemCode(dictCode,itemCode);
		List<DictItemModel> dictItems = dictionaryHandler.getDictItems(dictCode);
		for (DictItemModel dictItem : dictItems) {
			if (dictItem.getCode().equals(itemCode)) {
				return dictItem.getName();
			}
		}
		return null;// 返回itemName
	}

	/**
	 * 获取字典项中指定名称对应的编码(由于name值不具备唯一性，所以同时还必须指定)
	 * 
	 * @param dictCode
	 *            (字典编码)
	 * @param itemName
	 *            (字典项名称) ((根据字典编码和字典项名称获取CodeName对象))
	 * @return
	 */
	static public String nameToCode(String dictCode, String itemName) {
		DictItemModel codename = dictionaryHandler.getDictItem(dictCode, itemName);
		return codename.getCode();// 返回字典项编码
	}

	/**
	 * 获取指定编码的字典中的所有字典项
	 */
	static public List<DictItemModel> getDictItems(String dictCode) {
		return dictionaryHandler.getDictItems(dictCode);// 返回字典项列表
	}

	/**
	 * 获取指定编码的字典中的除exclusion集合之外的其它字典项
	 * 
	 * @param dictCode
	 * @param exclusion
	 * @return
	 */
	static public List<DictItemModel> getDictItems(String dictCode,
			List<String> exclusion) {
		List<DictItemModel> list = dictionaryHandler.getDictItems(dictCode);
//		list.removeAll(exclusion);
		return list;// 返回字典项列表
	}

	/**
	 * 获取指定编码的字典中的所有字典项,同时添加一个”全部”选项做为第一项
	 * 
	 * @param dictCode
	 * @return(new一个代表全部的)
	 */
	static public List<DictItemModel> getDictItemsWithOptionALL(String dictCode) {
		List<DictItemModel> list = dictionaryHandler.getDictItems(dictCode);
		DictItemModel item = new DictItemModel();
		item.setName("全部");
		item.setCode("");
		list.add(0, item);
		// list.add(0, "全部");
		return list;// 返回字典项列表
	}

	/**
	 * 获取指定编码的字典中的除exclusion集合之外的其它字典项, 同时添加一个”全部”选项做为第一项
	 * 
	 * @param dictCode
	 * @param exclusion
	 * @return(new一个代表全部的)
	 */
	static public List<DictItemModel> getDictItemsWithOptionALL(String dictCode,
			List<String> exclusion) {
		List<DictItemModel> list = dictionaryHandler.getDictItems(dictCode);
//		list.removeAll(exclusion);
		DictItemModel item = new DictItemModel();
		item.setName("全部");
		item.setCode("");

		list.add(0, item);
		// list.addAll(0, list);//添加整个字典项集合
		return list;// 返回字典项列表
	}

	/**
	 * 获取指定编码的字典中的所有字典项,同时添加一个”请选择”选项做为第一项
	 * 
	 * @param dictCode
	 * @return
	 */
	static public List<DictItemModel> getDictItemsWithOptionSELECT(String dictCode) {
		List<DictItemModel> list = dictionaryHandler.getDictItems(dictCode);
		List<DictItemModel> newlist = new ArrayList<DictItemModel>(list);
		DictItemModel item = new DictItemModel();
		item.setName("请选择");
		item.setCode("");

		newlist.add(0, item);
		return newlist;// 返回字典项列表
	}

	/**
	 * 获取指定编码的字典中的除exclusion集合之外的其它字典项, 同时添加一个”请选择”选项做为第一项
	 * 
	 * @param dictCode
	 * @param exclusion
	 * @return
	 */
	static public List<DictItemModel> getDictItemsWithOptionSELECT(String dictCode,
			List<String> exclusion) {
		List<DictItemModel> list = dictionaryHandler.getDictItems(dictCode);
//		list.removeAll(exclusion);
		DictItemModel item = new DictItemModel();
		item.setName("请选择");
		item.setCode("");

		list.add(0, item);
		return list;// 返回字典项列表
	}

	/**
	 * 获取指定编码的字典中的所有字典项, 同时添加一个选项 (code:defaultCode,name:defaultName)做为第一项
	 * 
	 * @param defaultCode
	 * @param defaultName
	 * @param dictCode
	 * @return
	 */
	static public List<DictItemModel> getDictItemsWithCustomOption(
			String defaultCode, String defaultName, String dictCode) {
		List<DictItemModel> list = dictionaryHandler.getDictItems(dictCode);
		DictItemModel item = new DictItemModel();
		item.setCode(defaultCode);
		item.setName(defaultName);
		list.add(0, item);
		return list;// 返回字典项列表
	}

	/**
	 * 获取指定编码的字典中的除exclusion集合之外的其它字典项,
	 * 同时添加一个选项(code:defaultCode,name:defaultName)做为第一项 * @param defaultCode
	 * 
	 * @param defaultName
	 * @param dictCode
	 * @param exclusion
	 * @return
	 */
	static public List<DictItemModel> getDictItemsWithCustomOption(
			String defaultCode, String defaultName, String dictCode,
			List<String> exclusion) {
		List<DictItemModel> list = dictionaryHandler.getDictItems(dictCode);
//		list.removeAll(exclusion);
		DictItemModel item = new DictItemModel();
		item.setCode(defaultCode);
		item.setName(defaultName);
		list.add(0, item);
		return list;// 返回字典项列表
	}
	
	static public List<DictItemModel> getDictItemsByPid(String dictCode,
			Long pid) {
		List<DictItemModel> list = dictionaryHandler.getDictItems(dictCode,pid);
		return list;// 返回字典项列表
	}
	/**
	 * 根据pid获取公共
	 * @param dictCode
	 * @param pid
	 * @return
	 */
	static public List<DictItemModel> getDictItemsByPids(String dictCode,
			Long pid,Long pids) {
		List<DictItemModel> list = dictionaryHandler.getDictItemsByPids(dictCode, pid, pids);
		return list;// 返回字典项列表
	}
}
