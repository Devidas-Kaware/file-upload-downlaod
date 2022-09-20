package com.devidas.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devidas.utility.ExcelUtility;

@RestController
@CrossOrigin
public class ExcelUtilityController {

	@GetMapping("/download")
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/vmd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=" + "testsheet.xls");
		try {
			XSSFWorkbook workbook = ExcelUtility.createExcelSheet();
			OutputStream stream = response.getOutputStream();
			workbook.write(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
