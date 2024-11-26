package com.mengadmin.common.core.annotation;

import java.lang.annotation.*;

/**
 * 操作日志模块注解
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationModule {

    /**
     * 模块名称
     */
    String value();

}
