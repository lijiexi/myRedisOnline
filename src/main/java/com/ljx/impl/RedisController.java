package com.ljx.impl;


import com.ljx.api.controller.IRedisController;


import com.ljx.myRedis.api.ICache;
import com.ljx.myRedis.core.bs.CacheBs;
import com.ljx.myRedis.core.support.load.CacheLoads;
import com.ljx.myRedis.core.support.persist.CachePersists;
import com.ljx.result.ErrorResult;
import com.ljx.result.HelpResult;
import com.ljx.result.ResponseResult;
import com.ljx.strategy.*;
import com.ljx.utils.IPUtil;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RedisController implements IRedisController {


    public Object eval(String command, String session_id, HttpServletRequest request) throws Exception {
        Map<String, Strategy> map = new HashMap<>();
        map.put("set",new setStrategy());
        map.put("get",new getStrategy());
        map.put("decr",new decrStrategy());
        map.put("incr",new incrStrategy());
        map.put("del",new delStrategy());

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
        return this.execute(record,ip,map);

      //  return ErrorResult.ok();
    }
    private Object execute (String[] record, String ip, Map<String, Strategy> map) throws Exception {



        Strategy strategy = map.get(record[0]);
        if (strategy == null) {
            return ErrorResult.ok();
        }
        return strategy.run(record,ip);

    }


}
