package com.example.demo.domain;

public class OrganizationDo {
    private Integer id;
    private String organizationZhName;
    private String organizationEnName;
    private String description;
    private String backgroundLocation;
    private String logoLocation;
    private Long createTime;
    private Long updateTime;

    public OrganizationDo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganizationZhName() {
        return organizationZhName;
    }

    public void setOrganizationZhName(String organizationZhName) {
        this.organizationZhName = organizationZhName;
    }

    public String getOrganizationEnName() {
        return organizationEnName;
    }

    public void setOrganizationEnName(String organizationEnName) {
        this.organizationEnName = organizationEnName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackgroundLocation() {
        return backgroundLocation;
    }

    public void setBackgroundLocation(String backgroundLocation) {
        this.backgroundLocation = backgroundLocation;
    }

    public String getLogoLocation() {
        return logoLocation;
    }

    public void setLogoLocation(String logoLocation) {
        this.logoLocation = logoLocation;
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
        return "OrganizationDo{" +
                "id=" + id +
                ", organizationZhName='" + organizationZhName + '\'' +
                ", organizationEnName='" + organizationEnName + '\'' +
                ", description='" + description + '\'' +
                ", backgroundLocation='" + backgroundLocation + '\'' +
                ", logoLocation='" + logoLocation + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
