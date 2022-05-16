package com.ljx.impl;


import com.ljx.api.controller.IRedisController;


import com.ljx.myRedis.api.ICache;
import com.ljx.myRedis.core.bs.CacheBs;
import com.ljx.myRedis.core.support.load.CacheLoads;
import com.ljx.myRedis.core.support.persist.CachePersists;
import com.ljx.result.ErrorResult;
import com.ljx.result.HelpResult;
import com.ljx.result.ResponseResult;
import com.ljx.utils.IPUtil;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class RedisController implements IRedisController {

    public Object eval(String command, String session_id, HttpServletRequest request) {

        String ip = IPUtil.getRequestIp(request);
        System.out.println("command is: "+command);
        System.out.println("session_id is: "+ session_id);
        System.out.println(ip);

        String[] record = command.split(" ");
        //help operation
        if (record[0].toLowerCase().equals("help")) {
            if (record.length == 1) {
                return new HelpResult();
            }
            //返回具体指令指示
            return new HelpResult(record[1]);
        }
        //执行操作
        return this.execute(record,ip);

      //  return ErrorResult.ok();
    }
    private Object execute (String[] record,String ip) {
        ICache<String,String> cache = CacheBs.<String,String>newInstance()
                .persist(CachePersists.aof(ip+".txt"))
                .load(CacheLoads.aof(ip+".txt"))
                .build();
        int n = record.length;
        String command = record[0];

        if ("set".equalsIgnoreCase(command)) {
            if (n > 3) {
                return ErrorResult.syntaxError();
            }
            cache.put(record[1],record[2]);
            return ResponseResult.ok();
        } else if ("get".equalsIgnoreCase(command)) {

            if (n != 2) {
                return ErrorResult.syntaxError();
            }
            String res = cache.get(record[1]);
            if (res == null)
                res = "nil";
            return ResponseResult.ok(res,"");
        } else if ("incr".equalsIgnoreCase(command)) {
            if (n != 2 || !isNumber(record[1])) {
                return ErrorResult.syntaxError();
            }
            cache.put(record[1],Integer.valueOf(cache.getOrDefault(record[1],"0"))+1+"");
            String res = cache.get(record[1]);
            return ResponseResult.ok(res,"");
        } else if ("decr".equalsIgnoreCase(command)) {
            if (n != 2 || !isNumber(record[1])) {
                return ErrorResult.syntaxError();
            }
            cache.put(record[1],Integer.valueOf(cache.getOrDefault(record[1],"0"))-1+"");
            String res = cache.get(record[1]);
            return ResponseResult.ok(res,"");
        } else if ("del".equalsIgnoreCase(command)) {
            if (n != 2 || !isNumber(record[1])) {
                return ErrorResult.syntaxError();
            }
            String res = cache.remove(record[1]);
            if (res == null) {
                return ResponseResult.ok("integer 0","");
            } else {
                return ResponseResult.ok("integer 1","");
            }
        }
        return null;
    }

    /**
     * 判断参数是否为数字
     * @param str string
     * @return
     */
    private boolean isNumber (String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
