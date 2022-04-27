package com.ljx.api.controller;

import com.ljx.result.ErrorResult;
import com.ljx.result.HelpResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RedisController {
    @GetMapping("/eval")
    public Object hello(String command, String session_id) {
        System.out.println("command is: "+command);
        System.out.println("session_id is: "+ session_id);
        String[] record = command.split(" ");
        if (record[0].toLowerCase().equals("help")) {
            if (record.length == 1) {
                return new HelpResult();
            }
            //返回具体指令指示
            return new HelpResult(record[1]);
        }

        return ErrorResult.ok();
    }
}
