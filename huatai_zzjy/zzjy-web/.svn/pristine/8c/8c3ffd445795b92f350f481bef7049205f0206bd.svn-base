package com.huatai.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.Notice;
import com.huatai.web.model.NoticeFile;
import com.huatai.web.service.NoticeFileService;
import com.huatai.web.service.NoticeService;
import com.huatai.web.utils.CephUtil;

/**
 * @author 胡智辉 2017年8月9日
 */
@Controller
@RequestMapping("admin/notice")
public class NoticeController {

	final static Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private DictionaryService dictionaryService;

	@Autowired
	private NoticeFileService noticeFileService;

//	@Value("${file.upload.path}")
//	private String savePath_Template;// 上传路径

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView main() throws Exception {
		ModelAndView view = new ModelAndView();
		String dstr = SessionContextUtils.getCurrentUser().getDeptCode().toString();
		String[] ds = dstr.split(",");
		List<DictionaryItem> dictItem = new ArrayList<>();
		for (String d : ds) {
			dictItem.add(dictionaryService.findByDictCodeAndDictItemCode("DETP_DICT", d));
		}
		view.addObject("dictItem", dictItem);
		view.setViewName("/admin/notice/index");
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<Notice> find(Pager<Notice> pager, Notice record) {
		String dstr = SessionContextUtils.getCurrentUser().getDeptCode().toString();
		record.setDeptCode(dstr);
		Pager<Notice> result = noticeService.findNoticePager(pager, record);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "findFile")
	public List<NoticeFile> findFile(String noticeId) {
		List<NoticeFile> result = noticeFileService.findNoticeId(Integer.valueOf(noticeId));
		return result;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public int create(@Valid Notice record, HttpServletRequest request, MultipartHttpServletRequest multiReq,
			Integer[] nfId) {
		String bucketName = "upload";
		int result = 0;
		try {
			Integer noticeId = record.getNoticeId();
			if (noticeId != null) {
				update(record);
				// return 1;
			} else {
				record.setOpType("A");
				record.setCreatorId(SessionContextUtils.getLoginName().toString());
				record.setCreateTime(new Date());
				record.setModifyTime(new Date());
				record.setModifierId(SessionContextUtils.getLoginName().toString());
				result = noticeService.insertSelective(record);
			}
			// 开始保存文件
			List<MultipartFile> files = multiReq.getFiles("file1");
			MultipartFile file = null;
			BufferedOutputStream bos = null;
			InputStream inputStream=null;
			// 更新，首先删除不在nfId列表中的文件
			if (noticeId != null) {
				List<NoticeFile> oldNfList = noticeFileService.findNoticeId(record.getNoticeId());
				for (NoticeFile noticeFile : oldNfList) {
					boolean isContain = false;
					String filePath = noticeFile.getFilePath();
					//String path = filePath.substring(0, filePath.lastIndexOf("/"));
					if (nfId != null) {
						for (int i = 0; i < nfId.length; i++) {
							if (noticeFile.getNfId() == nfId[i]) {
								isContain = true;
								break;
							}
						}
						if (!isContain) {
							noticeFile.setOpType("D");
							noticeFileService.update(noticeFile);
							//getFile(filePath).delete();
						}
					}else{
						noticeFile.setOpType("D");
						noticeFileService.update(noticeFile);
						getFile(filePath).delete();
						//getFile(path).delete();
					}
				}
			}
			for (int i = 0; i < files.size(); ++i) {
				file = files.get(i);
				if (!file.getName().isEmpty()) {
					try {
						inputStream = file.getInputStream();
						
						NoticeFile noticeFile = new NoticeFile();
						String uploadFilePath = file.getOriginalFilename();
						String uploadFileName = uploadFilePath.substring(uploadFilePath.lastIndexOf(File.separator) + 1,
								uploadFilePath.indexOf('.'));
						String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.indexOf('.') + 1,
								uploadFilePath.length());
						String relativeFilePath = uploadFileName + "." + uploadFileSuffix;
//						String path = savePath_Template + "/" + currentTimeMillis;
//						File uploadFile = getFile(path);
//						uploadFile.mkdirs();
//						String filePath = path + "/" + relativeFilePath;
						//stream = new BufferedOutputStream(new FileOutputStream(filePath));
						/*byte[] bytes = file.getBytes();
						stream.write(bytes, 0, bytes.length);*/
						noticeFile.setNoticeId(record.getNoticeId());
						noticeFile.setFileName(uploadFilePath);
						noticeFile.setFilePath(bucketName);
						noticeFile.setOpType("A");
						CephUtil.uploadFile(inputStream, bucketName, relativeFilePath, file.getSize());
//						FileOutputStream fos = new FileOutputStream(filePath);
//						bos = new BufferedOutputStream(fos, 1024);
//						int length = 0;
//						byte[] buffer = new byte[1024];
//						while ((length = inputStream.read(buffer)) != -1) {
//						    bos.write(buffer, 0, length);
//						}
//						bos.flush();
						inputStream.close();
						noticeFileService.insertSelective(noticeFile);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							if (bos != null) {
								inputStream.close();
								bos.close();
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					noticeFileService.deleteByNoticeId(record.getNoticeId());
				}
			}
			System.out.println("文件接受成功了");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public int update(@Valid Notice record) {
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		record.setModifyTime(new Date());
		record.setOpType("U");
		int result = noticeService.updateByPrimaryKeySelective(record);
		return result;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public int delete(@Valid Notice record) {
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		record.setModifyTime(new Date());
		record.setOpType("D");
		int result = noticeService.deleteByPrimaryKey(record);
		return result;
	}
	
	private File getFile(String filePath){
		File file=new File(filePath);
		return file;
	}

}
