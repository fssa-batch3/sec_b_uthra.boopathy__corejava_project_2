package in.fssa.tharasworld.dto;

import java.util.List;

import in.fssa.tharasworld.model.Price;

public class ProductDetailDTO {

	private int pdtId;
	private String name;
	private String description;
	private int sellerId;
	private int typeId;
	private boolean isActive;
	
	
	public int getPdtId() {
		return pdtId;
	}
	
	public void setPdtId(int pdtId) {
		this.pdtId = pdtId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getSellerId() {
		return sellerId;
	}
	
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	
	public int getTypeId() {
		return typeId;
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public List<Price> getListOfPrices() {
		return listOfPrices;
	}

	public void setListOfPrices(List<Price> listOfPrices) {
		this.listOfPrices = listOfPrices;
	}

	List<Price> listOfPrices;
	
}