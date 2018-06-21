package com.fairyland.jdp.springboot.config.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fairyland.jdp.framework.util.PropsUtil;

@WebServlet(urlPatterns = "/configProp")
public class ConfigPropServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public static final String Password="Htconfig*180108";
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        if(password!=null && password.equals(Password)){
			resp.setHeader("Content-type", "text/plain;charset=UTF-8");
			resp.setCharacterEncoding("UTF-8");
			BufferedReader reader = new BufferedReader(new InputStreamReader(PropsUtil.class.getResourceAsStream("/application.properties"),"UTF-8"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			do{
				line = reader.readLine();
				if(line!=null && !line.startsWith("#"))
					sb.append(line).append("\r\n");
			}while(line!=null);
			
			resp.getWriter().write(sb.toString());
        }
	}
}
