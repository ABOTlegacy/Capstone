package com.controller;

import com.implementations.CommandIdentifierImpl;
import com.model.CommandIdentifier;
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
@RequestMapping("/CommandIdentifier")
public class CommandIdentifierController {
	
    
    @RequestMapping(value = "/ById/CommandIdentifier/{commandIdentifierId}", method = RequestMethod.GET)
    @ResponseBody CommandIdentifier getByCommandIdentifierId(@PathVariable("commandIdentifierId") int commandIdentifierId) {
        return CommandIdentifierImpl.findByCommandIdentifierId(commandIdentifierId);
    }
    
    
    @RequestMapping(value = "/ById/CommandElement/{commandElementId}", method = RequestMethod.GET)
    @ResponseBody List<CommandIdentifier> getByCommandElementId(@PathVariable("commandElementId") int commandElementId) {
        return CommandIdentifierImpl.findByCommandElementId(commandElementId);
    }
    
    
    @RequestMapping(value = "/Create/{commandElementId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody CommandIdentifier create(@RequestBody CommandIdentifier commandIdentifier, @PathVariable("commandElementId") int commandElementId) {
        return CommandIdentifierImpl.create(commandIdentifier, commandElementId);
    }
    
    
    @RequestMapping(value = "/Update/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody CommandIdentifier update(@RequestBody CommandIdentifier commandIdentifier) {
        return CommandIdentifierImpl.update(commandIdentifier);
    }
    
    
    @RequestMapping(value = "/ById/CommandIdentifier/{commandIdentifierId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByCommandIdentifierId(@PathVariable("commandIdentifierId") int commandIdentifierId) {
        CommandIdentifierImpl.deleteByCommandIdentifierId(commandIdentifierId);
    }
    
    
    @RequestMapping(value = "/ById/CommandElement/{commandElementId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByCommandElementId(@PathVariable("commandElementId") int commandElementId) {
        CommandIdentifierImpl.deleteByCommandElementId(commandElementId);
    }
    
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Command Identifier not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}