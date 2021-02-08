package cn.hyqup.common.web.validator.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/8
 * @description:
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        assertApplicationContext(null, null);
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        assertApplicationContext(beanName, null);
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requiredType) {
        assertApplicationContext(null, requiredType);
        return applicationContext.getBean(requiredType);
    }

    private static void assertApplicationContext(String beanName, Class requiredType) {
        if (SpringContextHolder.applicationContext == null) {
            throw new RuntimeException("ApplicaitonContext property is NULL, please check whether SpringContextHolder is injected!");
        }
        if (beanName != null) {
            boolean have = applicationContext.containsBean(beanName);
            if (!have) {
                throw new RuntimeException("This bean is not managed by the Spring container  ");
            }
        }
        if (requiredType != null) {
            String[] beanNamesForType = applicationContext.getBeanNamesForType(requiredType);
            if (beanNamesForType == null || beanNamesForType.length == 0) {
                throw new RuntimeException("This bean is not managed by the Spring container  ");
            }
            if (beanNamesForType.length != 1) {
                throw new RuntimeException("Class type fetching is not supported for multiple types of beans. Use the name to get this bean");
            }
            boolean have = applicationContext.containsBean(beanNamesForType[0]);
            if (!have) {
                throw new RuntimeException("This bean is not managed by the Spring container ");
            }
        }

    }

}
