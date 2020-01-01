package com.dream.mobile.rmi.interfaces;

public class Analytics implements java.io.Serializable{
	private Integer id;
	private String name;
	private String oS;
	private String price;
	private String ram;
	private String screen;
	private String backCamera;
	private String frontCamera;
	private String storage;
	private String microSd;
	private String brand;
	private String other;
	
	public Analytics() {
		
	}
	
	public Analytics(Integer id, String name, String oS, String price, String ram, String screen, String backCamera,
			String frontCamera, String storage, String microSd, String brand, String other) {
		super();
		this.id = id;
		this.name = name;
		this.oS = oS;
		this.price = price;
		this.ram = ram;
		this.screen = screen;
		this.backCamera = backCamera;
		this.frontCamera = frontCamera;
		this.storage = storage;
		this.microSd = microSd;
		this.brand = brand;
		this.other = other;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOS() {
		return oS;
	}
	public void setOS(String oS) {
		oS = oS;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public String getBackCamera() {
		return backCamera;
	}
	public void setBackCamera(String backCamera) {
		this.backCamera = backCamera;
	}
	public String getFrontCamera() {
		return frontCamera;
	}
	public void setFrontCamera(String frontCamera) {
		this.frontCamera = frontCamera;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getMicroSd() {
		return microSd;
	}
	public void setMicroSd(String microSd) {
		this.microSd = microSd;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}


}
