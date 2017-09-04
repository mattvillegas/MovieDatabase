/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasejavafx;

import dataModel.FilmDAO;
import inputOutput.ConnectionData;
import inputOutput.PostgreSQLConnect;
import inputOutput.XmlParser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
/**
 *
 * @author MatthewVillegas
 */
public class DatabaseJavaFx extends Application {
    private Logger logger = Logger.getLogger(DatabaseJavaFx.class.getName());
    ObservableList<FilmDAO> list = FXCollections.observableArrayList();
    
    // sets up the JavaFX GUI
    @Override
    public void start(Stage primaryStage) {
        
      logger.log(Level.INFO, "Setting up Table");
      TableView table = new TableView();
      table.setEditable(false);
      
      Label label = new Label("Films");
      
      TableColumn title = new TableColumn("Title");
      title.setMinWidth(200);
      title.setCellValueFactory(new PropertyValueFactory<FilmDAO, String>("filmName"));
      
      
      TableColumn description = new TableColumn("Description");
      description.setMinWidth(400);
      description.setCellValueFactory(new PropertyValueFactory<FilmDAO, String>("filmDescription"));
      
      TableColumn rental = new TableColumn("Rental Rate");
      rental.setMinWidth(200);
      rental.setCellValueFactory(new PropertyValueFactory<FilmDAO, Double>("filmPrice"));
      
      TableColumn rating = new TableColumn("Rating");
      rating.setMinWidth(200);
      rating.setCellValueFactory(new PropertyValueFactory<FilmDAO, String>("filmRating"));
      
      table.getColumns().addAll(title, description, rental, rating);
      Button fetch = new Button("Fetch data from database");
      fetch.setOnAction(new EventHandler<ActionEvent>() 
      {
          @Override
          public void handle(ActionEvent event) {
              fetchData(table);
          }
      });
      
      Scene scene = new Scene(new Group());
      
      
      VBox vbox = new VBox();
      
      vbox.setPrefHeight(800);
      vbox.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
      vbox.getChildren().addAll(label, table);
      
      ((Group) scene.getRoot()).getChildren().addAll(vbox, fetch);
      
      primaryStage.setTitle("Films for Rent");
      
      primaryStage.setScene(scene);
      
      primaryStage.show();
    }
    
    /**
     *
     * @param view
     */
    // Tries to populate data from the PostgreSQL database 
    public void fetchData(TableView view)
    {
        try(Connection connection = getConnection();)
        {
             logger.log(Level.INFO, "Fetching data");
            view.setItems(fetchFilms(connection));
         
            
        }
        catch(SQLException | ClassNotFoundException e)
        {
            logger.log(Level.SEVERE, "Exception Found", e);
        }
                      
    }
    
    /**
     *
     * @return returns populated connection variable
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    
    // parses through xml and then connects to server using the xml information
    public Connection getConnection() throws SQLException, ClassNotFoundException
    {
       XmlParser parser = new XmlParser("inputOutput/properties.xml");
       ConnectionData connectionData = parser.getConnectionData();
       PostgreSQLConnect connect = new PostgreSQLConnect(connectionData);
       
       Connection connectData = connect.getConnection();
       
       return connectData;
       
    }
    
    /**
     *
     * @param connection
     * @return
     * @throws SQLException
     */
    
    // populates GUI table with the data from the server
    public ObservableList<FilmDAO> fetchFilms(Connection connection) throws SQLException
    {
       logger.log(Level.INFO, "Populating table");
        ObservableList<FilmDAO> newList = FXCollections.observableArrayList();
        String query = new String("select title, rental_rate, rating, description " +
                        "from film " +
                        "order by title;");
        logger.log(Level.INFO, "Querying database with select title, rental_rate, rating, description"
                + " from film " + "order by title;");
        
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(query);
        while(set.next() == true)
        {
            FilmDAO films = new FilmDAO();
            films.setFilmName(set.getString("title"));
            films.setFilmDescription(set.getString("description"));
            films.setFilmPrice(set.getDouble("rental_rate"));
            films.setFilmRating(set.getString("rating"));
            newList.add(films);   
        }
        logger.log(Level.INFO, "Found " + newList.size() + " films");
        return newList;
        
    }
   

    /**
     * @param args the command line arguments
     */
    // launches JavaFX 
    public static void main(String[] args) {
        launch(args);
    }
    
}
