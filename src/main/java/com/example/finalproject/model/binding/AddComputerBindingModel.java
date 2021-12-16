package com.example.finalproject.model.binding;

import com.example.finalproject.model.entity.CityEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AddComputerBindingModel {

    @NotNull
    private String make;
    @NotNull
    private String model;
    @NotNull
    private String cpu;
    private String gpu;
    @NotNull
    @Positive
    private int ram;
    @NotNull
    @Positive
    private int diskSpace;
    @NotNull
    @Positive
    private double price;
    @Positive
    private int powerSupply;
    @NotNull
    private String motherboard;
    private CityEntity city;

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

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

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getDiskSpace() {
        return diskSpace;
    }

    public void setDiskSpace(int diskSpace) {
        this.diskSpace = diskSpace;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
