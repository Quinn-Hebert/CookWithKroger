package com.cookwithkroger.rev2019.model;

import java.util.List;
import java.util.Map;

public class Recipe {
	
	private int recipeId;
	private String name;
	private String description;
	private String category;
	private List<String> instructions;
	private Map<Product, Integer> ingredients;
	private int cookTime;
	private double price;
	private String recipe_image;
	private String recipe_video;
	
	public Recipe() {
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRecipeImage() {
		return recipe_image;
	}
	
	public void setRecipeImage(String recipe_image) {
		this.recipe_image = recipe_image;
	}
	
	public String getRecipeVideo() {
		return recipe_video;
	}
	
	public void setRecipeVideo(String recipe_video) {
		this.recipe_video = recipe_video;
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

	public List<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Map<Product, Integer> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Map<Product, Integer> ingredients) {
		this.ingredients = ingredients;
	}

	public int getCookTime() {
		return cookTime;
	}

	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}
}
