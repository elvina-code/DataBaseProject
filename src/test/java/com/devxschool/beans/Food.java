package com.devxschool.beans;

import lombok.Data;


/***
 *Create a Bean class Food that has following fields:
 *  id, description, food_type, image_url,name,price
 *  Override and Implement equals() , hashcode(), compareTo() methods,
 *  that will help you to perform comparison and sorting operations.
 */
@Data
public class Food implements Comparable< Food > {
    private Integer id;
    private String description;
    private Integer food_type;
    private String image_url;
    private String name;
    private Double price;



    @Override
    public int compareTo(Food o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return id.equals(food.id) &&
                description.equals(food.description)
                && food_type.equals(food.food_type)
                && image_url.equals(food.image_url)
                && name.equals(food.name)
                && price.equals(food.price);
    }

    @Override
    public int hashCode() {
        return id;
    }

    //To see the data in Error messages
    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", food_type=" + food_type +
                ", image_url='" + image_url + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
