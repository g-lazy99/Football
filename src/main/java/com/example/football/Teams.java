package com.example.football;

public class Teams {
    int id;
    String name, trainer, country, year;

    public Teams(int id, String year, String name, String trainer, String country) {
        this.id = id;
        this.year = year;
        this.name = name;
        this.trainer = trainer;
        this.country = country;
    }

    public Teams() {
    }


    @Override
    public String toString() {
        return "Team{" +
                "trainer=" + trainer +
                ", country=" + country +
                ", id=" + id +
                ", year=" + year +
                ", name='" + name + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
