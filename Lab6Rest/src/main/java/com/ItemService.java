package com;

import model.Item;

// for REST service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// for JSON
import com.google.gson.*;

// for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Items")
public class ItemService {
	Item itemObj = new Item();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return itemObj.readItems();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(	@FormParam("itemCode") String itemCode, 
								@FormParam("itemName") String itemName, 
								@FormParam("itemPrice") String itemPrice, 
								@FormParam("itemDesc") String itemDesc) 
	{
		String output = itemObj.insertItem(itemCode, itemName, itemPrice, itemDesc);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData) {
		// convert the input string into JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		
		//Read the values from JSON object
		String itemID = itemObject.get("itemID").getAsString(); 
		String itemCode = itemObject.get("itemCode").getAsString(); 
		String itemName = itemObject.get("itemName").getAsString(); 
		String itemPrice = itemObject.get("itemPrice").getAsString(); 
		String itemDesc = itemObject.get("itemDesc").getAsString();
		
		String output = itemObj.updateItem(itemID, itemCode, itemName, itemPrice, itemDesc); 
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData) {
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		
		//Read the value from the element <itemID>
		String itemID = doc.select("itemID").text();
		String output = itemObj.deleteItem(itemID); 
		
		return output;
	}
}
