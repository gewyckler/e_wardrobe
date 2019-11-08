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

    @GetMapping("/addCloth")
    public String addClothToOutfit(Model model,
                                   @RequestParam(name = "outfitId") Long outfitId,
                                   @RequestParam(name = "clothId") Long clothId) {

        outfitService.addClothToOutfit(outfitId, clothId);
        Outfit outfit = outfitService.findById(outfitId);

        model.addAttribute("outfit", outfit);
        model.addAttribute("clothList", clothService.getAllNoFilter());
        sendListOfTypesOccasionSeason(model);

        return "outfit-add";
    }

    @GetMapping("/add")
    public String createOutfit(Model model, Outfit outfit, HttpServletRequest request) {
        outfitService.deleteIfNull();
        model.addAttribute("outfit", outfit);
        model.addAttribute("backReferer", request.getHeader("referer"));
        model.addAttribute("clothList", clothService.getAllNoFilter());
        sendListOfTypesOccasionSeason(model);
        outfitService.save(outfit);
        return "outfit-add";
    }

    @PostMapping("/add")
    public String createOutfit(Outfit outfit) {
        outfitService.save(outfit);
        return "redirect:/outfit/listOutfit";
    }

    @GetMapping("/update/{outfitId}")
    public String updateOutfit(Model model, HttpServletRequest request,
                               @PathVariable(name = "outfitId") Long outfitId) {
        Outfit outfit = outfitService.findById(outfitId);
        model.addAttribute("outfit", outfit);
        model.addAttribute("backReferer", request.getHeader("referer"));
        model.addAttribute("clothList", clothService.getAllNoFilter());
        sendListOfTypesOccasionSeason(model);
        return "outfit-add";
    }

    @GetMapping("/listCloth")
    public String list(Model model,
                       @RequestParam(name = "brandsFilter", required = false) Brand brandName,
                       @RequestParam(name = "typeFilter", required = false) ClothType clothType,
                       @RequestParam(name = "occasionFilter", required = false) Long occasionId,
                       @RequestParam(name = "seasonFilter", required = false) Long seasonId) {

        List<Cloth> clothList = clothService.getAll(brandName, clothType, occasionId, seasonId);

        sendListOfTypesOccasionSeason(model);
        model.addAttribute("clothList", clothList);
        return "outfit-add";
    }

    @GetMapping("/listOutfit")
    public String listOutfit(Model model, HttpServletRequest request) {
        outfitService.deleteIfNull();
        List<Outfit> outfitList = outfitService.findAll();
        model.addAttribute("outfitList", outfitList);
        model.addAttribute("backReferer", request.getHeader("referer"));
        return "outfit-list";
    }

    @GetMapping("/removeCloth")
    public String removeClothFromOutfit(Model model, HttpServletRequest request,
                                        @RequestParam(name = "outfitId") Long outfitId,
                                        @RequestParam(name = "clothId") Long clothId) {
        outfitService.removeClothFromOutfit(outfitId, clothId);
        Outfit outfit = outfitService.findById(outfitId);
        model.addAttribute("outfit", outfit);
        model.addAttribute("backReferer", request.getHeader("referer"));
        model.addAttribute("clothList", clothService.getAllNoFilter());
        sendListOfTypesOccasionSeason(model);
        return "outfit-add";
    }

    @GetMapping("/delete/{outfitID}")
    public String deleteOutfit(HttpServletRequest request,
                               @PathVariable(name = "outfitID") Long outfitId) {
        outfitService.deteById(outfitId);
        return "redirect:" + request.getHeader("referer");
    }

    private void sendListOfTypesOccasionSeason(Model model) {
        model.addAttribute("brands", Brand.values());
        model.addAttribute("clothTypes", ClothType.values());
        model.addAttribute("occasionList", occasionService.getAll());
        model.addAttribute("seasonList", seasonService.getAll());
    }
}
