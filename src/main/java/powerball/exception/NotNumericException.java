package powerball.exception;

import static powerball.constant.Exception.NOT_NUMERIC;

import powerball.constant.Exception;

public class NotNumericException extends PowerBallException {
    private static final Exception TYPE = NOT_NUMERIC;

    public NotNumericException() {
        super(TYPE.getMessage());
    }

    public String errorPage() {
        return TYPE.getUrl();
    }
}
