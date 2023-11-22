package powerball.domain;

import static powerball.constant.Ball.INDEX_DIFFERENCE;
import static powerball.constant.Ball.WHITE_BALL;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import powerball.exception.InvalidLengthException;
import powerball.exception.NotNumericException;
import powerball.exception.OutOfRangeException;
import powerball.util.StringUtil;

public class WhiteBall {
    private final List<Integer> numbers;

    public WhiteBall(final String input) {
        validate(StringUtil.toList(input));
        numbers = StringUtil.toList(input).stream().map(Integer::parseInt).toList();
    }

    public WhiteBall() {
        numbers = generateWhiteBalls();
    }

    private static void validate(final List<String> numbers) {
        if (numbers.size() != WHITE_BALL.getProperLength()) {
            throw new InvalidLengthException();
        }
        for (String number : numbers) {
            isNumeric(number);
            inRange(Integer.parseInt(number));
        }
    }

    private static void isNumeric(final String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new NotNumericException();
        }
    }

    private static void inRange(final int number) {
        if (number < WHITE_BALL.getMin() || number > WHITE_BALL.getMax()) {
            throw new OutOfRangeException();
        }
    }

    private static List<Integer> generateWhiteBalls() {
        final Set<Integer> numbers = new HashSet<>();
        final Random random = new Random();
        while (numbers.size() != WHITE_BALL.getProperLength()) {
            numbers.add(random.nextInt(WHITE_BALL.getMax() + INDEX_DIFFERENCE));
        }
        return numbers.stream().toList();
    }

    public List<Integer> inquireDetails() {
        return numbers;
    }

}
