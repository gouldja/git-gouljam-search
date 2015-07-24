package com.gouldja.schools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.csvreader.CsvReader;
import com.gouldja.schools.model.edubase.School;

public class Test {
	
	private static final Logger logger = LogManager.getLogger(Test.class);

	public static void main(String[] args) {
		logger.debug("Running");
		
		
		
		try {
			URL url = new URL("file:///C:/Users/OFFICE/Downloads/edubasealldata20150724.csv");
			

			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			CsvReader schools = new CsvReader(in);
			
			schools.readHeaders();
			

			while (schools.readRecord())
			{
				School s = new School(schools);
				//s.debug();
			}
			
			in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		logger.debug("Finished");
		
		

	}

}
