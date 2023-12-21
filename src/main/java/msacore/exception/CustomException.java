package msacore.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class CustomException extends Exception {

    private String errorCode;
    private String errorMessage;
    HttpStatus httpStatus = HttpStatus.OK;

    CustomException(DefaultMessage dm){
        this.errorCode = dm.getCode();
        this.errorMessage = dm.getMessage();
    }
}
