package com.automation;

import com.common.AliasHelper;
import com.implementations.AutomationImpl;
import com.implementations.CodeImpl;
import com.implementations.CommandElementImpl;
import com.implementations.RevisionImpl;
import com.implementations.RewardImpl;
import com.implementations.SpeedyImpl;
import com.implementations.SubmissionImpl;
import com.implementations.SubmissionRewardImpl;
import com.model.Automation;
import com.model.Code;
import com.model.CommandElement;
import com.model.CommandIdentifier;
import com.model.Question;
import com.model.Revision;
import com.model.Submission;
import com.model.SubmissionReward;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

public class AutomationHelper {
    private static List<Element> _elements;
// @TODO: Add so that if an automation needs an element but wasn't in the answer, then it just won't display
    
    /**
     * @Gets a List of Commands
     * @param commandIdentifiers
     * @return 
     */
    public static List<Element> getElements(List<CommandElement> commandElements, Submission submission) {
        // Instantiate Variables
        List<Element> elements = new ArrayList<Element>();
        List<Identifier> identifiers;
        String answer;
        
        // Transfer Command Elements Into Elements
        for(int i = 0; i < commandElements.size(); i++) {
            answer = commandElements.get(i).getTestData();
            
            // Get The User Submitted Answers
            if(commandElements.get(i).getCode().getCode().equals("TEXTBOX") || commandElements.get(i).getCode().getCode().equals("TEXTAREA") || commandElements.get(i).getCode().getCode().equals("RADIO") || commandElements.get(i).getCode().getCode().equals("CHECKBOX") || commandElements.get(i).getCode().getCode().equals("SELECT_BOX")) {
                if(submission != null && commandElements.get(i).getFormElements().size() > 0) {
                    for(int j = 0; j < submission.getSubmissionAnswers().size(); j++) {
                        if(submission.getSubmissionAnswers().get(j).getFormElementId() == commandElements.get(i).getFormElements().get(0).getFormElementId()) {
                            answer = submission.getSubmissionAnswers().get(j).getValue();
                            break;
                        }
                    }
                }
            }
            
            // Set the Identifiers
            identifiers = AutomationHelper.getIdentifiers(commandElements.get(i).getCommandIdentifiers());
            if(commandElements.get(i).getCode().getCode().equals("RADIO") || commandElements.get(i).getCode().getCode().equals("CHECKBOX")) {
                for(int j = 0; j < identifiers.size(); j++) {
                    identifiers.get(j).setValue(answer);
                }
            }
            
            // Add the Final Element
            elements.add(new Element(commandElements.get(i).getCommandElementId(), commandElements.get(i).getCode().getCode(), answer, identifiers));
        }
        
        // Return Elements
        return elements;
    }
    
    
    
    /**
     * @description Gets a list of Identifiers
     * @param commandIdentifiers
     * @return 
     */
    public static List<Identifier> getIdentifiers(List<CommandIdentifier> commandIdentifiers) {
        // Instantiate Variables
        List<Identifier> identifiers = new ArrayList<Identifier>();
        for(int i = 0; i < commandIdentifiers.size(); i++) {
            Code code = CodeImpl.findByCodeId(commandIdentifiers.get(i).getCode().getCodeId());
            identifiers.add(new Identifier(code.getCode(), commandIdentifiers.get(i).getValue()));
        }
        return identifiers;
    }

    
    
