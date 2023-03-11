package com.example.demo.unicorn;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

@Service
public class UnicornService {

    // Inject the UnicornRepository interface
    private final UnicornRepository unicornRepository;

    // Constructor to initialize UnicornRepository instance
    public UnicornService(UnicornRepository unicornRepository) {
        this.unicornRepository = unicornRepository;
    }

    // Method to create a new unicorn and save it to the repository
    public Unicorn createUnicorn(Unicorn unicorn) {
        return unicornRepository.save(unicorn);
    }

    // Method to get all unicorns from the repository
    public List<Unicorn> getAllUnicorns() {
        return unicornRepository.findAll();
    }

    // Method to get a specific unicorn by its id from the repository
    public Unicorn getUnicornById(Long id) {
        // If a unicorn with the given id doesn't exist, throw a NoSuchElementException with a message
        return unicornRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Unicorn not found"));
    }

    // Method to update an existing unicorn with new attributes and save it to the repository
    public Unicorn updateUnicorn(Long id, Unicorn unicorn) {
        // Get the existing unicorn from the repository using its id
        Unicorn existingUnicorn = getUnicornById(id);

        // Set the attributes of the existing unicorn to the values of the new unicorn
        existingUnicorn.setName(unicorn.getName());
        existingUnicorn.setHairColor(unicorn.getHairColor());
        existingUnicorn.setHornLength(unicorn.getHornLength());
        existingUnicorn.setHornColor(unicorn.getHornColor());
        existingUnicorn.setEyeColor(unicorn.getEyeColor());
        existingUnicorn.setHeight(unicorn.getHeight());
        existingUnicorn.setHeightUnit(unicorn.getHeightUnit());
        existingUnicorn.setWeight(unicorn.getWeight());
        existingUnicorn.setWeightUnit(unicorn.getWeightUnit());
        existingUnicorn.setIdentifiableMarks(unicorn.getIdentifiableMarks());

        // Save the updated unicorn to the repository and return it
        return unicornRepository.save(existingUnicorn);
    }
}
