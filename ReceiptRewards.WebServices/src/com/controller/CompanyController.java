package com.controller;

import com.implementations.CompanyImpl;
import com.model.Company;
import java.util.ArrayList;
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
@RequestMapping("/Company")
public class CompanyController {
	

    @RequestMapping(value = "/All/", method = RequestMethod.GET)
    @ResponseBody List<Company> getAll() {
        return CompanyImpl.findAll();
    }

    
    @RequestMapping(value = "/ById/Company/{companyId}", method = RequestMethod.GET)
    @ResponseBody Company getByCompanyId(@PathVariable("companyId") int companyId) {
        return CompanyImpl.findByCompnayId(companyId);
    }

    
    @RequestMapping(value = "/Create/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Company create(@RequestBody Company company) {
        return CompanyImpl.create(company);
    }

    
    @RequestMapping(value = "/Update/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Company update(@RequestBody Company company) {
        return CompanyImpl.update(company);
    }

    
    @RequestMapping(value = "/ById/Company/{companyId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void removeById(@PathVariable("companyId") int companyId) {
        CompanyImpl.deleteById(companyId);
    }

    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Company not found")
    void handleNotFound(NotFoundException exc) {}
    
    
    class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;		
    }
}