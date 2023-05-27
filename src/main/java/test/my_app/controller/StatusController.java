package test.my_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.my_app.domain.Status;
import test.my_app.services.StatusService;

import java.util.List;

@RestController
@RequestMapping("/statuses")
public class StatusController {
    @Autowired
    private StatusService statusService;

    // Endpoint for creating a new status
    @PostMapping("/add")
    public Status createStatus(@RequestBody Status status) {
        return statusService.createStatus(status);
    }

    // Endpoint for retrieving a status by id
    @GetMapping("/{id}")
    public Status getStatusById(@PathVariable Long id) {
        return statusService.getStatusById(id);
    }

    // Endpoint for updating a status
    @PutMapping("/{id}/update")
    public Status updateStatus(@PathVariable Long id, @RequestBody Status status) {
        status.setId(id); // Set the id of the status
        return statusService.updateStatus(status);
    }

    // Endpoint for deleting a status by id
    @DeleteMapping("/{id}/delete")
    public void deleteStatus(@PathVariable Long id) {
        statusService.deleteStatus(id);
    }

    // Endpoint for retrieving all statuses
    @GetMapping("/all")
    public List<Status> getAllStatuses() {
        return statusService.getAllStatuses();
    }
}