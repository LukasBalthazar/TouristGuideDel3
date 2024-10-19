package org.example.touristguidedel3.controller;

import org.example.touristguidedel3.model.TouristAttraction;
import org.example.touristguidedel3.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Change to @Controller
@RequestMapping("/tourist-attractions") // Base URL
public class TouristController {

    @Autowired
    private TouristRepository touristRepository;

    // Display all tourist attractions on the HTML page
    @GetMapping
    public String getTouristAttractions(Model model) {
        List<TouristAttraction> attractions = touristRepository.getAllAttractions();
        model.addAttribute("attractions", attractions); // Add the list of attractions to the model
        return "attractions"; // Return the name of the Thymeleaf template (e.g., attractions.html)
    }

    // Display form to add a new attraction
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("touristAttraction", new TouristAttraction()); // Add an empty attraction object for the form
        return "add-attraction"; // Return the form template
    }

    // Handle form submission to add a new tourist attraction
    @PostMapping
    public String addTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristRepository.addTouristAttraction(touristAttraction);
        return "redirect:/tourist-attractions"; // Redirect to the list of attractions after adding
    }

    // Update an existing tourist attraction (display form)
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int attractionNo, Model model) {
        // Add the existing attraction to the model for editing
        TouristAttraction existingAttraction = touristRepository.getAttractionById(attractionNo);

        if (existingAttraction != null) {
            model.addAttribute("touristAttraction", existingAttraction); // Pass the attraction for editing
        } else {
            // Handle case when attraction is not found, maybe redirect to an error page
            model.addAttribute("error", "Attraction not found");
        }

        return "update-attraction"; // Return the form for editing
    }

    // Handle update form submission
    @PostMapping("/update/{id}")
    public String updateTouristAttraction(@PathVariable("id") int attractionNo, @ModelAttribute TouristAttraction touristAttraction) {
        touristRepository.updateTouristAttraction(attractionNo, touristAttraction); // Pass the object
        return "redirect:/tourist-attractions"; // Redirect after updating
    }



    // Delete a tourist attraction
    @GetMapping("/delete/{id}")
    public String deleteTouristAttraction(@PathVariable("id") int attractionNo) {
        touristRepository.deleteTouristAttraction(attractionNo); // Pass the attraction number
        return "redirect:/tourist-attractions"; // Redirect to the list of attractions after deleting
    }
}

