//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.24 at 01:55:59 PM BST 
//


package com.gouldja.xsd.ukpostcodes;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gouldja.postcode.model.ukpostcodes package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gouldja.postcode.model.ukpostcodes
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Result }
     * 
     */
    public Result createResult() {
        return new Result();
    }

    /**
     * Create an instance of {@link Result.Administrative }
     * 
     */
    public Result.Administrative createResultAdministrative() {
        return new Result.Administrative();
    }

    /**
     * Create an instance of {@link Result.Geo }
     * 
     */
    public Result.Geo createResultGeo() {
        return new Result.Geo();
    }

    /**
     * Create an instance of {@link Result.Administrative.Council }
     * 
     */
    public Result.Administrative.Council createResultAdministrativeCouncil() {
        return new Result.Administrative.Council();
    }

    /**
     * Create an instance of {@link Result.Administrative.County }
     * 
     */
    public Result.Administrative.County createResultAdministrativeCounty() {
        return new Result.Administrative.County();
    }

    /**
     * Create an instance of {@link Result.Administrative.Ward }
     * 
     */
    public Result.Administrative.Ward createResultAdministrativeWard() {
        return new Result.Administrative.Ward();
    }

    /**
     * Create an instance of {@link Result.Administrative.Constituency }
     * 
     */
    public Result.Administrative.Constituency createResultAdministrativeConstituency() {
        return new Result.Administrative.Constituency();
    }

    /**
     * Create an instance of {@link Result.Administrative.Parish }
     * 
     */
    public Result.Administrative.Parish createResultAdministrativeParish() {
        return new Result.Administrative.Parish();
    }

    /**
     * Create an instance of {@link Result.Administrative.ElectoralDistrict }
     * 
     */
    public Result.Administrative.ElectoralDistrict createResultAdministrativeElectoralDistrict() {
        return new Result.Administrative.ElectoralDistrict();
    }

}