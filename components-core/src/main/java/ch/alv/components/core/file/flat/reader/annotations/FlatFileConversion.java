package ch.alv.components.core.file.flat.reader.annotations;

import ch.alv.components.core.beans.factory.BeanFactory;
import ch.alv.components.core.file.flat.reader.FlatFileColumnSeparatorType;
import ch.alv.components.core.file.flat.reader.internal.FlatFileBeanFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FlatFileConversion {

    FlatFileColumnSeparatorType separatorType() default FlatFileColumnSeparatorType.CHARACTER;

    String columnSeparator() default ";";

    Class<? extends BeanFactory> beanFactory() default FlatFileBeanFactory.class;

    boolean skipFirstLine() default false;
}