package com.cdac.acts.cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cdac.acts.POJO.Product;

public class ShoppingCart implements Cart {

	List<Product> allItems = new ArrayList<>();
	@Override
	public void addToCart(Product objProduct) {
		// TODO Auto-generated method stub
		allItems.add(objProduct);
	}

	@Override
	public void removeFromCart(int categoryId, int productId) {
		// TODO Auto-generated method stub
		allItems.remove(productId);
	}

	@Override
	public Iterator<Product> getAllItems() throws CartException {
		// TODO Auto-generated method stub
		if(allItems.size() > 0) {
			return allItems.iterator();
		}
		else {
			throw new CartException("Not items in the cart");
		}
	
	}
	
}