    /**
     * @description Gets the Web Element from the Driver
     * @param driver
     * @param identifiers
     * @return Web Element from the Driver
     */
    public static WebElement getWebElement(WebDriver driver, List<Identifier> identifiers) {
        // Instantiate Variables
        WebElement webElement = null;
        By by = null;
        
        // Check to see if value is null
        boolean valueNull = false;
        if(identifiers.get(0).getValue().isEmpty()) {
            System.out.println("CATWOMAN: IT IS EMPTY");
            valueNull = true;
        }
        
        // Find the By Identifier
        if(! valueNull) {
            if(identifiers.get(0).getType().equals("ID")) {
                by = By.id(identifiers.get(0).getValue());
            } else if(identifiers.get(0).getType().equals("NAME")) {
                by = By.name(identifiers.get(0).getValue());
            } else if(identifiers.get(0).getType().equals("CLASS")) {
                by = By.className(identifiers.get(0).getValue());
            } else if(identifiers.get(0).getType().equals("XPATH")) {
                System.out.println("CATWOMAN: XPATH = " + identifiers.get(0).getValue());
                by = By.xpath(identifiers.get(0).getValue());
            } else if(identifiers.get(0).getType().equals("SKIPPABLE") || identifiers.get(0).getType().equals("DEPENDS_ON")) {
                // Nothing, but don't log error
            } else {
                // @TODO: Log Error
                System.out.print("CATWOMAN: IDENTIFIER ERROR");
                return null;
            }
        }
        
        // Find the Element
        for(int i = 0; i < identifiers.size(); i++) {
            if(identifiers.get(i).getType().equals("SKIPPABLE")) {
                
                // If valueNull then just skip immediately
                if(valueNull) {
                    return null;
                }
                
                // Try to find element else, just break
                if (! driver.findElements(by).isEmpty()) {
                    break;
                } else {
                    System.out.print("CATWOMAN: FIND ELEMENT ERROR");
                    return null;
                }
            }
        }
        webElement = driver.findElement(by);
        
        // Determine That Element is Dependent on Another Element
        for(int i = 0; i < identifiers.size(); i++) {
            if(identifiers.get(i).getType().equals("DEPENDS_ON")) {
                boolean found = false;
                for(int j = 0; j < AutomationHelper._elements.size(); j++) {
                    if(identifiers.get(i).getValue().equals(String.valueOf(AutomationHelper._elements.get(j).getElementId()))) {
                        if(AutomationHelper.getWebElement(driver, AutomationHelper._elements.get(j).getIdentifiers()) == null) {
                            //System.out.println("CATWOMAN: SKIPPED DUE TO DEPENDS_ON");
                            return null;
                        } else {
                            found = true;
                        }
                    }
                }
                if(! found) {
                    //System.out.println("CATWOMAN: SKIPPED DUE TO DEPENDS_ON");
                    return null;
                }
            }
        }
        
        // Return
        return webElement;
    }
    
    
    
    /**
     * @description Does the corresponding action depending on the Element Type
     * @param webElement
     * @param element 
     */
    public static void performElementAutomation(WebDriver driver, Element element) {
        // Instantiate Variables
        WebElement webElement = null;
        System.out.println("MR.FREEZE: CommandElementID = " + element.getElementId());
        // URL
        if(element.getType().equals("URL")) {
            driver.get(element.getValue());
            
        // Radio Button
        } else if(element.getType().equals("PROXY_SECURITY")) {
            System.out.println("Proxy Security is about to begin");
            ProxySecurityHelper.doProxySecurity(driver, driver.getCurrentUrl());
            
        } else { // Need to get WebElement
            webElement = AutomationHelper.getWebElement(driver, element.getIdentifiers());
            if (webElement != null) {
                // Textbox
                if(element.getType().equals("TEXTBOX")) {
                    webElement.sendKeys(element.getValue());

                // Textarea
                } else if(element.getType().equals("TEXTAREA")) {
                    webElement.sendKeys(element.getValue());

                // Radio Button
                } else if(element.getType().equals("RADIO")) {
                    webElement.click();

                // Select Box
                } else if(element.getType().equals("SELECT_BOX")) {
                    if(new Select(webElement).getOptions().size() > 0){ // All Options
                        List<WebElement> lstOptions = new Select(webElement).getOptions();
                        for(int j = 0; j < lstOptions.size(); j++){
                            if(lstOptions.get(j).getText() != null && lstOptions.get(j).getText().equals(element.getValue())){
                                new Select(webElement).selectByIndex(j);
                                break;
                            }else if(lstOptions.get(j).getAttribute("value") != null && lstOptions.get(j).getAttribute("value").equals(element.getValue())){
                                new Select(webElement).selectByIndex(j);
                                break;
                            }
                        }
                    }

                // Button
                } else if(element.getType().equals("BUTTON")) {
                    webElement.click();

                // Submit
                } else if(element.getType().equals("SUBMIT")) {
                    webElement.submit();

                } else {
                    // @TODO: Log Element Automation Error
                    System.out.print("MR.FREEZE: ERROR");
                }
            } else {
                // @TODO: Log Element Automation Error
                System.out.print("MR.FREEZE: ELEMENT SKIPPED");
            }
        }
    }
    
    
    
    /**
     * @description Does the corresponding action depending on the Reward Type
     * @param webElement
     * @param element 
     */
    public static String performRewardAutomation(WebDriver driver, Element element) {
        // Instantiate Variables
        WebElement webElement = null;
        
        // False at the moment
        if(false) {
            // Do stuff without the WebElement
            return "";
            
        } else { // Need to get WebElement
            webElement = AutomationHelper.getWebElement(driver, element.getIdentifiers());
            
            // Textbox
            if(element.getType().equals("REWARD_GET_CODE")) {
                return webElement.getText();
            
            } else {
                // @TODO: Log Reward Automation Error
                System.out.println("POISON IVY: Rewards Not Found!");
                return "";
            }
        }
    }
    
    
    
