package util;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import java.io.FileOutputStream;
import java.sql.RowIdLifetime;

import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;
	private static HSSFSheet ExcelWSheetH;
	private static XSSFWorkbook ExcelWBook;
	private static HSSFWorkbook ExcelWBookH;
	private static XSSFCell Cell;
	private static HSSFCell HCell;
	private static HSSFWorkbook excelObject;
	//HSSF is the POI Project's pure Java implementation of the Excel '97 file format. XSSF is the POI Project's pure Java implementation of the Excel 2007 OOXML (.xlsx) file format.
	private static XSSFRow Row;
	private static HSSFRow HRow;
	private static String extension;
	private static UtilityFileReader configReader=new UtilityFileReader();

//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

public static void setExcelFile() throws Exception {
	try {

			// Open the Excel file
		String path=configReader.getExcelPath();
		FileInputStream ExcelFile = new FileInputStream(path);
		extension = path.substring(path.lastIndexOf("."));
		if (extension.equals(".xlsx"))
				{
				// Access the required workbook
				ExcelWBook = new XSSFWorkbook(ExcelFile);
				// Access the required test data sheet
			
				ExcelWSheet = ExcelWBook.getSheet(configReader.getSheetName());
				}
		else
			{ExcelWBookH = new HSSFWorkbook(ExcelFile);
			// Access the required test data sheet
			ExcelWSheetH = ExcelWBookH.getSheet(configReader.getSheetName());
				
			}
		} 
		catch (Exception e){
			System.out.println("File creation"+e.getMessage());
			throw (e);
		}

}

//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num


public static String getCellData(int RowNum, int ColNum) throws Exception{

	try{
		if (extension.equals(".xlsx"))
			{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
	
			String CellData = Cell.getStringCellValue();
	
			return CellData;
	
			}
		else
		{
			HCell = ExcelWSheetH.getRow(RowNum).getCell(ColNum);

			String CellData = HCell.getStringCellValue();

			return CellData;
		}
	}
		catch (Exception e){	System.out.println("File reading"+e.getMessage());
		return"";
	
			}
			
}


//This method is to write in the Excel cell, Row num and Col num are the parameters

public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{

		try{
			FileOutputStream fileOut = new FileOutputStream(configReader.getExcelPath());
			if (extension.equals(".xlsx"))
			{
			Row  = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum);
		
		if (Cell == null) {

			Cell = Row.createCell(ColNum);

			Cell.setCellValue(Result);
		
			} else {

				Cell.setCellValue(Result);
				
			}
			

				ExcelWBook.write(fileOut);

				fileOut.flush();

				fileOut.close();
			}
			else
			{
				HRow  = ExcelWSheetH.getRow(RowNum);
				System.out.println("cell accessed");
			HCell = HRow.getCell(ColNum);

			if (HCell == null) {

				HCell = HRow.createCell(ColNum);

				HCell.setCellValue(Result);
				
				} else {

					HCell.setCellValue(Result);
					

				}
			
					ExcelWBookH.write(fileOut);

					fileOut.flush();

					fileOut.close();
			}
			}catch(Exception e){
				System.out.println("File writing"+e.getMessage());
				throw (e);
			}
		}
		

}