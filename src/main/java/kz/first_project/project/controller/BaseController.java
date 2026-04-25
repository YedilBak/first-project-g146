package kz.first_project.project.controller;

import kz.first_project.project.db.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    @GetMapping(value = "/") //localhost:8080/
    public String getMainPage(Model model){

        model.addAttribute("users", DBManager.getUsers());

        return "index";
    }

    @GetMapping(value = "/other") //localhost:8080/other
    public String getOtherPage(Model model){
        return "other-page";
    }
}
