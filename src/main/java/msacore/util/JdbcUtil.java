package msacore.util;

import msacore.annotation.BatchIgnore;
import msacore.annotation.BatchWhereClause;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcUtil
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
public class JdbcUtil {

    /**
     * <pre>
     *     batch용 insertSql문을 문자열로 만들어 넘겨준다.
     * </pre>
     * @param clazz Class<E>
     * @return String
     * @param <E> entity
     */
    public static <E> String makeInsertSql(Class<E> clazz) {
        String tableName = clazz.getAnnotation(Table.class).name();
        List<String> columns = new ArrayList<>();
        List<String> params = new ArrayList<>();

        for(Field field : clazz.getDeclaredFields()){
            if(field.getAnnotation(BatchIgnore.class) != null) continue;

            columns.add(field.getAnnotation(Column.class).name());
            params.add(":" + field.getName());
        }

        return "INSERT INTO " + tableName + " (" + StringUtils.join(columns, ", ") + ") VALUES (" + StringUtils.join(params, ", ") + ")";
    }

    /**
     * <pre>
     *     batch용 updateSql문을 문자열로 만들어 넘겨준다.
     * </pre>
     * @param clazz Class<E>
     * @return String
     * @param <E> entity
     */
    public static <E> String makeUpdateSql(Class<E> clazz) {

        String tableName = clazz.getAnnotation(Table.class).name();
        List<String> columns = new ArrayList<>();
        List<String> whereClause = new ArrayList<>();

        for(Field field : clazz.getDeclaredFields()){
            if(field.getAnnotation(BatchIgnore.class) != null) continue;

            String column = field.getAnnotation(Column.class).name() + " = : " + field.getName();

            if(field.getAnnotation(BatchWhereClause.class) != null){
                whereClause.add(column);
                continue;
            }

            columns.add(column);
        }

        return "UPDATE " + tableName + " SET " + StringUtils.join(columns, ", ") + " WHERE " + StringUtils.join(whereClause, " AND ");
    }
}
