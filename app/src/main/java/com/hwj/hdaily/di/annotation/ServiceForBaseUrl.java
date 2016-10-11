package com.hwj.hdaily.di.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * 作者: huangwenjian
 * <p>
 * 时间: 16/10/6
 * <p>
 * 描述:
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceForBaseUrl {
}
