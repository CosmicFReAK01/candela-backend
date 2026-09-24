package com.candelaconstruction.project.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String slug;

    private String cat;
    private String tag;
    private String tagColor;
    private String title;
    private String meta;
    private String diameter;
    private String length;
    private String wallThickness;
    private String pressure;
    private String duration;
    private String location;
    private String state;
    private String client;
    private String projectType;
    private String status;

    @Column(length = 2000)
    private String shortDesc;

    @Column(length = 5000)
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "project_scope", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "scope_item", length = 1000)
    private List<String> scopeOfWork = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "project_challenges", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "challenge_item", length = 1000)
    private List<String> keyChallenges = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "project_execution", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "execution_item", length = 1000)
    private List<String> execution = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "project_highlights", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "highlight_item", length = 1000)
    private List<String> highlights = new ArrayList<>();

    private String specLabel;
    private String specValue;

    public Project() {}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getCat() { return cat; }
    public void setCat(String cat) { this.cat = cat; }

    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }

    public String getTagColor() { return tagColor; }
    public void setTagColor(String tagColor) { this.tagColor = tagColor; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMeta() { return meta; }
    public void setMeta(String meta) { this.meta = meta; }

    public String getDiameter() { return diameter; }
    public void setDiameter(String diameter) { this.diameter = diameter; }

    public String getLength() { return length; }
    public void setLength(String length) { this.length = length; }

    public String getWallThickness() { return wallThickness; }
    public void setWallThickness(String wallThickness) { this.wallThickness = wallThickness; }

    public String getPressure() { return pressure; }
    public void setPressure(String pressure) { this.pressure = pressure; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public String getProjectType() { return projectType; }
    public void setProjectType(String projectType) { this.projectType = projectType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getShortDesc() { return shortDesc; }
    public void setShortDesc(String shortDesc) { this.shortDesc = shortDesc; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getScopeOfWork() { return scopeOfWork; }
    public void setScopeOfWork(List<String> scopeOfWork) { this.scopeOfWork = scopeOfWork; }

    public List<String> getKeyChallenges() { return keyChallenges; }
    public void setKeyChallenges(List<String> keyChallenges) { this.keyChallenges = keyChallenges; }

    public List<String> getExecution() { return execution; }
    public void setExecution(List<String> execution) { this.execution = execution; }

    public List<String> getHighlights() { return highlights; }
    public void setHighlights(List<String> highlights) { this.highlights = highlights; }

    public String getSpecLabel() { return specLabel; }
    public void setSpecLabel(String specLabel) { this.specLabel = specLabel; }

    public String getSpecValue() { return specValue; }
    public void setSpecValue(String specValue) { this.specValue = specValue; }
}
