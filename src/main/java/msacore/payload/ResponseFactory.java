package msacore.payload;

import msacore.constant.COMMON_MESSAGE;
import msacore.exception.CustomException;
import org.springframework.http.HttpStatus;

/**
 * ResponseFactory
 *
 * <pre>
 * 코드 히스토리 (필요시 변경사항 기록)
 * </pre>
 *
 * @author JandB
 * @since 1.0
 */
public class ResponseFactory {

    /**
     * <pre>
     *    성공 응답을 넘겨준다.
     * </pre>
     * @return Response<D>
     * @param <D> Object Type
     */
    public static <D> Response<D> createSuccess(){
        return createSuccess(null, COMMON_MESSAGE.SUCCESS.getCode(), COMMON_MESSAGE.SUCCESS.getMessage());
    }

    /**
     * <pre>
     *     요청한 결과를 포함한 성공 응답을 넘겨준다.
     * </pre>
     * @param data 요청한 결과 data
     * @return Response<D>
     * @param <D> Object Type
     */
    public static <D> Response<D> createSuccess(D data){
        return createSuccess(data, COMMON_MESSAGE.SUCCESS.getCode(), COMMON_MESSAGE.SUCCESS.getMessage());
    }

    /**
     * <pre>
     *    성공 응답을 넘겨준다.
     * </pre>
     * @param data 요청한 결과 data
     * @param code 상태 코드
     * @param message 상태 메세지
     * @return Response<D>
     * @param <D> Object Type
     */
    public static <D> Response<D> createSuccess(D data, String code, String message){
        Response<D> response = new Response<>();
        response.setStatus(HttpStatus.OK.value());
        response.setCode(code);
        response.setMessage(message);
        if(data != null) response.setData(data);

        return response;
    }

    /**
     * <pre>
     *    실패 응답을 넘겨준다.
     * </pre>
     * @param e Exception
     * @return Response<D>
     * @param <D> Object type
     */
    public static <D> Response<D> createError(Exception e){

        return createError(null, e);
    }

    /**
     * <pre>
     *    실패 응답을 넘겨준다.
     * </pre>
     * @param data 넘겨줄 데이터
     * @param e Exception
     * @return Response<D>
     * @param <D> Object type
     */
    public static <D> Response<D> createError(D data, Exception e){
        Response<D> response = new Response<>();

        if(e instanceof CustomException){
            CustomException ce = (CustomException) e;
            response.setCode(ce.getErrorCode());
            response.setMessage(ce.getErrorMessage());
            response.setStatus(ce.getHttpStatus().value());
        }else{
            response.setCode(COMMON_MESSAGE.UNKNOWN.getCode());
            response.setMessage(COMMON_MESSAGE.UNKNOWN.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        if(data != null) response.setData(data);

        return response;
    }
}
