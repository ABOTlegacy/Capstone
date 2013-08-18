package com.controller;

import com.implementations.SurveyImpl;
import com.model.Survey;
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
@RequestMapping("/Survey")
public class SurveyController {
	

    @RequestMapping(value = "/All/", method = RequestMethod.GET)
    @ResponseBody List<Survey> getAll() {
        return SurveyImpl.findAll();
    }

    
    @RequestMapping(value = "/ById/Company/{companyId}", method = RequestMethod.GET)
    @ResponseBody List<Survey> getByCompanyId(@PathVariable("companyId") int companyId) {
        return SurveyImpl.findByCompanyId(companyId);
    }

    
    @RequestMapping(value = "/ById/Survey/{surveyId}", method = RequestMethod.GET)
    @ResponseBody Survey getBySurveyId(@PathVariable("surveyId") int surveyId) {
        return SurveyImpl.findBySurveyId(surveyId);
    }

    
    @RequestMapping(value = "/Create/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Survey create(@RequestBody Survey survey) {
        return SurveyImpl.create(survey);
    }

    
    @RequestMapping(value = "/Update/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Survey update(@RequestBody Survey survey) {
        return SurveyImpl.update(survey);
    }

    
    @RequestMapping(value = "/ById/Survey/{surveyId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeBySurveyId(@PathVariable("companyId") int surveyId) {
        SurveyImpl.deleteBySurveyId(surveyId);
    }
    
    
    @RequestMapping(value = "/ById/Company/{companyId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByCompanyId(@PathVariable("companyId") int companyId) {
        SurveyImpl.deleteByCompanyId(companyId);
    }

    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Survey not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}