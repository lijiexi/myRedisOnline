package com.ljx.strategy;

import com.ljx.myRedis.api.ICache;
import com.ljx.myRedis.core.bs.CacheBs;
import com.ljx.myRedis.core.support.load.CacheLoads;
import com.ljx.myRedis.core.support.persist.CachePersists;
import com.ljx.result.ErrorResult;
import com.ljx.result.ResponseResult;

public class setStrategy implements Strategy{
    @Override
    public Object run(String[] record, String ip) throws Exception {
        ICache<String,String> cache = CacheBs.<String,String>newInstance()
                .persist(CachePersists.aof(ip+".txt"))
                .load(CacheLoads.aof(ip+".txt"))
                .build();
        int n = record.length;
        String command = record[0];
        if (n > 3 || n == 1) {
            return ErrorResult.syntaxError();
        }
        cache.put(record[1],record[2]);
        return ResponseResult.ok();
    }
}
