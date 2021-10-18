package com.wjq.demo.common.annotation;

import java.lang.annotation.*;

/**
 * @author wjq
 * @since 2021-10-18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
}
