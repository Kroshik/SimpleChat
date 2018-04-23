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
import ru.sberbank.final_task.babbler.domain.Message;
import ru.sberbank.final_task.babbler.domain.auth.User;
import ru.sberbank.final_task.babbler.service.MessageService;
import ru.sberbank.final_task.babbler.service.UserService;
import ru.sberbank.final_task.babbler.web.dto.DeletedMessagesDto;
import ru.sberbank.final_task.babbler.web.dto.MessageDto;
import ru.sberbank.final_task.babbler.web.dto.UserRegistrationDto;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    @NonNull
    MessageService messageService;
    @NonNull
    private UserService userService;

    @ModelAttribute("message")
    public MessageDto messageDto() {
        return new MessageDto();
    }

    @ModelAttribute("userSetting")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @ModelAttribute("deleteMessage")
    public DeletedMessagesDto deleteMessageDto() {
        return new DeletedMessagesDto();
    }

    @GetMapping(value = "/")
    public ModelAndView getUserName() {
        val mav = new ModelAndView("main");
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            val email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByEmail(email);
            mav.addObject("user_info", user);
//            mav.addObject("userSetting", userRegistrationDto());
            List<Message> messages = messageService.getMessages(user.getId(), user.getId());
            mav.addObject("messages", messages);
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
