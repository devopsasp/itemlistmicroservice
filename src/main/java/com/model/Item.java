package com.model;

import jakarta.persistence.*;
@Entity
@Table(name="itemtable")
public class Item {

	@Id
	@GeneratedValue
	private int itemId;
	private String itemName;
	private float price;
	
	public Item()
	{}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", price=" + price + "]";
	}
	
	
}
