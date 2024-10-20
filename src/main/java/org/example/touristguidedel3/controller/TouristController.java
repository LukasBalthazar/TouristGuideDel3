package org.example.touristguidedel3.controller;

import org.example.touristguidedel3.model.TouristAttraction;
import org.example.touristguidedel3.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tourist-attractions")
public class TouristController {

    @Autowired
    private TouristService touristService; // Only use the service layer here

    // Display all tourist attractions on the index.html page
    @GetMapping
    public String getTouristAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions(); // Make sure this is returning data
        model.addAttribute("attractions", attractions);
        return "index"; // Ensure this corresponds to your Thymeleaf template
    }

    // Display form to add a new attraction
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("touristAttraction", new TouristAttraction());
        return "add-attraction"; // Add form page
    }

    // Handle form submission to add a new tourist attraction
    @PostMapping
    public String addTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.addTouristAttraction(touristAttraction); // Use the service
        return "redirect:/tourist-attractions"; // Redirect after adding
    }

    // Display form to update an existing tourist attraction
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int attractionNo, Model model) {
        TouristAttraction existingAttraction = touristService.getAttractionById(attractionNo); // Fetch by id

        if (existingAttraction != null) {
            model.addAttribute("touristAttraction", existingAttraction);
        } else {
            model.addAttribute("error", "Attraction not found");
        }

        return "update-attraction"; // Return the update form page
    }

    // Handle form submission to update tourist attraction
    @PostMapping("/update/{id}")
    public String updateTouristAttraction(@PathVariable("id") int attractionNo, @ModelAttribute TouristAttraction touristAttraction) {
        touristService.updateTouristAttraction(attractionNo, touristAttraction); // Update the attraction
        return "redirect:/tourist-attractions"; // Redirect after updating
    }

    // Delete a tourist attraction
    @GetMapping("/delete/{id}")
    public String deleteTouristAttraction(@PathVariable("id") int attractionNo) {
        touristService.deleteTouristAttraction(attractionNo); // Delete by id
        return "redirect:/tourist-attractions"; // Redirect after deletion
    }
}
