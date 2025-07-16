package com.data.session07.controller;

import com.data.session07.entity.Worker;
import com.data.session07.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @GetMapping
    public List<Worker> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @PostMapping
    public Worker addWorker(@RequestBody Worker worker) {
        return workerService.addWorker(worker);
    }

    @PutMapping("/{id}")
    public Worker updateWorker(@PathVariable Long id, @RequestBody Worker worker) {
        return workerService.updateWorker(id, worker);
    }

    @DeleteMapping("/{id}")
    public void deleteWorker(@PathVariable Long id) {
        workerService.deleteWorker(id);
    }
}