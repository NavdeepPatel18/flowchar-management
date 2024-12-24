package com.navdeep.flowchartmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "edge")
public class Edge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String source;
    private String target;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "flowchart_id")
    private Flowchart flowchart;

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

    public Edge() {
    }

    public Edge(Long id, String source, String target, Flowchart flowchart, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.flowchart = flowchart;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    private Edge(EdgeBuilder edgeBuilder) {
        this.source = edgeBuilder.source;
        this.target = edgeBuilder.target;
        this.flowchart = edgeBuilder.flowchart;
    }


    public static EdgeBuilder builder() {
        return new EdgeBuilder();
    }

    public static class EdgeBuilder{

                private String source;
                private String target;
                private Flowchart flowchart;

                public EdgeBuilder source(String source) {
                    this.source = source;
                    return this;
                }

                public EdgeBuilder target(String target) {
                    this.target = target;
                    return this;
                }

                public EdgeBuilder flowchart(Flowchart flowchart) {
                    this.flowchart = flowchart;
                    return this;
                }

                public Edge build() {
                    return new Edge(this);
                }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Flowchart getFlowchart() {
        return flowchart;
    }

    public void setFlowchart(Flowchart flowchart) {
        this.flowchart = flowchart;
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
        Edge edge = (Edge) o;
        return Objects.equals(id, edge.id) && Objects.equals(source, edge.source) && Objects.equals(target, edge.target) && Objects.equals(flowchart, edge.flowchart) && Objects.equals(createdAt, edge.createdAt) && Objects.equals(updatedAt, edge.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, target, flowchart, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", flowchart=" + flowchart +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
