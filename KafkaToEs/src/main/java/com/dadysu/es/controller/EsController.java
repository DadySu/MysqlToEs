/*
package com.dadysu.es.controller;

import com.es.service.RenterInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EsController {

    @Resource
    private RenterInfoService renterInfoService;

    @GetMapping("importAllData")
    public String importAllData(){
        renterInfoService.importAllData();
        return "success";
    }

    @GetMapping("queryById/{id}")
    public String queryById(@PathVariable Long id){
        renterInfoService.queryById(id);
        return "success";
    }

    @GetMapping("createIndex")
    public String createIndex(){
        renterInfoService.createIndex();
        return "success";
    }

    @GetMapping("save")
    public String save(){
        renterInfoService.save();
        return "success";
    }

}
*/
