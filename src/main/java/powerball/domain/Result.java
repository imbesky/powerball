package powerball.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import powerball.constant.Prize;

public class Result {
    private static final int INITIAL_VALUE = 0;
    private static final int ONE_CASE = 1;
    private final Map<Prize, Integer> results = new HashMap<>();
    private final WinPowerBall winPowerBall;
    private final BoughtPowerBalls boughtPowerBalls;

    public Result(final WinPowerBall winPowerBall, final BoughtPowerBalls boughtPowerBalls,
                  final int boughtPowerBallNumber) {
        for (Prize prize : Prize.values()) {
            results.put(prize, INITIAL_VALUE);
        }
        this.winPowerBall = winPowerBall;
        this.boughtPowerBalls = boughtPowerBalls;
        for (int i = 0; i < boughtPowerBallNumber; i++) {
            checkResult(i);
        }
    }

    private void checkResult(final int index) {
        BoughtPowerBall ball = boughtPowerBalls.numberDetail(index);
        for (Prize prize : Prize.values()) {
            checkPrize(prize,
                    winPowerBall.checkWhiteBalls(ball.whiteBallsDetail()),
                    winPowerBall.checkRedPowerBall(ball.redPowerBallDetail()));
        }
    }

    private void checkPrize(final Prize prize, final int matchedWhiteBalls, final boolean isRedPowerBallMatched) {
        if (prize.getMatchedWhiteBall() == matchedWhiteBalls
                && prize.isMatchRedPowerBall() == isRedPowerBallMatched) {
            results.replace(prize, results.get(prize) + ONE_CASE);
        }
    }

    public Integer numberByPrize(final Prize prize) {
        return results.get(prize);
    }

    public long totalEarning() {
        int earing = INITIAL_VALUE;
        for (Entry<Prize, Integer> result : results.entrySet()) {
            earing += result.getKey().getPrizeValue() * result.getValue();
        }
        return earing;
    }
}
