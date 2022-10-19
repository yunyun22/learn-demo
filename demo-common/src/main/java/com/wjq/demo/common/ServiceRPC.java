package com.wjq.demo.common;

import java.lang.annotation.*;

/**
 * @author wjq
 * @since 2022-03-22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceRPC {

    String serviceName()  default "" ;
}
