package com.devxschool.utils.db;

import com.devxschool.utils.config.Config;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseUtils {
    private static Connection connection;
    private static Statement statement;
    private static QueryRunner queryRunner; // Apache common Db Utils
    private static ResultSet resultSet;


    /***
     * CREATE A METHOD THAT OPENS A CONNECTION TO THE DATABASE
     * @throws Exception
     */
    public static void connectToDatabase() throws Exception {
        try{
            connection = DriverManager.getConnection(Config.getPropertiesValue("url"), Config.getPropertiesValue("user"),
                    System.getProperty("dbPassword")); // this is from Run->edit configuration -VM options ->password so it is comes from
            // system local machine System.setProperty("password", "hdiofbes");
            // System.getProperty("password");
            //in Terminal you can change system property:
            //for example: mvn verify -Dpassword="qqqqq"
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            queryRunner = new QueryRunner();
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Connection failed to Database with url" + Config.getPropertiesValue("url"));
        }

    }
    public static void closeConnection() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***
     * Implement a method that executes the query, and saves/returns the records in List<Map<String, Object>>.
     * @param query
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> executeQuery(String query) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        if (statement.execute(query)) {
            resultSet = statement.executeQuery(query);
            // get MetaData (Data about data)
            ResultSetMetaData rsMdata = resultSet.getMetaData();// Metadata contains names of the columns
            int colCount = rsMdata.getColumnCount();// Returns the number of columns in our result set
            while (resultSet.next()) {
                Map<String, Object> rowMap = new HashMap<>(); // declare a map that would represent a row
                for (int col = 1; col <= colCount; col++) {
                    rowMap.put(rsMdata.getColumnName(col), resultSet.getObject(col));
                    // populating the row with (key = header name; value = is a value from result set under that column
                    //getColumnName(i)  Returns the name of the column, expects the index
                }
                list.add(rowMap);
            }
        }
        return list;


    }

    /***
     * Implement a method that performs update sql functions.
     * @param query
     * @param params
     * @return
     * @throws SQLException
     */
    public static int executeUpdate(String query, Object... params) throws SQLException {
        // Prepared statement is used to execute a number of same queries with different params
        PreparedStatement stmt = connection.prepareStatement(query); // instantiate new prep statement
        for (int i = 0; i <params.length ; i++) {
            // Will replace a ? under index i + 1 with an object from params array under index i
            stmt.setObject(i +1, params[i]);
        }
        // Sends our parametrized query to the DB and returns ResultSet
        return stmt.executeUpdate();


    }

    /***
     * Implement a method that will insert new records as a Bean
     * @param query
     * @param bean
     * @param properties
     * @param <T>
     * @return
     * @throws SQLException
     */
    public static <T> boolean executeInsert(String query, T bean, String[] properties) throws SQLException {
        // Prepared statement is used to execute a number of same queries with different params
        PreparedStatement preparedStatement = connection.prepareStatement(query);// instantiate new prep statement
        queryRunner.fillStatementWithBean(preparedStatement, bean, properties); // fill the statement
        // (replace ? with values from our Bean(Object) from variable names provide as properties )
        return preparedStatement.execute();


    }

    /***
     * Implement a method that reads the records from database and save it to List<Bean> objects.
     * @param object
     * @param query
     * @param <T>
     * @return
     * @throws SQLException
     */

    public static <T> List<T> executeQueryToBean(Class<T> object, String query) throws SQLException {

        resultSet = statement.executeQuery(query);
        return new BeanProcessor().toBeanList(resultSet, object);
        // Coming form Apache DB Utils and used in order to convert(map) result set to Java Object Beans



    }


}

