package powerball.domain.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import powerball.domain.BoughtPowerBall;

public record BoughtPowerBallsDto(
        List<BoughtPowerBall> boughtPowerBalls
) {
    private static final int INITIAL_VALUE = 0;

    public static BoughtPowerBallsDto of(final int boughtPowerBallNumber, final int boughtPowerPlayNumber) {
        final List<BoughtPowerBall> balls = new ArrayList<>();
        final Random random = new Random();
        final List<Boolean> isPowerPlay = powerPlayOrNot(boughtPowerBallNumber, boughtPowerPlayNumber);
        while (balls.size() != boughtPowerBallNumber) {
            balls.add(new BoughtPowerBall(isPowerPlay.get(balls.size())));
        }
        return new BoughtPowerBallsDto(balls);
    }

    private static List<Boolean> powerPlayOrNot(final int boughtPowerBallNumber, int boughtPowerPlayNumber) {
        final List<Boolean> powerPlay = new ArrayList<>();
        while (boughtPowerPlayNumber != INITIAL_VALUE) {
            powerPlay.add(true);
            boughtPowerPlayNumber--;
        }
        while (powerPlay.size() != boughtPowerBallNumber) {
            powerPlay.add(false);
        }
        Collections.shuffle(powerPlay);
        return powerPlay;
    }
}
