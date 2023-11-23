package powerball.domain;

import static powerball.constant.Ball.WHITE_BALL;
import static powerball.util.RandomUtil.createUniqueRandomNumbers;

import java.util.List;

public record WhiteBall(
        List<Integer> numbers
) {

    public static WhiteBall createWhiteBalls() {
        return new WhiteBall(createUniqueRandomNumbers(
                WHITE_BALL.getMin(), WHITE_BALL.getMax(), WHITE_BALL.getProperLength()));
    }

}
