package com.springboot.couchbase.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.couchbase.entity.Cook;
import com.springboot.couchbase.exception.EntityNotFound;
import com.springboot.couchbase.repository.CookRepository;

//spring annotation
@Service
public class CookService {

    @Autowired
    CookRepository repository;

    //save new cook in the couchbase
    public void save(final Cook c) {
        repository.save(c);
    }

    //get total count in the couchbase
    public long count() {
        return repository.count();
    }

    //get all cooks from the couchbase
    public List<Cook> getCooks() {
        final Iterable<Cook> cookIterable = repository.findAll();
        return StreamSupport.stream(cookIterable.spliterator(), false).collect(Collectors.toList());
    }

    //get cook by id from the couchbase
    public Cook getCook(final String cid) throws EntityNotFound {
        return repository.findById(cid).orElseThrow(EntityNotFound::new);
    }

  //insert cook by id from the couchbase
    public Cook insertCook(final Cook cook) throws EntityNotFound {
        return repository.save(cook);
    }
    
  //update cook by id from the couchbase
    public Cook updateCook(final Cook employee) throws EntityNotFound {
        return repository.save(employee);
    }
    
  //delete cook by id from the couchbase
    public void deleteCook(final String cokId) throws EntityNotFound{
        repository.deleteById(cokId);
    }
    //get employees by department from the couchbase
    public List<Cook> getCookByEmail(final String email) {
        return repository.findAllByEmail(email);
    }
}
