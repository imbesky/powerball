package powerball.domain;

import java.util.List;
import powerball.constant.PowerPlay;

public class BoughtPowerBall {
    private final WhiteBall whiteBalls = WhiteBall.createWhiteBalls();
    private final RedPowerBall redPowerBall = RedPowerBall.createRedPowerBall();
    private final int multiplierNumber;

    public BoughtPowerBall(final boolean isPowerPlay) {
        if (isPowerPlay) {
            multiplierNumber = PowerPlay.randomRate();
            return;
        }
        multiplierNumber = PowerPlay.DEFAULT_MULTIPLIER_NUMBER;
    }

    public List<Integer> whiteBallsDetail() {
        return whiteBalls.numbers();
    }

    public int redPowerBallDetail() {
        return redPowerBall.number();
    }

    public int multiplyRate() {
        return multiplierNumber;
    }

}
