package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

// @GetMapping("hello")
 @RequestMapping(path = "hello", method = RequestMethod.GET, produces = "application/json")
    public String  getHello(){
     return "This is Spring";
 }
}
