package powerball.domain;

import static powerball.constant.Ball.RED_POWER_BALL;
import static powerball.constant.Ball.WHITE_BALL;

import java.util.List;
import powerball.domain.dto.WinPowerBallDto;
import powerball.exception.InvalidLengthException;
import powerball.exception.NotNumericException;
import powerball.exception.OutOfRangeException;
import powerball.util.StringUtil;

public class WinPowerBall {
    private static final int TRUE = 1;
    private static final int FALSE = 0;
    private final WhiteBall whiteBalls;
    private final RedPowerBall redPowerBall;

    public WinPowerBall(final String whiteBalls, final String redPowerBall) {
        final List<String> numbers = StringUtil.toList(whiteBalls);
        whiteBallValidate(numbers);
        this.whiteBalls = new WhiteBall(numbers.stream().map(Integer::parseInt).toList());
        redPowerBallValidate(redPowerBall);
        this.redPowerBall = new RedPowerBall(Integer.parseInt(redPowerBall));
    }

    public static WinPowerBall from(final WinPowerBallDto winPowerBallDto) {
        return new WinPowerBall(winPowerBallDto.whiteBalls(), winPowerBallDto.redPowerBall());
    }

    private void whiteBallValidate(final List<String> numbers) {
        if (numbers.size() != WHITE_BALL.getProperLength()) {
            throw new InvalidLengthException();
        }
        for (String number : numbers) {
            isNumeric(number);
            inWhiteBallRange(Integer.parseInt(number));
        }
    }

    private void redPowerBallValidate(final String number) {
        isNumeric(number);
        inRedPowerBallRange(Integer.parseInt(number));
    }

    private void isNumeric(final String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new NotNumericException();
        }
    }

    private void inWhiteBallRange(final int number) {
        if (number < WHITE_BALL.getMin() || number > WHITE_BALL.getMax()) {
            throw new OutOfRangeException();
        }
    }

    private void inRedPowerBallRange(final int number) {
        if (number < RED_POWER_BALL.getMin() || number > RED_POWER_BALL.getMax()) {
            throw new OutOfRangeException();
        }
    }

    public int checkWhiteBalls(final List<Integer> inputNumbers) {
        int matchedNumber = 0;
        for (int number : inputNumbers) {
            matchedNumber += contains(number);
        }
        return matchedNumber;
    }

    private int contains(final int number) {
        if (whiteBalls.numbers().contains(number)) {
            return TRUE;
        }
        return FALSE;
    }

    public boolean checkRedPowerBall(final int number) {
        return redPowerBall.number() == number;
    }

}
