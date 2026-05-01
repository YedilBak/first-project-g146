package kz.first_project.project.controller;

import kz.first_project.project.db.DBConnector;
import kz.first_project.project.db.DBManager;
import kz.first_project.project.model.BankUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BaseController {

    @GetMapping(value = "/") //localhost:8080/
    public String getMainPage(Model model){

        model.addAttribute("users", DBConnector.getAllUsersFromBase());

        return "index";
    }

    @GetMapping(value = "/other") //localhost:8080/other
    public String getOtherPage(Model model){
        return "other-page";
    }

    @PostMapping(value = "/add")
    public String addUser(BankUser user){

        DBManager.addUser(user);

        return "redirect:/";
    }

    @GetMapping(value = "/add")
    public String addUserPage(){
        return "add-page";
    }

    @GetMapping(value = "/details/{id}")
    public String getUserByID(Model model,
                              @PathVariable int id){

        model.addAttribute("user", DBConnector.findBankUserById(id));

        return "details-page";
    }

    @PostMapping(value = "/update")
    public String updateUser(BankUser bankUser){

        DBManager.updateUser(bankUser);

        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam int id){

        DBManager.deleteUser(id);

        return "redirect:/";
    }


}
