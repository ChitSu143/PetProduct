package com.hostmdy.model;

public class Pet {
	
	private int id;
	private String petName;
	private int petAge;
	private String ownerName;
	private double ownerAge;
	private boolean purchasedStatus;
	
	public Pet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pet(String petName, int petAge, String ownerName, double ownerAge, boolean purchasedStatus) {
		super();
		this.petName = petName;
		this.petAge = petAge;
		this.ownerName = ownerName;
		this.ownerAge = ownerAge;
		this.purchasedStatus = purchasedStatus;
	}

	public Pet(int id, String petName, int petAge, String ownerName, double ownerAge, boolean purchasedStatus) {
		super();
		this.id = id;
		this.petName = petName;
		this.petAge = petAge;
		this.ownerName = ownerName;
		this.ownerAge = ownerAge;
		this.purchasedStatus = purchasedStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public int getPetAge() {
		return petAge;
	}

	public void setPetAge(int petAge) {
		this.petAge = petAge;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public double getOwnerAge() {
		return ownerAge;
	}

	public void setOwnerAge(double ownerAge) {
		this.ownerAge = ownerAge;
	}

	public boolean isPurchasedStatus() {
		return purchasedStatus;
	}

	public void setPurchasedStatus(boolean purchasedStatus) {
		this.purchasedStatus = purchasedStatus;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", petName=" + petName + ", petAge=" + petAge + ", ownerName=" + ownerName
				+ ", ownerAge=" + ownerAge + ", purchasedStatus=" + purchasedStatus + "]";
	}
	
	
	

}
