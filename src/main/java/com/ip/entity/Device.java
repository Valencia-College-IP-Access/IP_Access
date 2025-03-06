package com.ip.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "Devices", schema = "IP_Access_Project_DB")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceID")
    private Integer id;

    @Column(name = "deviceName", length = 50)
    private String deviceName;

    @ColumnDefault("1")
    @Column(name = "availability", nullable = false)
    private Boolean availability = false;

    @OnDelete(action = OnDeleteAction.SET_NULL)
    @Column(name = "renterID")
    private Integer renterID;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    public Device() {

    }

    public Device(String deviceName, boolean availability) {
        setDeviceName(deviceName);
        setAvailability(availability);
    }

    public Device(String deviceName, boolean availability, int renterID) {
        setDeviceName(deviceName);
        setAvailability(availability);
        setRenterID(renterID);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public int getRenterID() {
        return renterID;
    }

    public void setRenterID(int renterID) {
        this.renterID = renterID;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}