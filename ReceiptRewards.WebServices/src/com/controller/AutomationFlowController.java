package com.controller;

import com.implementations.AutomationFlowImpl;
import com.model.AutomationFlow;
import java.util.List;
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
@RequestMapping("/AutomationFlow")
public class AutomationFlowController {
	
    @RequestMapping(value = "/ById/AutomationFlow/{automationFlowId}", method = RequestMethod.GET)
    @ResponseBody AutomationFlow getByAutomationFlowId(@PathVariable("automationFlowId") int automationFlowId) {
        return AutomationFlowImpl.findByAutomationFlowId(automationFlowId);
    }
    @RequestMapping(value = "/ById/AutomationFlow/{automationFlowId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody AutomationFlow getByAutomationFlowId(@PathVariable("automationFlowId") int automationFlowId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return AutomationFlowImpl.findByAutomationFlowId(automationFlowId, recursiveLevel);
    }
    
    
    @RequestMapping(value = "/ById/Automation/{automationId}", method = RequestMethod.GET)
    @ResponseBody List<AutomationFlow> getByAutomationId(@PathVariable("automationId") int automationId) {
        return AutomationFlowImpl.findByAutomationId(automationId);
    }
    @RequestMapping(value = "/ById/Automation/{automationId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody List<AutomationFlow> getByAutomationId(@PathVariable("automationId") int automationId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return AutomationFlowImpl.findByAutomationId(automationId, recursiveLevel);
    }
    
    
    @RequestMapping(value = "/Create/", method = RequestMethod.POST)
    @ResponseBody AutomationFlow create(@RequestBody AutomationFlow automationFlow) {
        return AutomationFlowImpl.create(automationFlow);
    }

    
    @RequestMapping(value = "/ById/AutomationFlow/{automationFlowId}", method = RequestMethod.DELETE)
    void removeByAutomationFlowId(@PathVariable("automationFlowId") int automationFlowId) {
        AutomationFlowImpl.deleteByAutomationFlowId(automationFlowId);
    }

    
    @RequestMapping(value = "/ById/Automation/{automationId}", method = RequestMethod.DELETE)
    void removeByAutomationId(@PathVariable("automationId") int automationId) {
        AutomationFlowImpl.deleteByAutomationId(automationId);
    }

    
    @RequestMapping(value = "/ById/Question/{questionId}", method = RequestMethod.DELETE)
    void removeByQuestionId(@PathVariable("questionId") int questionId) {
        AutomationFlowImpl.deleteByQuestionId(questionId);
    }
    
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Automation Flow not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}