package ua.vlad.hg.web.excepton.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.vlad.hg.core.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice("ua.vlad.hg.web.controller")
public class ControllerExceptionHandler {

    private static final String ERROR_PAGE = "/administrator/error";

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(final Exception e, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        log.error("Exception", e);
        ModelAndView modelAndView = new ModelAndView();
        String referer = request.getHeader("Referer");
        if (referer != null) {
            modelAndView.setViewName("redirect:" + referer);
            redirectAttributes.addFlashAttribute("exception", e);
        } else {
            // todo: (002) status do not work with redirect
            modelAndView.setStatus(e instanceof ApplicationException
                    ? ((ApplicationException) e).getExceptionCode().getStatus()
                    : HttpStatus.NOT_FOUND);
            modelAndView.setViewName(ERROR_PAGE);
            modelAndView.addObject("message", e);
        }
        return modelAndView;
    }

}
