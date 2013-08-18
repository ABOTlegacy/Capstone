package com.tek271.reverseProxy.model;

public class ModelFactory {
  public static final Mapping MAPPING1= new Mapping("maps.google.com, localhost:8080, /rp/maps");
  public static final Mapping MAPPING2= new Mapping("www.facebook.com, localhost:8080, /rp/fb");
  public static final Mapping MAPPING3= new Mapping("http://140.104.69.94:8080/RestfulWebService/bti/Company/All, localhost:8080, /rp/company/all");
  public static final Mappings MAPPINGS = Mappings.create(MAPPING1, MAPPING2, MAPPING3);
  
  
}
