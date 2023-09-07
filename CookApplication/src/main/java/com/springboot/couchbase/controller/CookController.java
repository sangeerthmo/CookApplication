package com.springboot.couchbase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.couchbase.entity.Cook;
import com.springboot.couchbase.exception.EntityNotFound;
import com.springboot.couchbase.service.CookService;

import lombok.extern.slf4j.Slf4j;

//lombok annotation
@Slf4j
//spring annotations
@RestController
@RequestMapping("/api")
public class CookController {

    @Autowired
    CookService service;

    //URL - http://localhost:9300/api/employees
    @GetMapping("/cooks")
    @ResponseStatus(HttpStatus.OK)
    public List<Cook> getCooks() {
        return service.getCooks();
    }

    //URL - http://localhost:9300/api/cook/<cid>
    //Example - http://localhost:9300/api/cook/73a02968-70a8-426a-b6d8-deaa96a597df
    @GetMapping("/cook/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cook getCookById(@PathVariable String cid)
            throws EntityNotFound {
        return service.getCook(cid);
    }

    //URL - http://localhost:9300/api/cook/<email>
    //Example - http://localhost:9300/api/cooks/abc@gmail.com
    @GetMapping("/cooks/{work_department}")
    @ResponseStatus(HttpStatus.OK)
    public List<Cook> getCookByEmail(@PathVariable(name = "email") String email) {
        return service.getCookByEmail(email);
    }

    //URL - http://localhost:9300/api/cooks/count
    @GetMapping("/cooks/count")
    @ResponseStatus(HttpStatus.OK)
    public long getTotalCooks() {
        return service.count();
    }
    
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public Cook registerCook(@RequestBody Cook cook) throws EntityNotFound {
        return service.insertCook(cook);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Cook updateCook(@RequestBody Cook cook) throws EntityNotFound {
        return service.updateCook(cook);
    }
    
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCook(@PathVariable("id") String cid) throws EntityNotFound {
        service.deleteCook(cid);
        
        return "Cook deleted successfully";
    }
}
