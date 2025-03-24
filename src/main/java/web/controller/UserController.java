package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "users";
    }

    @PostMapping("/addUser")
    public String addUser(@Valid @ModelAttribute("user") User user,
                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            return "users";
        }
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/editUser")
    public String editUserForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("editUser", userService.getUserById(id));
        return "users";
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestParam("id") Long id,
                             @Valid @ModelAttribute("editUser") User user,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            return "users";
        }

        User existingUser = userService.getUserById(id);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        userService.updateUser(existingUser);

        return "redirect:/users";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
