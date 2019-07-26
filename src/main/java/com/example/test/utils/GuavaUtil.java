package com.example.test.utils;


import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import okhttp3.*;
import org.apache.camel.processor.Splitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class GuavaUtil {
        private static Logger logger= LoggerFactory.getLogger(GuavaUtil.class);
        public static final String TOKEN_PREFIX="token_";
        private static LoadingCache<String,String> localCache= CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS)
                .build(new CacheLoader<String, String>() {
                    //默认的数据加载实现，当调用get取值的时候，如果key没有相应的值，就调用这个方法进行加载
                    @Override
                    public String load(String s) throws Exception {
                        return "null";
                    }
                });
        public static void setKey(String key,String value){
            localCache.put(key, value);
        }
        public static String getKey(String key){
            String value=null;
            try{
                value=localCache.get(key);
                if("null".equals(value)){
                    return null;
                }
                return value;
            }catch (Exception e){
                logger.error("localCache get error",e);
            }
            return null;
        }

    public static void main(String[] args) {


    }

}
