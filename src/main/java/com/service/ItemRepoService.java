package com.service;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.model.Item;

@Service
public class ItemRepoService {
	
	@Autowired
	private com.dao.ItemRepository itemRepository;
	public ResponseEntity<?> getItemList()
	{
		return new ResponseEntity<>(itemRepository.findAll(),HttpStatus.OK);
				}
	
	public ResponseEntity<?> addItem(Item item)
	{
		
		itemRepository.save(item);
		return new ResponseEntity<SuccessResponse>(new SuccessResponse("item addedd successfully"),HttpStatus.OK);
	}
	
	public ResponseEntity<?> updateItem(Item item) 
	{
		Item item_exists=findItem(item.getItemId()).getBody();
		if(item_exists!=null)
		{
		itemRepository.save(item);
		
		return new ResponseEntity<SuccessResponse>(new SuccessResponse("item updated successfully"),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<SuccessResponse>(new SuccessResponse("item does not exist"),HttpStatus.OK);
		}
		}
	
	public ResponseEntity<Item> findItem(int id)
	{
		Item item=itemRepository.findById(id).get();
		return new ResponseEntity<Item>(item,HttpStatus.OK);
	}
	public ResponseEntity<?> deleteItem(int id)
	{ 
		Item item=findItem(id).getBody();
		itemRepository.delete(item);
		return new ResponseEntity<SuccessResponse>(new SuccessResponse("item deleted successfully"),HttpStatus.OK);
	}

	 public ResponseEntity<Item> findByName(String itemName)
	 { 
		 Item item=itemRepository.findByItemName(itemName).get();
		 return new ResponseEntity<Item>(item,HttpStatus.OK);
	 }
}
