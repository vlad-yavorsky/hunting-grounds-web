package ua.vlad.hg.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.vlad.hg.core.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/administrator")
@RequiredArgsConstructor
public class AdministratorController {

    private final UserService userService;

    @GetMapping
    public String index() {
        return "administrator/index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(name = "error", defaultValue = "false") final boolean error, final ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("error", error);
            return "administrator/login";
        }
        return "redirect:/administrator";
    }

    @GetMapping("/signup")
    public String signup() {
        return "administrator/signup";
    }

    @PostMapping("/signup")
    public String signup(final String email, final String username, final String password) {
        userService.create(email, username, password);
        return "redirect:/login?registered";
    }

    @GetMapping("/theme")
    public String theme(final HttpServletRequest request, final HttpSession session, @RequestParam(name = "theme", defaultValue = "default") final String theme) {
        session.setAttribute("theme", theme);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/international")
    public String international(final HttpServletRequest request) {
        return "redirect:" + request.getHeader("Referer");
    }

}
