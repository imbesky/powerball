package powerball.exception;

import static powerball.constant.Exception.OUT_OF_RANGE;

import powerball.constant.Exception;

public class OutOfRangeException extends PowerBallException {
    private static final Exception TYPE = OUT_OF_RANGE;

    public OutOfRangeException() {
        super(TYPE.getMessage());
    }

    public String errorPage() {
        return TYPE.getUrl();
    }
}
