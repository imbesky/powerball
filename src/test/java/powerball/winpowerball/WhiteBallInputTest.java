package powerball.winpowerball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import powerball.domain.WinPowerBall;
import powerball.exception.InvalidLengthException;
import powerball.exception.NotNumericException;
import powerball.exception.OutOfRangeException;

public class WhiteBallInputTest {

    @Test
    @DisplayName("올바르지 않은 개수의 화이트볼")
    void invalidWhiteBallNumber() {
        final String white = "1,2,3,4";
        final String red = "1";

        assertThrows(InvalidLengthException.class, () -> new WinPowerBall(white, red));
    }

    @Test
    @DisplayName("숫자가 아닌 화이트볼: 문자")
    void characterWhiteBall() {
        final String white = "1,a,3,4,5";
        final String red = "1";

        assertThrows(NotNumericException.class, () -> new WinPowerBall(white, red));
    }

    @Test
    @DisplayName("숫자가 아닌 화이트볼: 공백")
    void blankWhiteBall() {
        final String white = "1,2, ,4,5";
        final String red = "1";

        assertThrows(NotNumericException.class, () -> new WinPowerBall(white, red));
    }

    @Test
    @DisplayName("최소치 미만의 화이트볼 숫자")
    void whiteBallUnderMin() {
        final String white = "0,2,3,4,5";
        final String red = "1";

        assertThrows(OutOfRangeException.class, () -> new WinPowerBall(white, red));
    }

    @Test
    @DisplayName("최대치 초과의 화이트볼 숫자")
    void whiteBallOverMax() {
        final String white = "70,2,3,4,5";
        final String red = "1";

        assertThrows(OutOfRangeException.class, () -> new WinPowerBall(white, red));
    }

    @Test
    @DisplayName("화이트볼 일치 숫자 계산")
    void whiteBallMatchCalculate() {
        final String white = "1,2,3,4,5";
        final String red = "1";

        final WinPowerBall winPowerBall = new WinPowerBall(white, red);
        final List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 6, 7));

        assertThat(winPowerBall.checkWhiteBalls(numbers)).isEqualTo(3);
    }

}
