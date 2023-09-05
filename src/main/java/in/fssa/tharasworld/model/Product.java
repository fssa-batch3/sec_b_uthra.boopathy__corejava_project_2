package in.fssa.tharasworld.model;

public abstract class Product implements Comparable<Product> {

	private int pdtId;
	private String name;
	private String img;
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
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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
		
	
	@Override
	public String toString() {
		return "Product [pdtId=" + pdtId + ", name=" + name + ", img_url=" + img +", description=" + description + ", sellerId=" + sellerId
				+ ", typeId=" + typeId + ", isActive=" + isActive + "]";
	}
	
	@Override
	public int compareTo(Product p) {
			
		if (this.getPdtId() == p.getPdtId()) {
			return 0;
		} else {
			if (this.pdtId>getPdtId()) {
				return 1;
			} else {
				return -1;
			}
			
		}
		
	}
	
	
}
