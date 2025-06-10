package com.cdac.acts.POJO;

import java.io.Serializable;

public class Product implements Serializable{
	int categoryId;
	int productId;
	int quantity;
	int price;
		
	public Product() {
		super();
	}

	public Product(int categoryId, int productId, int quantity, int price) {
		super();
		this.categoryId = categoryId;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [categoryId=" + categoryId + ", productId=" + productId + ", quantity=" + quantity + ", price="
				+ price + "]";
	}
	
	
}
