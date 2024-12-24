package com.navdeep.flowchartmanagement.dto;


public class NodeRequest {

    private String name;

    public NodeRequest() {
    }

    public NodeRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
