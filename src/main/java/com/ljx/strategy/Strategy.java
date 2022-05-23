package com.ljx.strategy;

/**
 * 策略模式接口
 */
public interface Strategy {
    Object run (String[] record, String ip) throws Exception;
}
