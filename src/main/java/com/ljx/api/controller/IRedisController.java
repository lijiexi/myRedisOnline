package com.ljx.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin
public interface IRedisController {
    @GetMapping("/eval")
    public Object eval(@RequestParam String command, @RequestParam String session_id, HttpServletRequest request);
}
