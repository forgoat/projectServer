package com.example.demo.domain;

public class ProjectDo {
    private Long id;
    private Long organizationId;
    private String projectZhName;
    private String projectEnName;
    private String description;
    private Long createTime;
    private Long updateTime;

    public ProjectDo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ProjectDo{" +
                "id=" + id +
                ", organizationId=" + organizationId +
                ", projectZhName='" + projectZhName + '\'' +
                ", projectEnName='" + projectEnName + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
