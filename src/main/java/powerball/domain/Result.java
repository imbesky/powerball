package powerball.domain;

import static powerball.constant.PowerPlay.DEFAULT_MULTIPLIER_NUMBER;
import static powerball.constant.PowerPlay.POWER_PLAY_EXCEPTION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import powerball.constant.PowerPlay;
import powerball.constant.Prize;

public class Result {
    private static final int INITIAL_VALUE = 0;
    private final Map<Prize, List<Integer>> winResults = new HashMap<>();
    private final WinPowerBall winPowerBall;
    private final BoughtPowerBalls boughtPowerBalls;

    public Result(final WinPowerBall winPowerBall, final BoughtPowerBalls boughtPowerBalls,
                  final int boughtPowerBallNumber) {
        for (Prize prize : Prize.values()) {
            winResults.put(prize, new ArrayList<>());
        }
        this.winPowerBall = winPowerBall;
        this.boughtPowerBalls = boughtPowerBalls;
        for (int i = INITIAL_VALUE; i < boughtPowerBallNumber; i++) {
            checkResult(i);
        }
    }

    private void checkResult(final int index) {
        final BoughtPowerBall ball = boughtPowerBalls.numberDetail(index);
        final List<Integer> matchedValue = Arrays.asList(
                winPowerBall.checkMatchedWhiteBall(ball.whiteBallsDetail()),
                winPowerBall.checkMatchedRedPowerBall(ball.redPowerBallDetail())
        );
        final Prize result = Prize.findByMatchedValue(matchedValue);
        if (result != null) {
            saveResult(result, ball.multiplyRate());
        }
    }

    private void saveResult(final Prize prize, final int multiplyRate) {
        winResults.get(prize).add(multiplyRate);
    }

    public Integer winNumberByPrize(final Prize prize) {
        return winResults.get(prize).size();
    }

    public Map<PowerPlay, Integer> winNumberByPowerPlay(final Prize prize) {
        final List<Integer> values = winResults.get(prize);
        if (values.isEmpty() ||
                Collections.frequency(values, DEFAULT_MULTIPLIER_NUMBER) == values.size()) {
            return null;
        }
        final Map<PowerPlay, Integer> numbers = new HashMap<>();
        for (PowerPlay powerPlay : PowerPlay.values()) {
            if (values.contains(powerPlay.getRate())) {
                numbers.put(powerPlay, Collections.frequency(values, powerPlay.getRate()));
            }
        }
        return numbers;
    }

    public long totalEarning() {
        long earning = INITIAL_VALUE;
        for (Entry<Prize, List<Integer>> result : winResults.entrySet()) {
            earning += calculateEarning(result.getKey(), result.getValue());
        }
        return earning;
    }

    private long calculateEarning(final Prize prize, final List<Integer> numbers) {
        if (POWER_PLAY_EXCEPTION.contains(prize)) {
            return powerPlayExceptionEarning(prize.getPrizeValue(), numbers.size());
        }
        return powerPlayApplicableEarning(prize.getPrizeValue(), numbers);
    }

    private long powerPlayApplicableEarning(final int prizeValue, final List<Integer> numbers) {
        return prizeValue
                * numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();
    }

    private long powerPlayExceptionEarning(final int prizeValue, final int number) {
        return (long) prizeValue * number;
    }
}
