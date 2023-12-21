package msacore.annotation;

import msacore.constant.BATCH_QUERY_MODE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * BatchQuery(bulk batch query)
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@Target({METHOD})
@Retention(RUNTIME)
public @interface BatchQuery {

    String query() default "";
    BATCH_QUERY_MODE mode() default BATCH_QUERY_MODE.INSERT;
    Class<?>[] entity() default {};
}
