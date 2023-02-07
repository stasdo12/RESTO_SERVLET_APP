package com.epam.donetc.restaurant.database.entity;


/**
 * Category entity enum. Matches table 'category' in database.
 *
 * @author Stanislav Donetc
 * @version 1.0
 */
public enum Category  {
    PIZZA(1,"Pizza"),
    SUSHI(2,"Sushi"),
    BURGER(3, "Burger"),
    DRINK(4, "Drink"),
    SALAD(5, "Salad"),
    DESSERT(6,"Dessert");
    private final int id;
    private final String name;

    Category(int id, String name) {
        this.id = id;
        this.name = name;
        }

    public static Category getCategoryById(int id){
        for (Category c : values()) {
            if (c.id == id){
                return c;
            }
        }
        return null;
    }

    public static Category getCategoryByName(String name){
        for (Category c :values()) {
            if (c.name.equals(name)){
                return c;
            }

        }
        return null;
    }

    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return getName();
    }
}