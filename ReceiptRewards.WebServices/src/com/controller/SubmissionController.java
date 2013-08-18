package com.controller;

import com.automation.AutomationHelper;
import com.implementations.SubmissionImpl;
import com.model.Submission;
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
@RequestMapping("/Submission")
public class SubmissionController {
    
    
    @RequestMapping(value = "/ById/Submission/{submissionId}", method = RequestMethod.GET)
    @ResponseBody Submission getBySubmissionId(@PathVariable("submissionId") int submissionId) {
        return SubmissionImpl.findBySubmissionId(submissionId);
    }
    
    
    @RequestMapping(value = "/ById/Revision/{revisionId}", method = RequestMethod.GET)
    @ResponseBody List<Submission> getByRevisionId(@PathVariable("revisionId") int revisionId) {
        return SubmissionImpl.findByRevisionId(revisionId);
    }

    
    @RequestMapping(value = "/Create/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody int create(@RequestBody Submission formSubmission) {
        return SubmissionImpl.create(formSubmission).getSubmissionId();
    }
    
    
    @RequestMapping(value = "/Update/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Submission update(@RequestBody Submission formSubmission) {
        return SubmissionImpl.update(formSubmission);
    }
    
    
    @RequestMapping(value = "/ById/Submission/{submissionId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeBySubmissionId(@PathVariable("submissionId") int submissionId) {
        SubmissionImpl.deleteBySubmissionId(submissionId);
    }
    
    
    @RequestMapping(value = "/ById/Revision/{revisionId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByFormId(@PathVariable("revisionId") int revisionId) {
        SubmissionImpl.deleteByRevisionId(revisionId);
    }
    
    
    @RequestMapping(value = "/Action/Submission/{submissionId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Submission actionSubmission(@PathVariable("submissionId") int submissionId) {
        AutomationHelper.performAutomation(submissionId);
        return SubmissionImpl.findBySubmissionId(submissionId);
    }
    
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Submission not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}