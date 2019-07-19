package com.example.demo.request;

public class AddProjectReq {
    private Long organizationId;
    private String projectZhName;
    private String projectEnName;
    private String description;

    public AddProjectReq() {
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getProjectZhName() {
        return projectZhName;
    }

    public void setProjectZhName(String projectZhName) {
        this.projectZhName = projectZhName;
    }

    public String getProjectEnName() {
        return projectEnName;
    }

    public void setProjectEnName(String projectEnName) {
        this.projectEnName = projectEnName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AddProjectReq{" +
                "organizationId=" + organizationId +
                ", projectZhName='" + projectZhName + '\'' +
                ", projectEnName='" + projectEnName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
