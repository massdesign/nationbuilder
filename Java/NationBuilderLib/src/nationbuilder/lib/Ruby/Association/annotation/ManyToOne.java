package nationbuilder.lib.Ruby.Association.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by patrick on 10/6/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ManyToOne
{
	String mapIdTo() default "";
	String mappedBy() default "";
	Class mappedByClazz() default Object.class;

}
