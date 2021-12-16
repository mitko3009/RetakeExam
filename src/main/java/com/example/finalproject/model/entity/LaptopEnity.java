package com.example.finalproject.model.entity;

import com.example.finalproject.model.entity.baseEntity.GoodsBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "laptops")
public class LaptopEnity extends GoodsBaseEntity {

    @Column(name = "monitor_size")
    private double monitorSize;


    public double getMonitorSize() {
        return monitorSize;
    }

    public void setMonitorSize(double monitorSize) {
        this.monitorSize = monitorSize;
    }


}
