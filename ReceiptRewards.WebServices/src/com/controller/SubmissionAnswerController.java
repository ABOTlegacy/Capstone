package com.controller;

import com.implementations.SubmissionAnswerImpl;
import com.model.SubmissionAnswer;
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
@RequestMapping("/SubmissionAnswer")
public class SubmissionAnswerController {
    
    
    @RequestMapping(value = "/ById/SubmissionAnswer/{submissionAnswerId}", method = RequestMethod.GET)
    @ResponseBody SubmissionAnswer getBySubmissionAnswerId(@PathVariable("submissionAnswerId") int submissionAnswerId) {
        return SubmissionAnswerImpl.findBySubmissionAnswerId(submissionAnswerId);
    }
    
    
    @RequestMapping(value = "/ById/Submission/{submissionId}", method = RequestMethod.GET)
    @ResponseBody List<SubmissionAnswer> getBySubmissionId(@PathVariable("submissionId") int submissionId) {
        return SubmissionAnswerImpl.findBySubmissionId(submissionId);
    }

    
    @RequestMapping(value = "/Create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody SubmissionAnswer create(@RequestBody SubmissionAnswer submissionAnswer) {
        return SubmissionAnswerImpl.create(submissionAnswer);
    }
    
    
    @RequestMapping(value = "/Update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody SubmissionAnswer updateById(@RequestBody SubmissionAnswer submissionAnswer) {
        return SubmissionAnswerImpl.update(submissionAnswer);
    }
    
    
    @RequestMapping(value = "/ById/SubmissionAnswer/{submissionAnswerId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByFormAnswerId(@PathVariable("submissionAnswerId") int submissionAnswerId) {
        SubmissionAnswerImpl.deleteBySubmissionAnswerId(submissionAnswerId);
    }
    
    
    @RequestMapping(value = "/ById/Submission/{submissionId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByFormSubmissionId(@PathVariable("submissionId") int submissionId) {
        SubmissionAnswerImpl.deleteBySubmissionId(submissionId);
    }
    
    
    @RequestMapping(value = "/ById/Question/{questionId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByQuestionId(@PathVariable("questionId") int questionId) {
        SubmissionAnswerImpl.deleteByQuestionId(questionId);
    }
    
    
    @RequestMapping(value = "/ById/FormElement/{formElementId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByFormElementId(@PathVariable("formElementId") int formElementId) {
        SubmissionAnswerImpl.deleteByFormElementId(formElementId);
    }
    
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Code Type not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}