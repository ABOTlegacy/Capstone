package com.controller;

import com.implementations.CodeImpl;
import com.model.Code;
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
@RequestMapping("/Code")
public class CodeController {
	

    @RequestMapping(value = "/All/", method = RequestMethod.GET)
    @ResponseBody List<Code> getAll() {
        return CodeImpl.findAll();
    }

    
    @RequestMapping(value = "/ById/CodeType/{codeTypeId}", method = RequestMethod.GET)
    @ResponseBody List<Code> getType(@PathVariable("codeTypeId") int codeTypeId) {
        return CodeImpl.findByType(codeTypeId);
    }

    
    @RequestMapping(value = "/ById/Code/{codeId}", method = RequestMethod.GET)
    @ResponseBody Code getById(@PathVariable("codeId") int codeId) {
        return CodeImpl.findByCodeId(codeId);
    }
    
    
    @RequestMapping(value = "/ByCode/Code/{code}", method = RequestMethod.GET)
    @ResponseBody Code getByCode(@PathVariable("code") String code, @RequestBody CodeType codeType) {
        return CodeImpl.findByCode(code, codeType);
    }

    
    @RequestMapping(value = "/Create/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Code create(@RequestBody Code code) {
        return CodeImpl.create(code);
    }

    
    @RequestMapping(value = "/Update/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Code update(@RequestBody Code code) {
        return CodeImpl.update(code);
    }

    @RequestMapping(value = "/ById/Code/{codeId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeById(@PathVariable("codeId") int codeId) {
        CodeImpl.deleteById(codeId);
    }
    
    
    @RequestMapping(value = "/ByCode/Code/{code}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeByCode(@PathVariable("code") String code) {
        CodeImpl.deleteByCode(code);
    }

    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Code not found")
    void handleNotFound(NotFoundException exc) {}

    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}
