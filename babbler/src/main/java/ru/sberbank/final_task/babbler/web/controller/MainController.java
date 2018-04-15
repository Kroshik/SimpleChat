package ru.sberbank.final_task.babbler.web.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import ru.sberbank.final_task.babbler.service.MessageService;
import ru.sberbank.final_task.babbler.service.UserService;
import ru.sberbank.final_task.babbler.web.dto.MessageDto;

@Controller
@RequiredArgsConstructor
public class MainController {

    @ModelAttribute("message")
    public MessageDto messageDto() {
        return new MessageDto();
    }

    @NonNull
    private UserService userService;

    @NonNull
    MessageService messageService;

    @GetMapping(value = "/")
    public ModelAndView getUserName() {
        val mav = new ModelAndView("main");
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            val email = SecurityContextHolder.getContext().getAuthentication().getName();
            mav.addObject("user_info", userService.findByEmail(email));
            mav.addObject("messages", messageService.getMessages());
        }
        return mav;
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "auth/login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "home";
    }

}
