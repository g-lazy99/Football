package com.example.football;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "teams")
public class TeamsListWrapper {

    private List<Teams> cities;

    @XmlElement(name = "team")
    public List<Teams> getcities() {
        return cities;
    }

    public void setcities(List<Teams> cities) {
        this.cities = cities;
    }
}