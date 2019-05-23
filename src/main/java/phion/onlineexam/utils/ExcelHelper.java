package phion.onlineexam.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import phion.onlineexam.bean.Student;


public class ExcelHelper {


	/**
	 * 此处文件处理应考虑文件类型
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static List<Student> getDataAsStudent(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		List students = new ArrayList<Student>();
		//  获取每一个工作薄
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			HSSFRow hssfRow = hssfSheet.getRow(0);
			if(hssfRow==null) return students;
			if(!fontOk(hssfRow)) return students;
			// 获取当前工作薄的每一行
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				hssfRow = hssfSheet.getRow(rowNum);
				String stuNumber = getValue(hssfRow.getCell(0));
				String stuName = getValue(hssfRow.getCell(1));
				String stuClass = getValue(hssfRow.getCell(2));
				Student s = new Student(stuNumber, stuName, stuClass);
				//System.out.println(s);
				students.add(s);
			}
		}
		return students;
	}
	/**
	 * 获取当前工作薄的每一行
	 * @param hssfRow
	 * @return
	 */
	private static boolean fontOk(HSSFRow hssfRow) {
		if(!getValue(hssfRow.getCell(0)).equals("学号")) return false;
		if(!getValue(hssfRow.getCell(1)).equals("姓名")) return false;
		if(!getValue(hssfRow.getCell(2)).equals("班级")) return false;
		return true;
	}

	//判断excel表数据是否合理
	private static String getValue(HSSFCell hssfRow) {

		if (hssfRow.getCellType() == hssfRow.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfRow.getBooleanCellValue());
		} else if (hssfRow.getCellType() == hssfRow.CELL_TYPE_NUMERIC) {
			BigDecimal bg = new BigDecimal(hssfRow.getNumericCellValue());
			return bg.toPlainString();
		} else {
			return String.valueOf(hssfRow.getStringCellValue());
		}
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder filePath = new StringBuilder();
		filePath.append(System.getProperty("user.dir"));
		//System.out.println(path.toString());
		///OnlineExam/src/test/file/数据结构_学生名单.xls
		filePath.append(File.separator).append("src")
			.append(File.separator).append("test")
			.append(File.separator).append("file")
			.append(File.separator).append("数据结构_学生名单.xls");
	
		File file = new File(filePath.toString());
		List<Student> students =getDataAsStudent(file);
		System.out.println(Arrays.toString(students.toArray()));
	}
}
