package powerball.exception;

import static powerball.constant.ErrorMessage.ERROR_PREFIX;
import static powerball.constant.ErrorMessage.INVALID_PURCHASE;

public class InvalidPurchaseException extends IllegalArgumentException {
    public InvalidPurchaseException() {
        super(ERROR_PREFIX.concat(INVALID_PURCHASE));
    }
}
