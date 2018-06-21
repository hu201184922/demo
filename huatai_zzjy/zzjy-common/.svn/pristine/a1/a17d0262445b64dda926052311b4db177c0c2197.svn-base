package com.ehuatai.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fairyland.jdp.framework.util.PropsUtil;

/**
 * @功能 {POI对excel的操作}
 * @作者 MaxBill
 * @时间 2017年9月27日 上午9:42:46
 */

public class ExcelUtils {

	/**
	 * @功能 {导出excel}
	 * @作者 MaxBill
	 * @时间 2017年9月27日 上午9:45:08
	 */
	@SuppressWarnings("resource")
	public static void createExcel(String fileName, HttpServletResponse response, Object[] headList, Object[] fieldList,
			List<Map<String, Object>> dataList) {
		try {
			String filename = toUtf8String(fileName) + ".xlsx";
			response.setHeader("content-Type", "application/vnd.ms-excel");
			// 下载文件的默认名称
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			response.setCharacterEncoding("UTF-8");
			// 创建新的Excel 工作簿
			XSSFWorkbook workbook;
			workbook = new XSSFWorkbook();
			// 在Excel工作簿中建一工作表，其名为缺省值
			XSSFSheet sheet = workbook.createSheet();
			// 在索引0的位置创建行（最顶端的行）
			XSSFRow row = sheet.createRow(0);
			// 设置excel头（第一行）的头名称
			if (null != headList && headList.length > 0) {
				for (int i = 0; i < headList.length; i++) {
					// 自动调整列宽
					sheet.autoSizeColumn((short) i);
					// 在索引0的位置创建单元格（左上端）
					XSSFCell cell = row.createCell(i);
					// 在单元格中输入一些内容
					cell.setCellValue(headList[i].toString());
					// 设置表格宽度
					sheet.setColumnWidth(i, (headList[i].toString().length() + 3) * 512);
				}
			}
			// 添加数据
			if (null != dataList && !dataList.isEmpty()) {
				for (int n = 0; n < dataList.size(); n++) {
					// 在索引1的位置创建行（最顶端的行）
					XSSFRow row_value = sheet.createRow(n + 1);
					Map<String, Object> dataMap = dataList.get(n);
					for (int i = 0; i < fieldList.length; i++) {
						// 在索引0的位置创建单元格（左上端）
						XSSFCell cell = row_value.createCell(i);
						// 定义单元格为字符串类型
						// 在单元格中输入一些内容
						cell.setCellValue((dataMap.get(fieldList[i])).toString());
					}
				}
			}
			workbook.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @功能 {导出excel}
	 * @作者 MaxBill
	 * @时间 2017年9月27日 上午9:45:08
	 */
	@SuppressWarnings("resource")
	public static void createListExcel(String fileName, HttpServletResponse response, Object[] headList,
			Object[] fieldList, List<Map<String, Object>> dataList) {
		try {
			String filename = toUtf8String(fileName) + ".xlsx";
			response.setHeader("content-Type", "application/vnd.ms-excel");
			// 下载文件的默认名称
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			response.setCharacterEncoding("UTF-8");
			InputStream is = new FileInputStream("excel/test.xlsx");
			// 创建新的Excel 工作簿
			XSSFWorkbook workbook;
			workbook = new XSSFWorkbook(is);
			// 在Excel工作簿中建一工作表，其名为缺省值
			XSSFSheet sheet = workbook.getSheetAt(0);
			// 在索引0的位置创建行（最顶端的行）
			XSSFRow row = sheet.createRow(0);
			// 设置excel头（第一行）的头名称
			if (null != headList && headList.length > 0) {
				for (int i = 0; i < headList.length; i++) {
					// 自动调整列宽
					sheet.autoSizeColumn((short) i);
					// 在索引0的位置创建单元格（左上端）
					XSSFCell cell = row.createCell(i);
					// 在单元格中输入一些内容
					cell.setCellValue(headList[i].toString());
					// 设置表格宽度
					sheet.setColumnWidth(i, (headList[i].toString().length() + 3) * 512);
				}
			}
			// 添加数据
			if (null != dataList && !dataList.isEmpty()) {
				for (int n = 0; n < dataList.size(); n++) {
					// 在索引1的位置创建行（最顶端的行）
					XSSFRow row_value = sheet.createRow(n + 1);
					Map<String, Object> dataMap = dataList.get(n);
					for (int i = 0; i < fieldList.length; i++) {
						// 在索引0的位置创建单元格（左上端）
						XSSFCell cell = row_value.createCell(i);
						// 定义单元格为字符串类型
						// 在单元格中输入一些内容
						cell.setCellValue((dataMap.get(fieldList[i])).toString());
					}
				}
			}
			OutputStream out = new FileOutputStream(new File("G:/tmp/" + filename));
			workbook.write(out);
			out.close();
			is.close();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 导出Excel
	@SuppressWarnings("resource")
	public static void exportExcel(int len, int[][] mesh, String fileName, HttpServletResponse response,
			Object[] headList, Object[] fieldList, List<Map<String, Object>> dataList) {
		try {
			response.setHeader("content-Type", "application/vnd.ms-excel");
			// 下载文件的默认名称
			response.setHeader("Content-Disposition", "attachment;filename=" + toUtf8String(fileName) + ".xlsx");
			response.setCharacterEncoding("UTF-8");
			// 创建新的Excel 工作簿
			XSSFWorkbook workbook;
			workbook = new XSSFWorkbook();
			// 水平垂直居中
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			// 在Excel工作簿中建一工作表，其名为缺省值
			XSSFSheet sheet = workbook.createSheet();
			// 自动调整列宽
			sheet.autoSizeColumn(1, true);
			for (int n = 0; n < len; n++) {
				XSSFRow row = sheet.createRow(n);
				// 设置excel头（第一行）的头名称
				if (null != headList && headList.length > 0) {
					for (int i = 0; i < headList.length; i++) {
						int c;
						// 在索引0的位置创建单元格（左上端）
						if (mesh[i][0] == n) {
							if (mesh[i].length > 2) {
								c = mesh[i][2];
							} else {
								c = mesh[i][1];
							}
							XSSFCell cell = row.createCell(c);
							// 在单元格中输入一些内容
							cell.setCellValue(headList[i].toString());
							cell.setCellStyle(cellStyle);
							// 设置表格宽度
							sheet.setColumnWidth(i, (headList[i].toString().length() + 3) * 512);
						}
					}
				}
			}
			// 合并单元格
			if (null != headList && headList.length > 0) {
				for (int x = 0; x < headList.length; x++) {
					if (mesh[x].length > 2) {
						sheet.addMergedRegion(
								new CellRangeAddress(mesh[x][0], (short) mesh[x][1], mesh[x][2], (short) mesh[x][3]));
						sheet.setColumnWidth(x, (headList[x].toString().length() + 3) * 512);
					}
				}
			}
			// 添加数据
			if (null != dataList && !dataList.isEmpty()) {
				for (int n = 0; n < dataList.size(); n++) {
					// 在索引1的位置创建行（最顶端的行）
					XSSFRow row_value = sheet.createRow(n + len);
					Map<String, Object> dataMap = dataList.get(n);
					for (int i = 0; i < fieldList.length; i++) {
						// 在索引0的位置创建单元格（左上端）
						XSSFCell cell = row_value.createCell(i);
						// 定义单元格为字符串类型
						// 在单元格中输入一些内容
						cell.setCellValue((dataMap.get(fieldList[i])).toString());
					}
				}
			}
			workbook.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 字节转换
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	// 获得保存目录下的文件名
	@SuppressWarnings("resource")
	public static String getFileNameUrl(String fileName, HttpServletResponse response, Object[] headList,
			Object[] fieldList, List<Map<String, Object>> dataList) {
		String onlyFileName = "";
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		try {
			// FileInputStream is = new FileInputStream("excel/test.xlsx");
			InputStream is = PropsUtil.class.getResourceAsStream("/excel/test.xlsx");
			// 创建新的Excel 工作簿
			XSSFWorkbook workbook;
			workbook = new XSSFWorkbook(is);
			// 在Excel工作簿中建一工作表，其名为缺省值
			XSSFSheet sheet = workbook.getSheetAt(0);
			// 在索引0的位置创建行（最顶端的行）
			XSSFRow row = sheet.createRow(0);
			// 设置excel头（第一行）的头名称
			if (null != headList && headList.length > 0) {
				for (int i = 0; i < headList.length; i++) {
					// 自动调整列宽
					sheet.autoSizeColumn((short) i);
					// 在索引0的位置创建单元格（左上端）
					XSSFCell cell = row.createCell(i);
					// 在单元格中输入一些内容
					cell.setCellValue(headList[i].toString());
					// 设置表格宽度
					sheet.setColumnWidth(i, (headList[i].toString().length() + 3) * 512);
				}
			}
			// 添加数据
			if (null != dataList && !dataList.isEmpty()) {
				for (int n = 0; n < dataList.size(); n++) {
					// 在索引1的位置创建行（最顶端的行）
					XSSFRow row_value = sheet.createRow(n + 1);
					Map<String, Object> dataMap = dataList.get(n);
					for (int i = 0; i < fieldList.length; i++) {
						// 在索引0的位置创建单元格（左上端）
						XSSFCell cell = row_value.createCell(i);
						// 定义单元格为字符串类型
						String key = fieldList[i].toString();
						String val = dataMap.get(key).toString();
						// 在单元格中输入一些内容
						cell.setCellValue(val);
					}
				}
			}
			onlyFileName = fileName + sdf.format(now.getTime()) + ".xlsx";
			OutputStream out = new FileOutputStream(PropsUtil.get("tmppath") + onlyFileName);
			workbook.write(out);
			is.close();
			out.close();
			out.flush();
			// String path =
			// Thread.currentThread().getContextClassLoader().getResource("").getPath();
			// String[] str = path.split("target");
			String servicefileUrl = PropsUtil.get("tmppath") + onlyFileName;
			CephUtil.uploadFile(servicefileUrl, "comdownloadfile", onlyFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return onlyFileName;
	}

	// 导出Excel
	@SuppressWarnings("resource")
	public static String getexportExcelUrl(int len, int[][] mesh, String fileName, HttpServletResponse response,
			Object[] headList, Object[] fieldList, List<Map<String, Object>> dataList) {
		String onlyFileName = "";
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		try {
			InputStream is = PropsUtil.class.getResourceAsStream("/excel/test.xlsx");
			// 创建新的Excel 工作簿
			XSSFWorkbook workbook;
			workbook = new XSSFWorkbook(is);
			// 水平垂直居中
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			// 在Excel工作簿中建一工作表，其名为缺省值
			XSSFSheet sheet = workbook.getSheetAt(0);
			// 自动调整列宽
			sheet.autoSizeColumn(1, true);
			for (int n = 0; n < len; n++) {
				XSSFRow row = sheet.createRow(n);
				// 设置excel头（第一行）的头名称
				if (null != headList && headList.length > 0) {
					for (int i = 0; i < headList.length; i++) {
						int c;
						// 在索引0的位置创建单元格（左上端）
						if (mesh[i][0] == n) {
							if (mesh[i].length > 2) {
								c = mesh[i][2];
							} else {
								c = mesh[i][1];
							}
							XSSFCell cell = row.createCell(c);
							// 在单元格中输入一些内容
							cell.setCellValue(headList[i].toString());
							cell.setCellStyle(cellStyle);
							// 设置表格宽度
							sheet.setColumnWidth(i, (headList[i].toString().length() + 3) * 512);
						}
					}
				}
			}
			// 合并单元格
			if (null != headList && headList.length > 0) {
				for (int x = 0; x < headList.length; x++) {
					if (mesh[x].length > 2) {
						sheet.addMergedRegion(
								new CellRangeAddress(mesh[x][0], (short) mesh[x][1], mesh[x][2], (short) mesh[x][3]));
						sheet.setColumnWidth(x, (headList[x].toString().length() + 3) * 512);
					}
				}
			}
			// 添加数据
			if (null != dataList && !dataList.isEmpty()) {
				for (int n = 0; n < dataList.size(); n++) {
					// 在索引1的位置创建行（最顶端的行）
					XSSFRow row_value = sheet.createRow(n + len);
					Map<String, Object> dataMap = dataList.get(n);
					for (int i = 0; i < fieldList.length; i++) {
						// 在索引0的位置创建单元格（左上端）
						XSSFCell cell = row_value.createCell(i);
						// 定义单元格为字符串类型
						// 在单元格中输入一些内容
						cell.setCellValue((dataMap.get(fieldList[i])).toString());
					}
				}
			}
			onlyFileName = fileName + sdf.format(now.getTime()) + ".xlsx";
			OutputStream out = new FileOutputStream(PropsUtil.get("tmppath") + onlyFileName);
			workbook.write(out);
			is.close();
			out.close();
			out.flush();
			// String path =
			// Thread.currentThread().getContextClassLoader().getResource("").getPath();
			// String[] str = path.split("target");
			String servicefileUrl = PropsUtil.get("tmppath") + onlyFileName;
			CephUtil.uploadFile(servicefileUrl, "comdownloadfile", onlyFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return onlyFileName;
	}
}