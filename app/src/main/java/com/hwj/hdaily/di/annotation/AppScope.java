package com.hwj.hdaily.di.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 作者: huangwenjian
 * <p>
 * 时间: 16/10/5
 * <p>
 * 描述:
 */

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