    /***** Perform Automation Test *****/
    public static String performAutomationTest(int revisionId) {
        // Instantiate Variables
        List<CommandElement> commandElements = null;
        List<Element> elements = null;
        String rewardCode = ""; // @TODO This may need to be changed.
        
        try { // Get Command Elements
            Automation automation = AutomationImpl.findByRevisionId(revisionId);
            commandElements = CommandElementImpl.findByAutomationId(automation.getAutomationId());
            WebDriver driver = new FirefoxDriver();
            //WebDriver driver = new HtmlUnitDriver(true);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            
            // Do the stuff
            elements = AutomationHelper.getElements(commandElements, null);
            for(int i = 0; i < elements.size(); i++) {
                if(elements.get(i).getType().contains("REWARD")) {
                    rewardCode = AutomationHelper.performRewardAutomation(driver, elements.get(i));
                } else {
                    AutomationHelper.performElementAutomation(driver, elements.get(i));
                }
            }
            driver.close();
        } catch(Exception ex) {
            System.out.println("Error: performAutomationTest = " + ex.getMessage());
            // @TODO: Handle Exception
        } finally {}
        
        // Return
        System.out.println("BATMAN: Reward = "  + rewardCode);
        return rewardCode;
    }
    
    
    
    /***** Perform Automation Test *****/
    public static Submission performAutomation(int submissionId) {
        // Instantiate Variables
        List<CommandElement> commandElements = null;
        List<Element> elements = null;
        String rewardCode = ""; // @TODO This may need to be changed.
        Submission submission = null;
        Revision revision;
        Question currentQuestion;
        WebDriver driver = null;
        
        try { // Get Command Elements
            submission = SubmissionImpl.findBySubmissionId(submissionId);
            revision = AliasHelper.getRevision(SpeedyImpl.findByRevisionId(submission.getRevisionId(), "AUTOMATION"));
            currentQuestion = revision.getQuestion();
            boolean foundNext = false;
            
            // Initialize the Driver
            driver = new FirefoxDriver(); // Use to see what the webdriver actually does. For testing purposes only.
            /*driver = new HtmlUnitDriver(true){ 
                @Override 
                protected WebClient modifyWebClient(final WebClient client) { 
                    client.setThrowExceptionOnScriptError(false);
                    return client;
                }
            };*/
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
            System.out.println("BATMAN: SUBMISSION_ID = " + submission.getSubmissionId());
            System.out.println("BATMAN: REVISION_ID = " + revision.getRevisionId());
            
            do { // Do the stuff
                System.out.println("QUESTION: " + currentQuestion.getQuestionId());
                commandElements = currentQuestion.getAutomation().getCommandElements();
                elements = AutomationHelper.getElements(commandElements, submission);
                AutomationHelper._elements = elements;
                for(int i = 0; i < elements.size(); i++) {
                    System.out.println("QUESTION: ELEMENT: Type = " + elements.get(i).getType());
                    if(elements.get(i).getType().contains("REWARD")) {
                        System.out.println("BATMAN: REWARD TIME!!!");
                        rewardCode = AutomationHelper.performRewardAutomation(driver, elements.get(i));
                    } else {
                        AutomationHelper.performElementAutomation(driver, elements.get(i));
                    }
                }
                
                // Check for next question
                foundNext = false;
                for(int i = 0; i < currentQuestion.getAutomation().getAutomationFlows().size(); i++) {
                    for(int j = 0; j < submission.getSubmissionAnswers().size(); j++) {
                        if(! foundNext && currentQuestion.getAutomation().getAutomationFlows().get(i).getQuestion().getQuestionId() == submission.getSubmissionAnswers().get(j).getQuestionId()) {
                            currentQuestion = currentQuestion.getAutomation().getAutomationFlows().get(i).getQuestion();
                            foundNext = true;
                        }
                    }
                }
            } while (foundNext);
            System.out.println("BATMAN: Ended on Question ID = " + currentQuestion.getQuestionId());
            driver.close();
            
            // Save Reward
            if(rewardCode != null && ! rewardCode.isEmpty()) {
                if(revision.getReward() == null) {
                    revision.setReward(RewardImpl.findByRevisionId(revision.getRevisionId()));
                }
                submission.setSubmissionReward(SubmissionRewardImpl.create(new SubmissionReward(0, revision.getReward(), rewardCode, false)));
                SubmissionImpl.update(submission);
            } else {
                System.out.println("BATMAN: REWARD CODE WAS NOT FOUND!!!");
            }
            
        } catch(Exception ex) {
            System.out.println("Error: performAutomation = " + ex.getMessage());
            if(driver != null) {
                System.out.println("Error: The Title WAS = " + driver.getTitle());
                System.out.println("Error: The URL WAS = " + driver.getCurrentUrl());
                System.out.println("\n\nError: The Source WAS = " + driver.getPageSource());
                driver.close();
            }
            // @TODO: Handle Exception
        } finally {}
        
        // Return
        System.out.println("BATMAN: Reward = "  + rewardCode);
        return submission;
    }
}