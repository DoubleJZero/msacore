package msacore.exception;

import msacore.constant.COMMON_MESSAGE;
import msacore.payload.Response;
import msacore.payload.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * CustomExceptionHandler
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
@ControllerAdvice(annotations = RestController.class)
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CustomBadRequestException.class)
    public Response<?> handleException(CustomBadRequestException cbre){
        return createCustomError(cbre);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(CustomUnauthorizedException.class)
    public Response<?> handleException(CustomUnauthorizedException cue){
        return createCustomError(cue);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomNotFoundException.class)
    public Response<?> handleException(CustomNotFoundException cnfe){
        return createCustomError(cnfe);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(CustomForbiddenException.class)
    public Response<?> handleException(CustomForbiddenException cfe){
        return createCustomError(cfe);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CustomBusinessException.class)
    public Response<?> handleException(CustomBusinessException cbe){
        return createCustomError(cbe);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Response<?> handleException(Exception e){
        return createCustomError(CustomExceptionFactory.createBusinessException(COMMON_MESSAGE.UNKNOWN));
    }

    private Response<?> createCustomError(CustomException ce){
        return ResponseFactory.createError(ce);
    }
}
