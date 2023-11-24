package powerball.controller;

import static powerball.constant.Format.INPUT_EXAMPLE;
import static powerball.constant.Format.INPUT_RED_POWER_BALL;
import static powerball.constant.Format.INPUT_WHITE_BALL;
import static powerball.constant.Format.WIN_NUMBER_INPUT_HEADER;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import powerball.domain.dto.WinPowerBallDto;
import powerball.exception.PowerBallException;
import powerball.service.WinNumberInputService;

@Controller
public class WinNumberController {
    private final WinNumberInputService winNumberInputService;

    public WinNumberController(WinNumberInputService winNumberInputService) {
        this.winNumberInputService = winNumberInputService;
    }

    @GetMapping("/winning-number")
    public void winNumberInputNotices(final Model model) {
        model.addAttribute("header", WIN_NUMBER_INPUT_HEADER);
        model.addAttribute("whiteBallNotice", INPUT_WHITE_BALL);
        model.addAttribute("redPowerBallNotice", INPUT_RED_POWER_BALL);
        model.addAttribute("example", INPUT_EXAMPLE);
    }

    @GetMapping("/win-input")
    public String purchaseInput(@RequestParam final WinPowerBallDto winPowerBallDto) {
        try {
            winNumberInputService.saveWinPowerBall(winPowerBallDto);
        } catch (PowerBallException exception) {
            return exception.errorPage();
        }
        return "/result";
    }

}
