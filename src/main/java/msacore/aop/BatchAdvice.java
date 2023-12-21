package msacore.aop;

import msacore.annotation.BatchQuery;
import msacore.constant.BATCH_QUERY_MODE;
import msacore.dto.BatchDto;
import msacore.util.JdbcUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * BatchAdvice
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@Component
@Aspect
public class BatchAdvice {
    @Pointcut("@Annotation(msacore.annotation.BatchQuery)")
    public void batchPointcut() { }

    @Before(value ="batchPointcut()")
    public void batchQueryExecute(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        BatchQuery batchQuery = methodSignature.getMethod().getAnnotation(BatchQuery.class);

        String query = batchQuery.query();

        if(StringUtils.isEmpty(query)){
            Class<?> entity = batchQuery.entity()[0];
            BATCH_QUERY_MODE mode = batchQuery.mode();

            switch (mode) {
                case INSERT:
                    query = JdbcUtil.makeInsertSql(entity);
                    break;
                case UPDATE:
                    query = JdbcUtil.makeUpdateSql(entity);
                    break;
            }
        }

        Object[] args = joinPoint.getArgs();
        BatchDto<?> dto = (BatchDto<?>) args[0];

        Class<?> clazz = dto.getClass();

        Field field = clazz.getDeclaredField("query");
        field.setAccessible(true);
        field.set(dto, query);
    }
}
