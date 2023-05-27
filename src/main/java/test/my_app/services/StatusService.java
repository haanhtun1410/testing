package test.my_app.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.my_app.domain.Status;
import test.my_app.repos.StatusRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    // Method for creating a new status
    public Status createStatus(Status status) {
        return statusRepository.save(status);
    }

    // Method for retrieving a status by id
    public Status getStatusById(Long id) {
        Optional<Status> optionalStatus = statusRepository.findById(id);
        if (optionalStatus.isPresent()) {
            return optionalStatus.get();
        } else {
            throw new EntityNotFoundException("Status not found with id: " + id);
        }
    }

    // Method for updating a status
    public Status updateStatus(Status status) {
        return statusRepository.save(status);
    }

    // Method for deleting a status by id
    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }

    // Method for retrieving all statuses
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }
}