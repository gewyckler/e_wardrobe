package pl.javagda25.ewardrobe.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javagda25.ewardrobe.model.Brand;
import pl.javagda25.ewardrobe.service.BrandService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/brand/")
public class BrandController {
    private final BrandService brandService;

    @GetMapping("/add")
    public String addBrand(Model model, Brand brand, HttpServletRequest request) {
        model.addAttribute("brand", brand);
        model.addAttribute("backReferer", request.getHeader("referer"));
        return "brand-add";
    }

    @PostMapping("/add")
    public String addBrand(Brand brandEmpty) {
        brandService.addBrand(brandEmpty);
        return "redirect:/cloth/add";
    }

    @GetMapping("/list")
    public String listBrand(Model model) {
        List<Brand> brandList = brandService.getAll();
        model.addAttribute("brandList", brandList);
        return "brand-list";
    }
}
