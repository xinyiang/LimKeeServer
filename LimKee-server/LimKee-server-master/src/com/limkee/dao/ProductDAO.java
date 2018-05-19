package com.limkee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.limkee.model.Product;
import com.oss.steve.http.Database;
import com.oss.steve.http.ModelDao;

public class ProductDAO extends ModelDao {
	
	public static Database.ResultSetCallback<Product> listCallback = new Database.ResultSetCallback<Product>(){

        @Override
        public Product parse(ResultSet resultSet) throws SQLException {
        	Product product = new Product(resultSet);
        	
        	product.setName(resultSet.getString("name"));
        	product.setUrl(resultSet.getString("url"));
        	product.setPrice(resultSet.getDouble("price"));
        	product.setDescription(resultSet.getString("description"));
        	product.setBlogshopId(resultSet.getInt("blogshop_id"));
        	product.setCategoryId(resultSet.getInt("category_id"));
        	product.setNumOfClicks(resultSet.getInt("num_of_clicks"));
        	product.setDiscount(resultSet.getDouble("discount"));
        	product.setImageUrl(resultSet.getString("image_url"));
        	product.setBlogshopName(resultSet.getString("blogshop_name"));

            return product;
        }
    };
    
    public static Database.ResultSetCallback<Product> defaultCallback = new Database.ResultSetCallback<Product>(){

        @Override
        public Product parse(ResultSet resultSet) throws SQLException {
        	Product product = new Product(resultSet);
        	
        	product.setName(resultSet.getString("name"));
        	product.setUrl(resultSet.getString("url"));
        	product.setPrice(resultSet.getDouble("price"));
        	product.setDescription(resultSet.getString("description"));
        	product.setBlogshopId(resultSet.getInt("blogshop_id"));
        	product.setCategoryId(resultSet.getInt("category_id"));
        	product.setNumOfClicks(resultSet.getInt("num_of_clicks"));
        	product.setDiscount(resultSet.getDouble("discount"));
        	product.setBlogshopName(resultSet.getString("blogshop_name"));
        	
            return product;
        }
    };
    
    public static ArrayList<Product> getProductsSortPriceHighLow(String blogshopId, String categoryId) throws SQLException {
        String sql = "select pbs.*, i.image_url from"
        		+ " (select p.*, bs.name as blogshop_name from Product p inner join Blogshop bs on p.Blogshop_id = bs.id) pbs"
        		+ " left join (select * from Image where isDisplayImage = 1) i on pbs.id = i.Product_id"
        		+ " where Blogshop_id like ? and Category_id like ? order by pbs.Price desc";

        ArrayList<Object> params = new ArrayList<>();
        params.add(blogshopId);
        params.add(categoryId);

        return query(sql, params, listCallback);
    }
    
    public static ArrayList<Product> getProductsSortPriceLowHigh(String blogshopId, String categoryId) throws SQLException {
    	String sql = "select pbs.*, i.image_url from"
        		+ " (select p.*, bs.name as blogshop_name from Product p inner join Blogshop bs on p.Blogshop_id = bs.id) pbs"
        		+ " left join (select * from Image where isDisplayImage = 1) i on pbs.id = i.Product_id"
        		+ " where Blogshop_id like ? and Category_id like ? order by pbs.Price";

        ArrayList<Object> params = new ArrayList<>();
        params.add(blogshopId);
        params.add(categoryId);

        return query(sql, params, listCallback);
    }
    
    public static ArrayList<Product> getProductsSortLatest(String blogshopId, String categoryId) throws SQLException {
    	String sql = "select pbs.*, i.image_url from"
        		+ " (select p.*, bs.name as blogshop_name from Product p inner join Blogshop bs on p.Blogshop_id = bs.id) pbs"
        		+ " left join (select * from Image where isDisplayImage = 1) i on pbs.id = i.Product_id"
        		+ " where Blogshop_id like ? and Category_id like ? order by pbs.created_at desc";

        ArrayList<Object> params = new ArrayList<>();
        params.add(blogshopId);
        params.add(categoryId);

        return query(sql, params, listCallback);
    }
    
