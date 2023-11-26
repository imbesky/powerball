package powerball.winpowerball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import powerball.domain.WinPowerBall;
import powerball.exception.NotNumericException;
import powerball.exception.OutOfRangeException;

public class RedPowerBallInputTest {
    @Test
    @DisplayName("숫자가 아닌 레드 파워볼: 문자")
    void characterRedPowerBall() {
        final String white = "1,2,3,4,5";
        final String red = "a";

        assertThrows(NotNumericException.class, () -> new WinPowerBall(white, red));
    }

    @Test
    @DisplayName("숫자가 아닌 레드 파워볼: 공백")
    void blankRedPowerBall() {
        final String white = "1,2,3,4,5";
        final String red = "   ";

        assertThrows(NotNumericException.class, () -> new WinPowerBall(white, red));
    }

    @Test
    @DisplayName("최소치 미만의 레드 파워볼 숫자")
    void redPowerBallUnderMin() {
        final String white = "1,2,3,4,5";
        final String red = "0";

        assertThrows(OutOfRangeException.class, () -> new WinPowerBall(white, red));
    }

    @Test
    @DisplayName("최대치 초과의 레드 파워볼 숫자")
    void redPowerBallOverMax() {
        final String white = "1,2,3,4,5";
        final String red = "27";

        assertThrows(OutOfRangeException.class, () -> new WinPowerBall(white, red));
    }

    @Test
    @DisplayName("레드 파워볼 일치 숫자 확인")
    void redPowerBallMatchCheck() {
        final String white = "1,2,3,4,5";
        final String red = "1";

        final WinPowerBall winPowerBall = new WinPowerBall(white, red);
        final int compare = 1;

        assertThat(winPowerBall.checkMatchedRedPowerBall(compare)).isEqualTo(1);
    }
}
