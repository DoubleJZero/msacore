package msacore.constant;

import lombok.Getter;
import msacore.exception.DefaultMessage;

/**
 * COMMON_MESSAGE
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@Getter
public enum COMMON_MESSAGE implements DefaultMessage {
    SUCCESS("CM0000", "성공"),
    BAD_REQUEST("CM0001", "잘못된 요청을 했습니다."),
    UNKNOWN("CM9999","알수없는 에러");

    private String code;
    private String message;

    COMMON_MESSAGE(String code, String message){
        this.code = code;
        this.message = message;
    }
}
