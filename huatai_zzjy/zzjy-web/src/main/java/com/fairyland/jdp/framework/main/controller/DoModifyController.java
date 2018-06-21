package com.fairyland.jdp.framework.main.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.framework.exception.MetLifeCrmException;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.spring.SpringContextUtil;
import com.fairyland.jdp.orm.util.common.StringUtil;

@Controller
@RequestMapping(value = "/admin/datamodify")
public class DoModifyController {
	
	@RequestMapping(value = "index")
	public ModelAndView index(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/domodify/index");
		return view;
	}
	
	/**
	 * 数据修正
	 * @param request
	 * @param response
	 * @throws SchedulerException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/domodify")
	@ResponseBody
	public Map<String,String>  dodatamodify(HttpServletRequest request,HttpServletResponse response){
		User user = SessionContextUtils.getCurrentUser();
		String sqlStr = request.getParameter("sql");
		sqlStr = sqlStr.trim();
		String dsIndex = request.getParameter("dsIndex");   //1.mairadb 2.oracle 3.impala
		String limitNum = request.getParameter("limitNum"); //on | null
		String beginRow = request.getParameter("beginRow"); //空字符串 | 数值
		String endRow = request.getParameter("endRow");     //空字符串 | 数值
		
		StringBuffer outStr = new StringBuffer();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		if(user != null && user.getIsAdmin() != null && user.getIsAdmin()){
			if(StringUtils.isBlank(beginRow)){
				beginRow="1";
			}
			if(StringUtils.isBlank(endRow)){
				beginRow="10";
			}
			if("on".equals(limitNum) && "2".equals(dsIndex)){  //分页
				if(sqlStr.length()>6 && sqlStr.substring(0, 6).equalsIgnoreCase("select")){
					sqlStr = "SELECT * FROM "+
								"(SELECT tt__.*, ROWNUM AS rowno FROM( "+ 
								sqlStr + 
								") tt__ "+
								"WHERE ROWNUM <= "+endRow+ ") ttt__ "+
							  "WHERE ttt__.rowno >= "+beginRow;
				}
			}
			if("on".equals(limitNum) && "1".equals(dsIndex)){  //分页
				if(sqlStr.length()>6 && sqlStr.substring(0, 6).equalsIgnoreCase("select")){
					sqlStr = sqlStr +" limit "+beginRow+","+endRow;
				}
			}
			String DataSourceBeanStr=null;
			if("2".equals(dsIndex)){
				DataSourceBeanStr="dataSource2";
			}else if("3".equals(dsIndex)){
				DataSourceBeanStr="dataSource3";
			}
			/*else if("4".equals(dsIndex)){
				DataSourceBeanStr="dataSource4";
			}*/
			else{
				DataSourceBeanStr="dataSource1";
			}
			try{
				DataSource ds = (DataSource)SpringContextUtil.getBean(DataSourceBeanStr);
				conn = ds.getConnection();
				conn.setAutoCommit(true);
				stmt = conn.createStatement(); 
				if(sqlStr.length()>4 && sqlStr.substring(0, 4).equalsIgnoreCase("with") ||
						(sqlStr.length()>6 && sqlStr.substring(0, 6).equalsIgnoreCase("select")) ){
					rs = stmt.executeQuery(sqlStr);
					ResultSetMetaData md = rs.getMetaData();
					int mdc = md.getColumnCount();
					
					outStr.append("<div style='width: "+(mdc*180+50)+"px;'>");
					outStr.append("<div style=' margin-bottom: 10px;'>") ;
					outStr.append("<ul class='tr_red_ul tr_red'>") ;
					for(int j = 1;j<= mdc;j++){
						outStr.append("<li class='tr_red_ul_li wd180'>"+md.getColumnName(j)+"</li>") ;
					}
					outStr.append("</ul></div>") ;
					int rowid = 0;
//					Integer maxRow = Integer.parseInt(request.getParameter("maxrow"));
					String tip = null;
					while(rs.next() /* && rowid < maxRow*/) 
					{ 
						outStr.append("<div class='margin_top1'><ul class='tr_red_ul ul_border'>") ;
						for(int j = 1;j<= mdc;j++){
							tip = StringUtil.escapeHtml(rs.getString(j));
							outStr.append("<li class='tr_red_ul_li wd180'><span class=\"mainplantip\" tip=\""+tip+"\">"+tip+"</span></li>") ;
						}
						outStr.append("</ul></div>") ;
						rowid++;
					}
					outStr.append(" <div class='clear'></div></div><div style='width: "+(mdc*180+50)+"px;'>总计:"+rowid+"条</div> ");
					outStr.append("<div style='height: 50px;'></div> ");
				}else if(sqlStr.length()>6 && sqlStr.substring(0, 6).toLowerCase().equalsIgnoreCase("insert")){
					Integer cnt = stmt.executeUpdate(sqlStr);
					outStr.append("受影响的行数:"+cnt);
				}else if(sqlStr.length()>6 && sqlStr.substring(0, 6).toLowerCase().equalsIgnoreCase("update")
						||sqlStr.trim().substring(0, 6).toLowerCase().equalsIgnoreCase("delete")){
					if(sqlStr.indexOf("where") == -1 && sqlStr.indexOf("WHERE") == -1){
						outStr.append("为了防止误操作，UPDATE ,DELETE 语句必须包含WHERE条件，不需要WHERE条件可以用 WHERE 1=1");
					}else{
						Integer cnt = stmt.executeUpdate(sqlStr);
						outStr.append("受影响的行数:"+cnt);
					}
				}else{
					stmt.execute(sqlStr);
					rs =  stmt.getResultSet();
					if(rs != null){
						ResultSetMetaData md = rs.getMetaData();
						int mdc = md.getColumnCount();
						outStr.append("<div style='width: "+(mdc*180+50)+"px;'>" );
						outStr.append("<div style=' margin-bottom: 10px;'>");
						outStr.append("<ul class='tr_red_ul tr_red'>");
						for(int j = 1;j<= mdc;j++){
							outStr.append("<li class='tr_red_ul_li wd180'>"+md.getColumnName(j)+"</li>") ;
						}
						outStr.append("</ul></div>") ;
//						int rowid = 0;
//						Integer maxRow = Integer.parseInt(request.getParameter("maxrow"));
						while(rs.next() /* && rowid < maxRow*/) 
						{ 
							outStr.append("<div class='margin_top1'><ul class='tr_red_ul ul_border'>") ;
							for(int j = 1;j<= mdc;j++){
								outStr.append("<li class='tr_red_ul_li wd180'><span class=\"mainplantip\" tip=\""+rs.getString(j)+"\">"+rs.getString(j)+"</span></li>") ;
							}
							outStr.append("</ul></div>") ;
//							rowid++;
						}
						outStr.append("<div class='clear'></div></div>"); 
					}else{
						outStr.append(stmt.getWarnings().getMessage());
					}
				}
			}catch(Throwable e){
				outStr .append( e.getMessage() );
			}finally {
				try {
					if(rs!=null){
						rs.close();
						rs = null;
					}
					if (stmt != null) {
						stmt.close();
						stmt = null;
					}
					if (conn != null) {
						conn.close();
						conn = null;
					}
				} catch (SQLException e) {
				}
			}
		}else{
			throw new MetLifeCrmException("您无法进行此操作");
		}
		
		Map<String,String>outMap = new HashMap<String,String>();
		outMap.put("msg", outStr.toString());
		return outMap;
	}
}
