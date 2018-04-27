package ru.sberbank.final_task.babbler.web.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sberbank.final_task.babbler.domain.Message;
import ru.sberbank.final_task.babbler.domain.auth.User;
import ru.sberbank.final_task.babbler.service.UserService;
import ru.sberbank.final_task.babbler.web.dto.UserRegistrationDto;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/setting")
public class UserSettingController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String showSettingForm(Model model) {
        return "/";
    }

    @PostMapping
    public String changeUserSetting(@ModelAttribute("userSetting") UserRegistrationDto userDto,
                                    @RequestPart("avatar") MultipartFile avatar,
                                    BindingResult result) {
        val email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        if (!userDto.getPassword().equals(userDto.getConfirmEmail()) && !userDto.getPassword().equals("")) {
            result.rejectValue("password", null, "Confirm password != password");
            return "redirect:/";
            /** Here it is necessary to finish
             *  Now the changes are not applied
             *  Needed to implement the message Success / Failed
             * **/
        }
        userDto.setEmail(email);
        userDto.setAvatar(avatar);
        userService.updateUser(userDto);
        return "redirect:/";
    }

    @RequestMapping(value = "/picture/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<byte[]> getAvatar(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        byte[] targetArray = user.getAvatar();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(targetArray.length);
        return new ResponseEntity<>(targetArray, headers, HttpStatus.OK);

    }
}
