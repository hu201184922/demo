package com.fairyland.jdp.framework.menu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.fairyland.jdp.framework.menu.domain.MenuItem;
import com.fairyland.jdp.framework.tree.TreeNodeDao;

public interface MenuItemDao extends JpaRepository<MenuItem,Long>, JpaSpecificationExecutor<MenuItem>,TreeNodeDao<MenuItem>{

	@Query("select b from MenuItem  b order by b.sortIndex asc")
	public List<MenuItem> findAllMenuItem();
	@Query("select b from MenuItem  b where b.pid=null")
	public MenuItem findNullNode();
}
