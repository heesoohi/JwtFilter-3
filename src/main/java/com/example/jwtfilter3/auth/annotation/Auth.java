package com.example.jwtfilter3.auth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 이 어노테이션을 ArgumentResolver 의 파라미터에 쓸거니까!
@Retention(RetentionPolicy.RUNTIME) // 이 어노테이션이 런타임동안 살아있어야 하니까!
public @interface Auth {
}
