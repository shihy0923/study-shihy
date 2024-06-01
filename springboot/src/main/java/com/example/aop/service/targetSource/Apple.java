package com.example.aop.service.targetSource;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Note:
 * Date: 2023/6/22 23:08
 * Author: shihy
 */
@Component
@Data
public class Apple {
    private int id;

    public Apple(int id) {
        this.id = id;
    }

    public void eat() {
        System.out.println("eat apple, id: " + id);
    }
}
