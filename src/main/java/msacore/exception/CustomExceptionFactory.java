package msacore.exception;

import msacore.constant.EXCEPTION_TYPE;
import org.springframework.http.HttpStatus;

/**
 * CustomExceptionFactory
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
public class CustomExceptionFactory {

    static CustomException createException(DefaultMessage dm, EXCEPTION_TYPE type){
        CustomException ce;
        switch (type) {
            case BAD_REQUEST:
                ce = new CustomBadRequestException(dm);
                ce.setHttpStatus(HttpStatus.BAD_REQUEST);
                break;
            case UNAUTHORIZED:
                ce = new CustomUnauthorizedException(dm);
                ce.setHttpStatus(HttpStatus.UNAUTHORIZED);
                break;
            case FORBIDDEN:
                ce = new CustomForbiddenException(dm);
                ce.setHttpStatus(HttpStatus.FORBIDDEN);
                break;
            case NOT_FOUND:
                ce = new CustomNotFoundException(dm);
                ce.setHttpStatus(HttpStatus.NOT_FOUND);
                break;
            case BUSINESS:
            default:
                ce = new CustomBusinessException(dm);
                ce.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                break;
        }

        return ce;
    }

    /**
     * <pre>
     *     BAD_REQUEST exception create
     * </pre>
     * @param dm DefaultMessage
     * @return CustomException
     */
    public static CustomException createBadRequestException(DefaultMessage dm){
        return createException(dm, EXCEPTION_TYPE.BAD_REQUEST);
    }

    /**
     * <pre>
     *     BUSINESS exception create
     * </pre>
     * @param dm DefaultMessage
     * @return CustomException
     */
    public static CustomException createBusinessException(DefaultMessage dm){
        return createException(dm, EXCEPTION_TYPE.BUSINESS);
    }
}
