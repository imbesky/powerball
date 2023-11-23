package powerball.constant;

public enum Prize {
    FIRST(1, 5, true, 20_000_000),
    SECOND(2, 5, false, 1_000_000),
    THIRD(3, 4, true, 50_000),
    FOURTH(4, 4, false, 100),
    FIFTH(5, 3, true, 100),
    SIXTH(6, 3, false, 7),
    SEVENTH(7, 2, true, 7),
    EIGHTH(8, 1, true, 4),
    NINTH(9, 0, true, 4);
    private final int grade;
    private final int matchedWhiteBall;
    private final boolean matchRedPowerBall;
    private final int prizeValue;

    Prize(int grade, int matchedWhiteBall, boolean matchRedPowerBall, int prizeValue) {
        this.grade = grade;
        this.matchedWhiteBall = matchedWhiteBall;
        this.matchRedPowerBall = matchRedPowerBall;
        this.prizeValue = prizeValue;
    }

    public int getGrade() {
        return grade;
    }

    public int getMatchedWhiteBall() {
        return matchedWhiteBall;
    }

    public boolean isMatchRedPowerBall() {
        return matchRedPowerBall;
    }

    public int getPrizeValue() {
        return prizeValue;
    }
}
