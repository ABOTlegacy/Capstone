package com.controller;

import com.implementations.RevisionImpl;
import com.model.Revision;
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
@RequestMapping("/Revision")
public class RevisionController {
	

    @RequestMapping(value = "/All/", method = RequestMethod.GET)
    @ResponseBody List<Revision> getAll() {
        return RevisionImpl.findAll();
    }
    @RequestMapping(value = "/All/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody List<Revision> getAll(@PathVariable("recursiveLevel") int recursiveLevel) {
        return RevisionImpl.findAll(recursiveLevel);
    }

    
    @RequestMapping(value = "/ById/Active/{surveyId}", method = RequestMethod.GET)
    @ResponseBody List<Revision> getByActiveSurveyId(@PathVariable("surveyId") int surveyId) {
        return RevisionImpl.findByActiveSurveyId(surveyId);
    }
    @RequestMapping(value = "/ById/Active/{surveyId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody List<Revision> getByActiveSurveyId(@PathVariable("surveyId") int surveyId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return RevisionImpl.findByActiveSurveyId(surveyId, recursiveLevel);
    }
    
    
    @RequestMapping(value = "/ById/Survey/{surveyId}", method = RequestMethod.GET)
    @ResponseBody List<Revision> getBySurveyId(@PathVariable("surveyId") int surveyId) {
        return RevisionImpl.findBySurveyId(surveyId);
    }
    @RequestMapping(value = "/ById/Survey/{surveyId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody List<Revision> getBySurveyId(@PathVariable("surveyId") int surveyId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return RevisionImpl.findBySurveyId(surveyId, recursiveLevel);
    }
    
    
    @RequestMapping(value = "/ById/Revision/{revisionId}", method = RequestMethod.GET)
    @ResponseBody Revision getByRevisionId(@PathVariable("revisionId") int revisionId) {
        return RevisionImpl.findByRevisionId(revisionId);
    }
    @RequestMapping(value = "/ById/Revision/{revisionId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody Revision getByRevisionId(@PathVariable("revisionId") int revisionId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return RevisionImpl.findByRevisionId(revisionId, recursiveLevel);
    }
    
    
    @RequestMapping(value = "/ById/Question/{questionId}", method = RequestMethod.GET)
    @ResponseBody List<Revision> getByQuestionId(@PathVariable("questionId") int questionId) {
        return RevisionImpl.findByQuestionId(questionId);
    }
    @RequestMapping(value = "/ById/Question/{questionId}/{recursiveLevel}", method = RequestMethod.GET)
    @ResponseBody List<Revision> getByQuestionId(@PathVariable("questionId") int questionId, @PathVariable("recursiveLevel") int recursiveLevel) {
        return RevisionImpl.findByQuestionId(questionId, recursiveLevel);
    }

    
    @RequestMapping(value = "/Create/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Revision create(@RequestBody Revision revision) {
        return RevisionImpl.create(revision);
    }

    
    @RequestMapping(value = "/Update/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Revision updateById(@RequestBody Revision revision) {
        return RevisionImpl.update(revision);
    }

    
    @RequestMapping(value = "/ById/Survey/{surveyId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeBySurveyId(@PathVariable("companyId") int surveyId) {
        RevisionImpl.deleteBySurveyId(surveyId);
    }
    
    
    @RequestMapping(value = "/ById/Revision/{revisionId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByRevisionId(@PathVariable("revisionId") int revisionId) {
        RevisionImpl.deleteByRevisionId(revisionId);
    }

    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Revision not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}
