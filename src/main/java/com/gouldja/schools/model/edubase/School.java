package com.gouldja.schools.model.edubase;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.csvreader.CsvReader;

public class School {
	
	private static final Logger logger = LogManager.getLogger(School.class);
	
	private String urn;
	private String la_code;
	private String la_name;
	private String establishment_number;
	private String establishment_name;
	private String postcode;
	
	public School(CsvReader record) {
		try {
			this.urn = record.get("URN");
			this.la_code = record.get("LA (code)");
			this.la_name = record.get("LA (name)");
			this.establishment_number = record.get("EstablishmentNumber");
			this.establishment_name = record.get("EstablishmentName");
			this.postcode = record.get("Postcode");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void debug() {
		logger.debug(this.urn +  " : " + this.establishment_name +  " - " + this.postcode);
	}
	
	public String getUrn() {
		return urn;
	}
	public String getLa_code() {
		return la_code;
	}
	public String getLa_name() {
		return la_name;
	}
	public String getEstablishment_number() {
		return establishment_number;
	}
	public String getEstablishment_name() {
		return establishment_name;
	}

}
