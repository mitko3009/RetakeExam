package com.example.finalproject.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class LaptopUpdateBindingModel {

    private Long id;
    @NotNull
    private String cpu;
    @NotNull
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
