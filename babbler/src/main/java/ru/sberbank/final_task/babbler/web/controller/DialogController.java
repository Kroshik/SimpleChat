package ru.sberbank.final_task.babbler.web.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.sberbank.final_task.babbler.domain.Message;
import ru.sberbank.final_task.babbler.domain.auth.User;
import ru.sberbank.final_task.babbler.service.MessageService;
import ru.sberbank.final_task.babbler.service.UserService;
import ru.sberbank.final_task.babbler.web.dto.MessageDto;
import ru.sberbank.final_task.babbler.web.dto.UserRegistrationDto;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DialogController {
    @Autowired
    MessageService messageService;

    @NonNull
    private UserService userService;

    @ModelAttribute("message")
    public MessageDto messageDto() {
        return new MessageDto();
    }


    @ModelAttribute("userSetting")
    private UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping(value = "/dialog/{id}")
    public ModelAndView showMessage(@PathVariable("id") Long id) {
        val mav = new ModelAndView("main");
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            val email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByEmail(email);
            mav.addObject("user_info", user);
            List<Message> messages = messageService.getDialog(user.getId(), id);
            mav.addObject("friend_info", userService.findById(id));
//            mav.addObject("message", messageDto());
            mav.addObject("messages", messages);
        }
        return mav;
    }

    @PostMapping(value = "/dialog/{id}")
    public String sendMessage(@PathVariable("id") Long id,
                              @ModelAttribute("message") MessageDto messageDto,
                              @RequestParam("file") MultipartFile[] files) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            val email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByEmail(email);
            messageDto.setDateMessage(LocalDateTime.now());
            messageDto.setIdFromUser(user.getId());
            messageDto.setIdToUser(id);
            messageDto.setNameFrom(user.getFirstName());
            messageDto.setFile(files);
            messageService.save(messageDto);
        }
        return "redirect:/dialog/" + id;
    }
}
