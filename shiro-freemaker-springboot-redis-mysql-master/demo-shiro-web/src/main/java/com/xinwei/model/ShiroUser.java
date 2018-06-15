package com.xinwei.model;

import java.util.Date;


public class ShiroUser implements java.io.Serializable{

    private Integer id;
     
    private String username;
     
    private String password;
    
   
    private Date createTime;
   
    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String status;//用户状态:0-注册用户未审核，1-注册用户已审核 ，2-锁定

    public ShiroUser(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}