package com.controller;

import com.automation.AutomationHelper;
import com.implementations.AutomationImpl;
import com.implementations.CommandElementsImpl;
import com.model.Automation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/Automation")
public class AutomationController {
	
    
    @RequestMapping(value = "/ById/Automation/{automationId}", method = RequestMethod.GET)
    @ResponseBody Automation getByAutomationId(@PathVariable("automationId") int automationId) {
        return AutomationImpl.findByAutomationId(automationId);
    }
    @RequestMapping(value = "/ById/Automation/{automationId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody Automation getByAutomationId(@PathVariable("automationId") int automationId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return AutomationImpl.findByAutomationId(automationId, recursiveLevel);
    }
    
    
    @RequestMapping(value = "/ById/Question/{questionId}", method = RequestMethod.GET)
    @ResponseBody Automation getByQuestionId(@PathVariable("questionId") int questionId) {
        return AutomationImpl.findByQuestionId(questionId);
    }
    @RequestMapping(value = "/ById/Question/{questionId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody Automation getByQuestionId(@PathVariable("questionId") int questionId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return AutomationImpl.findByQuestionId(questionId, recursiveLevel);
    }
    
    
    @RequestMapping(value = "/ById/Revision/{revisionId}", method = RequestMethod.GET)
    @ResponseBody Automation getByRevisionId(@PathVariable("revisionId") int revisionId) {
        return AutomationImpl.findByRevisionId(revisionId);
    }
    @RequestMapping(value = "/ById/Revision/{revisionId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody Automation getByRevisionId(@PathVariable("revisionId") int revisionId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return AutomationImpl.findByRevisionId(revisionId, recursiveLevel);
    }
    
    
    @RequestMapping(value = "/Create/", method = RequestMethod.POST)
    @ResponseBody Automation create(@RequestBody Automation automation) {
        return AutomationImpl.create(automation);
    }
    
    
    @RequestMapping(value = "/Update/Weight/", method = RequestMethod.PUT)
    @ResponseBody Automation updateOrder(@RequestBody Automation automation) {
        CommandElementsImpl.updateOrder(automation);
        return automation;
    }

    
    @RequestMapping(value = "/ById/Automation/{automationId}", method = RequestMethod.DELETE)
    void removeByAutomationId(@RequestBody int automationId) {
        AutomationImpl.deleteByAutomationId(automationId);
    }

    
    @RequestMapping(value = "/ById/Question/{questionId}", method = RequestMethod.DELETE)
    void removeByQuestionId(@RequestBody int questionId) {
        AutomationImpl.deleteByQuestionId(questionId);
    }

    
    @RequestMapping(value = "/ById/Revision/{revisionId}", method = RequestMethod.DELETE)
    void removeByRevisionId(@RequestBody int revisionId) {
        AutomationImpl.deleteByRevisionId(revisionId);
    }
    
    
    @RequestMapping(value = "Action/Test/Revision/{revisionId}", method = RequestMethod.GET)
    String performAutomationTest(@PathVariable("revisionId") int revisionId) {
        return AutomationHelper.performAutomationTest(revisionId);
    }
    
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Automation not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}