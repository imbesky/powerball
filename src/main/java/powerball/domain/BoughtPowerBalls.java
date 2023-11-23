package powerball.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import powerball.domain.dto.BoughtPowerBallsDto;

public class BoughtPowerBalls {
    private static final int INITIAL_VALUE = 0;
    private final List<BoughtPowerBall> balls = new ArrayList<>();

    public BoughtPowerBalls(final int boughtPowerBallNumber, final int boughtPowerPlayNumber) {
        buyBalls(boughtPowerBallNumber, boughtPowerPlayNumber);
    }

    private void buyBalls(final int boughtPowerBallNumber, final int boughtPowerPlayNumber) {
        final List<Boolean> isPowerPlay = powerPlayGenerator(boughtPowerBallNumber, boughtPowerPlayNumber);
        while (balls.size() != boughtPowerBallNumber) {
            balls.add(new BoughtPowerBall(isPowerPlay.get(balls.size())));
        }
    }

    private List<Boolean> powerPlayGenerator(final int boughtPowerBallNumber, int boughtPowerPlayNumber) {
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

    public BoughtPowerBallsDto toDto() {
        return BoughtPowerBallsDto.from(balls);
    }
}