    public static ArrayList<Product> getProductsSortPopularity(String blogshopId, String categoryId) throws SQLException {
    	String sql = "select pbs.*, i.image_url from"
        		+ " (select p.*, bs.name as blogshop_name from Product p inner join Blogshop bs on p.Blogshop_id = bs.id) pbs"
        		+ " left join (select * from Image where isDisplayImage = 1) i on pbs.id = i.Product_id"
        		+ " where Blogshop_id like ? and Category_id like ? order by pbs.num_of_clicks desc";

        ArrayList<Object> params = new ArrayList<>();
        params.add(blogshopId);
        params.add(categoryId);

        return query(sql, params, listCallback);
    }
    
    public static Product getProductDetails(String productId) throws SQLException {
        String sql = "select * from (select p.*, bs.name as blogshop_name from Product p inner join Blogshop bs on p.Blogshop_id = bs.id) pbs where pbs.id = ?";

        ArrayList<Object> params = new ArrayList<>();
        params.add(productId);
        
        ArrayList<Product> products = query(sql, params, defaultCallback);
        
        return products.size() == 1 ? products.get(0) : null;
    }
    
    public static ArrayList<Product> getProductsInBudget(String budgetId) throws SQLException {
        String sql = "select pbs.*, i.image_url from"
        		+ " ((select p.*, bs.name as blogshop_name from Product p inner join Blogshop bs on p.Blogshop_id = bs.id) pbs"
        		+ " left join (select * from Image where isDisplayImage = 1) i on pbs.id = i.Product_id)"
        		+ " inner join Budget_has_Product bp on pbs.id = bp.Product_id where bp.budget_id =  ?";

        ArrayList<Object> params = new ArrayList<>();
        params.add(budgetId);
        
        return query(sql, params, listCallback);
    }
    
    public static ArrayList<Product> getAllWishlistProducts(String wishlistId)  throws SQLException {
        String sql = "select pbs.*, i.image_url from"
        		+ " ((select p.*, bs.name as blogshop_name from Product p inner join Blogshop bs on p.Blogshop_id = bs.id) pbs"
        		+ " left join (select * from Image where isDisplayImage = 1) i on pbs.id = i.Product_id)"
        		+ " inner join Product_has_Wishlist wp on pbs.id = wp.Product_id where wp.Wishlist_id = ? order by wp.created_at desc";

        ArrayList<Object> params = new ArrayList<>();
        params.add(wishlistId);
        
        return query(sql, params, listCallback);
    }
    
    public static ArrayList<Product> getSearchProductsLatest(List<String> queries, List<String> blogshopIds, List<String> categoryIds)  throws SQLException {
        String sql = "select pbs.*, i.image_url from"
        		+ " (select p.*, bs.name as blogshop_name from Product p inner join Blogshop bs on p.Blogshop_id = bs.id) pbs"
        		+ " left join (select * from Image where isDisplayImage = 1) i on pbs.id = i.Product_id"
        		+ " where (pbs.name like ?";

        ArrayList<Object> params = new ArrayList<>();
        String firstQuery = "%" + queries.get(0) + "%";
        params.add(firstQuery);
        
        if(hasIndexCheck(queries, (1))){
    		sql += " or pbs.name like ?";
    	}
        
        for(int i=1; i < queries.size(); i++){
        	String query = "%" + queries.get(i) + "%";
        	System.out.println("query" + i + " [" + query + "]");
        	params.add(query);
        	if(hasIndexCheck(queries, (i+1))){
        		sql += " or pbs.name like ?";
        	}
        }
        sql += ") and ((pbs.Blogshop_id like ?";
        
        params.add(blogshopIds.get(0));
        
        if(hasIndexCheck(blogshopIds, (1))){
    		sql += " or pbs.Blogshop_id like ?";
    	}
        
        for(int i=1; i < blogshopIds.size(); i++){
        	String bsId = "%" + blogshopIds.get(i) + "%";
        	System.out.println("bsId" + i + " [" + bsId + "]");
        	params.add(bsId);
        	if(hasIndexCheck(blogshopIds, (i+1))){
        		sql += " or pbs.Blogshop_id like ?";
        	}
        }
        
        sql += ") and (pbs.Category_id like ?";
        
        params.add(categoryIds.get(0));
        
        if(hasIndexCheck(categoryIds, (1))){
    		sql += " or pbs.Category_id like ?";
    	}
        
        for(int i=1; i < categoryIds.size(); i++){
        	String catId = "%" + categoryIds.get(i) + "%";
        	System.out.println("catId" + i + " [" + catId + "]");
        	params.add(catId);
        	if(hasIndexCheck(categoryIds, (i+1))){
        		sql += " or pbs.Category_id like ?";
        	}
        }
        
        sql += ")) order by pbs.created_at desc";
        return query(sql, params, listCallback);
    }
    
