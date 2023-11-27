package powerball.constant;

public enum Exception {
    NOT_NUMERIC("숫자가 아닌 입력입니다."),
    OUT_OF_RANGE("입력된 숫자는 올바른 범위가 아닙니다."),
    INVALID_LENGTH("입력된 숫자의 개수가 올바르지 않습니다."),
    INVALID_PURCHASE("입력된 금액이 올바르지 않습니다.");
    private static final String ERROR_PREFIX = "[ERROR] ";
    public static final String URL = "exception";
    private final String message;

    Exception(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX.concat(message);
    }
}
