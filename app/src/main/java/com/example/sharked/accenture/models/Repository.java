package com.example.sharked.accenture.models;

import com.google.gson.annotations.SerializedName;

public class Repository {
    public Float id;
    public String name;

    @SerializedName("node_id")
    public String nodeId;
    @SerializedName("full_name")
    public String fullName;


    public Float getId() {
        return id;
    }

    public void setId(Float id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
