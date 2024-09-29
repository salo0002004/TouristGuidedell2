package java.service;

import org.springframework.stereotype.Service;

import java.model.TouristAttraction;
import java.repository.TouristRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TouristService {

    private final TouristRepository repository;

    // Constructor-based dependency injection
    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    // Return all tourist attractions
    public List<TouristAttraction> getAllAttractions() {
        return repository.getAllAttractions();
    }

    // Get a tourist attraction by name
    public Optional<TouristAttraction> getAttractionByName(String name) {
        return repository.getAttractionByName(name);
    }

    // Add a new tourist attraction
    public void addAttraction(TouristAttraction attraction) {
        repository.addAttraction(attraction);
    }

    // Update an existing tourist attraction by name
    public boolean updateAttraction(String name, TouristAttraction updatedAttraction) {
        return repository.updateAttraction(name, updatedAttraction);
    }

    // Delete a tourist attraction by name
    public boolean deleteAttraction(String name) {
        return repository.deleteAttraction(name);
    }

    // Get a list of distinct cities from all tourist attractions
    public List<String> getCities() {
        return repository.getCities();
    }

    public List<String> getTags() {
        return repository.getTags();
    }

}
