package com.ljx.strategy;

import com.ljx.myRedis.api.ICache;
import com.ljx.myRedis.core.bs.CacheBs;
import com.ljx.myRedis.core.support.load.CacheLoads;
import com.ljx.myRedis.core.support.persist.CachePersists;
import com.ljx.result.ErrorResult;
import com.ljx.result.ResponseResult;

import static com.ljx.utils.commonUtil.isNumber;

public class expireStrategy implements Strategy{
    @Override
    public Object run(String[] record, String ip) throws Exception {
        ICache<String,String> cache = CacheBs.<String,String>newInstance()
                .persist(CachePersists.aof(ip+".txt"))
                .load(CacheLoads.aof(ip+".txt"))
                .build();
        int n = record.length;
        String command = record[0];

        if (n != 3 || !isNumber(record[2])) {
            return ErrorResult.syntaxError();
        }
        if (cache.get(record[1]) == null)
            return ResponseResult.ok("integer 0","");
        cache.expire(record[1],Integer.valueOf(record[2])*1000);
        return ResponseResult.ok("integer 1","");
    }
}
