package com.wjq.demo.spring;

import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;

public class SpelTest {

    public static void main(String[] args) throws NoSuchMethodException {

        Method test = SpelTest.class.getMethod("test", String.class);

        SpelExpressionParser parser = new SpelExpressionParser();
        ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        String[] argments = new String[]{"wwww"};

        String s = "#a";
        Object value = null;
        if (s.startsWith("#")) {
            EvaluationContext context = new MethodBasedEvaluationContext(null, test, argments, parameterNameDiscoverer);
            value= parser.parseExpression(s).getValue(context);
        } else {
            value = s;
        }
        System.out.println(value);


    }


    public void test(String a) {
        System.out.println(a);
    }
}
