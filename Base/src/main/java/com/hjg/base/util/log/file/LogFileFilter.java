package com.hjg.base.util.log.file;


/**
 * Created by pengwei on 2017/3/31.
 * 日志过滤器
 */

public interface LogFileFilter {
    boolean accept(int level, String tag, String logContent);
}