package com.navdeep.flowchartmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "flowchart")
public class Flowchart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "flowchart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Node> nodes;

    @OneToMany(mappedBy = "flowchart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Edge> edges;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    public Flowchart() {
    }

    public Flowchart(Long id, String name, String description, List<Node> nodes, List<Edge> edges, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nodes = nodes;
        this.edges = edges;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private Flowchart(FlowchartBuilder flowchartBuilder) {
        this.name = flowchartBuilder.name;
        this.description = flowchartBuilder.description;
    }

    public static FlowchartBuilder builder() {
        return new FlowchartBuilder();
    }

    public static class FlowchartBuilder {

        private String name;
        private String description;

        public FlowchartBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FlowchartBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Flowchart build() {
            return new Flowchart(this);
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

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flowchart flowchart = (Flowchart) o;
        return Objects.equals(id, flowchart.id) && Objects.equals(name, flowchart.name) && Objects.equals(description, flowchart.description) && Objects.equals(nodes, flowchart.nodes) && Objects.equals(edges, flowchart.edges) && Objects.equals(createdAt, flowchart.createdAt) && Objects.equals(updatedAt, flowchart.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, nodes, edges, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Flowchart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", nodes=" + nodes +
                ", edges=" + edges +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
