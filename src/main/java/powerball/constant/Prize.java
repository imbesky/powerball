package powerball.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Prize {
    FIRST(1, 5, 1, 20_000_000),
    SECOND(2, 5, 0, 1_000_000),
    THIRD(3, 4, 1, 50_000),
    FOURTH(4, 4, 0, 100),
    FIFTH(5, 3, 1, 100),
    SIXTH(6, 3, 0, 7),
    SEVENTH(7, 2, 1, 7),
    EIGHTH(8, 1, 1, 4),
    NINTH(9, 0, 1, 4);
    private final static Map<List<Integer>, Prize> matchedValues = Collections
            .unmodifiableMap(Stream.of(values())
                    .collect(Collectors
                            .toMap(Prize -> Arrays.asList(
                                            Prize.matchedWhiteBallNumber(),
                                            Prize.matchedRedPowerBallNumber()),
                                    Prize -> Prize)));
    private final int grade;
    private final int matchedWhiteBall;
    private final int matchRedPowerBall;
    private final int prizeValue;

    Prize(int grade, int matchedWhiteBall, int matchRedPowerBall, int prizeValue) {
        this.grade = grade;
        this.matchedWhiteBall = matchedWhiteBall;
        this.matchRedPowerBall = matchRedPowerBall;
        this.prizeValue = prizeValue;
    }

    public int getGrade() {
        return grade;
    }

    private int matchedWhiteBallNumber() {
        return matchedWhiteBall;
    }

    private int matchedRedPowerBallNumber() {
        return matchRedPowerBall;
    }

    public int getPrizeValue() {
        return prizeValue;
    }

    public static Prize findByMatchedValue(final List<Integer> matchedValue) {
        return matchedValues.get(matchedValue);
    }
}
