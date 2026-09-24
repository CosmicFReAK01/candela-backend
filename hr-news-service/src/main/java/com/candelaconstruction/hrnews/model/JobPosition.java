package com.candelaconstruction.hrnews.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_positions")
public class JobPosition {

    @Id
    private String id;
    private String title;
    private String department;
    private String location;
    private String experience;
    private String type;

    @Column(name = "job_desc", length = 2000)
    private String desc;

    public JobPosition() {}

    public JobPosition(String id, String title, String department, String location, String experience, String type, String desc) {
        this.id = id;
        this.title = title;
        this.department = department;
        this.location = location;
        this.experience = experience;
        this.type = type;
        this.desc = desc;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
}
