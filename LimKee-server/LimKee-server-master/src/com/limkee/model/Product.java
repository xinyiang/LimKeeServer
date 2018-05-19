package com.limkee.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oss.steve.http.Model;

public class Product extends Model {
	
	private String name;
	private String url;
	private double price;
	private String description;
	private int blogshopId;
	private int categoryId;
	private int numOfClicks;
	private double discount;
	private String imageUrl;
	
	private ArrayList<Image> images;
	private Colour colour;
	private String blogshopName;
	
	
    public Product() throws SQLException {
        super("Product");
    }

    public Product(ResultSet resultSet) throws SQLException {
        super("Product", resultSet);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBlogshopId() {
		return blogshopId;
	}

	public void setBlogshopId(int blogshopId) {
		this.blogshopId = blogshopId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getNumOfClicks() {
		return numOfClicks;
	}

	public void setNumOfClicks(int numOfClicks) {
		this.numOfClicks = numOfClicks;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public ArrayList<Image> getImages() {
		return images;
	}

	public void setImages(ArrayList<Image> images) {
		this.images = images;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public String getBlogshopName() {
		return blogshopName;
	}

	public void setBlogshopName(String blogshopName) {
		this.blogshopName = blogshopName;
	}

	
}
