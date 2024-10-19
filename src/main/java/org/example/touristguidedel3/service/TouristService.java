package org.example.touristguidedel3.service;

import org.example.touristguidedel3.model.TouristAttraction;
import org.example.touristguidedel3.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {

    @Autowired
    private TouristRepository touristRepository;

    public String addTouristAttraction(TouristAttraction touristAttraction) {
        touristRepository.addTouristAttraction(touristAttraction);
        return "Tourist Attraction added successfully!";
    }

    // Get all tourist attractions
    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAllAttractions();
    }

    // Get tourist attractions by tag
    public List<TouristAttraction> getAttractionsByTag(String tag) {
        return touristRepository.getSpecificAttractionsByTags(tag);
    }

    // Update a tourist attraction
    public String updateTouristAttraction(int attractionNo, TouristAttraction touristAttraction) {
        touristRepository.updateTouristAttraction(attractionNo, touristAttraction);
        return "Tourist Attraction updated successfully!";
    }

    // Delete a tourist attraction
    public String deleteTouristAttraction(int attractionNo) {
        touristRepository.deleteTouristAttraction(attractionNo); // Pass the attraction number
        return "Tourist Attraction deleted successfully!";
    }
}
