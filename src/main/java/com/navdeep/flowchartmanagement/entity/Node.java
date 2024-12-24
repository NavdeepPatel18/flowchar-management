package com.navdeep.flowchartmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "node")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

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


    public Node() {
    }

    public Node(Long id, String name, Flowchart flowchart, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.flowchart = flowchart;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private Node(NodeBuilder nodeBuilder) {
        this.name = nodeBuilder.name;
        this.flowchart = nodeBuilder.flowchart;
    }

    public static NodeBuilder builder() {
        return new NodeBuilder();
    }

    public static class NodeBuilder{

            private String name;
            private Flowchart flowchart;

            public NodeBuilder name(String name) {
                this.name = name;
                return this;
            }

            public NodeBuilder flowchart(Flowchart flowchart) {
                this.flowchart = flowchart;
                return this;
            }

            public Node build() {
                return new Node(this);
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
        Node node = (Node) o;
        return Objects.equals(id, node.id) && Objects.equals(name, node.name) && Objects.equals(flowchart, node.flowchart) && Objects.equals(createdAt, node.createdAt) && Objects.equals(updatedAt, node.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, flowchart, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flowchart=" + flowchart +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
