package powerball.domain;

import java.util.List;

public class WinPowerBall {
    private static final int TRUE = 1;
    private static final int FALSE = 0;
    private final WhiteBall whiteBalls;
    private final RedPowerBall redPowerBall;

    public WinPowerBall(final WhiteBall whiteBalls, final RedPowerBall redPowerBall) {
        this.whiteBalls = whiteBalls;
        this.redPowerBall = redPowerBall;
    }

    public int checkWhiteBalls(final List<Integer> inputNumbers) {
        int matchedNumber = 0;
        for (int number : inputNumbers) {
            matchedNumber += contains(number);
        }
        return matchedNumber;
    }

    private int contains(final int number) {
        if (whiteBalls.inquireDetails().contains(number)) {
            return TRUE;
        }
        return FALSE;
    }

    public boolean checkRedPowerBall(final int number) {
        return redPowerBall.inquireDetail() == number;
    }

}
