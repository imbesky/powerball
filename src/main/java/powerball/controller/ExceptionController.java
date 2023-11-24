package powerball.controller;

import static powerball.constant.Format.EXCEPTION_NOTICE;
import static powerball.constant.Format.REDIRECT;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import powerball.constant.Exception;

@Controller
public class ExceptionController {
    @GetMapping("/exception")
    public void handleException(@RequestParam("type") final String type, final Model model) {
        final Exception exception = Exception.findByType(type);
        model.addAttribute("message", EXCEPTION_NOTICE.concat(exception.getMessage()));
        model.addAttribute("redirect", REDIRECT);
    }
}
