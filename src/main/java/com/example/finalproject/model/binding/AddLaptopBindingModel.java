package com.example.finalproject.model.binding;

import com.example.finalproject.model.entity.CityEntity;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AddLaptopBindingModel {

    @NotNull
    private String make;
    @NotNull
    private String model;
    @NotNull
    private String cpu;
    private String gpu;
    @NotNull
    @Positive
    private Integer ram;
    @NotNull
    @Positive
    private Integer diskSpace;
    @NotNull
    @Positive
    private double price;
    @NotNull
    @Positive
    private double monitorSize;
    private CityEntity city;

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
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

    public double getMonitorSize() {
        return monitorSize;
    }

    public void setMonitorSize(double monitorSize) {
        this.monitorSize = monitorSize;
    }
}
