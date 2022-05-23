package com.ljx.strategy;

import com.ljx.myRedis.api.ICache;
import com.ljx.myRedis.core.bs.CacheBs;
import com.ljx.myRedis.core.support.load.CacheLoads;
import com.ljx.myRedis.core.support.persist.CachePersists;
import com.ljx.result.ErrorResult;
import com.ljx.result.ResponseResult;

import static com.ljx.utils.commonUtil.isNumber;

public class delStrategy implements Strategy{
    @Override
    public Object run(String[] record, String ip) throws Exception {
        ICache<String,String> cache = CacheBs.<String,String>newInstance()
                .persist(CachePersists.aof(ip+".txt"))
                .load(CacheLoads.aof(ip+".txt"))
                .build();
        int n = record.length;
        String command = record[0];
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
}
