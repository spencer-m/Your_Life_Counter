package com.group19.seng301_w2016.yourlifecounter;



public class Food implements Comparable<Food> {


	private String name;
	private String foodCategory;
	private String caloriesPerServing;
	private String servingSize;
	private String servingUnit;
	//private HashMap<String, Integer> nutrients;


	public Food() {}

	/**
	 * name, food group, calories per serving and serving size are required at the creation of the food
	 */
	public Food(String xname, String xfoodCategory, String xservingSize, String xservingUnit, String xcaloriesPerServing) {

		name = xname;
		foodCategory = xfoodCategory;
		caloriesPerServing = xcaloriesPerServing;
		servingSize = xservingSize;
		servingUnit = xservingUnit;

		//nutrients = new HashMap<String, Integer>();

	}
	public Food(Food f) {

		name = f.getName();
		foodCategory = f.getFoodCategory();
		caloriesPerServing = f.getCaloriesPerServing();
		servingSize = f.getServingSize();
		servingUnit = f.getServingUnit();
	}

	public String toString() {

		String theString = name + "\t" + foodCategory + "\t" + servingSize + "\t" + servingUnit + "\t" + caloriesPerServing;

		//for (String x : nutrients.keySet())
		//	theString = theString + x + "\t" + nutrients.get(x) + "\t" ;

		theString = theString + "\n";

		return theString;
	}

	public String getFoodCategory() {

		return new String(foodCategory);
	}
	public String getName() {

		return new String(name);
	}

	public String getCaloriesPerServing() {

		return new String(caloriesPerServing);
	}

	public String getServingSize() {

		return new String(servingSize);
	}

	public String getServingUnit() {

		return new String(servingUnit);
	}

	public void setFoodCategory(String s) {

		foodCategory = s;
	}
	public void setName(String s) {

		name = s;
	}

	public void setCaloriesPerServing(String s) {

		caloriesPerServing = s;
	}

	public void setServingSize(String s) {

		servingSize = s;
	}

	public void setServingUnit(String s) {

		servingUnit = s;
	}

	public boolean equals(Object obj) {

		if (!(obj instanceof Food)) {
			return false;
		}
		Food food = (Food) obj;

		if (food.getName().toLowerCase().equals(name.toLowerCase()) && food.getFoodCategory().equals(foodCategory) && food.getCaloriesPerServing().equals(caloriesPerServing) && food.getServingSize().equals(servingSize) && food.getServingUnit().equals(servingUnit)) {
			return true;
		}
		return false;
	}

	public int hashCode() {

		String nameTemp = name.toLowerCase();
		int result=0;

		for(int i=0; i < nameTemp.length(); i++){

			result = result + nameTemp.charAt(i);

		}
		return result;
	}

	public int compareTo(Food food) {

		return name.compareTo(food.getName());
	}

}