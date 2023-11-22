package powerball.exception;

import static powerball.constant.ErrorMessage.ERROR_PREFIX;
import static powerball.constant.ErrorMessage.INVALID_LENGTH;

public class InvalidLengthException extends IllegalArgumentException {
    public InvalidLengthException() {
        super(ERROR_PREFIX.concat(INVALID_LENGTH));
    }
}
