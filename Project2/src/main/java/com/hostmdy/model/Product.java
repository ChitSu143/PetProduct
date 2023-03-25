package com.hostmdy.model;

public class Product {
	private int id;
	private String productName;
	private int qty;
	private String description;
	private double sellingprice;
	private boolean discount;
	private String imageName;
	private String brandName;
	
	
	


	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Product(int id, String productName, int qty, String description, double sellingprice, boolean isDiscount,
			String imageName, String brandName) {
		super();
		this.id = id;
		this.productName = productName;
		this.qty = qty;
		this.description = description;
		this.sellingprice = sellingprice;
		this.discount = isDiscount;
		this.imageName = imageName;
		this.brandName = brandName;
	}

	

	public Product(String productName, int qty, String description, double sellingprice, boolean isDiscount,
			String imageName, String brandName) {
		super();
		this.productName = productName;
		this.qty = qty;
		this.description = description;
		this.sellingprice = sellingprice;
		this.discount = isDiscount;
		this.imageName = imageName;
		this.brandName = brandName;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getSellingprice() {
		return sellingprice;
	}


	public void setSellingprice(double sellingprice) {
		this.sellingprice = sellingprice;
	}





	public boolean isDiscount() {
		return discount;
	}



	public void setDiscount(boolean discount) {
		this.discount = discount;
	}



	public String getImageName() {
		return imageName;
	}



	public void setImageName(String imageName) {
		this.imageName = imageName;
	}



	public String getBrandName() {
		return brandName;
	}



	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", qty=" + qty + ", description=" + description
				+ ", sellingprice=" + sellingprice + ", isDiscount=" + discount + ", imageName=" + imageName
				+ ", brandName=" + brandName + ", getId()=" + getId() + ", getProductName()=" + getProductName()
				+ ", getQty()=" + getQty() + ", getDescription()=" + getDescription() + ", getSellingprice()="
				+ getSellingprice() + ", isDiscount()=" + isDiscount() + ", getImageName()=" + getImageName()
				+ ", getBrandName()=" + getBrandName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}


	
	
	
	
	
	

}
