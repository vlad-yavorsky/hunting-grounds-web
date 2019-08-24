package ua.vlad.hg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.vlad.hg.domain.Role;
import ua.vlad.hg.domain.User;
import ua.vlad.hg.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Date;

@Controller
@RequestMapping("/administrator")
@RequiredArgsConstructor
public class AdministratorController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String index() {
        return "administrator/index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(name = "error", defaultValue = "false") boolean error, ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/administrator";
        }
        model.addAttribute("error", error);
        return "administrator/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "administrator/signup";
    }

    @PostMapping("/signup")
    public String signup(User user) {
        User userFromDb = userService.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return "redirect:signup?error";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setCreated(new Date());
        user.setAuthorities(Collections.singleton(Role.Type.USER));
        userService.save(user);

        return "redirect:login?registered";
    }

    @GetMapping("/theme")
    public String theme(HttpServletRequest request, HttpSession session, @RequestParam(name = "theme", defaultValue = "default") String theme) {
        session.setAttribute("theme", theme);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/international")
    public String international(HttpServletRequest request) {
        return "redirect:" + request.getHeader("Referer");
    }
}
