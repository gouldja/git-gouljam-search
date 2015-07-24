package com.gouldja.postcodes.model.ukpostcodes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gouldja.common.framework.ApplicationConfiguration;
import com.gouldja.xsd.ukpostcodes.Result;
import com.sun.tools.xjc.runtime.JAXBContextFactory;

public class PostcodeTest {
	
	private static final Logger logger = LogManager.getLogger(PostcodeTest.class);

	public static void main(String[] args) {
		logger.debug("Running Message");
		
		logger.debug(ApplicationConfiguration.getApplicationProperty("app.name"));
		
		PostcodeTest test = new PostcodeTest();
		test.getPostcode("AL1 1AJ");

		
	}
	
	public void getPostcode(String code) {
		
		try {
			URL url = new URL("http://uk-postcodes.com/postcode/"+code+".xml");
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(url.openStream());
			NodeList nodes = doc.getElementsByTagName("geo");
			System.out.println(nodes.getLength() + " nodes found");
			
			JAXBContext context =  JAXBContext.newInstance(com.gouldja.xsd.ukpostcodes.Result.class);
			Unmarshaller un = context.createUnmarshaller();
			com.gouldja.xsd.ukpostcodes.Result postcode =  (Result) un.unmarshal(doc);
			
			logger.debug(postcode.getGeo().getEasting());
			logger.debug(postcode.getAdministrative().getCouncil().getTitle());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
