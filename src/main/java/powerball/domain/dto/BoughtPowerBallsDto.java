package powerball.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import powerball.domain.BoughtPowerBall;

public record BoughtPowerBallsDto(
        List<BoughtPowerBall> boughtPowerBalls
) {
    public static BoughtPowerBallsDto of(final int boughtPowerBallNumber, final int boughtPowerPlayNumber) {
        final List<BoughtPowerBall> balls = new ArrayList<>();
        final Random random = new Random();
        int currentPowerPlayNumber = 0;
        while (balls.size() != boughtPowerBallNumber) {
            balls.add(new BoughtPowerBall(true));
        }
        return new BoughtPowerBallsDto(balls);
    }
}
