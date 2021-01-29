package com.devxschool.steps;

import com.devxschool.beans.CartItems;
import com.devxschool.beans.Food;
import com.devxschool.beans.Orders;
import com.devxschool.utils.beanutils.BeanHelper;
import com.devxschool.utils.db.DataBaseUtils;
import com.mysql.cj.Query;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Da;
import org.junit.Assert;



import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataBaseValidationSteps {

    //this is needed for deleting related table with primary and foreign key.
    private static final String DISABLE_FOREIGNKEY_CHECKS_QUERY = "SET FOREIGN_KEY_CHECKS = 0;";
    private static final String ENABLE_FOREIGNKEY_CHECKS_QUERY = "SET FOREIGN_KEY_CHECKS = 1;";

    private String query;
    private List<Map<String, Object>> dbResulSet;

    @Given("^remove all records from \"([^\"]*)\" table$")
    public void remove_all_records_from_cart_item_table(String data) throws Throwable {
        DataBaseUtils.executeQuery(DISABLE_FOREIGNKEY_CHECKS_QUERY); // unrelated to other tables
        query = "TRUNCATE table "+data+";";
        DataBaseUtils.executeQuery(query);
        DataBaseUtils.executeQuery(ENABLE_FOREIGNKEY_CHECKS_QUERY);
        query = "SELECT * from "+data+";";
        dbResulSet = DataBaseUtils.executeQuery(query);
        Assert.assertTrue(dbResulSet.isEmpty());


    }

    @When("^add new data into the cart_items table$") // also for insert we need  DISABLE_FOREIGNKEY_CHECKS_QUERY
    public void add_new_data_into_the_cart_items_table(List<CartItems> items) throws Throwable {
        DataBaseUtils.executeQuery(DISABLE_FOREIGNKEY_CHECKS_QUERY); // removing primary key to inserting some data
        query = "INSERT INTO cart_item VALUES(?,?,?,?);"; // inserts from varargs
        for(CartItems cart_item: items){ //
            DataBaseUtils.executeInsert(query, cart_item, BeanHelper.getBeanPropertyNames(CartItems.class));//
        }
        DataBaseUtils.executeQuery(ENABLE_FOREIGNKEY_CHECKS_QUERY);
    }

    // this is easiest way :

//    @When("^add new data into the cart_items table$")
//    public void add_new_data_into_the_cart_items_table(List<CartItems> items) throws Throwable {
//        DataBaseUtils.executeQuery(DISABLE_FOREIGNKEY_CHECKS_QUERY);
//        for (CartItems cart_item : items) {
//            query = "INSERT INTO cart_item VALUES("+cart_item.getId()+", "
//                    +cart_item.getQuantity()+", "+cart_item.getTotal_price()+", "+cart_item.getFood_id()+");";
//            DataBaseUtils.executeQuery(query);
//        }
//        DataBaseUtils.executeQuery(ENABLE_FOREIGNKEY_CHECKS_QUERY);
//    }


    @Then("^verify that new data has been inserted into cart_item$")
    public void verify_that_new_data_has_been_inserted(List<CartItems> items) throws Throwable {
        query = "SELECT* from cart_item order by id;";
        List<CartItems> listItemsFromDB = DataBaseUtils.executeQueryToBean(CartItems.class, query);

        List<CartItems> itemsExpected = new ArrayList<>(items);
        Collections.sort(listItemsFromDB);
        Collections.sort(itemsExpected);
        Assert.assertEquals(listItemsFromDB.size(), itemsExpected.size());
        Assert.assertEquals(listItemsFromDB, itemsExpected);

    }

    @When("^add new data into the food table$")
    public void add_new_data_into_the_food_table(List<Food> items) throws Throwable {
        DataBaseUtils.executeQuery(DISABLE_FOREIGNKEY_CHECKS_QUERY); // removing primary key to inserting some data
        query = "INSERT INTO food VALUES(?,?,?,?,?,?);"; // inserts from varargs
        for(Food food: items){ //
            DataBaseUtils.executeInsert(query, food, BeanHelper.getBeanPropertyNames(Food.class));//
        }
        DataBaseUtils.executeQuery(ENABLE_FOREIGNKEY_CHECKS_QUERY);
    }

    @Then("^verify that (?:new data|data) has been (?:inserted|updated|removed) into food table$")
    public void verify_that_new_data_has_been_inserted_into_food_table(List<Food> items) throws Throwable {
        query = "SELECT* from food order by id;";
        List<Food> listItemsFromDB = DataBaseUtils.executeQueryToBean(Food.class, query);

        List<Food> itemsExpected = new ArrayList<>(items);
        Collections.sort(listItemsFromDB);
        Collections.sort(itemsExpected);
        Assert.assertEquals(listItemsFromDB.size(), itemsExpected.size());
        Assert.assertEquals(listItemsFromDB, itemsExpected);
    }

    @When("^add (?:new data|data) into the orders table$")
    public void add_new_data_into_the_orders_table(List<Orders> items) throws Throwable {
        DataBaseUtils.executeQuery(DISABLE_FOREIGNKEY_CHECKS_QUERY); // removing primary key to inserting some data
        query = "INSERT INTO orders VALUES(?,?,?,?,?);"; // inserts from varargs
        for(Orders order: items){ //
            DataBaseUtils.executeInsert(query, order, BeanHelper.getBeanPropertyNames(Orders.class));//
        }
        DataBaseUtils.executeQuery(ENABLE_FOREIGNKEY_CHECKS_QUERY);
    }

    @Then("^verify that (?:new data|data) has been (?:inserted|updated|removed) into order table$")
    public void verify_that_new_data_has_been_inserted_into_order_table(List<Orders> items) throws Throwable {
        query = "SELECT* from orders order by id;";
        List<Orders> listOrderFromDB = DataBaseUtils.executeQueryToBean(Orders.class, query);

        List<Orders> orderExpected = new ArrayList<>(items);
        Collections.sort(listOrderFromDB);
        Collections.sort(orderExpected);
        Assert.assertEquals(orderExpected.size(),listOrderFromDB.size());
        Assert.assertEquals(orderExpected, listOrderFromDB );
    }

    @Then("^update price to \"([^\"]*)\" into the food table id \"([^\"]*)\"$")
    public void update_price_to_into_the_food_table_id(Double price, int id)throws Throwable {
        query = "UPDATE food SET price = ? WHERE id = ?;";

        DataBaseUtils.executeUpdate(query,price,id);

        query = "SELECT * from  food WHERE id = " +id+ ";";

        List<Food> actual = DataBaseUtils.executeQueryToBean(Food.class, query);
        for(Food food: actual){
            if(food.getId() == id){
                Assert.assertEquals("Price didn't match", price,food.getPrice());
                break;
            }
        }
    }

    @Then("^remove record with id \"([^\"]*)\"$")
    public void remove_record_with_id(String id) throws Throwable {
        query = "DELETE from food WHERE id = " + id + ";";

        DataBaseUtils.executeQuery(query);

        query = "SELECT * from food WHERE id = " + id + ";";

        List<Food> listOfFoods = DataBaseUtils.executeQueryToBean(Food.class,query);
//        for(Food f: listOfFoods) {
//                Assert.assertFalse("id is exist",listOfFoods.contains(f)); // several id's
//            }
        Assert.assertTrue(listOfFoods.isEmpty());// single id
    }




    @Then("^update order status to \"([^\"]*)\" in orders table based on id \"([^\"]*)\"$")
    public void update_order_status_in_orders_table_based_on_id(int order_status,int id) throws Throwable {
        query = "UPDATE orders SET order_status = ? WHERE id = ?;";
        DataBaseUtils.executeUpdate(query,order_status,id);
        query = "SELECT * from  orders WHERE id = " +id+ ";";
        List<Orders> actual = DataBaseUtils.executeQueryToBean(Orders.class, query);
        for(Orders order: actual){
            if(order.getId() == id){
                Assert.assertEquals("Order status didn't match", order_status,order.getOrder_status().intValue());
                break;
            }
        }

    }
    @Then("^update order status in orders table based on id$")
    public void update_order_status_in_orders_table_based_on_id(List<Orders> orders) throws Throwable {
        query = "SELECT * from orders;";
        for(Orders o : orders){
            query = "UPDATE orders SET order_status = "+o.getOrder_status()+ " WHERE id = "+o.getId()+";";
            DataBaseUtils.executeUpdate(query);
        }

    }


    @Then("^remove all orders records placed after \"([^\"]*)\"$")
    public void remove_all_orders_records_placed_after(String time) throws Throwable {
        query = "select * from orders where order_placed_at > '" +time+"';";
        List <Orders> listOfOrders = DataBaseUtils.executeQueryToBean(Orders.class,query);
        Assert.assertFalse("list is not empty",listOfOrders.isEmpty());
        for(Orders order : listOfOrders){
            query = "DELETE from orders WHERE id = "+order.getId()+";";
        }




    }


}
