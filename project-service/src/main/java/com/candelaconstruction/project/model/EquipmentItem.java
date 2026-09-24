package com.candelaconstruction.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipment")
public class EquipmentItem {

    @Id
    private String name;
    private String application;
    private String capacity;
    private String units;
    private String make;

    public EquipmentItem() {}

    public EquipmentItem(String name, String application, String capacity, String units, String make) {
        this.name = name;
        this.application = application;
        this.capacity = capacity;
        this.units = units;
        this.make = make;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getApplication() { return application; }
    public void setApplication(String application) { this.application = application; }

    public String getCapacity() { return capacity; }
    public void setCapacity(String capacity) { this.capacity = capacity; }

    public String getUnits() { return units; }
    public void setUnits(String units) { this.units = units; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
}
