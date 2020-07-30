package pl.javagda25.ewardrobe.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javagda25.ewardrobe.model.ClothType;
import pl.javagda25.ewardrobe.service.ClothTypeService;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@RequestMapping("/clothType/")
@Controller
public class ClothTypeController {
    private final ClothTypeService clothTypeService;

    @GetMapping("/add")
    public String add(Model model, ClothType clothType, HttpServletRequest request) {
        model.addAttribute("clothType", clothType);
        model.addAttribute("backReferer", request.getHeader("referer"));
        return "clothType-add";
    }

    @PostMapping("/add")
    public String add(ClothType clothType) {
        clothTypeService.add(clothType);
        return "redirect:/cloth/add";
    }

}
