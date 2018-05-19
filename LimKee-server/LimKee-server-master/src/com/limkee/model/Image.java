package com.limkee.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.oss.steve.http.Model;

public class Image extends Model {
	
	private int productId;
	private int blogshopId;
	private String imageUrl;
	private boolean isDisplayImage;
	
    public Image() throws SQLException {
        super("Image");
    }

    public Image(ResultSet resultSet) throws SQLException {
        super("Image", resultSet);
    }

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getBlogshopId() {
		return blogshopId;
	}

	public void setBlogshopId(int blogshopId) {
		this.blogshopId = blogshopId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isDisplayImage() {
		return isDisplayImage;
	}

	public void setDisplayImage(boolean isDisplayImage) {
		this.isDisplayImage = isDisplayImage;
	}
}
