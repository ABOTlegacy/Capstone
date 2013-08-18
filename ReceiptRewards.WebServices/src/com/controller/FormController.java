package com.controller;

import com.implementations.FormElementsImpl;
import com.implementations.FormImpl;
import com.model.Form;
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
@RequestMapping("/Form")
public class FormController {
    
    
    @RequestMapping(value = "/ById/Form/{formId}", method = RequestMethod.GET)
    @ResponseBody Form getByFormId(@PathVariable("formId") int formId) {
        return FormImpl.findByFormId(formId);
    }
    @RequestMapping(value = "/ById/Form/{formId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody Form getByFormId(@PathVariable("formId") int formId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return FormImpl.findByFormId(formId, recursiveLevel);
    }
    
    
    @RequestMapping(value = "/ById/Question/{questionId}", method = RequestMethod.GET)
    @ResponseBody Form getByQuestionId(@PathVariable("questionId") int questionId) {
        return FormImpl.findByQuestionId(questionId);
    }
    @RequestMapping(value = "/ById/Question/{questionId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody Form getByQuestionId(@PathVariable("questionId") int questionId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return FormImpl.findByQuestionId(questionId, recursiveLevel);
    }
    
    
    @RequestMapping(value = "/ById/Revision/{revisionId}", method = RequestMethod.GET)
    @ResponseBody Form getByRevisionId(@PathVariable("revisionId") int revisionId) {
        return FormImpl.findByRevisionId(revisionId);
    }
    @RequestMapping(value = "/ById/Revision/{revisionId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody Form getByRevisionId(@PathVariable("revisionId") int revisionId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return FormImpl.findByRevisionId(revisionId, recursiveLevel);
    }

    
    @RequestMapping(value = "/Update/Weight/", method = RequestMethod.PUT)
    @ResponseBody Form updateOrder(@RequestBody Form form) {
        FormElementsImpl.updateOrder(form);
        return form;
    }
    
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Form not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}