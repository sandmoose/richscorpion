package com.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;



public class ExcelHandler {
	
	private static final String EXCEL_INPUT_PATH = "resources/excel/input/";
	
//	private static final Logger LOG = Logger.getLogger(ExcelHandler.class);
	
	private static HSSFSheet inputSheet;

	private static String OUTPUT_EXCEL_NAME="OutputSheet";
	
	
	//write map to output sheet with time stamp
	public static void writeToOutputSheet(Map <String, List<TestResultBean>> reportMap){
		try{
			String timeStamp = new SimpleDateFormat("MM-dd-YY_HH-mm").format(new Date());
			String finalOutputSheet = OUTPUT_EXCEL_NAME+"-"+ timeStamp +".xls";
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFCellStyle cellStyle = workBook.createCellStyle();
			HSSFFont fontBold = workBook.createFont();
			fontBold.setBoldweight((short)Font.BOLDWEIGHT_BOLD);
			cellStyle.setFont(fontBold);
			for (String testCaseId: reportMap.keySet()){
				List<TestResultBean> beanList = reportMap.get(testCaseId);
				int rowNum = 1;
				String sheetName = testCaseId;
				HSSFSheet workSheet = workBook.createSheet(sheetName);
				createHeader(workSheet, cellStyle);
				for(TestResultBean bean : beanList){
					HSSFRow row = workSheet.createRow(rowNum);
					HSSFCell firstCell = row.createCell(0);
					firstCell.setCellValue(bean.getTestId());
					HSSFCell secondCell = row.createCell(1);
					secondCell.setCellValue(bean.getAction());
					secondCell.getCellStyle().setWrapText(true);
					HSSFCell thirdCell = row.createCell(2);
					thirdCell.setCellValue (bean.getResult());
					HSSFCell fourthCell = row.createCell(3);
					fourthCell.setCellValue(bean.getUserName());
					fourthCell.getCellStyle().setWrapText(false);
					HSSFCell fifthCell = row.createCell(4);
					fifthCell.setCellValue(bean.getPassword());
					HSSFCell sixthCell = row.createCell(5);
					sixthCell.setCellValue(bean.getBrowser());
					HSSFCell seventhCell = row.createCell(6);
					seventhCell.setCellValue(bean.getException());
					seventhCell.getCellStyle().setWrapText(true);
					rowNum++;
				}
				FileOutputStream fileOut = new FileOutputStream("resources/excel/output/"+finalOutputSheet);
				workBook.write(fileOut);
				fileOut.close();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	//create header row
	private static void createHeader (HSSFSheet workSheet, HSSFCellStyle cellStyle){
		HSSFRow row = workSheet.createRow(0);
		HSSFCell firstCell =row.createCell(0);
		workSheet.setColumnWidth(0,  8500);
		firstCell.setCellValue("Test Case Id");
		firstCell.setCellStyle(cellStyle);
		HSSFCell secondCell = row.createCell(1);
		workSheet.setColumnWidth(1,  10000);
		secondCell.setCellValue("Action");
		secondCell.setCellStyle(cellStyle);
		HSSFCell thirdCell = row.createCell(2);
		workSheet.setColumnWidth(2,  10000);
		thirdCell.setCellValue("Result");
		thirdCell.setCellStyle(cellStyle);
		HSSFCell fourthCell = row.createCell(3);
		workSheet.setColumnWidth(3,  4000);
		fourthCell.setCellValue("User Id");
		fourthCell.setCellStyle(cellStyle);
		HSSFCell fifthCell = row.createCell(4);
		workSheet.setColumnWidth(4,  4000);
		fifthCell.setCellValue("Password");
		fifthCell.setCellStyle(cellStyle);
		HSSFCell sixthCell = row.createCell(5);
		workSheet.setColumnWidth(5,  6000);
		sixthCell.setCellValue("Browser");
		sixthCell.setCellStyle(cellStyle);
		HSSFCell seventhCell = row.createCell(6);
		workSheet.setColumnWidth(6, 18000);
		seventhCell.setCellValue("Exception");
		seventhCell.setCellStyle(cellStyle);
	}

	//read sheet
	public static HSSFSheet readInputSheet(String inputFileName, String sheetName) throws IOException{
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(EXCEL_INPUT_PATH+inputFileName);
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			inputSheet = wb.getSheet(sheetName);
		}catch(Error e){
			fis.close();
		}catch(Exception e){
			fis.close();
		}
		return inputSheet;
	}

	//read cell by Name
	public static String getCellString(String cellName){
		CellReference cellReference = new CellReference(cellName);
		Row row = inputSheet.getRow(cellReference.getRow());
		Cell cell = row.getCell(cellReference.getCol());
		return getCellValue(cell);
	}
	
	public String getRow(CellReference cellReference){
		return null;
		//return sheet.get
	}
	
	public Cell getCell(Row row, CellReference cellReference){
		Cell cell = null;
		if(row != null){
			cell = row.getCell(cellReference.getCol(), Row.RETURN_BLANK_AS_NULL);
		}
		return cell;
	}

	public static String getCellValue(Cell cell){
		String value = null;
		if(cell != null){
			switch(cell.getCellType()){
			case Cell.CELL_TYPE_NUMERIC:
				value = Double.toString(cell.getNumericCellValue());
				break;

			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;

			case Cell.CELL_TYPE_BOOLEAN:
				value = Boolean.toString(cell.getBooleanCellValue());
				break;
			}
		}
		return value;
	}

}
