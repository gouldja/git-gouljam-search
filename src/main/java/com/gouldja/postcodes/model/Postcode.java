package com.gouldja.postcodes.model;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.gouldja.common.framework.ApplicationConfiguration;
import com.gouldja.common.framework.JsonReader;

public class Postcode {
	
	private static final Logger logger = LogManager.getLogger(Postcode.class);
	
	String 		postcode;
	Integer 	eastings;
	Integer 	northings;
	Double	 	longitude;
	Double 		latitude;
	String 		country;
	String		nhs_ha;
	String 		parliamentary_constituency;
	String		primary_care_trust;
	String 		lsoa;
	String		msoa;
	String		nuts;
	String		incode;
	String		outcode;
	String		admin_district;
	String		parish;
	String		admin_county;
	String		admin_ward;
	String		ccg;
	
	private String API = ApplicationConfiguration.getApplicationProperty("postcode.io.api");
	
	
	public static void main(String[] argv) {
		
		Postcode p = new Postcode("AL11AA");
		
	}
	
	public Postcode(String code) {
		
		this.postcode = code.toString();
		try {
			JSONObject json = JsonReader.readJsonFromUrl(API + code);
			
			if (json != null) {
				
				JSONObject result = json.getJSONObject("result");
				
				this.postcode = result.getString("postcode");
				this.eastings = result.getInt("eastings");
				this.northings = result.getInt("northings");
				this.longitude = result.getDouble("longitude");
				this.latitude = result.getDouble("latitude");
				this.country = result.getString("country");
				this.nhs_ha = result.getString("nhs_ha");
				this.parliamentary_constituency = result.getString("parliamentary_constituency");
				this.primary_care_trust = result.getString("primary_care_trust");
				this.lsoa = result.getString("lsoa");
				this.msoa = result.getString("msoa");
				this.nuts = result.getString("nuts");
				this.incode = result.getString("incode");
				this.outcode = result.getString("outcode");
				this.admin_district = result.getString("admin_district");
				this.parish = result.getString("parish");
				try{this.admin_county = result.getString("admin_county"); }catch (JSONException e) {this.admin_county = "";}
				this.admin_ward = result.getString("admin_ward");
				this.ccg = result.getString("ccg");
			
				
				logger.debug(this.postcode + " ' " + this.longitude + " ' " + this.latitude);
			}
			
			
		} catch (JSONException e) {
			logger.debug(code);;
			e.printStackTrace();
			this.postcode = code.toString();
		} catch (IOException e) {
			e.printStackTrace();
			this.postcode = code.toString();
		}
		
	}
	
	
	
	public static Logger getLogger() {
		return logger;
	}
	public String getPostcode() {
		return postcode;
	}
	public Integer getEastings() {
		return eastings;
	}
	public Integer getNorthings() {
		return northings;
	}
	public Double getLongitude() {
		return longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public String getCountry() {
		return country;
	}
	public String getNhs_ha() {
		return nhs_ha;
	}
	public String getParliamentary_constituency() {
		return parliamentary_constituency;
	}
	public String getPrimary_care_trust() {
		return primary_care_trust;
	}
	public String getLsoa() {
		return lsoa;
	}
	public String getMsoa() {
		return msoa;
	}
	public String getNuts() {
		return nuts;
	}
	public String getIncode() {
		return incode;
	}
	public String getOutcode() {
		return outcode;
	}
	public String getAdmin_district() {
		return admin_district;
	}
	public String getParish() {
		return parish;
	}
	public String getAdmin_county() {
		return admin_county;
	}
	public String getAdmin_ward() {
		return admin_ward;
	}
	public String getCcg() {
		return ccg;
	}

}
