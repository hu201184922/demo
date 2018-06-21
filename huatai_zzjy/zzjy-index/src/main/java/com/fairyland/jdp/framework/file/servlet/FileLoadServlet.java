package com.fairyland.jdp.framework.file.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fairyland.jdp.framework.file.domain.Attach;
import com.fairyland.jdp.framework.file.service.FileManagerService;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.fairyland.jdp.orm.util.common.StringUtil;

/**
 * Servlet implementation class FileLoadServlet
 */

public class FileLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileLoadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("application/octet-stream");
			/** (flag = product)标识是知识库产品页面上的文件下载展示 */
			String flag = request.getParameter("flag");
			FileManagerService fileManagerService = (FileManagerService) SpringUtil
					.getBean("FileManagerService");
			/** 服务器上的文件名 */
			String downFileName = "";
			String oldFileName = "";
			String filepath = "";
			if (!StringUtil.nullOrSpace(request.getParameter("attachId"))) {
				Long attachId = Long
						.parseLong(request.getParameter("attachId"));
				Attach attach = fileManagerService.getAttachById(attachId);
				if (null != attach) {
					/** 服务器上的文件名 */
					downFileName = attach.getFileName();
					/** 文件旧的名称 */
					oldFileName = attach.getOldFileName() + attach.getFileType();
					/** 文件在服务器上的路径 */
					filepath = attach.getPath() + downFileName;
					/** 文件大小 */
					response.setHeader("fileSize", attach.getFileSize());
				}
			} else if (!StringUtil.nullOrSpace(request
					.getParameter("downloadPath"))) {
				File file = new File(request.getParameter("downloadPath"));
				downFileName = file.getName();
				oldFileName = file.getName();
				filepath = request.getSession().getServletContext()
						.getRealPath("")
						+ request.getParameter("downloadPath");
			}

			if (StringUtil.isNotNull(filepath)) {
				InputStream inputStream = new FileInputStream(filepath);
				response.setHeader("Location", oldFileName);
				if (!"product".equals(flag)) {
					response.setHeader(
							"Content-disposition",
							"attachment;filename=\""
									+ new String(oldFileName.getBytes("GBK"),
											"ISO8859_1") + "\"");
				} else {
					response.setHeader("Prama", "public");
				}
				OutputStream outputStream = response.getOutputStream();

				byte[] buffer = new byte[1024*1024];
				int i = -1;
				while ((i = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, i);
				}
				outputStream.flush();
				outputStream.close();
				inputStream.close();
			} else {
				response.setHeader("fileSize", "0");
				response.getOutputStream().print(new String("亲，版本更新中。敬请关注!".getBytes("utf-8"),"ISO8859_1"));
			}
		} catch (FileNotFoundException e1) {
			response.setHeader("fileSize", "0");
			response.getOutputStream().print(new String("亲，版本更新中。敬请关注!".getBytes("utf-8"),"ISO8859_1"));
		} catch (Exception e) {
			response.setHeader("fileSize", "0");
			response.getOutputStream().print(new String("亲，版本更新中。敬请关注!".getBytes("utf-8"),"ISO8859_1"));
		}
	}
}
