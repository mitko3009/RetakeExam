package com.example.finalproject.model.entity;

import com.example.finalproject.model.entity.baseEntity.GoodsBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "computers")
public class ComputerEntity extends GoodsBaseEntity {

    @Column(name = "power_supply")
    private int powerSupply;
    @NotNull
    private String motherboard;

    public String getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public int getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(int powerSupply) {
        this.powerSupply = powerSupply;
    }


}
