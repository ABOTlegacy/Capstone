package com.controller;

import com.implementations.FormElementOptionImpl;
import com.model.FormElementOption;
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
@RequestMapping("/FormElementOption")
public class FormElementOptionController {
	
    
    @RequestMapping(value = "/ById/FormElementOption/{formElementOptionId}", method = RequestMethod.GET)
    @ResponseBody FormElementOption getByFormElementOptionId(@PathVariable("formElementOptionId") int formElementOptionId) {
        return FormElementOptionImpl.findByFormElementOptionId(formElementOptionId);
    }
    
    
    @RequestMapping(value = "/ById/FormElement/{formElementId}", method = RequestMethod.GET)
    @ResponseBody List<FormElementOption> getByFormElementId(@PathVariable("formElementId") int formElementId) {
        return FormElementOptionImpl.findByFormElementId(formElementId);
    }
    
    
    @RequestMapping(value = "/Create/{formElementId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody FormElementOption create(@RequestBody FormElementOption formElementOption, @PathVariable("formElementId") int formElementId) {
        return FormElementOptionImpl.create(formElementOption, formElementId);
    }
    
    
    @RequestMapping(value = "/Update/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody FormElementOption updateById(@RequestBody FormElementOption formElementOption) {
        return FormElementOptionImpl.update(formElementOption);
    }
    
    
    @RequestMapping(value = "/ById/FormElementOption/{formElementOptionId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByFormElementOptionId(@PathVariable("formElementOptionId") int formElementOptionId) {
        FormElementOptionImpl.deleteByFormElementOptionId(formElementOptionId);
    }
    
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Form Element Option not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}