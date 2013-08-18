package com.controller;

import com.implementations.CommandElementImpl;
import com.model.CommandElement;
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
@RequestMapping("/CommandElement")
public class CommandElementController {
    
    
    @RequestMapping(value = "/ById/CommandElement/{commandElementId}", method = RequestMethod.GET)
    @ResponseBody CommandElement getByCommandElementId(@PathVariable("commandElementId") int commandElementId) {
        return CommandElementImpl.findByCommandElementId(commandElementId);
    }
    
    
    @RequestMapping(value = "/ById/AutomationId/{automationId}", method = RequestMethod.GET)
    @ResponseBody List<CommandElement> getByAutomationId(@PathVariable("automationId") int automationId) {
        return CommandElementImpl.findByAutomationId(automationId);
    }

    
    @RequestMapping(value = "/Create/{automationId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody CommandElement create(@RequestBody CommandElement commandElement, @PathVariable("automationId") int automationId) {
        return CommandElementImpl.create(commandElement, automationId);
    }

    
    @RequestMapping(value = "/Create/{automationId}/{commandElementId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody CommandElement createFromExisting(@PathVariable("commandElementId") int commandElementId, @PathVariable("automationId") int automationId) {
        return CommandElementImpl.createFromExisting(commandElementId, automationId);
    }

    
    @RequestMapping(value = "/Update/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody CommandElement update(@RequestBody CommandElement commandElement) {
        return CommandElementImpl.update(commandElement);
    }
    
    
    @RequestMapping(value = "/ById/CommandElement/{commandElementId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByCommandElementId(@PathVariable("commandElementId") int commandElementId) {
        CommandElementImpl.deleteByCommandElementId(commandElementId);
    }
    
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Command Element not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}