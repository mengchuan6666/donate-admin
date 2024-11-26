package com.mengadmin.common.core.annotation;

import java.lang.annotation.*;

/**
 * 操作日志记录注解
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {

    /**
     * 操作功能
     */
    String value() default "";

    /**
     * 操作模块
     */
    String module() default "";

    /**
     * 备注
     */
    String comments() default "";

    /**
     * 是否记录请求参数
     */
    boolean param() default true;

    /**
     * 是否记录返回结果
     */
    boolean result() default true;

}
