package com.concrete.android.challenge.di.module;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Documented
@MapKey
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewModelKey {
  Class<? extends ViewModel> value();
}
