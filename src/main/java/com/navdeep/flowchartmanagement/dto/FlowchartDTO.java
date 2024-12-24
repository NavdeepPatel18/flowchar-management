package com.navdeep.flowchartmanagement.dto;



import java.time.LocalDateTime;
import java.util.List;

public class FlowchartDTO {

    private Long id;
    private String name;
    private String description;
    private List<NodeDTO> nodes;
    private List<EdgeDTO> edges;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FlowchartDTO() {
    }

    public FlowchartDTO(Long id, String name, String description, List<NodeDTO> nodes, List<EdgeDTO> edges, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nodes = nodes;
        this.edges = edges;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private FlowchartDTO(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.nodes = builder.nodes;
        this.edges = builder.edges;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private List<NodeDTO> nodes;
        private List<EdgeDTO> edges;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder nodes(List<NodeDTO> nodes) {
            this.nodes = nodes;
            return this;
        }

        public Builder edges(List<EdgeDTO> edges) {
            this.edges = edges;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public FlowchartDTO build() {
            return new FlowchartDTO(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<NodeDTO> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeDTO> nodes) {
        this.nodes = nodes;
    }

    public List<EdgeDTO> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeDTO> edges) {
        this.edges = edges;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
