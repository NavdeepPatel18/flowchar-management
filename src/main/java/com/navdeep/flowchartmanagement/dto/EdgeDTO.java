package com.navdeep.flowchartmanagement.dto;

import java.time.LocalDateTime;

public class EdgeDTO {

    private Long id;
    private String source;
    private String target;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EdgeDTO() {
    }

    public EdgeDTO(Long id, String source, String target, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private EdgeDTO(Builder builder){
        this.id = builder.id;
        this.source = builder.source;
        this.target = builder.target;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String source;
        private String target;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Builder target(String target) {
            this.target = target;
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

        public EdgeDTO build() {
            return new EdgeDTO(this);
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
