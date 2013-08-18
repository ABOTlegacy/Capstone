package com.controller;

import com.implementations.QuestionImpl;
import com.model.Question;
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
@RequestMapping("/Question")
public class QuestionController {
    
    
    @RequestMapping(value = "/ById/Question/{questionId}", method = RequestMethod.GET)
    @ResponseBody Question getByQuestionId(@PathVariable("questionId") int questionId) {
        return QuestionImpl.findByQuestionId(questionId);
    }
    @RequestMapping(value = "/ById/Question/{questionId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody Question getByQuestionId(@PathVariable("questionId") int questionId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return QuestionImpl.findByQuestionId(questionId, recursiveLevel);
    }
    
    
    @RequestMapping(value = "/ById/Revision/{revisionId}", method = RequestMethod.GET)
    @ResponseBody List<Question> getByRevisionId(@PathVariable("revisionId") int revisionId) {
        return QuestionImpl.findByRevisionId(revisionId);
    }
    @RequestMapping(value = "/ById/Revision/{revisionId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody List<Question> getByRevisionId(@PathVariable("revisionId") int revisionId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return QuestionImpl.findByRevisionId(revisionId, recursiveLevel);
    }
    
    
    @RequestMapping(value = "/ById/Form/{formId}", method = RequestMethod.GET)
    @ResponseBody List<Question> getByFormId(@PathVariable("formId") int formId) {
        return QuestionImpl.findByFormId(formId);
    }
    @RequestMapping(value = "/ById/Form/{formId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody List<Question> getByFormId(@PathVariable("formId") int formId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return QuestionImpl.findByFormId(formId, recursiveLevel);
    }
    
    
    @RequestMapping(value = "/ById/Automation/{automationId}", method = RequestMethod.GET)
    @ResponseBody List<Question> getByAutomationId(@PathVariable("automationId") int automationId) {
        return QuestionImpl.findByAutomationId(automationId);
    }
    @RequestMapping(value = "/ById/Automation/{automationId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody List<Question> getByAutomationId(@PathVariable("automationId") int automationId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return QuestionImpl.findByAutomationId(automationId, recursiveLevel);
    }

    
    @RequestMapping(value = "/Create/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Question create(@RequestBody Question question) {
        return QuestionImpl.create(question);
    }
    
    
    @RequestMapping(value = "/Update/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Question update(@RequestBody Question question) {
        return QuestionImpl.update(question);
    }
    
    
    @RequestMapping(value = "/ById/Question/{questionId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByQuestionId(@PathVariable("questionId") int questionId) {
        QuestionImpl.deleteByQuestionId(questionId);
    }
    
    @RequestMapping(value = "/ById/Form/{formId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByFormId(@PathVariable("formId") int formId) {
        QuestionImpl.deleteByFormId(formId);
    }
    
    
    @RequestMapping(value = "/ById/Automation/{automationId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByAutomationId(@PathVariable("automationId") int automationId) {
        QuestionImpl.deleteByAutomationId(automationId);
    }
    
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Question not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}