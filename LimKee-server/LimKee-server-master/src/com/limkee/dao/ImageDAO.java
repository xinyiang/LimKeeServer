package com.limkee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.limkee.model.Image;
import com.limkee.model.Product;
import com.oss.steve.http.Database;
import com.oss.steve.http.ModelDao;

public class ImageDAO extends ModelDao {
	
	public static Database.ResultSetCallback<Image> defaultCallback = new Database.ResultSetCallback<Image>(){

        @Override
        public Image parse(ResultSet resultSet) throws SQLException {
        	Image image = new Image(resultSet);
        	
        	image.setProductId(resultSet.getInt("product_id"));
        	image.setBlogshopId(resultSet.getInt("product_blogshop_id"));
        	image.setImageUrl(resultSet.getString("image_url"));
        	image.setDisplayImage(resultSet.getBoolean("isdisplayimage"));

            return image;
        }
    };
    
    public static ArrayList<Image> getProductImages(String productId) throws SQLException{
    	String sql = "select * from Image i where i.Product_id = ?";

        ArrayList<Object> params = new ArrayList<>();
        params.add(productId);

        return query(sql, params, defaultCallback);
    }
}
