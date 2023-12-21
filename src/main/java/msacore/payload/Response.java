package msacore.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Response
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Response<D> {

    private int status;
    private String code;
    private String message;
    private D data;
}
