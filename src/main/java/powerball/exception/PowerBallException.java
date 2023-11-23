package powerball.exception;

public class PowerBallException extends IllegalArgumentException {
    public PowerBallException(final String string) {
        super(string);
    }

    public String errorPage() {
        return null;
    }
}
