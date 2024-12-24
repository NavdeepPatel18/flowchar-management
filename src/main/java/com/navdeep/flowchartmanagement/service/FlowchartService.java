package com.navdeep.flowchartmanagement.service;

import com.navdeep.flowchartmanagement.dto.EdgeDTO;
import com.navdeep.flowchartmanagement.dto.FlowchartDTO;
import com.navdeep.flowchartmanagement.dto.FlowchartRequest;
import com.navdeep.flowchartmanagement.dto.NodeDTO;
import com.navdeep.flowchartmanagement.entity.Edge;
import com.navdeep.flowchartmanagement.entity.Flowchart;
import com.navdeep.flowchartmanagement.entity.Node;
import com.navdeep.flowchartmanagement.repository.FlowchartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FlowchartService {

    private final FlowchartRepository flowchartRepository;
    private final NodeService nodeService;
    private final EdgeService edgeService;

    public FlowchartService(FlowchartRepository flowchartRepository, NodeService nodeService, EdgeService edgeService) {
        this.flowchartRepository = flowchartRepository;
        this.nodeService = nodeService;
        this.edgeService = edgeService;
    }

    public Flowchart createFlowchart(FlowchartRequest flowchartRequest) {

        Flowchart newFlowchart = Flowchart.builder()
                .name(flowchartRequest.getName())
                .description(flowchartRequest.getDescription())
                .build();

        Flowchart saveFlowchart = flowchartRepository.save(newFlowchart);
        if (flowchartRequest.getNodes() != null) {
            // Call NodeService to create nodes
            nodeService.createNodes(saveFlowchart, flowchartRequest.getNodes());
        }

        if(flowchartRequest.getEdges() != null) {
            // Call EdgeService to create edges
            edgeService.createEdges(saveFlowchart, flowchartRequest.getEdges());
        }

        return saveFlowchart;

    }

    public Optional<FlowchartDTO> getFlowchartById(Long id) {
        if(!flowchartRepository.existsById(id)) {
            return Optional.empty();
        }else{
            Flowchart flowchart = flowchartRepository.findById(id).get();
            return Optional.of(FlowchartDTO.builder()
                    .id(flowchart.getId())
                    .name(flowchart.getName())
                    .description(flowchart.getDescription())
                    .nodes(nodeService.getNodes(flowchart.getId()))
                    .edges(edgeService.getEdges(flowchart.getId()))
                    .createdAt(flowchart.getCreatedAt())
                    .updatedAt(flowchart.getUpdatedAt())
                    .build());
        }
    }

    @Transactional
    public Optional<FlowchartDTO> updateFlowchart(Long id, FlowchartRequest updatedFlowchartRequest) {
        if(!flowchartRepository.existsById(id)) {
            return Optional.empty();
        }

        Flowchart existingFlowchart = flowchartRepository.findById(id).get();

        existingFlowchart.setName(updatedFlowchartRequest.getName());
        existingFlowchart.setDescription(updatedFlowchartRequest.getDescription());

        // Call NodeService to update nodes
        List<NodeDTO> updatedNodes = nodeService.updateNodes(existingFlowchart, updatedFlowchartRequest.getNodes());

        // Call EdgeService to update edges
        List<EdgeDTO> updatedEdges = edgeService.updateEdges(existingFlowchart, updatedFlowchartRequest.getEdges());

        Flowchart updated = flowchartRepository.save(existingFlowchart);

        return Optional.of(FlowchartDTO.builder()
                .id(updated.getId())
                .name(updated.getName())
                .description(updated.getDescription())
                .nodes(updatedNodes)
                .edges(updatedEdges)
                .createdAt(updated.getCreatedAt())
                .updatedAt(updated.getUpdatedAt())
                .build());
    }

    public boolean deleteFlowchart(Long id) {
        if (flowchartRepository.existsById(id)) {

            Flowchart existingFlowchart = flowchartRepository.findById(id).get();
            edgeService.deleteEdges(existingFlowchart.getEdges());
            nodeService.deleteNodes(existingFlowchart.getNodes());

            flowchartRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
