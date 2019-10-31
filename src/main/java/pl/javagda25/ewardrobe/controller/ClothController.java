package pl.javagda25.ewardrobe.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.javagda25.ewardrobe.model.Brand;
import pl.javagda25.ewardrobe.model.Cloth;
import pl.javagda25.ewardrobe.model.ClothType;
import pl.javagda25.ewardrobe.service.ClothService;
import pl.javagda25.ewardrobe.service.OccasionService;
import pl.javagda25.ewardrobe.service.SeasonService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/cloth/")
public class ClothController {
    private final ClothService clothService;
    private final SeasonService seasonService;
    private final OccasionService occasionService;

    @GetMapping("/add")
    public String add(Model model, Cloth cloth, HttpServletRequest request) {
        model.addAttribute("cloth", cloth);
        sendListOfTypesOccasionSeason(model);
        model.addAttribute("backReferer", request.getHeader("referer"));
        return "cloth-add";
    }

    @PostMapping("/add")
    public String add(@RequestParam(name = "file") MultipartFile file,
                      Cloth cloth, Long occasionId, Long seasonId) {

        clothService.addCloth(cloth, occasionId, seasonId, file);
        return "redirect:/cloth/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Cloth> clothList = clothService.getAll();
        model.addAttribute("clothList", clothList);
        return "cloth-list";
    }

    @GetMapping("/update/{clothId}")
    public String update(Model model, HttpServletRequest request, MultipartFile file,
                         @PathVariable(name = "clothId") Long clothId) {
        Optional<Cloth> optionalCloth = clothService.getById(clothId);

        if (optionalCloth.isPresent()) {
            Cloth cloth = optionalCloth.get();
            model.addAttribute("cloth", cloth);
            sendListOfTypesOccasionSeason(model);
            return "cloth-add";
        }
        return "redirect:" + request.getHeader("referer");
    }

    @GetMapping("/delete/{clothId}")
    public String delete(HttpServletRequest request,
                         @PathVariable(name = "clothId") Long clothId) {
        clothService.deleteById(clothId);
        return "redirect:" + request.getHeader("referer");
    }

    private void sendListOfTypesOccasionSeason(Model model) {
        model.addAttribute("brands", Brand.values());
        model.addAttribute("clothTypes", ClothType.values());
        model.addAttribute("occasionList", occasionService.getAll());
        model.addAttribute("seasonList", seasonService.getAll());
    }
}
