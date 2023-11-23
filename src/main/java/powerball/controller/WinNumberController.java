package powerball.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import powerball.constant.Exception;
import powerball.domain.dto.WinPowerBallDto;
import powerball.exception.PowerBallException;
import powerball.service.WinNumberInputService;

@Controller
public class WinNumberController {
    private final WinNumberInputService winNumberInputService;

    public WinNumberController(WinNumberInputService winNumberInputService) {
        this.winNumberInputService = winNumberInputService;
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

    @GetMapping("/exception")
    public void handleException(@RequestParam("type") final String type, final Model model) {
        final Exception exception = Exception.findByType(type);
        model.addAttribute("message", exception.getMessage());
    }
}
