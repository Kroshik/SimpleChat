package ru.sberbank.final_task.babbler.web.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sberbank.final_task.babbler.domain.auth.User;
import ru.sberbank.final_task.babbler.service.UserService;
import ru.sberbank.final_task.babbler.web.dto.UserRegistrationDto;

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
        userService.updateUser(userDto);
        return "redirect:/";
    }
}
