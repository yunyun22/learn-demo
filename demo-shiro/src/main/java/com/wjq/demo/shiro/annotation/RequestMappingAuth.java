package com.wjq.demo.shiro.annotation;

import java.lang.annotation.*;

/**
 * @author wjq
 * @since 2021-10-11
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMappingAuth {
}
