package com.candelaconstruction.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "state_footprints")
public class StateFootprint {

    @Id
    private String id;
    private String name;
    private int projects;
    private int km;
    private String status;

    public StateFootprint() {}

    public StateFootprint(String id, String name, int projects, int km, String status) {
        this.id = id;
        this.name = name;
        this.projects = projects;
        this.km = km;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getProjects() { return projects; }
    public void setProjects(int projects) { this.projects = projects; }

    public int getKm() { return km; }
    public void setKm(int km) { this.km = km; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
