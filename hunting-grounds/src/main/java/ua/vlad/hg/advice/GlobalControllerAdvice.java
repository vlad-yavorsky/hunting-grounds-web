package ua.vlad.hg.advice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("currentRequestUri")
    public String currentRequestUri(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("httpStatus")
    public HttpStatus getHttpStatus(HttpServletResponse response) {
        // Returns the HttpStatus object based on the current response code
        return HttpStatus.resolve(response.getStatus());
    }

}
