package ru.sberbank.final_task.babbler.web.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.final_task.babbler.domain.auth.User;
import ru.sberbank.final_task.babbler.service.MessageService;
import ru.sberbank.final_task.babbler.service.UserService;
import ru.sberbank.final_task.babbler.web.dto.MessageDto;

import java.time.LocalDateTime;

@Controller
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    private UserService userService;

    //    @GetMapping(value = "/message/{id}")
    @GetMapping(value = "/message")
    public String showMessage() {
//    public String showMessage(@PathVariable("id") Long id) {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        val mav = new ModelAndView("/");
//        val message = messageService.getMessage(id);
//        mav.addObject("message", message);
//        return mav;
        return "/";
    }

    @PostMapping(value = "/message")
    public String sendMessage(@ModelAttribute("message") MessageDto messageDto) {
        val email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        messageDto.setDateMessage(LocalDateTime.now());
        messageDto.setIdFrom(user.getId());
        messageDto.setNameFrom(user.getFirstName());
        messageService.save(messageDto);  //Need do it later
        return "redirect:/";
    }

    @PostMapping(value = "/deleteMessage")
    public @ResponseBody
    String deleteMessages(@RequestBody MessageDto messageDto) {
        messageService.deleteMessages(messageDto);
        /*
        val email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);

        messageService.save(messageDto);  //Need do it later
        return "redirect:/"; */
        return "succes";
    }
}
