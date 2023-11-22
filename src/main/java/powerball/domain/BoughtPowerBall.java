package powerball.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BoughtPowerBall {
    private static final int DEFAULT_MULTIPLIER_NUMBER = 1;
    private static final List<Integer> POWER_PLAY = Arrays.asList(2, 3, 4, 5, 10);
    private final WhiteBall whiteBalls = new WhiteBall();
    private final RedPowerBall redPowerBall = new RedPowerBall();
    private final int multiplierNumber;

    public BoughtPowerBall(final boolean isPowerPlay) {
        if (isPowerPlay) {
            final Random random = new Random();
            multiplierNumber = POWER_PLAY.get(random.nextInt(POWER_PLAY.size()));
            return;
        }
        multiplierNumber = DEFAULT_MULTIPLIER_NUMBER;
    }

    public List<Integer> whiteBallsDetail() {
        return whiteBalls.inquireDetails();
    }

    public int redPowerBallDetail() {
        return redPowerBall.inquireDetail();
    }

    public int wonPrice(final int price) {
        return multiplierNumber * price;
    }

}
