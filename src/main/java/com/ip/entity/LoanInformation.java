package com.ip.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "LoanInformation", schema = "IP_Access_Project_DB")
public class LoanInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loanID")
    private Integer id;

    @Column(name = "userID", nullable = false)
    private Integer userID;

    @Column(name="deviceId", nullable = false)
    private Integer deviceId;

    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    @ColumnDefault("'on time'")
    @Column(name = "loanStatus", nullable = false, length = 20)
    private String loanStatus;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    public LoanInformation() {

    }

    public LoanInformation(int userId, int deviceId, String startDate, String loanStatus) {
        setUserID(userId);
        setDeviceId(deviceId);
        setStartDate(startDate);
        setLoanStatus(loanStatus);
    }
    public LoanInformation(int userId, int deviceId, LocalDate startDate, String loanStatus) {
        setUserID(userId);
        setDeviceId(deviceId);
        setStartDate(startDate);
        setLoanStatus(loanStatus);
    }
    public LoanInformation(int userId, int deviceId, String startDate, String loanStatus, String endDate) {
        setUserID(userId);
        setDeviceId(deviceId);
        setStartDate(startDate);
        setLoanStatus(loanStatus);
        setEndDate(endDate);
    }
    public LoanInformation(int userId, int deviceId, LocalDate startDate, String loanStatus, LocalDate endDate) {
        setUserID(userId);
        setDeviceId(deviceId);
        setStartDate(startDate);
        setLoanStatus(loanStatus);
        setEndDate(endDate);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDate) {
        try {
            this.startDate = LocalDate.parse(startDate);
        } catch (DateTimeParseException dtpe) {
            this.startDate = LocalDate.now();
        }
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(String endDate) {
        try {
            this.endDate = LocalDate.parse(endDate);
        } catch (DateTimeParseException dtpe) {
            System.out.println("Invalid end date.");
        }
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "LoanInformation{" +
                "id=" + id +
                ", userID=" + userID +
                ", deviceId=" + deviceId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", loanStatus='" + loanStatus + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}