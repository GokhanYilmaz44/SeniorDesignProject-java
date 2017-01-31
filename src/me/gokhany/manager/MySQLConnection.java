package me.gokhany.manager;

import java.sql.*;

/**
 * Created by Gokhany on 31/01/2017.
 */
public class MySQLConnection {


    public void infoConnection(){

        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;



        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/protein_tracker","root", "password12345");

                System.out.println("Connected to the database");
                Statement st = connection.createStatement();
                ResultSet result = st.executeQuery("SELECT * FROM  USERS");
                while (result.next()) {
                    int id = result.getInt("ID");
                    String text = result.getString("name");
                    System.out.println(id + "\t" + text);
                }
                connection.close();


        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

    }

    /**
     * Insert a new candidate
     * @param text
     * @param numberOfFavorite
     * @param numberOfFavorite
     * @param link
     * @param mention
     * @param standart
     * @param date
     * @param time
     * @return
     */
    public static int insertTweets(String text,int numberOfTweet,int numberOfFavorite,
                                      String link, String mention, String standart, Date date, Time time) {
        // for insert a new candidate
        ResultSet rs = null;
        int candidateId = 0;

        String sql = "INSERT INTO Tweet(Text,NumberOfTweet,NumberOfFavorite,Link,Mention,Standart,Date,Time) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialAnalysisDb","root", "password12345");
             PreparedStatement pstate = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {

            // set parameters for statement
            pstate.setString(1, text);
            pstate.setInt(2, numberOfTweet);
            pstate.setInt(3, numberOfFavorite);
            pstate.setString(4, link);
            pstate.setString(5, mention);
            pstate.setString(6, standart);
            pstate.setDate(7, date);
            pstate.setTime(8, time);


            int rowAffected = pstate.executeUpdate();
            if(rowAffected == 1)
            {
                // get candidate id
                rs = pstate.getGeneratedKeys();
                if(rs.next())
                    candidateId = rs.getInt(1);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if(rs != null)  rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return candidateId;
    }

    /**
     * Insert a new candidate
     * @param username
     * @return
     */
    public static int insertUsers(String username) {
        // for insert a new candidate
        ResultSet rs = null;
        int candidateId = 0;

        String sql = "INSERT INTO User(Username) "
                + "VALUES(?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialAnalysisDb","root", "password12345");
             PreparedStatement pstate = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {

            // set parameters for statement
            pstate.setString(1, username);

            int rowAffected = pstate.executeUpdate();
            if(rowAffected == 1)
            {
                // get candidate id
                rs = pstate.getGeneratedKeys();
                if(rs.next())
                    candidateId = rs.getInt(1);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if(rs != null)  rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return candidateId;
    }

    /**
     * Insert a new candidate
     * @param content
     * @return
     */
    public static int insertHashtags(String content) {

        // for insert a new candidate
        ResultSet rs = null;
        int candidateId = 0;

        String sql = "INSERT INTO Hashtag(Content) "
                + "VALUES(?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialAnalysisDb","root", "password12345");
             PreparedStatement pstate = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {


            // set parameters for statement
            pstate.setString(1, content);

            int rowAffected = pstate.executeUpdate();
            if(rowAffected == 1)
            {
                // get candidate id
                rs = pstate.getGeneratedKeys();
                if(rs.next())
                    candidateId = rs.getInt(1);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if(rs != null)  rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return candidateId;
    }

    /**
     * Insert a new candidate
     * @param location
     * @return
     */
    public static int insertLocations(String location) {

        // for insert a new candidate
        ResultSet rs = null;
        int candidateId = 0;

        String sql = "INSERT INTO Location(Place) "
                + "VALUES(?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialAnalysisDb","root", "password12345");
             PreparedStatement pstate = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {


            // set parameters for statement
            pstate.setString(1, location);

            int rowAffected = pstate.executeUpdate();
            if(rowAffected == 1)
            {
                // get candidate id
                rs = pstate.getGeneratedKeys();
                if(rs.next())
                    candidateId = rs.getInt(1);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally
        {try {
                if(rs != null)  rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return candidateId;
    }


}
