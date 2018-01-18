package com.klindziuk.mail.annotation;

import com.klindziuk.mail.enumeration.SearchBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Hp on 18/01/2018.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface Anno {
    SearchBy searchBy();
    String value();
}
