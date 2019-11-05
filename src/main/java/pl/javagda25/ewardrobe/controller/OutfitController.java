package pl.javagda25.ewardrobe.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.javagda25.ewardrobe.model.Brand;
import pl.javagda25.ewardrobe.model.Cloth;
import pl.javagda25.ewardrobe.model.ClothType;
import pl.javagda25.ewardrobe.model.Outfit;
import pl.javagda25.ewardrobe.service.ClothService;
import pl.javagda25.ewardrobe.service.OccasionService;
import pl.javagda25.ewardrobe.service.OutfitService;
import pl.javagda25.ewardrobe.service.SeasonService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/outfit/")
public class OutfitController {
    private final OutfitService outfitService;
    private final ClothService clothService;
    private final OccasionService occasionService;
    private final SeasonService seasonService;

    @GetMapping("/addCloth/")
    public String addClothToOutFir(HttpServletRequest request,
                                   @PathVariable(name = "outfitId") Long outfitId,
                                   @PathVariable(name = "clothId") Long clothId) {
        outfitService.addClothToOutfit(outfitId, clothId);

        return "redirect:" + request.getHeader("referer");
    }

    @GetMapping("/add")
    public String createOutfit(Model model, Outfit outfit, HttpServletRequest request) {
        model.addAttribute("outfit", outfit);
        model.addAttribute("clothType", ClothType.values());
        model.addAttribute("backReferer", request.getHeader("referer"));
        model.addAttribute("clothList", clothService.getAllNoFilter());

        sendListOfTypesOccasionSeason(model);

        outfitService.save(outfit);

        return "outfit-add";
    }

    @PostMapping("/outfit")
    public String createOutfit(Outfit outfit, HttpServletRequest request) {

        outfitService.save(outfit);
        return "redirect:" + request.getHeader("referer");
    }

    @GetMapping("/listCloth")
    public String list(@RequestParam(name = "brandsFilter", required = false) Brand brandName,
                       @RequestParam(name = "typeFilter", required = false) ClothType clothType,
                       @RequestParam(name = "occasionFilter", required = false) Long occasionId,
                       @RequestParam(name = "seasonFilter", required = false) Long seasonId,
                       Model model, HttpServletRequest request) {

        List<Cloth> clothList = clothService.getAll(brandName, clothType, occasionId, seasonId);

        sendListOfTypesOccasionSeason(model);
        model.addAttribute("clothList", clothList);
        return "outfit-add";
    }

    private void sendListOfTypesOccasionSeason(Model model) {
        model.addAttribute("brands", Brand.values());
        model.addAttribute("clothTypes", ClothType.values());
        model.addAttribute("occasionList", occasionService.getAll());
        model.addAttribute("seasonList", seasonService.getAll());
    }
}
