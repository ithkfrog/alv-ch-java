package ch.alv.components.core.file.flat.reader.annotations;

import ch.alv.components.core.file.flat.reader.Transformer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Transform {

	Transformer.ColumnSeparator columnSeparatorType() default Transformer.ColumnSeparator.CHARACTER;

	String columnSeparator() default ";";

	String beanCreator() default "ch.alv.components.core.file.flat.reader.DefaultBeanFactory";

	boolean skipFirstLine() default false;
}