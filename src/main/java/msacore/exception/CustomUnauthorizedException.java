package msacore.exception;

public class CustomUnauthorizedException extends CustomException {
    CustomUnauthorizedException(DefaultMessage dm) {
        super(dm);
    }
}
