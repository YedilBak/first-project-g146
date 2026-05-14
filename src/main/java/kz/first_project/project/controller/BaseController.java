package kz.first_project.project.controller;

import kz.first_project.project.model.BankUser;
import kz.first_project.project.repository.CityRepository;
import kz.first_project.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BaseController {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    @GetMapping(value = "/") //localhost:8080/
    public String getMainPage(Model model){

        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("cities", cityRepository.findAll());

        return "index";
    }

    @GetMapping(value = "/other") //localhost:8080/other
    public String getOtherPage(Model model){
        return "other-page";
    }

    @PostMapping(value = "/add")
    public String addUser(BankUser user){

        userRepository.save(user);

        return "redirect:/";
    }

    @GetMapping(value = "/add")
    public String addUserPage(Model model){
        model.addAttribute("cities", cityRepository.findAll());
        return "add-page";
    }

    @GetMapping(value = "/details/{id}")
    public String getUserByID(Model model,
                              @PathVariable int id){

        model.addAttribute("user", userRepository.findById(id).orElseThrow());
        model.addAttribute("cities", cityRepository.findAll());

        return "details-page";
    }

    @PostMapping(value = "/update")
    public String updateUser(BankUser bankUser){

       userRepository.save(bankUser);

        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam int id){

        userRepository.deleteById(id);

        return "redirect:/";
    }


}
