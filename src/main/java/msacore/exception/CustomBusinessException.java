package msacore.exception;

public class CustomBusinessException extends CustomException {
    CustomBusinessException(DefaultMessage dm) {
        super(dm);
    }
}
