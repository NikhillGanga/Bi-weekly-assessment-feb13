package com.training.web.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "policies")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyName;
    private String description;
    private double premiumAmount;
    private int duration;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPolicyName() { return policyName; }
    public void setPolicyName(String policyName) { this.policyName = policyName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPremiumAmount() { return premiumAmount; }
    public void setPremiumAmount(double premiumAmount) { this.premiumAmount = premiumAmount; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
}