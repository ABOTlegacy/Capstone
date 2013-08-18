package com.controller;

import com.aliasmodels.R;
import com.implementations.SpeedyImpl;
import com.model.Revision;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/Speedy")
public class SpeedyController {
	

    @RequestMapping(value = "/AutomationOnly/ById/Active/{surveyId}", method = RequestMethod.GET)
    @ResponseBody List<R> getByActiveSurveyIdAutomation(@PathVariable("surveyId") int surveyId) {
        return SpeedyImpl.findByActiveSurveyId(surveyId, "AUTOMATION");
    }

    @RequestMapping(value = "/FormOnly/ById/Active/{surveyId}", method = RequestMethod.GET)
    @ResponseBody List<R> getByActiveSurveyIdForm(@PathVariable("surveyId") int surveyId) {
        return SpeedyImpl.findByActiveSurveyId(surveyId, "FORM");
    }
    
    @RequestMapping(value = "/AutomationOnly/ById/Survey/{surveyId}", method = RequestMethod.GET)
    @ResponseBody List<R> getBySurveyIdAutomation(@PathVariable("surveyId") int surveyId) {
        return SpeedyImpl.findBySurveyId(surveyId, "AUTOMATION");
    }
    
    @RequestMapping(value = "/FormOnly/ById/Survey/{surveyId}", method = RequestMethod.GET)
    @ResponseBody List<R> getBySurveyIdForm(@PathVariable("surveyId") int surveyId) {
        return SpeedyImpl.findBySurveyId(surveyId, "FORM");
    }
    
    @RequestMapping(value = "/AutomationOnly/ById/Revision/{revisionId}", method = RequestMethod.GET)
    @ResponseBody R getByRevisionIdAutomation(@PathVariable("revisionId") int revisionId) {
        return SpeedyImpl.findByRevisionId(revisionId, "AUTOMATION");
    }
    
    @RequestMapping(value = "/FormOnly/ById/Revision/{revisionId}", method = RequestMethod.GET)
    @ResponseBody R getByRevisionIdForm(@PathVariable("revisionId") int revisionId) {
        return SpeedyImpl.findByRevisionId(revisionId, "FORM");
    }
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Revision not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}
