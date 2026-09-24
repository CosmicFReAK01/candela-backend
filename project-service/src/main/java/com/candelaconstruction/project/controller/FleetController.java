package com.candelaconstruction.project.controller;

import com.candelaconstruction.project.model.EquipmentItem;
import com.candelaconstruction.project.repository.EquipmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fleet")
@CrossOrigin(origins = "*")
public class FleetController {

    private final EquipmentRepository equipmentRepository;

    public FleetController(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @GetMapping
    public ResponseEntity<List<EquipmentItem>> getFleet() {
        return ResponseEntity.ok(equipmentRepository.findAll());
    }
}
