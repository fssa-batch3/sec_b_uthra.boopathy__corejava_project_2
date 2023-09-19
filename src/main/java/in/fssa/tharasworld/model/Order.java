package in.fssa.tharasworld.model;

import java.time.LocalDate;

public abstract class Order implements Comparable<Order> {

	private int orderId;
	private int priceId;
	private int userId;
	private int sellerId;
	private int addressId;
	private Long phoneNumber;
	private int quantity;
	private OrderStatus orderStatus;
	private String payment;
	private LocalDate orderedDate;
	private LocalDate cancelledAt;
	private LocalDate deliveryDate;
	private boolean isActive;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public LocalDate getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(LocalDate orderedDate) {
		this.orderedDate = orderedDate;
	}

	public LocalDate getCancelledAt() {
		return cancelledAt;
	}

	public void setCancelledAt(LocalDate cancelledAt) {
		this.cancelledAt = cancelledAt;
	}
	
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public int compareTo(Order o) {

		if (this.getOrderId() == o.getOrderId()) {
			return 0;
		} else {
			if (this.orderId > getOrderId()) {
				return 1;
			} else {
				return -1;
			}

		}

	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", priceId=" + priceId + ", userId=" + userId + ", sellerId=" + sellerId
				+ ", addressId=" + addressId + ", phoneNumber=" + phoneNumber + ", quantity=" + quantity
				+ ", orderStatus=" + orderStatus + ", payment=" + payment + ", orderedDate=" + orderedDate
				+ ", cancelledAt=" + cancelledAt + ", deliveryDate=" + deliveryDate + ", isActive=" + isActive + "]";
	}

}
