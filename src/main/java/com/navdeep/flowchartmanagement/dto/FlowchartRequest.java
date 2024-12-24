package com.navdeep.flowchartmanagement.dto;

import java.util.List;

public class FlowchartRequest {

    private String name;
    private String description;

    private List<NodeRequest> nodes;
    private List<EdgeRequest> edges;

    public FlowchartRequest() {
    }

    public FlowchartRequest(String name, String description, List<NodeRequest> nodes, List<EdgeRequest> edges) {
        this.name = name;
        this.description = description;
        this.nodes = nodes;
        this.edges = edges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<NodeRequest> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeRequest> nodes) {
        this.nodes = nodes;
    }

    public List<EdgeRequest> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeRequest> edges) {
        this.edges = edges;
    }
}
