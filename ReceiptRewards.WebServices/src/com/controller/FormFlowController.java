package com.controller;

import com.implementations.FormFlowImpl;
import com.model.FormFlow;
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
@RequestMapping("/FormFlow")
public class FormFlowController {
	
    
    @RequestMapping(value = "/ById/FormFlow/{formFlowId}", method = RequestMethod.GET)
    @ResponseBody FormFlow getByFormFlowId(@PathVariable("formFlowId") int formFlowId) {
        return FormFlowImpl.findByFormFlowId(formFlowId);
    }
    @RequestMapping(value = "/ById/FormFlow/{formFlowId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody FormFlow getByFormFlowId(@PathVariable("formFlowId") int formFlowId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return FormFlowImpl.findByFormFlowId(formFlowId, recursiveLevel);
    }
    
    
    @RequestMapping(value = "/ById/Form/{formId}", method = RequestMethod.GET)
    @ResponseBody List<FormFlow> getByFormId(@PathVariable("formId") int formId) {
        return FormFlowImpl.findByFormId(formId);
    }
    @RequestMapping(value = "/ById/Form/{formId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody List<FormFlow> getByFormId(@PathVariable("formId") int formId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return FormFlowImpl.findByFormId(formId, recursiveLevel);
    }
    
    
    @RequestMapping(value = "/Create", method = RequestMethod.POST)
    @ResponseBody FormFlow create(@RequestBody FormFlow formFlow) {
        return FormFlowImpl.create(formFlow);
    }

    
    @RequestMapping(value = "/ById/FormFlow/{formFlowId}", method = RequestMethod.DELETE)
    void removeByFormFlowId(@PathVariable("formFlowId") int formFlowId) {
        FormFlowImpl.deleteByFormFlowId(formFlowId);
    }

    
    @RequestMapping(value = "/ById/Form/{formId}", method = RequestMethod.DELETE)
    void removeByFormId(@PathVariable("formId") int formId) {
        System.out.println("BMMBM");
        FormFlowImpl.deleteByFormId(formId);
    }
    
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Form Flow not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}