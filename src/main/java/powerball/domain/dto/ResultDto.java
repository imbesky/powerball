package powerball.domain.dto;

import java.util.List;

public record ResultDto(
        List<String> results
) {
    public static ResultDto from(final List<String> results) {
        return new ResultDto(results);
    }
}
