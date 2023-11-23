package powerball.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomUtil {
    private final static Random random = new Random();

    public static int createRandomNumber(final int min, final int max) {
        return random.nextInt(max) + min;
    }

    public static List<Integer> createUniqueRandomNumbers(final int min, final int max, final int length) {
        final Set<Integer> numbers = new HashSet<>();
        while (numbers.size() != length) {
            numbers.add(random.nextInt(max) + min);
        }
        final List<Integer> integers = new ArrayList<>(numbers);
        Collections.sort(integers);
        return integers;
    }
}
