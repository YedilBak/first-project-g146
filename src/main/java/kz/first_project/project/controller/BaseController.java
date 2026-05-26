package kz.first_project.project.controller;

import kz.first_project.project.model.BankUser;
import kz.first_project.project.model2.User;
import kz.first_project.project.repository.CityRepository;
import kz.first_project.project.repository.BankUserRepository;
import kz.first_project.project.repository.PhoneNumberRepository;
import kz.first_project.project.repository2.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BaseController {

    private final BankUserRepository bankUserRepository;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;
    private final PhoneNumberRepository numberRepository;

    @GetMapping(value = "/") //localhost:8080/
    public String getMainPage(Model model){

        model.addAttribute("users", bankUserRepository.findAll());
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("phones", numberRepository.findAll());

        return "index";
    }

    @GetMapping(value = "/search")
    public String getFilterUsers(Model model,
                                 @RequestParam String word){

        model.addAttribute("users", bankUserRepository.searchByWord(word));
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("phones", numberRepository.findAll());
        return "index";
    }

    @GetMapping(value = "/other") //localhost:8080/other
    public String getOtherPage(Model model){
        return "other-page";
    }

    @PostMapping(value = "/add")
    public String addBankUser(BankUser user){
        bankUserRepository.save(user);

        return "redirect:/";
    }

    @PostMapping(value = "/add-user")
    public String addUser(User user){
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

        model.addAttribute("user", bankUserRepository.findById(id).orElseThrow());
        model.addAttribute("cities", cityRepository.findAll());

        return "details-page";
    }

    @PostMapping(value = "/update")
    public String updateUser(BankUser bankUser){

       bankUserRepository.save(bankUser);

        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam int id){

        bankUserRepository.deleteById(id);

        return "redirect:/";
    }


}
