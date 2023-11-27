package powerball.controller;

import static powerball.constant.Format.REDIRECT;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExceptionController {
    @GetMapping("/exception")
    public String handleException(@RequestParam("detail") final String detail, final Model model) {
        model.addAttribute("message", detail);
        model.addAttribute("redirect", REDIRECT);
        return "exception";
    }
}
