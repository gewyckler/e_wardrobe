package pl.javagda25.ewardrobe.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javagda25.ewardrobe.model.Occasion;
import pl.javagda25.ewardrobe.service.OccasionService;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Data
@Controller
@RequestMapping("/occasion/")
public class OccasionController {
    private final OccasionService occasionService;

    @GetMapping("/add")
    public String add(Model model, Occasion occasion, HttpServletRequest request) {
        model.addAttribute("occasion", occasion);
        model.addAttribute("backReferer", request.getHeader("referer"));
        return "occasion-add";
    }

    @PostMapping("/add")
    public String add(Occasion newOccasion) {
        occasionService.addOccasion(newOccasion);
        return "redirect:/cloth/add";
    }
}