    public static ArrayList<Product> getSearchProductsPopularity(List<String> queries, List<String> blogshopIds, List<String> categoryIds)  throws SQLException {
        String sql = "select pbs.*, i.image_url from"
        		+ " (select p.*, bs.name as blogshop_name from Product p inner join Blogshop bs on p.Blogshop_id = bs.id) pbs"
        		+ " left join (select * from Image where isDisplayImage = 1) i on pbs.id = i.Product_id"
        		+ " where (pbs.name like ?";

        ArrayList<Object> params = new ArrayList<>();
        String firstQuery = "%" + queries.get(0) + "%";
        params.add(firstQuery);
        
        if(hasIndexCheck(queries, (1))){
    		sql += " or pbs.name like ?";
    	}
        
        for(int i=1; i < queries.size(); i++){
        	String query = "%" + queries.get(i) + "%";
        	System.out.println("query" + i + " [" + query + "]");
        	params.add(query);
        	if(hasIndexCheck(queries, (i+1))){
        		sql += " or pbs.name like ?";
        	}
        }
        sql += ") and ((pbs.Blogshop_id like ?";
        
        params.add(blogshopIds.get(0));
        
        if(hasIndexCheck(blogshopIds, (1))){
    		sql += " or pbs.Blogshop_id like ?";
    	}
        
        for(int i=1; i < blogshopIds.size(); i++){
        	String bsId = "%" + blogshopIds.get(i) + "%";
        	System.out.println("bsId" + i + " [" + bsId + "]");
        	params.add(bsId);
        	if(hasIndexCheck(blogshopIds, (i+1))){
        		sql += " or pbs.Blogshop_id like ?";
        	}
        }
        
        sql += ") and (pbs.Category_id like ?";
        
        params.add(categoryIds.get(0));
        
        if(hasIndexCheck(categoryIds, (1))){
    		sql += " or pbs.Category_id like ?";
    	}
        
        for(int i=1; i < categoryIds.size(); i++){
        	String catId = "%" + categoryIds.get(i) + "%";
        	System.out.println("catId" + i + " [" + catId + "]");
        	params.add(catId);
        	if(hasIndexCheck(categoryIds, (i+1))){
        		sql += " or pbs.Category_id like ?";
        	}
        }
        
        sql += ")) order by pbs.num_of_clicks desc";
        return query(sql, params, listCallback);
    }
    
