package powerball.service;

import org.springframework.stereotype.Service;
import powerball.domain.WinPowerBall;
import powerball.domain.dto.WinPowerBallDto;
import powerball.repository.DbRepository;

@Service
public class WinNumberInputService {
    private final DbRepository dbRepository;

    public WinNumberInputService(DbRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    public void saveWinPowerBall(final WinPowerBallDto winPowerBallDto) {
        dbRepository.saveWinPowerBall(WinPowerBall.from(winPowerBallDto));
    }
}
