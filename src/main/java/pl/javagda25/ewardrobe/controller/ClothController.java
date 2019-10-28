package pl.javagda25.ewardrobe.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javagda25.ewardrobe.model.Brand;
import pl.javagda25.ewardrobe.model.Cloth;
import pl.javagda25.ewardrobe.model.ClothType;
import pl.javagda25.ewardrobe.service.ClothService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/cloth/")
public class ClothController {
    private ClothService clothService;

    @GetMapping("/add")
    public String add(Model model, Cloth cloth) {
        model.addAttribute("cloth", cloth);
        model.addAttribute("brands", Brand.values());
        model.addAttribute("clothTypes", ClothType.values());
        return "cloth-add";
    }

    @PostMapping("/add")
    public String add(Cloth cloth, HttpServletRequest request) {
        clothService.addCloth(cloth);
        return "redirect:" + request;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Cloth> clothList = clothService.getAll();
        model.addAttribute("clothList", clothList);
        return "cloth-list";
    }

    @GetMapping("/update/{clothId}")
    public String update(Model model, HttpServletRequest request,
                         @PathVariable(name = "clothId") Long clothId) {
        Optional<Cloth> optionalCloth = clothService.getById(clothId);
        if (optionalCloth.isPresent()) {
            model.addAttribute("clothToEdit", optionalCloth.get());
            return "cloth-add";
        }
        return "redirect:" + request.getHeader("referef");
    }

    @GetMapping("/delete/{clothId}")
    public String delete(HttpServletRequest request,
                         @PathVariable(name = "clothId") Long clothId) {
        clothService.deleteById(clothId);
        return "redirect: " + request.getHeader("referef");
    }
}
