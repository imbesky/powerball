package powerball.exception;

import static powerball.constant.ErrorMessage.ERROR_PREFIX;
import static powerball.constant.ErrorMessage.OUT_OF_RANGE;

public class OutOfRangeException extends IllegalArgumentException {
    public OutOfRangeException() {
        super(ERROR_PREFIX.concat(OUT_OF_RANGE));
    }
}
