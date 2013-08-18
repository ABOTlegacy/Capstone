package com.controller;

import com.implementations.FormElementImpl;
import com.model.FormElement;
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
@RequestMapping("/FormElement")
public class FormElementController {
    
    
    @RequestMapping(value = "/ById/FormElement/{formElementId}", method = RequestMethod.GET)
    @ResponseBody FormElement getByFormElementId(@PathVariable("formElementId") int formElementId) {
        return FormElementImpl.findByFormElementId(formElementId);
    }
    
    
    @RequestMapping(value = "/ById/Form/{formId}", method = RequestMethod.GET)
    @ResponseBody List<FormElement> getByFormId(@PathVariable("formId") int formId) {
        return FormElementImpl.findByFormId(formId);
    }

    
    @RequestMapping(value = "/Create/{formId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody FormElement create(@RequestBody FormElement formElement, @PathVariable("formId") int formId) {
        return FormElementImpl.create(formElement, formId);
    }
    
    @RequestMapping(value = "/Create/{formId}/{formElementId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody FormElement createFromExisting(@PathVariable("formElementId") int formElementId, @PathVariable("formId") int formId) {
        return FormElementImpl.createFromExisting(formElementId, formId);
    }
    
    
    @RequestMapping(value = "/Update/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody FormElement updateById(@RequestBody FormElement formElement) {
        return FormElementImpl.update(formElement);
    }
    
    
    @RequestMapping(value = "/ById/FormElement/{formElementId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByFormElementId(@PathVariable("formElementId") int formElementId) {
        FormElementImpl.deleteByFormElementId(formElementId);
    }
    
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Form Element not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}