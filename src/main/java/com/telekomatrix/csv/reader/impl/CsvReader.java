package com.telekomatrix.csv.reader.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.telekomatrix.csv.reader.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CsvReader implements ApplicationListener<ApplicationReadyEvent> {
	

	public void reader() {

		try {
			CsvMapper csvMapper = new CsvMapper();

			CsvSchema csvSchema = csvMapper.typedSchemaFor(Map.class).withHeader();
			MappingIterator<Map<String, String>> it = csvMapper.readerFor(Map.class)
					.with(csvSchema.withColumnSeparator(CsvSchema.DEFAULT_COLUMN_SEPARATOR))
					.readValues(CsvReader.class.getClassLoader().getResource("reader_file.csv"));
			List<Map<String, String>> listOfMapSchema = it.readAll();

			for (Map<String, String> map : listOfMapSchema) {
				System.out.println(map.get("FirstName"));
				System.out.println(map.get("MiddleName"));
				System.out.println(map.get("Surname"));
				System.out.println(map.get("Age"));
				System.out.println(map.get("City"));
				System.out.println(map.get("When"));
//				Customer customer = new Customer(map.get("FirstName"), map.get("MiddleName"), map.get("surname"), map.get("age"), map.get("city"), map.get("year"));
			}

		} catch (IOException ex) {

		}
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		reader();
		
	}

}
