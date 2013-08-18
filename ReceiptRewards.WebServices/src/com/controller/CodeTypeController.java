package com.controller;

import com.implementations.CodeTypeImpl;
import com.model.CodeType;
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
@RequestMapping("/CodeType")
public class CodeTypeController {
	

    @RequestMapping(value = "/All/", method = RequestMethod.GET)
    @ResponseBody List<CodeType> getAll() {
        return CodeTypeImpl.findAll();
    }


    @RequestMapping(value = "/ById/CodeType/{codeTypeId}", method = RequestMethod.GET)
    @ResponseBody CodeType getByCodeTypeId(@PathVariable("codeTypeId") int codeTypeId) {
        return CodeTypeImpl.findByCodeTypeId(codeTypeId);
    }
    
    
    @RequestMapping(value = "/ByType/CodeType/{type}", method = RequestMethod.GET)
    @ResponseBody CodeType getByType(@PathVariable("type") String type) {
        return CodeTypeImpl.findByType(type);
    }


    @RequestMapping(value = "/Create/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody CodeType create(@RequestBody CodeType codeType) {
        return CodeTypeImpl.create(codeType);
    }


    @RequestMapping(value = "/Update/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody CodeType update(@RequestBody CodeType codeType) {
        return CodeTypeImpl.update(codeType);
    }


    @RequestMapping(value = "/ById/CodeType/{codeTypeId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByCodeTypeId(@PathVariable("codeTypeId") int codeTypeId) {
        CodeTypeImpl.deleteById(codeTypeId);
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Code Type not found")
    void handleNotFound(NotFoundException exc) {}
	

    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}
