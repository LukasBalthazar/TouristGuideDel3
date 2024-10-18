package org.example.touristguidedel3.model;

import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private String district; // Keeping this as String
    private List<String> tags;
    private String city;

    // Constructor with 'district' as String
    public TouristAttraction(String name, String description, String district, List<String> tags, String city) {
        this.name = name;
        this.description = description;
        this.district = district;  // Initialize district here
        this.tags = tags;
        this.city = city;
    }

    // Getters and setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}

