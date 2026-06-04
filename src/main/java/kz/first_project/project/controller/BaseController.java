package kz.first_project.project.controller;

import kz.first_project.project.model.BankUser;
import kz.first_project.project.model2.User;
import kz.first_project.project.service.BankUserService;
import kz.first_project.project.service.CityService;
import kz.first_project.project.service.NumberService;
import kz.first_project.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BaseController {

    private final BankUserService bankUserService;
    private final CityService cityService;
    private final NumberService numberService;
    private final UserService userService;

    @GetMapping(value = "/") //localhost:8080/
    public String getMainPage(Model model) {

        model.addAttribute("users", bankUserService.getAllUsersFromBase());
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("phones", numberService.getAllPhones());

        return "index";
    }

    @GetMapping(value = "/search")
    public String getFilterUsers(Model model,
                                 @RequestParam String word) {

        model.addAttribute("users", bankUserService.getAllUsersByWord(word));
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("phones", numberService.getAllPhones());
        return "index";
    }

    @GetMapping(value = "/other") //localhost:8080/other
    public String getOtherPage(Model model) {
        return "other-page";
    }

    @PostMapping(value = "/add")
    public String addBankUser(BankUser user) {
        bankUserService.addUserToBase(user);

        return "redirect:/";
    }

    @PostMapping(value = "/add-user")
    public String addUser(User user) {
        userService.addUserToBase(user);
        return "redirect:/";
    }

    @GetMapping(value = "/add")
    public String addUserPage(Model model) {
        model.addAttribute("cities", cityService.getAllCities());
        return "add-page";
    }

    @GetMapping(value = "/details/{id}")
    public String getUserByID(Model model,
                              @PathVariable int id) {

        model.addAttribute("user", bankUserService.getUserById(id));
        model.addAttribute("cities", cityService.getAllCities());

        return "details-page";
    }

    @PostMapping(value = "/update")
    public String updateUser(BankUser bankUser) {

        bankUserService.addUserToBase(bankUser);

        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam int id) {

        bankUserService.deleteUserById(id);

        return "redirect:/";
    }


    @GetMapping(value = "/rating-more")
    public String getUsersByRatingMore(Model model,
                                       @RequestParam double rating) {

        model.addAttribute("users", bankUserService.findBankUsersByRatingGreaterThan(rating));

        return "index";

    }

    @GetMapping(value = "/rating-name")
    public String getUsersByRatingOrFullName(Model model,
                                             @RequestParam(required = false) Double rating,
                                             @RequestParam(required = false) String fullName) {

        model.addAttribute("users", bankUserService.findBankUsersByRatingAndFullName(rating, fullName));

        return "index";
    }

    @GetMapping(value = "/rating-sort")
    public String getUsersByRatingSort(Model model) {

        model.addAttribute("users", bankUserService.findBankUsersByRatingSort());

        return "index";
    }

    @GetMapping(value = "/sorted-pagination")
    public String getAllUsersByPagination(Model model,
                                          @RequestParam(defaultValue = "0") int page, //Номер страницы, по умолчанию указываем 0
                                          @RequestParam(defaultValue = "5") int size, //Количество записей на странице, по умолчанию указываем 5
                                          @RequestParam(defaultValue = "id") String param, //Параметр сортировки. по умолчанию 'id'
                                          @RequestParam(defaultValue = "asc") String direction) {//Направление сортировки, по умолчанию 'asc'
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(param).descending() : Sort.by(param).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<BankUser> usersPage = bankUserService.findAll(pageable);

        model.addAttribute("users", usersPage.getContent());

        return "index";

    }


    @GetMapping(value = "/rating-pagination")
    public String getAllUsersByRatingPagination(Model model,
                                          @RequestParam double rating,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "2") int size,
                                          @RequestParam(defaultValue = "id") String param,
                                          @RequestParam(defaultValue = "asc") String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(param).descending() : Sort.by(param).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<BankUser> usersPage = bankUserService.findByRatingGreaterThan(rating, pageable);

        model.addAttribute("users", usersPage.getContent());

        return "index";

    }

}
