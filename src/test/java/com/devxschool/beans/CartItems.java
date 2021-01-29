package com.devxschool.beans;

import lombok.Data;
import org.apache.commons.dbutils.BeanProcessor;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*** PLAIN OLD JAVA OBJECT
 *Create a Bean class CartItems that has following fields:
 *  id, quantity, total_price, food_id
 *  Override and Implement equals() , hashcode(), compareTo() methods,
 *  that will help you to perform comparison and sorting operations.
 */
@Data
public class CartItems implements Comparable< CartItems >{
    private Integer id;
    private Integer quantity;
    private Double total_price;
    private Integer food_id;







    @Override
    public int compareTo(CartItems o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItems items = (CartItems) o;
        return id.equals(items.id) &&
                quantity.equals(items.quantity)
                && total_price.equals(items.total_price)
                && food_id.equals(items.food_id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", total_price=" + total_price +
                ", food_id=" + food_id +
                '}';
    }
}
