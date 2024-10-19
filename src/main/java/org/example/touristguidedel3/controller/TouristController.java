package org.example.touristguidedel3.controller;

import org.example.touristguidedel3.model.TouristAttraction;
import org.example.touristguidedel3.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tourist-attractions") //Base URL
public class TouristController {

@Autowired
    private TouristRepository touristRepository;

//POST (add attraction)
    @PostMapping
    public  ResponseEntity<String> addTouristAttraction(@RequestBody TouristAttraction touristAttraction){
        touristRepository.addTouristAttraction(touristAttraction);
        return ResponseEntity.ok("Tourist Attraction added");
    }

    //Get (all)
    @GetMapping
    public ResponseEntity<List<TouristAttraction>> getTouristAttractions(){
        List<TouristAttraction> attractions = touristRepository.getAllAttractions();
        return ResponseEntity.ok(attractions);
    }

    //Get (search perimeters)
    @GetMapping("/search")
    public ResponseEntity<List<TouristAttraction>> getAttractionsByTag(@RequestParam String tag) {
        List<TouristAttraction> attractions = touristRepository.getSpecificAttractionsByTags(tag);
        return ResponseEntity.ok(attractions);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTouristAttraction(@PathVariable("id") int attractionNo) {
        touristRepository.updateTouristAttraction(attractionNo);
        return ResponseEntity.ok("Tourist Attraction updated successfully!");
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTouristAttraction(@PathVariable("id") int attractionNo) {
        touristRepository.deleteTouristAttraction();
        return ResponseEntity.ok("Tourist Attraction deleted successfully!");
    }

}
