package POI;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class POIReadTest {

	/**
	 * POI解析Excel文件内容
	 * 
	 * @author David
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {

		// 需要解析的Excel文件
		File file = new File("d:/普通用户信息导入.xls");
		try {
			// 创建Excel，读取文件内容
			HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
			// 获取第一个工作表workbook.getSheet("Sheet0");
			// HSSFSheet sheet = workbook.getSheet("Sheet0");
			// 读取默认第一个工作表sheet
			HSSFSheet sheet = workbook.getSheetAt(0);
			// 从第一行开始读取
			int firstRowNum = 2;
			// 获取sheet中最后一行行号
			int lastRowNum = sheet.getLastRowNum();
			for (int i = firstRowNum; i <= lastRowNum; i++) {
				HSSFRow row = sheet.getRow(i);
				// 获取当前行最后单元格列号
				String[] as = new String[10];
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum - 1; j++) {
					HSSFCell cell = row.getCell(j);
					String value = cell.getStringCellValue();
					as[j] = value;
					System.out.print(value + "  ");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
