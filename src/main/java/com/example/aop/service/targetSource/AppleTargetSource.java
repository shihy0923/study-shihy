package com.example.aop.service.targetSource;

import org.springframework.aop.TargetSource;
import org.springframework.aop.target.AbstractBeanFactoryBasedTargetSource;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Note:
 * Date: 2023/6/22 23:08
 * Author: shihy
 */
public class AppleTargetSource extends AbstractBeanFactoryBasedTargetSource {
    private Apple apple1;
    private Apple apple2;

    public AppleTargetSource() {
        this.apple1 = new Apple(1);
        this.apple2 = new Apple(2);
    }

    @Override
    public Class<?> getTargetClass() {
        return Apple.class;
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public Object getTarget() throws Exception {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int index = random.nextInt(2);
        return index % 2 == 0 ? apple1 : apple2;
    }

    @Override
    public void releaseTarget(Object target) throws Exception {
        System.out.println("eat apple finish, id: " + target);
    }
}
