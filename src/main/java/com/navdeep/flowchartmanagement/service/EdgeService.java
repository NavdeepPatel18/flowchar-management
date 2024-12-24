package com.navdeep.flowchartmanagement.service;

import com.navdeep.flowchartmanagement.dto.EdgeDTO;
import com.navdeep.flowchartmanagement.dto.EdgeRequest;
import com.navdeep.flowchartmanagement.entity.Edge;
import com.navdeep.flowchartmanagement.entity.Flowchart;
import com.navdeep.flowchartmanagement.repository.EdgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EdgeService {

    private final EdgeRepository edgeRepository;

    public EdgeService(EdgeRepository edgeRepository) {
        this.edgeRepository = edgeRepository;
    }

    public Boolean createEdges(Flowchart flowchart, List<EdgeRequest> edges) {

        List<Edge> edgeList = new ArrayList<>();

        for(EdgeRequest edge : edges) {
            Edge newEdge = Edge.builder()
                    .source(edge.getSource())
                    .target(edge.getTarget())
                    .flowchart(flowchart)
                    .build();
            edgeList.add(newEdge);
        }

        List<Edge> savedEdges = edgeRepository.saveAll(edgeList);

        return savedEdges.size() == edgeList.size();
    }

    public List<EdgeDTO> getEdges(Long id) {
        if(edgeRepository.existsById(id)) {
            return edgeRepository.findAllByFlowchartId(id).stream()
                    .map(
                            edge -> {
                                new EdgeDTO();
                                return EdgeDTO.builder()
                                        .id(edge.getId())
                                        .source(edge.getSource())
                                        .target(edge.getTarget())
                                        .createdAt(edge.getCreatedAt())
                                        .updatedAt(edge.getUpdatedAt())
                                        .build();
                            }
                    ).toList();
        }
        return null;
    }

    public List<EdgeDTO> updateEdges(Flowchart existingFlowchart, List<EdgeRequest> edges) {
        if(edges == null) {
            return null;
        } else if (edges.isEmpty()) {
            deleteEdges(existingFlowchart.getEdges());
            return null;
        } else if (existingFlowchart.getEdges().isEmpty()) {
            return createEdges(existingFlowchart, edges) ?
                    getEdges(existingFlowchart.getId()).stream()
                            .map(
                                    edge -> {
                                        new EdgeDTO();
                                        return EdgeDTO.builder()
                                                .id(edge.getId())
                                                .source(edge.getSource())
                                                .target(edge.getTarget())
                                                .createdAt(edge.getCreatedAt())
                                                .updatedAt(edge.getUpdatedAt())
                                                .build();
                                    }
                            ).toList() : null;

        }

        deleteEdges(existingFlowchart.getEdges());
        return createEdges(existingFlowchart, edges) ?
                getEdges(existingFlowchart.getId()).stream()
                        .map(
                                edge -> {
                                    new EdgeDTO();
                                    return EdgeDTO.builder()
                                            .id(edge.getId())
                                            .source(edge.getSource())
                                            .target(edge.getTarget())
                                            .createdAt(edge.getCreatedAt())
                                            .updatedAt(edge.getUpdatedAt())
                                            .build();
                                }
                        ).toList() : null;
    }

    public void deleteEdges(List<Edge> Edges) {
        edgeRepository.deleteAll(Edges);
    }

}
