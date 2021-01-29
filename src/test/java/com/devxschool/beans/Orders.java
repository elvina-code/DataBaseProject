package com.devxschool.beans;

import lombok.Data;

/***
 *Create a Bean class Orders that has following fields:
 *  id, order_placed_at, order_status, order_updated_at,custom_user_id
 *  Override and Implement equals() , hashcode(), compareTo() methods,
 *  that will help you to perform comparison and sorting operations.
 */
@Data
public class Orders implements Comparable<Orders> {
    private Integer id;
    private String order_placed_at;
    private Integer order_status;
    private String order_updated_at;
    private Integer custom_user_id;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id.equals(orders.id) &&
                order_placed_at.equals(orders.order_placed_at)
                && order_status.equals(orders.order_status)
                && order_updated_at.equals(orders.order_updated_at)
                && custom_user_id.equals(orders.custom_user_id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int compareTo(Orders o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", order_placed_at='" + order_placed_at + '\'' +
                ", order_status=" + order_status +
                ", order_updated_at='" + order_updated_at + '\'' +
                ", custom_user_id=" + custom_user_id +
                '}';
    }
}
