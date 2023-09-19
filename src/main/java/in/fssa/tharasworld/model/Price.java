package in.fssa.tharasworld.model;

public abstract class Price implements Comparable<Price> {

	private int priceId;
	private int sizeId;
	private int pdtId;
	private double actualPrice;
	private double currentPrice;
	private double discount;
	private boolean isActive;

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public int getSizeId() {
		return sizeId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public int getPdtId() {
		return pdtId;
	}

	public void setPdtId(int pdtId) {
		this.pdtId = pdtId;
	}

	public double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Price [priceId=" + priceId + ", sizeId=" + sizeId + ", pdtId=" + pdtId + ", actualPrice=" + actualPrice
				+ ", currentPrice=" + currentPrice + ", discount=" + discount + ", isActive=" + isActive + "]";
	}

	@Override
	public int compareTo(Price p) {

		if (this.getPriceId() == p.getPriceId()) {
			return 0;
		} else {
			if (this.priceId > getPriceId()) {
				return 1;
			} else {
				return -1;
			}

		}

	}

}
