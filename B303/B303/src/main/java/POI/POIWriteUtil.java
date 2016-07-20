package POI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import Util.DateUtil;

public class POIWriteUtil{
	public static void main(String[] args) {
			String[] title = {"id","name","sex"};
			
			//创建Excel工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
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
			//追加数据
			for (int i = 1; i <= 10; i++) {
				HSSFRow nextrow = sheet.createRow(i);
				HSSFCell cell2 = nextrow.createCell(0);
				cell2.setCellValue("a" + i);
				cell2 = nextrow.createCell(1);
				cell2.setCellValue("user" + i);
				cell2 = nextrow.createCell(2);
				cell2.setCellValue("男");
			}
			//创建一个文件
			
			String fileName=DateUtil.format("yyyyMMddHHmmss", new Date())+".xls";
			File file = new File("d:/"+fileName);
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
	
	public  void  makeHSSF(File file){
		//创建Excel工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		String[] title = {"id","name","sex"};
		List list=new ArrayList<>();
		addData(title,workbook,list);
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
	public  void  addData(String[] title,HSSFWorkbook workbook,List data){
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
//				for (int j = 0; j < nextrow.length; j++) {
//					cell2= nextrow.createCell(j);
//					cell2.setCellValue("a" + i);
//				}
				cell2= nextrow.createCell(0);
				cell2.setCellValue("a" + i);
				cell2 = nextrow.createCell(1);
				cell2.setCellValue("user" + i);
				cell2 = nextrow.createCell(2);
				cell2.setCellValue("男");
			}
		}else {
			HSSFRow nextrow = sheet.createRow(1);
			HSSFCell cell2 =nextrow.createCell(0);
			cell2.setCellValue("本次查询没有数据！");
		}
	}
	
}
