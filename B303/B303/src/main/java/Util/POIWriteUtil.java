package Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class POIWriteUtil{
	public static String makeBalance(List data,String path,String fileName) {
		Map<String, Object> map=new HashMap<>();
			String[] title = {"id","number","orderDate","totalPrice","payType","phone","addr","status"};
			map.put("title", title);
			//创建一个文件
			//String fileName=DateUtil.format("yyyyMMddHHmmss", new Date())+".xls";
			File file = new File(path+File.separator+fileName);
			map.put("file", file);
			map.put("data", data);
			//System.out.println("getAbsolutePath"+file.getAbsolutePath());
			//System.out.println("getPath"+file.getPath());
			makeHSSF(map);
			return  fileName;
		}
	public static void  makeHSSF(Map<String, Object> map){
		//创建Excel工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		addData((String[])map.get("title"),workbook,(List)map.get("data"));
		File file=(File) map.get("file");
		try {
			file.createNewFile();
			//将Excel内容存盘
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void  addData(String[] title,HSSFWorkbook workbook,List data){
				//创建一个工作表sheet
				HSSFSheet sheet = workbook.createSheet();
				//创建第一行
				HSSFRow row = sheet.createRow(0);
				HSSFCell cell = null;
				//插入第一行数据 id,name,sex
				for (int i = 0; i < title.length; i++) {
					cell = row.createCell(i);
					cell.setCellValue(title[i]);
				}
		if(!data.isEmpty()){
				for (int i = 1; i <= data.size(); i++) {
					HSSFRow nextrow = sheet.createRow(i);
					HSSFCell cell2 =null;
//					for (int j = 0; j <=5; j++) {
//						cell2= nextrow.createCell(j);
//						cell2.setCellValue("a" + i);
//					}
					// {"id","number","orderDate","totalPrice","payType","phone","addr","status"};
					cell2= nextrow.createCell(0);
					cell2.setCellValue("a" + i);
//					Order user=(Order) data.get(i-1);
//					cell2= nextrow.createCell(0);
//					cell2.setCellValue(user.getId());
//					cell2 = nextrow.createCell(1);
//					cell2.setCellValue(user.getNumber());
//					cell2 = nextrow.createCell(2);
//					cell2.setCellValue(user.getOrderDate());
//					cell2= nextrow.createCell(3);
//					cell2.setCellValue(user.getTotalPrice());
//					cell2 = nextrow.createCell(4);
//					cell2.setCellValue(user.getPayType().ordinal());
//					cell2 = nextrow.createCell(5);
//					cell2.setCellValue(user.getPhone());
//					cell2 = nextrow.createCell(6);
//					cell2.setCellValue(user.getAddr());
//					cell2 = nextrow.createCell(7);
//					cell2.setCellValue(user.getStatus().ordinal());
					
				}
		}else {
			HSSFRow nextrow = sheet.createRow(1);
			HSSFCell cell2 =nextrow.createCell(0);
			cell2.setCellValue("本次查询没有数据！");
		}
	}
	
	
	
}
