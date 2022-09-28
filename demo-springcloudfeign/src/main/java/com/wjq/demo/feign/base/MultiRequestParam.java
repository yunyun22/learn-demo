package com.wjq.demo.feign.base;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author wjq
 * @since 2022-09-28
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MultiRequestParam {

    /**
     * Alias for {@link #name}.
     */
    @AliasFor("name")
    String value() default "";

    /**
     * The name of the request parameter to bind to.
     * @since 4.2
     */
    @AliasFor("value")
    String name() default "";
}
