package powerball.exception;

import static powerball.constant.Exception.INVALID_LENGTH;

import powerball.constant.Exception;

public class InvalidLengthException extends PowerBallException {
    private static final Exception TYPE = INVALID_LENGTH;

    public InvalidLengthException() {
        super(TYPE.getMessage());
    }
}
