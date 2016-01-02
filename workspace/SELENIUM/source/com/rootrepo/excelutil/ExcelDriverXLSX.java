package com.rootrepo.excelutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rootrepo.exception.SeleLibException;
import com.rootrepo.pojo.InstructionPojo;

public class ExcelDriverXLSX {
	
	static Logger logger = LoggerFactory.getLogger(ExcelDriverXLSX.class);

	//Excel driver  for Instruction POJO STARTS !

	public static ConcurrentHashMap<String,List<InstructionPojo>> getDataFromFileXLSX(String fullPathToFile, String returnFormat) {
		
		FileInputStream xlsxFileInputStream = null;
		try {
			xlsxFileInputStream = new FileInputStream(new File(fullPathToFile));
		} catch (FileNotFoundException e) {
			SeleLibException libException = new SeleLibException("FileNotFoundException path not valid : "+ fullPathToFile);
			libException.addSuppressed(e);
			logger.error(e.getMessage()+"",libException);
			//e.addSuppressed(new SeleLibException("FileNotFoundException path not valid : "+ fullPathToFile));
			//e.printStackTrace();
		}/*finally{
			if(xlsxFileInputStream != null)
				try {
					xlsxFileInputStream.close();
				} catch (IOException e) {
					SeleLibException libException = new SeleLibException("Exception while closing file Input Stream"+ fullPathToFile);
					libException.addSuppressed(e);
					logger.error(e.getMessage()+"",libException);
					//e.printStackTrace();
				}
		}*/
		
		
		if(returnFormat.equalsIgnoreCase("LIST") ){
			ConcurrentHashMap<String, List<InstructionPojo>> scenariosAsList = getDataFromFileXLSXAsLIST(xlsxFileInputStream);
			return scenariosAsList;
		}
			return null;
	}
	
	//public static List 

	public static ConcurrentHashMap<String,List<InstructionPojo>> getDataFromFileXLSXAsLIST(FileInputStream xlsxFileInputStream) {
	

		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(xlsxFileInputStream);
		} catch (IOException e) {
			SeleLibException libException = new SeleLibException("Exception while Reading file Inputsteam");
			libException.addSuppressed(e);
			logger.error(e.getMessage()+"",libException);
			//e.printStackTrace();
		}finally{
			try {
				workbook.close();
			} catch (IOException e) {
				SeleLibException libException = new SeleLibException("Exception while closing wotkbook");
				libException.addSuppressed(e);
				logger.error(e.getMessage()+"",libException);
				//e.printStackTrace();
			}
		}
		Iterator<Sheet> sheetIterator = workbook.iterator();
		int sheetCount = 0;
		ConcurrentHashMap<String, List<InstructionPojo>> instructionsHashMap = new ConcurrentHashMap<>();
		while (sheetIterator.hasNext()) {
			Sheet sheet = (Sheet) sheetIterator.next();
			List<InstructionPojo> instructionPojos = getDataFromSheetAsList(sheet);
			instructionsHashMap.put("sheet"+sheetCount, instructionPojos);
			sheetCount++;
		}
	
		return instructionsHashMap;
	}

	public static List<InstructionPojo> getDataFromSheetAsList(Sheet sheet) {

		Iterator<Row> rowIterator = sheet.iterator();
		InstructionPojo tempInstructionPojo = null;
		List<InstructionPojo> instructionPojos = new ArrayList<InstructionPojo>();
		while (rowIterator.hasNext()) {
			
			Row row = (Row) rowIterator.next();
			Iterator<Cell> cellIterator = row.iterator();
			int count = 0;
			tempInstructionPojo = new InstructionPojo();
			while (cellIterator.hasNext()) {
				
				Cell cell = (Cell) cellIterator.next();
				if(count == 0){
					tempInstructionPojo.action = cell.getStringCellValue();
					count++;
				}else {
					tempInstructionPojo.value = cell.getStringCellValue();
				}
			}
			instructionPojos.add(tempInstructionPojo);
			
		}
		return instructionPojos;
	}


	
public static ConcurrentMap<String,ConcurrentHashMap<String,String>> getDataFromFileXLSXAsMAP(String fullPathToFile, String returnFormat) {
		
		FileInputStream xlsxFileInputStream = null;
		XSSFWorkbook workbook = null;
		try {
			xlsxFileInputStream = new FileInputStream(new File(fullPathToFile));
			if(xlsxFileInputStream != null){
				try {
					workbook = new XSSFWorkbook(xlsxFileInputStream);
				} catch (IOException e) {
					SeleLibException libException = new SeleLibException("Exception while Reading file Inputsteam");
					libException.addSuppressed(e);
					logger.error(e.getMessage()+"",libException);
					//e.printStackTrace();
				}finally{
					try {
						workbook.close();
					} catch (IOException e) {
						SeleLibException libException = new SeleLibException("Exception while closing wotkbook");
						libException.addSuppressed(e);
						logger.error(e.getMessage()+"",libException);
						//e.printStackTrace();
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			SeleLibException libException = new SeleLibException("FileNotFoundException path not valid : "+ fullPathToFile);
			libException.addSuppressed(e);
			logger.error(e.getMessage()+"",libException);
			//e.addSuppressed(new SeleLibException("FileNotFoundException path not valid : "+ fullPathToFile));
			//e.printStackTrace();
		}
		
		if(xlsxFileInputStream != null && workbook != null){
		Iterator<Sheet> sheetIterator = workbook.iterator();
		int sheetCount = 0;
		ConcurrentHashMap<String, ConcurrentHashMap<String, String>> mapValuesAsMap = new ConcurrentHashMap<>();
		while (sheetIterator.hasNext()) {
			Sheet sheet = (Sheet) sheetIterator.next();
			ConcurrentHashMap<String, String> mapValues = getDataFromSheetAsMap(sheet);
			mapValuesAsMap.put("sheet"+sheetCount, mapValues);
			sheetCount++;
		}
		
		return mapValuesAsMap;
		}
		/*finally{
			if(xlsxFileInputStream != null)
				try {
					xlsxFileInputStream.close();
				} catch (IOException e) {
					SeleLibException libException = new SeleLibException("Exception while closing file Input Stream"+ fullPathToFile);
					libException.addSuppressed(e);
					logger.error(e.getMessage()+"",libException);
					//e.printStackTrace();
				}
		}*/
		
		
		/*if(returnFormat.equalsIgnoreCase("MAP") ){
			ConcurrentHashMap<String, List<InstructionPojo>> scenariosAsList = getDataFromFileXLSXAsLIST(xlsxFileInputStream);
			return scenariosAsList;
		}*/
			return null;
	}

private static ConcurrentHashMap<String, String> getDataFromSheetAsMap(
		Sheet sheet) {
	
	Iterator<Row> rowIterator = sheet.iterator();
	ConcurrentHashMap<String, String> mapValues = new ConcurrentHashMap<String, String>();
	while (rowIterator.hasNext()) {
		
		Row row = (Row) rowIterator.next();
		Iterator<Cell> cellIterator = row.iterator();
		if (cellIterator.hasNext()) {
			
			Cell cell = (Cell) cellIterator.next();
			String key = cell.getStringCellValue();
			if (cellIterator.hasNext()) {

				cell = (Cell) cellIterator.next();
				String value = cell.getStringCellValue();
				mapValues.put(key,value);
			}	
			
			
		}
		
	}
	return mapValues;
}

}
