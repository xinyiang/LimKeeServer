package com.limkee.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.limkee.constant.ErrorConstants;
import com.limkee.dao.*;
import com.limkee.model.*;

public class CatalogueService {
	public static Response doGetProducts (String blogshopId, String categoryId, String sortType) throws Exception{
		if(blogshopId == null || blogshopId.isEmpty()){
			blogshopId = "%";
		}
		
		if(categoryId == null || categoryId.isEmpty()){
			categoryId = "%";
		}
		
		if(sortType == null || sortType.isEmpty()){
			sortType = "latest";
		}
		
		if(sortType.equals("popularity")){
			ArrayList<Product> products = ProductDAO.getProductsSortPopularity(blogshopId, categoryId);
			return Response.status(200).entity(products).build();
		} else if(sortType.equals("pricehighlow")){
			ArrayList<Product> products = ProductDAO.getProductsSortPriceHighLow(blogshopId, categoryId);
			return Response.status(200).entity(products).build();
		} else if(sortType.equals("pricelowhigh")){
			ArrayList<Product> products = ProductDAO.getProductsSortPriceLowHigh(blogshopId, categoryId);
			return Response.status(200).entity(products).build();
		} else{
			ArrayList<Product> products = ProductDAO.getProductsSortLatest(blogshopId, categoryId);
			return Response.status(200).entity(products).build();
		}
	}
	
	public static Response doQueryProducts (String queryStr, List<String> blogshopIds, List<String> categoryIds, String sortType) throws Exception{
		
		if(blogshopIds == null || blogshopIds.isEmpty()){
			blogshopIds = new ArrayList<>();
			blogshopIds.add("%");
		}
		
		if(categoryIds == null || categoryIds.isEmpty()){
			categoryIds = new ArrayList<>();
			categoryIds.add("%");
		}

		if(sortType == null || sortType.equals("")){
			sortType = "latest";
		}
		
		//Changing String[] to ArrayList<String>
		ArrayList<String> queries = new ArrayList<>();
		if(queryStr == null || queryStr.equals("")){
			queryStr = "%";
			queries.add(queryStr);
		} else {
			queryStr = queryStr.trim();
			String[] arrQuery = queryStr.split(" ");
			for(int i=0; i<arrQuery.length; i++){
				queries.add(arrQuery[i]);
			}
		}
		
		if(sortType.equals("popularity")){
			ArrayList<Product> products = ProductDAO.getSearchProductsPopularity(queries, blogshopIds, categoryIds);
			return Response.status(200).entity(products).build();
		} else if(sortType.equals("pricehighlow")){
			ArrayList<Product> products = ProductDAO.getSearchProductsPriceHighLow(queries, blogshopIds, categoryIds);
			return Response.status(200).entity(products).build();
		} else if(sortType.equals("pricelowhigh")){
			ArrayList<Product> products = ProductDAO.getSearchProductsPriceLowHigh(queries, blogshopIds, categoryIds);
			return Response.status(200).entity(products).build();
		} else{
			ArrayList<Product> products = ProductDAO.getSearchProductsLatest(queries, blogshopIds, categoryIds);
			return Response.status(200).entity(products).build();
		}
	}
	
	public static Response doGetProduct (String productId) throws Exception{
		if(productId == null || productId.isEmpty()){
			throw new Exception(ErrorConstants.MISSING_PRODUCTID.getErrorCode());
		}
		
		Product product = ProductDAO.getProductDetails(productId);
		
		ArrayList<Image> images = ImageDAO.getProductImages(productId);
		
		product.setImages(images);
		
		Colour colour = ColourDAO.getProductColour(productId);
		
		product.setColour(colour);
		
		return Response.status(200).entity(product).build();
	}
	
	public static Response doGetBlogshops() throws Exception{
		return Response.status(200).entity(BlogshopDAO.getAllBlogshops()).build();
	}
	
	public static Response doGetCategories() throws Exception{
		return Response.status(200).entity(CategoryDAO.getAllCategories()).build();
	}
}
