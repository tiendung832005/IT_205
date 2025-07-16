package com.data.session07.service;

import com.data.session07.entity.Worker;
import com.data.session07.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepository workerRepository;

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public Worker getWorkerById(Long id) {
        return workerRepository.findById(id).orElse(null);
    }

    public Worker addWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    public Worker updateWorker(Long id, Worker worker) {
        worker.setId(id);
        return workerRepository.save(worker);
    }

    public void deleteWorker(Long id) {
        workerRepository.deleteById(id);
    }
}