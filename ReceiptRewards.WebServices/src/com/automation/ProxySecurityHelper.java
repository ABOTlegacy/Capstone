package com.automation;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;

public class ProxySecurityHelper {

    
    
    /**
     * Automates the Proxy Security
     */
    public static void doProxySecurity(WebDriver driver, String url) {
        System.out.println("BATMAN: URL TO HACK IS = " + url);
        int x = Math.round((float)Math.random() * 3);
        switch(x) {
            case 0: {
                ProxySecurityHelper.source01(driver, url);
                break;
            }
            case 1: {
                ProxySecurityHelper.source02(driver, url);
                break;
            }
            case 2: {
                ProxySecurityHelper.source03(driver, url);
                break;
            }
            case 3: {
                ProxySecurityHelper.source04(driver, url);
                break;
            }
            default: {
                System.out.println("BATWING: Failed");
                break;
            }
            
        }
    }
    
    
    
    /**
     * http://find-proxy.com/ 
     */
    public static void source01(WebDriver driver, String url) {
        // Instantiate Variables
        List<Element> elements = new ArrayList<Element>();
        
        // URL
        Element step01 = new Element();
        step01.setValue("http://find-proxy.com/");
        step01.setType("URL");
        elements.add(step01);
        
        // Textbox
        Element step02 = new Element();
        step02.setValue(url);
        step02.setType("TEXTBOX");
        List<Identifier> identifiers02 = new ArrayList<Identifier>();
        Identifier identifier02 = new Identifier();
        identifier02.setValue("address_box");
        identifier02.setType("ID");
        identifiers02.add(identifier02);
        step02.setIdentifiers(identifiers02);
        elements.add(step02);
        
        // Submit
        Element step03 = new Element();
        step03.setValue(url);
        step03.setType("BUTTON");
        List<Identifier> identifiers03 = new ArrayList<Identifier>();
        Identifier identifier03 = new Identifier();
        identifier03.setValue("go");
        identifier03.setType("ID");
        identifiers03.add(identifier03);
        step03.setIdentifiers(identifiers03);
        elements.add(step03);
        
        // Perform Actions
        for(int i = 0; i < elements.size(); i++) {
            AutomationHelper.performElementAutomation(driver, elements.get(i));
        }
    }

    
    
    /**
     * http://baramiji.info/
     */
    public static void source02(WebDriver driver, String url) {
        // Instantiate Variables
        List<Element> elements = new ArrayList<Element>();
        
        // URL
        Element step01 = new Element();
        step01.setValue("http://baramiji.info/");
        step01.setType("URL");
        elements.add(step01);
        
        // Textbox
        Element step02 = new Element();
        step02.setValue(url);
        step02.setType("TEXTBOX");
        List<Identifier> identifiers02 = new ArrayList<Identifier>();
        Identifier identifier02 = new Identifier();
        identifier02.setValue("address_box");
        identifier02.setType("ID");
        identifiers02.add(identifier02);
        step02.setIdentifiers(identifiers02);
        elements.add(step02);
        
        // Submit
        Element step03 = new Element();
        step03.setValue(url);
        step03.setType("BUTTON");
        List<Identifier> identifiers03 = new ArrayList<Identifier>();
        Identifier identifier03 = new Identifier();
        identifier03.setValue("go");
        identifier03.setType("ID");
        identifiers03.add(identifier03);
        step03.setIdentifiers(identifiers03);
        elements.add(step03);
        
        // Perform Actions
        for(int i = 0; i < elements.size(); i++) {
            AutomationHelper.performElementAutomation(driver, elements.get(i));
        }
    }

    
    
    /**
     * http://lmjarab.com/
     */
    public static void source03(WebDriver driver, String url) {
        // Instantiate Variables
        List<Element> elements = new ArrayList<Element>();
        
        // URL
        Element step01 = new Element();
        step01.setValue("http://lmjarab.com/");
        step01.setType("URL");
        elements.add(step01);
        
        // Textbox
        Element step02 = new Element();
        step02.setValue(url);
        step02.setType("TEXTBOX");
        List<Identifier> identifiers02 = new ArrayList<Identifier>();
        Identifier identifier02 = new Identifier();
        identifier02.setValue("address_box");
        identifier02.setType("ID");
        identifiers02.add(identifier02);
        step02.setIdentifiers(identifiers02);
        elements.add(step02);
        
        // Submit
        Element step03 = new Element();
        step03.setValue(url);
        step03.setType("BUTTON");
        List<Identifier> identifiers03 = new ArrayList<Identifier>();
        Identifier identifier03 = new Identifier();
        identifier03.setValue("browse");
        identifier03.setType("ID");
        identifiers03.add(identifier03);
        step03.setIdentifiers(identifiers03);
        elements.add(step03);
        
        // Perform Actions
        for(int i = 0; i < elements.size(); i++) {
            AutomationHelper.performElementAutomation(driver, elements.get(i));
        }
    }

    
    
    /**
     * http://appfire.info/
     */
    public static void source04(WebDriver driver, String url) {
        // Instantiate Variables
        List<Element> elements = new ArrayList<Element>();
        
        // URL
        Element step01 = new Element();
        step01.setValue("http://appfire.info/");
        step01.setType("URL");
        elements.add(step01);
        
        // Textbox
        Element step02 = new Element();
        step02.setValue(url);
        step02.setType("TEXTBOX");
        List<Identifier> identifiers02 = new ArrayList<Identifier>();
        Identifier identifier02 = new Identifier();
        identifier02.setValue("minime_url_textbox");
        identifier02.setType("ID");
        identifiers02.add(identifier02);
        step02.setIdentifiers(identifiers02);
        elements.add(step02);
        
        // Submit
        Element step03 = new Element();
        step03.setValue(url);
        step03.setType("BUTTON");
        List<Identifier> identifiers03 = new ArrayList<Identifier>();
        Identifier identifier03 = new Identifier();
        identifier03.setValue("minime_submit");
        identifier03.setType("ID");
        identifiers03.add(identifier03);
        step03.setIdentifiers(identifiers03);
        elements.add(step03);
        
        // Perform Actions
        for(int i = 0; i < elements.size(); i++) {
            AutomationHelper.performElementAutomation(driver, elements.get(i));
        }
    }
}