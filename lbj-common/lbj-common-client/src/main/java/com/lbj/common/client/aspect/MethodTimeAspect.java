package com.lbj.common.client.aspect;

import com.lbj.common.client.annotation.RunTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @Classname MethodTimeAspect
 * @Description 函数执行时间切面
 * @Date 2021/2/8 18:07
 * @Created by lbj
 */
@Slf4j
@Aspect
@Component
public class MethodTimeAspect {
    //声明切面类路径，类型必须为final String类型的,注解里要使用的变量只能是静态常量类型的
    @Pointcut("execution(* com.lbj.*.*.*(..))")
    public void point() {
    }

    @Around("@annotation(runTime)")
    public Object doAround(ProceedingJoinPoint pjp, RunTime runTime) {
        long startTime = System.currentTimeMillis();
        Object obj = null;
        try {
            obj = pjp.proceed();
        } catch (Throwable e) {
            log.error("环绕通知错误",e);
        }
        long endTime = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

        log.info("函数名【{}】耗时：【{}】毫秒，备注【{}】", methodName, endTime - startTime, runTime.remark());
        return obj;
    }
}
