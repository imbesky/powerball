package powerball.exception;

import static powerball.constant.Exception.INVALID_PURCHASE;

import powerball.constant.Exception;

public class InvalidPurchaseException extends PowerBallException {
    private static final Exception TYPE = INVALID_PURCHASE;

    public InvalidPurchaseException() {
        super(TYPE.getMessage());
    }
}
