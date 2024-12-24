package com.navdeep.flowchartmanagement.controller;

import com.navdeep.flowchartmanagement.dto.EdgeDTO;
import com.navdeep.flowchartmanagement.dto.FlowchartDTO;
import com.navdeep.flowchartmanagement.dto.FlowchartRequest;
import com.navdeep.flowchartmanagement.entity.Flowchart;
import com.navdeep.flowchartmanagement.service.EdgeService;
import com.navdeep.flowchartmanagement.service.FlowchartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/flowchart")

public class FlowchartController {

    @Autowired
    private FlowchartService flowchartService;
    private EdgeService service;
    @Autowired
    private EdgeService edgeService;

    @PostMapping
    public ResponseEntity<String> createFlowchart(@RequestBody FlowchartRequest flowchartRequest) {
        Flowchart savedFlowchart =  flowchartService.createFlowchart(flowchartRequest);
        if(savedFlowchart == null) {
            return ResponseEntity.badRequest().body("Flowchart creation failed");
        }
        return ResponseEntity.ok("Flowchart created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFlowchart(@PathVariable Long id) {
        Optional<FlowchartDTO> flowchart = flowchartService.getFlowchartById(id);
        if (flowchart.isPresent()) {
            return ResponseEntity.ok(flowchart.get());  // Return Flowchart object as the response body
        } else {
            return ResponseEntity.status(404).body("Flowchart not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFlowchart(@PathVariable Long id, @RequestBody FlowchartRequest updatedFlowchartRequest) {
        Optional<FlowchartDTO> updated = flowchartService.updateFlowchart(id, updatedFlowchartRequest);
        if (updated.isPresent()) {
            return ResponseEntity.ok("Flowchart updated successfully");
        } else {
            return ResponseEntity.status(404).body("Flowchart not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlowchart(@PathVariable Long id) {
        if (flowchartService.deleteFlowchart(id)) {
            return ResponseEntity.ok("Flowchart deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Flowchart not found");
        }
    }

    @GetMapping("/validate/{id}")
    public ResponseEntity<String> validateFlowchart(@PathVariable Long id) {
        if (flowchartService.validateFlowchart(id)) {
            return ResponseEntity.ok("Flowchart is valid");
        } else {
            return ResponseEntity.status(400).body("Flowchart is invalid");
        }
    }

    @GetMapping("/{id}/outgoingedges/{node}")
    public ResponseEntity<?> getOutgoingEdges(@PathVariable Long id, @PathVariable String node) {
        Optional<List<EdgeDTO>> outgoingEdges = edgeService.outgoingEdgesFroNode(id,node);
        if (outgoingEdges.isPresent()) {
            return ResponseEntity.ok(outgoingEdges.get());
        } else {
            return ResponseEntity.status(404).body("Flowchart not found");
        }
    }

}
