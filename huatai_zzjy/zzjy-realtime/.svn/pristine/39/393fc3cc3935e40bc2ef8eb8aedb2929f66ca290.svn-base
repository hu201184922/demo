package com.fairyland.jdp.framework.dictionary.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.dictionary.domain.Dictionary;
import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
import com.fairyland.jdp.framework.domain.TreeNodeEntity;
import com.fairyland.jdp.framework.tree.controller.TreeController;
import com.fairyland.jdp.framework.tree.service.TreeFactory;

@RestController
@RequestMapping(value="/admin/dict/user")
public class DictionaryUserController extends TreeController<Dictionary>{
	@Autowired	
	private DictionaryService dictionaryService;
	
	
	@Autowired
	private @Qualifier(value="zTreeFactory")TreeFactory<Dictionary> treeFactory;
	
	@RequestMapping(method = RequestMethod.GET)
	public String main(){
		return "/jdp-framework/dictionary/dictionary-user-main";
	}
	
	@RequestMapping(value = "/get-tree/user", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public List<Map<String, Object>> getTree() throws Exception {
		Map<String, Object> searchParams=new HashMap<String, Object>();
		searchParams.put("EQ_system", false);
		return treeFactory.getTree(Dictionary.class,TreeNodeEntity.ROOT_PARENT_ID,searchParams);
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public Dictionary create(@Valid Dictionary dictionary) {
		dictionary.setName("新字典");
		dictionary.setSystem(false);
		dictionaryService.createDictionary(dictionary);
		return dictionary;
	}
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("dict") Dictionary dictionary, RedirectAttributes redirectAttributes) {
		dictionary.setSystem(false);
		dictionaryService.updateDictionary(dictionary);
		redirectAttributes.addFlashAttribute("message", "字典保存成功");
		return "redirect:/admin/dict/user/";
	}
	
	@RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
		dictionaryService.deleteDictionary(id);
		redirectAttributes.addFlashAttribute("message", "字典删除成功");
		return "redirect:/admin/dict/user/";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value ="getDictItem/{id}", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public List<Map<String, Object>> getDictItem(@PathVariable("id") Long id) throws Exception {
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		Dictionary dictionary=dictionaryService.readDictionary(id);
		Set<DictionaryItem> set=dictionary.getItems();
		Iterator<DictionaryItem> it=set.iterator();
		while(it.hasNext()){
			DictionaryItem dictItem=it.next();
			@SuppressWarnings("type")
			Map map=new HashMap();
			map.put("id",dictItem.getId());
			map.put("code",dictItem.getCode());
			map.put("name",dictItem.getName());
			map.put("descript",dictItem.getDescript());
			if(dictItem.getSortIndex()!=null){
				map.put("sortIndex",dictItem.getSortIndex());
			}
			else{
				map.put("sortIndex","");
			}
			list.add(map);
		}
		return list;
	}
	
	
	@RequestMapping(value = "/getDict/{id}", method = RequestMethod.GET)
	public String fetch(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dictionaryItem",
				dictionaryService.getDictionaryItem(id));
		return "/jdp-framework/dictionary/dictionaryItem-user" ;
	}
		
	@RequestMapping(value ="createDictItempage/{id}")
	public String createDictItempage(@PathVariable("id") Long id,Model model){
		model.addAttribute("id",id);
		return "/jdp-framework/dictionary/dictionaryItem-user-create";
	}
	@RequestMapping(value="createDictItem/{dictid}", method = RequestMethod.POST)
	public String createDictItem(@PathVariable("dictid") Long dictid,DictionaryItem dictionaryItem,
			@RequestParam("code") String code, RedirectAttributes redirectAttributes){
		Dictionary dictionary=new Dictionary();
		dictionary.setId(dictid);
		dictionaryItem.setDictionary(dictionary);
		dictionaryService.createDictionaryItem(dictionaryItem);
		return "redirect:/admin/dict/user/createDictItempage/{dictid} ";
	}
	@RequestMapping(value="updateDictItempage/{dictItemId}")
	public String updateDictItempage(@PathVariable("dictItemId") Long dictItemId,Model model){
		DictionaryItem dictionaryitem=dictionaryService.readDictionaryItem(dictItemId);
		model.addAttribute("dictionaryItem", dictionaryitem);
		return "/jdp-framework/dictionary/dictionaryItem-user-update";
	}
	@RequestMapping(value="updateDictionaryItem", method = RequestMethod.POST)
	public String updateDictItem(DictionaryItem dictionaryitem,Model model){
		DictionaryItem dictionaryItem=dictionaryService.readDictionaryItem(dictionaryitem.getId());
		dictionaryitem.setDictionary(dictionaryItem.getDictionary());
		dictionaryService.updateDictionaryItem(dictionaryitem);
		return "redirect:/admin/dict/user/createDictItempage/"+dictionaryitem.getDictionary().getId();
	}
	@RequestMapping(value="deleteDictItem/{ids}",method = RequestMethod.POST)
	public String deleteDictItem(@PathVariable("ids") String itemids){
		String[] ids=itemids.split(",");
		for(int i=0;i<ids.length;i++){
			DictionaryItem dictionaryItem=dictionaryService.readDictionaryItem(Long.valueOf(ids[i]));
			dictionaryService.deleteDictionaryItem(dictionaryItem);
		}
		return "redirect:/admin/dict/user";
	}
	@RequestMapping(value="deleteDictItemOne/{id}",method = RequestMethod.POST)
	public String deleteDictItemOne(@PathVariable("id") Long id){
		DictionaryItem dictionaryItem=dictionaryService.readDictionaryItem(id);
		dictionaryService.deleteDictionaryItem(dictionaryItem);
		return "redirect:/admin/dict/user";
	}
	@ModelAttribute
	public void getdict(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model) {
		if (id.longValue() != -1) {
			model.addAttribute("dict", dictionaryService.readDictionary(id));
		}
	}
	@RequestMapping(value="check",method=RequestMethod.POST)
	@ResponseBody
	public String check(@RequestParam(value="code")String code,
						@RequestParam(value="initalname")String initalname){
		if(initalname.equals(code)){
			return "true";
		}
		Dictionary dict=dictionaryService.getDictByCode(code);
		if(dict==null){
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value="checkItem",method = RequestMethod.POST)
	@ResponseBody
	public String checkItem(@RequestParam(value="initalname")String initalname,
							@RequestParam(value="code")String code,
							@RequestParam(value="dictId")Long dictId){
		if(initalname.equals(code)){
			return "true";
		}
		DictionaryItem dictItem=dictionaryService.getDictItemByCodeAndDictId(code,dictId);
		if(dictItem==null){
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value = "move/{id}")
	@ResponseBody
	public void moveTo(@PathVariable("id") Long id
			,@RequestParam("parentId") Long parentId) {
		Dictionary dictionary = 
				dictionaryService.readDictionary(id);
		if(parentId == null){
			dictionary.setParent(null);
		}else {
			dictionary.setParent(dictionaryService.readDictionary(parentId));
		}
		
		dictionaryService.updateDictionary(dictionary);
	}
	@RequestMapping(value ="checkDict/{id}", method = RequestMethod.POST)
	@ResponseBody
	public List<Long> getDict(@PathVariable("id") Long id) throws Exception {
		List<Long> dictionary=dictionaryService.findByPid(id);
		return dictionary;
	}
}
