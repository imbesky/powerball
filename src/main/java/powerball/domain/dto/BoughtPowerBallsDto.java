package powerball.domain.dto;

import java.util.List;
import powerball.domain.BoughtPowerBall;

public record BoughtPowerBallsDto(
        List<BoughtPowerBall> boughtPowerBalls
) {
    public static BoughtPowerBallsDto from(final List<BoughtPowerBall> boughtPowerBalls) {
        return new BoughtPowerBallsDto(boughtPowerBalls);
    }
}
