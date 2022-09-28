package com.wjq.demo.feign.base;

import feign.MethodMetadata;
import feign.Param;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import static feign.Util.checkState;
import static feign.Util.emptyToNull;

/**
 * @author wjq
 * @since 2022-09-28
 */
@Component
public class RequestMultiParamParameterProcessor implements AnnotatedParameterProcessor {

    private static final Class<MultiRequestParam> ANNOTATION = MultiRequestParam.class;

    @Override
    public Class<? extends Annotation> getAnnotationType() {
        return ANNOTATION;
    }

    @Override
    public boolean processArgument(AnnotatedParameterContext context, Annotation annotation, Method method) {
        int parameterIndex = context.getParameterIndex();
        Class<?> parameterType = method.getParameterTypes()[parameterIndex];
        MethodMetadata data = context.getMethodMetadata();


        Field[] fields = parameterType.getDeclaredFields();

        Collection<String> names = new ArrayList<>();
        for (Field field : fields) {
            names.add(field.getName());
        }
        MultiRequestParam requestParam = ANNOTATION.cast(annotation);
        String name = requestParam.value();
        checkState(emptyToNull(name) != null,
                "RequestParam.value() was empty on parameter %s", parameterIndex);
        context.setParameterName(name);

        Collection<String> query = context.setTemplateParameter(name, names);
        context.getMethodMetadata().indexToExpander().put(context.getParameterIndex(), Object::toString);
        data.template().query(name, query);
        return true;
    }
}
