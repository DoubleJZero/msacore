package msacore.exception;

public class CustomBadRequestException extends CustomException {
    CustomBadRequestException(DefaultMessage dm) {
        super(dm);
    }
}
