package com.navdeep.flowchartmanagement.service;

import com.navdeep.flowchartmanagement.dto.NodeDTO;
import com.navdeep.flowchartmanagement.dto.NodeRequest;
import com.navdeep.flowchartmanagement.entity.Flowchart;
import com.navdeep.flowchartmanagement.entity.Node;
import com.navdeep.flowchartmanagement.repository.NodeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NodeService {

    private final NodeRepository nodeRepository;

    public NodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public Boolean createNodes(Flowchart flowchart, List<NodeRequest> nodeDTOList) {

        List<Node> nodes = new ArrayList<>();

        for(NodeRequest nodeDTO : nodeDTOList) {
            Node node = Node.builder()
                    .name(nodeDTO.getName())
                    .flowchart(flowchart)
                    .build();
            nodes.add(node);
        }

        List<Node> savedNodes = nodeRepository.saveAll(nodes);

        return savedNodes.size() == nodes.size();
    }

    public List<NodeDTO> getNodes(Long flowchartId) {
        return nodeRepository.findAllByFlowchartId(flowchartId).stream()
                .map(
                        node -> {
                            new NodeDTO();
                            return NodeDTO.builder()
                                    .id(node.getId())
                                    .name(node.getName())
                                    .createdAt(node.getCreatedAt())
                                    .updatedAt(node.getUpdatedAt())
                                    .build();
                        }
                ).toList();
    }

    @Transactional
    public List<NodeDTO> updateNodes(Flowchart existingFlowchart, List<NodeRequest> nodes) {
        if(nodes == null) {
            return null;
        } else if (nodes.isEmpty()) {
            deleteNodes(existingFlowchart.getNodes());
            return new ArrayList<>();
        }
        else if(existingFlowchart.getNodes().isEmpty()) {
            return createNodes(existingFlowchart, nodes) ?
                    getNodes(existingFlowchart.getId()).stream()
                    .map(
                            node -> {
                                new NodeDTO();
                                return NodeDTO.builder()
                                        .id(node.getId())
                                        .name(node.getName())
                                        .createdAt(node.getCreatedAt())
                                        .updatedAt(node.getUpdatedAt())
                                        .build();
                            }
                    ).toList() :
                    null;
        }

        deleteNodes(existingFlowchart.getNodes());
        return createNodes(existingFlowchart, nodes) ?
                getNodes(existingFlowchart.getId()).stream()
                .map(
                        node -> {
                            new NodeDTO();
                            return NodeDTO.builder()
                                    .id(node.getId())
                                    .name(node.getName())
                                    .createdAt(node.getCreatedAt())
                                    .updatedAt(node.getUpdatedAt())
                                    .build();
                        }
                ).toList() :
                null;
    }

    public void deleteNodes(List<Node> nodesToBeDeleted) {
        nodeRepository.deleteAll(nodesToBeDeleted);
    }

    public boolean findNodeByNameAndFlowchartId(String name, Long flowchartId) {
        return nodeRepository.existsByNameAndFlowchartId(name, flowchartId);
    }


}
