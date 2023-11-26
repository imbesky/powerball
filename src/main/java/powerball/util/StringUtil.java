package powerball.util;

import static powerball.constant.Format.BLANK;
import static powerball.constant.Format.COMMA;
import static powerball.constant.Format.EMPTY;

import java.util.Arrays;
import java.util.List;

public class StringUtil {
    public static List<String> toList(final String string) {
        return Arrays.stream(string.replace(BLANK, EMPTY).split(COMMA)).toList();
    }
}
