package com.candelaconstruction.project.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "services")
public class ServiceItem {

    @Id
    private String id;

    private String n;
    private String icon;
    private String title;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(length = 2000)
    private String text;

    private String standard;
    private String color;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "service_bullets", joinColumns = @JoinColumn(name = "service_id"))
    @Column(name = "bullet", length = 1000)
    private List<String> bullets = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "service_subcategories", joinColumns = @JoinColumn(name = "service_id"))
    @Column(name = "subcategory", length = 500)
    private List<String> subcategories = new ArrayList<>();

    public ServiceItem() {}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getN() { return n; }
    public void setN(String n) { this.n = n; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getStandard() { return standard; }
    public void setStandard(String standard) { this.standard = standard; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public List<String> getBullets() { return bullets; }
    public void setBullets(List<String> bullets) { this.bullets = bullets; }

    public List<String> getSubcategories() { return subcategories; }
    public void setSubcategories(List<String> subcategories) { this.subcategories = subcategories; }
}
