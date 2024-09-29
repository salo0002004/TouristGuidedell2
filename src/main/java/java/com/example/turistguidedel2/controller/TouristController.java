package java.com.example.turistguidedel2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.model.TouristAttraction;
import java.service.TouristService;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/attractions")
public class TouristController {

    private final TouristService touristService;

    @Autowired
    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping
    public String getAllAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList";  // Thymeleaf template 'attractionList.html'
    }

    @GetMapping("/{name}/tags")
    public String getAttractionTags(@PathVariable String name, Model model) {
        Optional<TouristAttraction> attraction = touristService.getAttractionByName(name);
        attraction.ifPresent(attractionValue -> model.addAttribute("attraction", attractionValue));
        return attraction.isPresent() ? "tags" : "redirect:/attractions";
    }

    @GetMapping("/add")
    public String addAttractionForm(Model model) {
        model.addAttribute("touristAttraction", new TouristAttraction());
        model.addAttribute("cities", touristService.getCities());
        return "addAttraction";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.addAttraction(attraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/edit")
    public String editAttractionForm(@PathVariable String name, Model model) {
        Optional<TouristAttraction> attraction = touristService.getAttractionByName(name);
        attraction.ifPresent(attractionValue -> model.addAttribute("touristAttraction", attractionValue));
        return attraction.isPresent() ? "editAttraction" : "redirect:/attractions";
    }

    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.updateAttraction(attraction.getName(), attraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/delete")
    public String deleteAttraction(@PathVariable String name) {
        touristService.deleteAttraction(name);
        return "redirect:/attractions";
    }
}
