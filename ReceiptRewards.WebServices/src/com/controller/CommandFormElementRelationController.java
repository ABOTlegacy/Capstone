package com.controller;

import com.implementations.CommandFormElementRelationImpl;
import com.model.CommandFormElementRelation;
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
@RequestMapping("/CommandFormElementRelation")
public class CommandFormElementRelationController {
	
    
    @RequestMapping(value = "/ById/CommandElement/{commandElementId}", method = RequestMethod.GET)
    @ResponseBody List<CommandFormElementRelation> getByCommandElementId(@PathVariable("commandElementId") int commandElementId) {
        return CommandFormElementRelationImpl.findByCommandElementId(commandElementId);
    }
    
    
    @RequestMapping(value = "/ById/FormElement/{formElementId}", method = RequestMethod.GET)
    @ResponseBody List<CommandFormElementRelation> getByFormElementId(@PathVariable("formElementId") int formElementId) {
        return CommandFormElementRelationImpl.findByFormElementId(formElementId);
    }
    
    
    @RequestMapping(value = "/Create/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody CommandFormElementRelation create(@RequestBody CommandFormElementRelation commandFormElementRelation) {
        return CommandFormElementRelationImpl.create(commandFormElementRelation);
    }
    
    
    @RequestMapping(value = "/ById/CommandFormElementRelation/{commandFormElementRelationId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByCommandFormElementRelationId(@PathVariable("commandFormElementRelationId") int commandFormElementRelationId) {
        CommandFormElementRelationImpl.deleteByCommandFormElementRelationId(commandFormElementRelationId);
    }
    
    
    @RequestMapping(value = "/ById/CommandElement/{commandElementId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByCommandElementId(@PathVariable("commandElementId") int commandElementId) {
        CommandFormElementRelationImpl.deleteByCommandElementId(commandElementId);
    }
    
    
    @RequestMapping(value = "/ById/FormElement/{formElementId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByFormElementId(@PathVariable("formElementId") int formElementId) {
        CommandFormElementRelationImpl.deleteByFormElementId(formElementId);
    }
    
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="CommandFormElementRelation not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}