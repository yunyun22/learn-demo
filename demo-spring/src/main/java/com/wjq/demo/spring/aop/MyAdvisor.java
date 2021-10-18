package com.wjq.demo.spring.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.stereotype.Component;

/**
 * @author wjq
 * @since 2021-10-18
 */
@Component
public class MyAdvisor  implements PointcutAdvisor {
    @Override
    public Pointcut getPointcut() {
        return new MyPointCut();
    }

    @Override
    public Advice getAdvice() {
        return new MyMethodBeforeAdvice();
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
