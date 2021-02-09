package com.lbj.common.client.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname RunTime
 * @Description 统计函数执行时间切面
 * @Date 2021/2/8 17:59
 * @Created by lbj
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RunTime {

    /**
     * 函数备注
     *
     * @return
     */
    String remark() default "";
}
