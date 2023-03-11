package com.example.demo.unicorn;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="unicorns")
public class Unicorn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String hairColor;

    private int hornLength;

    private String hornColor;

    private String eyeColor;

    private int height;

    private String heightUnit;

    private int weight;

    private String weightUnit;

    @ElementCollection // Define a collection of basic types
    @OneToMany(cascade = CascadeType.ALL) // Specifies the relationship between the entity and a collection of other entities
    @JoinColumn(name = "unicorn_id") // Specifies the column used for joining the two tables
    private List<IdentifiableMark> identifiableMarks;

    public void setIdentifiableMarks(List<IdentifiableMark> identifiableMarks) {
        this.identifiableMarks = identifiableMarks;
    }

    public List<IdentifiableMark> getIdentifiableMarks() {
        return identifiableMarks;
    }






    public Unicorn() {
    }

    public Unicorn(Long id, String name, String hairColor, int hornLength, String hornColor, String eyeColor, int height, String heightUnit, int weight, String weightUnit, List<IdentifiableMark> identifiableMarks) {
        this.id = id;
        this.name = name;
        this.hairColor = hairColor;
        this.hornLength = hornLength;
        this.hornColor = hornColor;
        this.eyeColor = eyeColor;
        this.height = height;
        this.heightUnit = heightUnit;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.identifiableMarks = identifiableMarks;

    }

    public Unicorn(String name, String hairColor, int hornLength, String hornColor, String eyeColor, int height, String heightUnit, int weight, String weightUnit, List<IdentifiableMark> identifiableMarks) {
        this.name = name;
        this.hairColor = hairColor;
        this.hornLength = hornLength;
        this.hornColor = hornColor;
        this.eyeColor = eyeColor;
        this.height = height;
        this.heightUnit = heightUnit;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.identifiableMarks = identifiableMarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public int getHornLength() {
        return hornLength;
    }

    public void setHornLength(int hornLength) {
        this.hornLength = hornLength;
    }

    public String getHornColor() {
        return hornColor;
    }

    public void setHornColor(String hornColor) {
        this.hornColor = hornColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit(String heightUnit) {
        this.heightUnit = heightUnit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }


}
