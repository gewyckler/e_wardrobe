//package pl.javagda25.ewardrobe.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import pl.javagda25.ewardrobe.model.Account;
//import pl.javagda25.ewardrobe.service.AccountRoleService;
//import pl.javagda25.ewardrobe.service.AccountServices;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping(path = "/admin/account/")
//@PreAuthorize(value = "hasRole('ADMIN')")
//public class AdminAccountController {
//
//    @Autowired
//    private AccountServices accountServices;
//    @Autowired
//    private AccountRoleService accountRoleService;
//
//
//    @GetMapping("/list")
//    public String getUserList(Model model) {
//        List<Account> accountList = accountServices.findAll();
//        model.addAttribute("accountsł", accountList);
//        return "account-list";
//    }
//
//    @GetMapping("/remove")
//    public String deleteById(@RequestParam(name = "accountId") Long accountId) {
//        accountServices.deleteById(accountId);
//        return "redirect:/admin/account/list";
//    }
//
//    @GetMapping("/toggleLock")
//    public String toggleLock(@RequestParam(name = "accountId") Long accountId) {
//        accountServices.toggleLock(accountId);
//        return "redirect:/admin/account/list";
//    }
//
//    @GetMapping("/resetPassword")
//    public String resetPassword(Model model,
//                                @RequestParam(name = "accountId") Long accountId) {
//        Optional<Account> accountOptional = accountServices.findById(accountId);
//        if (accountOptional.isPresent()) {
//            model.addAttribute("account", accountOptional.get());
//            return "account-passwordreset";
//        }
//        return "redirect:/admin/account/list";
//    }
//
//    @GetMapping("/editRoles")
//    public String editRoles(Model model,
//                            @RequestParam(name = "accountId") Long accountId) {
//        Optional<Account> accountOptional = accountServices.findById(accountId);
//        if (accountOptional.isPresent()) {
//            model.addAttribute("roles", accountRoleService.getAll());
//            model.addAttribute("account", accountOptional.get());
//            return "account-roles";
//        }
//        return "redirect:/admin/account/list";
//    }
//
//    @PostMapping("/editRoles")
//    public String editRoles(Long accountId, HttpServletRequest request) {
//        accountServices.editRole(accountId, request);
//        return "redirect:/admin/account/list";
//    }
//}
