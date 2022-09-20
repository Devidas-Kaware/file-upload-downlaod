package com.devidas.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devidas.utility.ExcelUtility;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;

@RestController
@CrossOrigin
public class CSVGenrateController {

	@GetMapping("/download-csv")
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("csv");
		response.setHeader("Content-Disposition", "attachment; filename=" + "testsheet.csv");

		List<Object> listData = ExcelUtility.toFetchData();
		try {

			Writer out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));

			// create CSVWriter object filewriter object as parameter
			CSVWriter writer = new CSVWriter(out);

			// adding header to csv
			String[] header = { "Name", "Code", "Country" };
			writer.writeNext(header);

			// add data to csv
			for (Object footBallTeam : listData) {
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(footBallTeam);
				JsonNode jsonNode = mapper.readTree(json);
				String[] data = { jsonNode.get("name").asText(), jsonNode.get("code").asText(),
						jsonNode.get("country").asText() };
				writer.writeNext(data);
			}

			// closing writer connection
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
