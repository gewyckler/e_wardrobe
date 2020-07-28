//package pl.javagda25.ewardrobe.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import pl.javagda25.ewardrobe.model.Account;
//import pl.javagda25.ewardrobe.service.AccountServices;
//
//@Controller
//@RequestMapping("/user/")
//public class AccountController {
//
//    @Autowired
//    private AccountServices accountServices;
//
//    @GetMapping("/register")
//    public String registrationFormModel(Model model, Account account) {
//        model.addAttribute("newAccount", account);
//        return "registration-form";
//    }
//
//    @PostMapping("/register")
//    public String register(Model model,
//                           Account account,
//                           BindingResult result,
//                           String passwordConfirm) {
//        if (result.hasErrors()) {
//            return registrationError(model, account, result.getFieldError().getDefaultMessage());
//        }
//
//        if (!account.getPassword().equals(passwordConfirm)) {
//            registrationError(model, account, "Passwords do not match.");
//            return "registration-form";
//        }
//
//        if (!accountServices.register(account)) {
//            registrationError(model, account, "User with given username already exists.");
//            return "registration-form";
//
//        }
//        registrationError(model, account, "User with given username already exists.");
//        return "redirect:/login";
//    }
//
//    private String registrationError(Model model, Account account, String message) {
//        model.addAttribute("newAccount", account);
//        model.addAttribute("errorMessage", message);
//
//        return "registration-form";
//    }
//}
