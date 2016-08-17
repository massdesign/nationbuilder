package nationbuilder.lib.Ruby.Association.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author patrick.ekkel
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ID
{
	// TODO: niet zo'n sexy methode om aan te geven waar het id van een tableperclass structuur gelaten moet worden, zorgt er namelijk voor dat je de superclass aan moet passen voor een subclass toevoeging
	String mapIdToEntity() default "";
}
