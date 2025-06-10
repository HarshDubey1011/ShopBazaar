package com.cdac.acts.cart;

import java.util.Iterator;

import com.cdac.acts.POJO.Product;

public interface Cart {
	public void addToCart(Product objProduct);
	public void removeFromCart(int categoryId, int productId);
	public Iterator<Product> getAllItems() throws CartException;
}
