package com.fairyland.jdp.framework.dictionary.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fairyland.jdp.framework.dictionary.domain.Dictionary;
import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.tree.TreeNodeDao;

public interface DictionaryDao extends CrudRepository<Dictionary, Long>, TreeNodeDao<Dictionary> {
	Dictionary findByCode(String code);

	@Query("select b from Dictionary  b order by b.sortIndex asc")
	public List<Dictionary> findAllDictionary();

	@Query("select b from DictionaryItem  b where b.dictionary.id=?1 order by b.sortIndex asc")
	public List<DictionaryItem> findDictionaryItemByDicId(Long id);

	@Query("select b.id from Dictionary  b  where b.parent.id=?1")
	public List<Long> findByPid(Long id);

	@Query("select b from Dictionary  b  where b.pid is null")
	public List<Dictionary> findParentIdNull();

}
