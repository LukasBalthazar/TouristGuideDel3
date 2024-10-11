package org.example.touristguidedel3.model;

import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private List<String> district;
    private List<String> tags;
    private String city;

    public TouristAttraction(String name, String description, List<String> district, List<String> tags) {
        this.name = name;
        this.description = description;
        this.district = district;
        this.tags = tags;
        this.city = city;
    }

    public String getCity{
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
    public List<String> getDistrict() {
        return district;
    }
    public void setDistrict(List<String> district) {
        this.district = district;
    }
}