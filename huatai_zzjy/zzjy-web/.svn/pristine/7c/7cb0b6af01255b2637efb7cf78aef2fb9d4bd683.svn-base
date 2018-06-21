package com.fairyland.jdp.framework.dictionary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.framework.dictionary.domain.Dictionary;
import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;

@Controller
@RequestMapping(value = "/admin/dict")
public class DictionaryController {
	@Autowired
	private DictionaryService dictionaryService;

	@RequestMapping("index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/dict/index");
		return view;
	};

	@RequestMapping("/tree")
	@ResponseBody
	public List<Dictionary> tree() {
		List<Dictionary> list = dictionaryService.findParentIdNull();
		return list;
	}

	@RequestMapping("/getDictItem")
	@ResponseBody
	public List<DictionaryItem> getDictItem(Long id) {
		Dictionary dictionary = dictionaryService.readDictionary(id);
		List<DictionaryItem> list = new ArrayList<DictionaryItem>(
				dictionary.getItems());
		return list;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public void del(Long id){
		dictionaryService.deleteDictionary(id);
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Dictionary create(Dictionary dictionary){
		Dictionary pdy = new Dictionary();
		pdy.setId(dictionary.getPid());
		dictionary.setParent(pdy);
		if(dictionary.getId()!=null&&dictionaryService.readDictionary(dictionary.getId())!=null){
			dictionaryService.updateDictionary(dictionary);
		}else{
			dictionaryService.createDictionary(dictionary);
		}
		return dictionary;
	}
	
	@RequestMapping("/createItem")
	@ResponseBody
	public DictionaryItem createItem(DictionaryItem item,Long dictId){
		Dictionary dy = new Dictionary();
		dy.setId(dictId);
		item.setDictionary(dy);
		dictionaryService.createDictionaryItem(item);
		return item;
	}
	
	@RequestMapping("/deleteItem")
	@ResponseBody
	public void delete(Long id){
		dictionaryService.deleteDictionaryItem(id);
	}
	@RequestMapping("/updateItem")
	@ResponseBody
	public DictionaryItem updateItem(DictionaryItem item,Long dictId){
		Dictionary dy = new Dictionary();
		dy.setId(dictId);
		item.setDictionary(dy);
		dictionaryService.updateDictionaryItem(item);
		return item;
	}
}
