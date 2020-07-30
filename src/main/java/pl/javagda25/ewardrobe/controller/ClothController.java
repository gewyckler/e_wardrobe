package pl.javagda25.ewardrobe.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.javagda25.ewardrobe.model.Cloth;
import pl.javagda25.ewardrobe.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/cloth/")
public class ClothController {
    private final ClothService clothService;
    private final SeasonService seasonService;
    private final OccasionService occasionService;
    private final OutfitService outfitService;
    private final BrandService brandService;
    private final ClothTypeService clothTypeService;

    @GetMapping("/add")
    public String add(Model model, Cloth cloth, HttpServletRequest request) {
        outfitService.deleteIfNull();
        model.addAttribute("cloth", cloth);
        sendListOfTypesOccasionSeason(model);
        model.addAttribute("backReferer", request.getHeader("referer"));
        return "cloth-add";
    }

    @PostMapping("/add")
    public String add(@RequestParam(name = "file") MultipartFile file,
                      Cloth cloth, Long clothTypeId, Long brandId, Long occasionId, Long seasonId) {

        clothService.addCloth(cloth, clothTypeId, brandId, file, occasionId, seasonId);
        return "redirect:/cloth/list";
    }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(name = "brandsFilter", required = false) Long brandId,
                       @RequestParam(name = "typeFilter", required = false) Long clothTypeId,
                       @RequestParam(name = "occasionFilter", required = false) Long occasionId,
                       @RequestParam(name = "seasonFilter", required = false) Long seasonId) {

        outfitService.deleteIfNull();

        List<Cloth> clothList = clothService.getAll(brandId, clothTypeId, occasionId, seasonId);

//        sendListOfTypesOccasionSeason(model);
        model.addAttribute("clothList", clothList);

        model.addAttribute("brands", brandService.getAll());
        model.addAttribute("clothTypes", clothTypeService.getAll());
        model.addAttribute("occasionList", occasionService.getAll());
        model.addAttribute("seasonList", seasonService.getAll());
        return "cloth-list";
    }

    @GetMapping("/update/{clothId}")
    public String update(Model model, HttpServletRequest request,
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
        model.addAttribute("brands", brandService.getAll());
        model.addAttribute("clothTypes", clothTypeService.getAll());
        model.addAttribute("occasionList", occasionService.getAll());
        model.addAttribute("seasonList", seasonService.getAll());
    }
}