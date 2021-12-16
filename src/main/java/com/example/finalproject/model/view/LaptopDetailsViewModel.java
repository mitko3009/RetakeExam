package com.example.finalproject.model.view;

import com.example.finalproject.model.entity.CityEntity;
import com.example.finalproject.model.entity.UserEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

public class LaptopDetailsViewModel {

    private Long id;
    private String make;
    private String model;
    private String cpu;
    private String gpu;
    private Integer ram;
    private Integer diskSpace;
    private double price;
    private String sellerName;
    private double monitorSize;
    private CityEntity city;

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getDiskSpace() {
        return diskSpace;
    }

    public void setDiskSpace(Integer diskSpace) {
        this.diskSpace = diskSpace;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public double getMonitorSize() {
        return monitorSize;
    }

    public void setMonitorSize(double monitorSize) {
        this.monitorSize = monitorSize;
    }
}
