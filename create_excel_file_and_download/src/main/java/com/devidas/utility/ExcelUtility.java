package com.devidas.utility;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.devidas.dto.JsonObj;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExcelUtility {

	// To fetch json data from json
	public static List<Object> toFetchData() {
		try (InputStream resourceAsastream = ExcelUtility.class.getClassLoader()
				.getResourceAsStream("static/football_team.json")) {
//			 pass InputStream to JSON-Library, e.g. using Jackson
			ObjectMapper mapper = new ObjectMapper();
			JsonObj list = mapper.readValue(resourceAsastream, JsonObj.class);
			return list.getFootballTeam();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	//To create excel-sheet
	public static XSSFWorkbook createExcelSheet() throws JsonProcessingException {
		List<Object> listData = ExcelUtility.toFetchData();

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("test_sheet");

		int rowcount = 0;
		int columnCount = 0;
		Row row = sheet.createRow(rowcount++);

		Cell hsCell = row.createCell(columnCount++);
		hsCell.setCellValue("name");
		Cell hsCell1 = row.createCell(columnCount++);
		hsCell1.setCellValue("code");
		Cell hsCell2 = row.createCell(columnCount++);
		hsCell2.setCellValue("country");

		for (Object footballTeam : listData) {
			columnCount = 0;
			Row row1 = sheet.createRow(rowcount++);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(footballTeam);
			JsonNode jsonNode = mapper.readTree(json);

			Cell cell1 = row1.createCell(columnCount++);
			cell1.setCellValue(jsonNode.get("name").textValue());

			Cell cell2 = row1.createCell(columnCount++);
			cell2.setCellValue(jsonNode.get("code").textValue());

			Cell cell3 = row1.createCell(columnCount++);
			cell3.setCellValue(jsonNode.get("country").textValue());
		}

		return workbook;
	}

}