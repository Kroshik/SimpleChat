package ru.sberbank.final_task.babbler.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.sberbank.final_task.babbler.domain.Contact;
import ru.sberbank.final_task.babbler.domain.Message;
import ru.sberbank.final_task.babbler.domain.auth.User;
import ru.sberbank.final_task.babbler.service.ContactService;
import ru.sberbank.final_task.babbler.service.FileService;
import ru.sberbank.final_task.babbler.service.MessageService;
import ru.sberbank.final_task.babbler.service.UserService;
import ru.sberbank.final_task.babbler.web.dto.MessageDto;
import ru.sberbank.final_task.babbler.web.dto.SearchDto;
import ru.sberbank.final_task.babbler.web.dto.UserRegistrationDto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class DialogController {
    @Autowired
    MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private FileService fileService;

    @ModelAttribute("textSearch")
    public SearchDto searchDto() {
        return new SearchDto();
    }

    @ModelAttribute("message")
    public MessageDto messageDto() {
        return new MessageDto();
    }

    @ModelAttribute("userSetting")
    private UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @RequestMapping(value = "/picture/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<byte[]> getImageAsByteArray(@PathVariable("id") Long id) throws IOException {
        Message message = messageService.getMessage(id);
        byte[] targetArray = message.getFile();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(targetArray.length);
        return new ResponseEntity<>(targetArray, headers, HttpStatus.OK);

    }

    @GetMapping(value = "/dialog/{id}")
    public ModelAndView showMessage(@PathVariable("id") Long id) {
        if(userService.findById(id) == null) {
            return new ModelAndView("redirect:/");
        }
        val mav = new ModelAndView("main");
        val email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        mav.addObject("user_info", user);
        List<Message> messages = messageService.getDialog(user.getId(), id);
        User friend = userService.findById(id);

        if (friend.getStatus().equals("online") &&
                Math.abs(friend.getLastSeen().getMinute() - LocalDateTime.now().getMinute()) >= 1) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String status = "Last seen  " + String.valueOf(friend.getLastSeen().format(formatter));
            userService.updateLastSeen(friend.getEmail(), friend.getLastSeen(), status);
        }
        userService.updateLastSeen(user.getEmail(), LocalDateTime.now(), "online");
        Set<User> contacts = user
                .getContacts()
                .stream()
                .map(x -> userService.findById(x.getFriendId()))
                .collect(Collectors.toSet());

        mav.addObject("contacts", contacts);
        mav.addObject("friend_info", friend);

        mav.addObject("messages", messages);
        return mav;
    }

    @PostMapping(value = "/dialog/{id}")
    public @ResponseBody
    Message sendMessage(@PathVariable("id") Long id,
                       @ModelAttribute("message") MessageDto messageDto,
                        @RequestPart("file") List<MultipartFile> files) {
        Message ret = null;
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            val email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByEmail(email);
            User friend = userService.findById(id);
            if (!user.getContacts().stream().filter(x -> Objects.equals(friend.getId(), x.getFriendId())).findAny().isPresent()) {
                Set<Contact> contacts = user.getContacts();
                Contact userContact = new Contact(friend.getId());
                contactService.save(userContact);

                Contact friendContact = new Contact(user.getId());
                contactService.save(friendContact);

                contacts.add(userContact);
                friend.getContacts().add(friendContact);
            }
            messageDto.setDateMessage(LocalDateTime.now());
            messageDto.setIdFromUser(user.getId());
            messageDto.setIdToUser(id);
            messageDto.setNameFrom(user.getFirstName());
            messageDto.setFiles(files);
            messageService.save(messageDto);
            ret = messageService.getLast(user.getId());
        }
        return ret;
    }
}
