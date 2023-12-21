package msacore.constant;

import lombok.Getter;

/**
 * EXCEPTION_TYPE
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@Getter
public enum EXCEPTION_TYPE {
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    BUSINESS(500);

    private int status;

    EXCEPTION_TYPE(int status){
        this.status = status;
    }
}
