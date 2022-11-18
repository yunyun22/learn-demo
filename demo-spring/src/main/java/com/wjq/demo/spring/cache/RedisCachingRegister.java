package com.wjq.demo.spring.cache;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Administrator
 */
public class RedisCachingRegister implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {


    private ResourceLoader resourceLoader;

    private Environment environment;

    private Set<String> set = new HashSet<>();

    RedisCachingRegister() {
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {

        LinkedHashSet<BeanDefinition> candidateComponents = new LinkedHashSet<>();


        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        scanner.setResourceLoader(this.resourceLoader);
        scanner.addIncludeFilter(new MethodTypeFilter(RedisCacheable.class));
        Set<String> basePackages = getBasePackages(importingClassMetadata);

        for (String basePackage : basePackages) {
            candidateComponents.addAll(scanner.findCandidateComponents(basePackage));
        }


        for (BeanDefinition beanDefinition : candidateComponents) {
            String beanClassName = beanDefinition.getBeanClassName();
            if (!StringUtils.hasText(beanClassName)) {
                continue;
            }
            try {
                Class<?> aClass = Class.forName(beanClassName);

                Method[] methods = aClass.getMethods();

                for (Method method : methods) {
                    RedisCacheable annotation = method.getAnnotation(RedisCacheable.class);
                    if (annotation == null) {
                        continue;
                    }
                    String[] value = annotation.value();
                    String ttl = annotation.ttl();
                    String ttlType = annotation.ttlType();
                    if (set.contains(value[0])) {
                        continue;
                    }


                    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CacheConfig.class)
                            .addConstructorArgValue(ttl)
                            .addConstructorArgValue(ttlType)
                            .addConstructorArgValue(value)
                            .setLazyInit(true)
                            .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
                    AbstractBeanDefinition beanDefinition1 = beanDefinitionBuilder.getBeanDefinition();

                    BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition1, beanClassName.concat(".").concat(value[0]));
                    BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
                    set.add(value[0]);
                }
            } catch (ClassNotFoundException e) {
                continue;
            }


        }


    }


    private Set<String> getBasePackages(AnnotationMetadata importingClassMetadata) {

        Set<String> basePackages = new HashSet<>();

        basePackages.add(
                ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        return basePackages;
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    protected ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false, this.environment) {
            @Override
            protected boolean isCandidateComponent(
                    AnnotatedBeanDefinition beanDefinition) {
                return true;
            }
        };
    }


}
