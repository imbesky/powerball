package powerball.constant;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Exception {
    NOT_NUMERIC("숫자가 아닌 입력입니다.", "not-numeric", "/exception?type=not-numeric"),
    OUT_OF_RANGE("입력된 숫자는 올바른 범위가 아닙니다.", "out-of-range", "/exception?type=out-of-range"),
    INVALID_LENGTH("입력된 숫자의 개수가 올바르지 않습니다.", "invalid-length", "/exception?type=invalid-length"),
    INVALID_PURCHASE("입력된 금액이 올바르지 않습니다.", "invalid-purchase", "/exception?type=invalid-purchase");
    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;
    private final String type;
    private final String url;

    Exception(String message, String type, String url) {
        this.message = message;
        this.type = type;
        this.url = url;
    }

    public String getMessage() {
        return ERROR_PREFIX.concat(message);
    }

    public String getUrl() {
        return url;
    }

    private String getType() {
        return type;
    }

    public static Exception findByType(final String type) {
        final Map<String, Exception> exceptions = Collections
                .unmodifiableMap(Stream
                        .of(values())
                        .collect(Collectors
                                .toMap(Exception -> Exception.getType(), Exception -> Exception)));
        return exceptions.get(type);
    }
}
