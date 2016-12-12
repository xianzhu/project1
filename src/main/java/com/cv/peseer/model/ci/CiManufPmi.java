package com.cv.peseer.model.ci;

public class CiManufPmi {
    private Integer id;

    private String statDate;

    private Double manuf;

    private Double product;

    private Double newOrder;

    private Double newExportOrder;

    private Double backlogOrder;

    private Double manufInventory;

    private Double purchase;

    private Double importation;

    private Double purchasePrice;

    private Double materialStock;

    private Double employee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate == null ? null : statDate.trim();
    }

    public Double getManuf() {
        return manuf;
    }

    public void setManuf(Double manuf) {
        this.manuf = manuf;
    }

    public Double getProduct() {
        return product;
    }

    public void setProduct(Double product) {
        this.product = product;
    }

    public Double getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(Double newOrder) {
        this.newOrder = newOrder;
    }

    public Double getNewExportOrder() {
        return newExportOrder;
    }

    public void setNewExportOrder(Double newExportOrder) {
        this.newExportOrder = newExportOrder;
    }

    public Double getBacklogOrder() {
        return backlogOrder;
    }

    public void setBacklogOrder(Double backlogOrder) {
        this.backlogOrder = backlogOrder;
    }

    public Double getManufInventory() {
        return manufInventory;
    }

    public void setManufInventory(Double manufInventory) {
        this.manufInventory = manufInventory;
    }

    public Double getPurchase() {
        return purchase;
    }

    public void setPurchase(Double purchase) {
        this.purchase = purchase;
    }

    public Double getImportation() {
        return importation;
    }

    public void setImportation(Double importation) {
        this.importation = importation;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getMaterialStock() {
        return materialStock;
    }

    public void setMaterialStock(Double materialStock) {
        this.materialStock = materialStock;
    }

    public Double getEmployee() {
        return employee;
    }

    public void setEmployee(Double employee) {
        this.employee = employee;
    }
}