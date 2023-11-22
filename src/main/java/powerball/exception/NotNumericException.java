package powerball.exception;

import static powerball.constant.ErrorMessage.ERROR_PREFIX;
import static powerball.constant.ErrorMessage.NOT_NUMERIC;

public class NotNumericException extends IllegalArgumentException {
    public NotNumericException() {
        super(ERROR_PREFIX.concat(NOT_NUMERIC));
    }
}
