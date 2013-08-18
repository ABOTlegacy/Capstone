package com.controller;

import com.implementations.FormElementAttributeImpl;
import com.model.FormElementAttribute;
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
@RequestMapping("/FormElementAttribute")
public class FormElementAttributeController {
	
    
    @RequestMapping(value = "/ById/FormElementAttribute/{formElementAttributeId}", method = RequestMethod.GET)
    @ResponseBody FormElementAttribute getByFormElementAttributeId(@PathVariable("formElementAttributeId") int formElementAttributeId) {
        return FormElementAttributeImpl.findByFormElementAttributeId(formElementAttributeId);
    }
    
    
    @RequestMapping(value = "/ById/FormElement/{formElementId}", method = RequestMethod.GET)
    @ResponseBody List<FormElementAttribute> getByFormElementId(@PathVariable("formElementId") int formElementId) {
        return FormElementAttributeImpl.findByFormElementId(formElementId);
    }
    
    
    @RequestMapping(value = "/Create/{formElementId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody FormElementAttribute create(@RequestBody FormElementAttribute formElementAttribute, @PathVariable("formElementId") int formElementId) {
        return FormElementAttributeImpl.create(formElementAttribute, formElementId);
    }
    
    
    @RequestMapping(value = "/Update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody FormElementAttribute update(@RequestBody FormElementAttribute formElementAttribute) {
        return FormElementAttributeImpl.update(formElementAttribute);
    }
    
    
    @RequestMapping(value = "/ById/FormElementAttribute/{formElementAttributeId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByFormElementAttributeId(@PathVariable("formElementAttributeId") int formElementAttributeId) {
        FormElementAttributeImpl.deleteByFormElementAttributeId(formElementAttributeId);
    }
    
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Form Element Attribute not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}