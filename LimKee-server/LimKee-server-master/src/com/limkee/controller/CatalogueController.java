package com.limkee.controller;

import io.swagger.annotations.*;

import com.limkee.model.*;
import com.limkee.service.*;
import com.limkee.utility.*;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "Catalogue", description = "Cataloging of products")
@Path("/catalogue")
@Produces({"application/json"})
public class CatalogueController {
	@POST
    @Path("/get-catalogue")
    @ApiOperation(value = "Get All Products for Catalogue",
            notes = "Retrieval of all products for catalogue. Returns an array of Product object.",
            response = Product.class)
	@ApiParam
    @ApiResponses(value = {
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response doGetProducts(@ApiParam(value = "ID of the blogshop", required = false) @FormParam("blogshopId") String blogshopId,
    							  @ApiParam(value = "ID of the category", required = false) @FormParam("categoryId") String categoryId,
                                  @ApiParam(value = "Sort Type: latest (by default) / popularity / pricehighlow / pricelowhigh", required = false) @FormParam("sortType") String sortType) {

        try {
            return CatalogueService.doGetProducts(blogshopId, categoryId, sortType);
        } catch (Exception e){
            return ExceptionHandler.handleException(e);
        }
    }
	
	@POST
    @Path("/get-product")
    @ApiOperation(value = "Get Product Detail",
            notes = "Retrieval of product details. Returns a Product object.",
            response = Product.class)
    @ApiParam
    @ApiResponses(value = {
    		@ApiResponse(code = 101, message = "Missing Product ID")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response doGetProduct(@ApiParam(value = "", required = true) @FormParam("productId") String productId) {

        try {
            return CatalogueService.doGetProduct(productId);
        } catch (Exception e){
            return ExceptionHandler.handleException(e);
        }
    }
	
	@POST
    @Path("/search-catalogue")
    @ApiOperation(value = "Search for Products in Catalogue",
            notes = "Retrieval of all products based on the search query within the catalogue. Returns an array of Product object.",
            response = Product.class)
	@ApiParam
    @ApiResponses(value = {
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response doQueriedProducts(@ApiParam(value = "Search query entered", required = false) @FormParam("query") String query,
    								  @ApiParam(value = "ID of the blogshop", required = false) @FormParam("blogshopId") List<String> blogshopIds,
    								  @ApiParam(value = "ID of the category", required = false) @FormParam("categoryId") List<String> categoryIds,
    								  @ApiParam(value = "Sort Type: latest (by default) / popularity / pricehighlow / pricelowhigh", required = false) 
    								  		@FormParam("sortType") String sortType) {

        try {
            return CatalogueService.doQueryProducts(query, blogshopIds, categoryIds, sortType);
        } catch (Exception e){
            return ExceptionHandler.handleException(e);
        }
    }
	
	@POST
    @Path("/get-blogshops")
    @ApiOperation(value = "Retrieve of all blogshops' details",
            notes = "Retrieval of all blogshops' details available within the catalogue. Returns an array of Blogshop object.",
            response = Blogshop.class)
	@ApiParam
    @ApiResponses(value = {
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response doGetBlogshops() {

        try {
            return CatalogueService.doGetBlogshops();
        } catch (Exception e){
            return ExceptionHandler.handleException(e);
        }
    }
	
	@POST
    @Path("/get-categories")
    @ApiOperation(value = "Retrieve of all categories",
            notes = "Retrieval of all categories' details available within the catalogue. Returns an array of Category object.",
            response = Category.class)
	@ApiParam
    @ApiResponses(value = {
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response doGetCategories() {

        try {
            return CatalogueService.doGetCategories();
        } catch (Exception e){
            return ExceptionHandler.handleException(e);
        }
    }
}
