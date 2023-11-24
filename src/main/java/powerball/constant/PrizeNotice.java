package powerball.constant;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PrizeNotice {
    FIRST(1, "화이트볼 5개 일치, 레드 파워볼 일치 (Grand Prize: 20,000,000달러) - %d개"),
    SECOND(2, "화이트볼 5개 일치 (1,000,000달러) - %d개"),
    THIRD(3, "화이트볼 4개 일치, 레드 파워볼 일치 (50,000달러) - %d개"),
    FOURTH(4, "화이트볼 4개 일치  (100달러) - %d개"),
    FIFTH(5, "화이트볼 3개 일치, 레드 파워볼 일치 (100달러) - %d개"),
    SIXTH(6, "화이트볼 3개 일치 (7달러) - %d개"),
    SEVENTH(7, "화이트볼 2개 일치, 레드 파워볼 일치 (7달러) - %d개"),
    EIGHTH(8, "화이트볼 1개 일치, 레드 파워볼 일치 (4달러) - %d개"),
    NINTH(9, "레드 파워볼 일치 (4달러) - %d개");
    private final int grade;
    private final String notice;

    PrizeNotice(int grade, String notice) {
        this.grade = grade;
        this.notice = notice;
    }

    private final static Map<Integer, PrizeNotice> notices =
            Collections.unmodifiableMap(Stream
                    .of(values())
                    .collect(Collectors
                            .toMap(PrizeNotice -> PrizeNotice.getGrade(), PrizeNotice -> PrizeNotice)));

    private int getGrade() {
        return grade;
    }

    public String getNotice() {
        return notice;
    }

    public static PrizeNotice findByGrade(final int grade) {
        return notices.get(grade);
    }
}