    public static ArrayList<Product> getSearchProductsPriceHighLow(List<String> queries, List<String> blogshopIds, List<String> categoryIds)  throws SQLException {
        String sql = "select pbs.*, i.image_url from"
        		+ " (select p.*, bs.name as blogshop_name from Product p inner join Blogshop bs on p.Blogshop_id = bs.id) pbs"
        		+ " left join (select * from Image where isDisplayImage = 1) i on pbs.id = i.Product_id"
        		+ " where (pbs.name like ?";

        ArrayList<Object> params = new ArrayList<>();
        String firstQuery = "%" + queries.get(0) + "%";
        params.add(firstQuery);
        
        if(hasIndexCheck(queries, (1))){
    		sql += " or pbs.name like ?";
    	}
        
        for(int i=1; i < queries.size(); i++){
        	String query = "%" + queries.get(i) + "%";
        	System.out.println("query" + i + " [" + query + "]");
        	params.add(query);
        	if(hasIndexCheck(queries, (i+1))){
        		sql += " or pbs.name like ?";
        	}
        }
        sql += ") and ((pbs.Blogshop_id like ?";
        
        params.add(blogshopIds.get(0));
        
        if(hasIndexCheck(blogshopIds, (1))){
    		sql += " or pbs.Blogshop_id like ?";
    	}
        
        for(int i=1; i < blogshopIds.size(); i++){
        	String bsId = "%" + blogshopIds.get(i) + "%";
        	System.out.println("bsId" + i + " [" + bsId + "]");
        	params.add(bsId);
        	if(hasIndexCheck(blogshopIds, (i+1))){
        		sql += " or pbs.Blogshop_id like ?";
        	}
        }
        
        sql += ") and (pbs.Category_id like ?";
        
        params.add(categoryIds.get(0));
        
        if(hasIndexCheck(categoryIds, (1))){
    		sql += " or pbs.Category_id like ?";
    	}
        
        for(int i=1; i < categoryIds.size(); i++){
        	String catId = "%" + categoryIds.get(i) + "%";
        	System.out.println("catId" + i + " [" + catId + "]");
        	params.add(catId);
        	if(hasIndexCheck(categoryIds, (i+1))){
        		sql += " or pbs.Category_id like ?";
        	}
        }
        
        sql += ")) order by pbs.Price desc";
        return query(sql, params, listCallback);
    }
    
    public static ArrayList<Product> getSearchProductsPriceLowHigh(List<String> queries, List<String> blogshopIds, List<String> categoryIds)  throws SQLException {
        String sql = "select pbs.*, i.image_url from"
        		+ " (select p.*, bs.name as blogshop_name from Product p inner join Blogshop bs on p.Blogshop_id = bs.id) pbs"
        		+ " left join (select * from Image where isDisplayImage = 1) i on pbs.id = i.Product_id"
        		+ " where (pbs.name like ?";

        ArrayList<Object> params = new ArrayList<>();
        String firstQuery = "%" + queries.get(0) + "%";
        params.add(firstQuery);
        
        if(hasIndexCheck(queries, (1))){
    		sql += " or pbs.name like ?";
    	}
        
        for(int i=1; i < queries.size(); i++){
        	String query = "%" + queries.get(i) + "%";
        	System.out.println("query" + i + " [" + query + "]");
        	params.add(query);
        	if(hasIndexCheck(queries, (i+1))){
        		sql += " or pbs.name like ?";
        	}
        }
        sql += ") and ((pbs.Blogshop_id like ?";
        
        params.add(blogshopIds.get(0));
        
        if(hasIndexCheck(blogshopIds, (1))){
    		sql += " or pbs.Blogshop_id like ?";
    	}
        
        for(int i=1; i < blogshopIds.size(); i++){
        	String bsId = "%" + blogshopIds.get(i) + "%";
        	System.out.println("bsId" + i + " [" + bsId + "]");
        	params.add(bsId);
        	if(hasIndexCheck(blogshopIds, (i+1))){
        		sql += " or pbs.Blogshop_id like ?";
        	}
        }
        
        sql += ") and (pbs.Category_id like ?";
        
        params.add(categoryIds.get(0));
        
        if(hasIndexCheck(categoryIds, (1))){
    		sql += " or pbs.Category_id like ?";
    	}
        
        for(int i=1; i < categoryIds.size(); i++){
        	String catId = "%" + categoryIds.get(i) + "%";
        	System.out.println("catId" + i + " [" + catId + "]");
        	params.add(catId);
        	if(hasIndexCheck(categoryIds, (i+1))){
        		sql += " or pbs.Category_id like ?";
        	}
        }
        
        sql += ")) order by pbs.Price";
        return query(sql, params, listCallback);
    }
    
    private static boolean hasIndexCheck(List<String> list, int index){
	    if(index < list.size()){
	    	return true;
	    }
	    return false;
	}
}
