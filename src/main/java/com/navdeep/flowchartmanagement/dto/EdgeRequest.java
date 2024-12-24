package com.navdeep.flowchartmanagement.dto;


public class EdgeRequest {

    private String source;
    private String target;

    public EdgeRequest() {
    }

    public EdgeRequest(String source, String target) {
        this.source = source;
        this.target = target;
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
}
