package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /**
         * loads moin form screen
         * */
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("/view/MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1225, 650);
        stage.setTitle("Inventory System");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * contains mock data for Inventory
     * */
    public static void addTestData(){
        Part brakes = new InHouse(1, "Windshield", 50.00, 7, 2, 44, 9);
        Inventory.addPart(brakes);

        Part wheel = new InHouse(2, "Wheels", 400.67, 11, 2, 24, 8);
        Inventory.addPart(wheel);

        Part seat = new InHouse(3, "Handlebars", 39.25, 13, 0, 200, 7);
        Inventory.addPart(seat);

        Part spoke = new Outsourced(4, "Cover", 40.25, 15, 0, 100, "random");
        Inventory.addPart(spoke);

        Product GiantBike = new Product(200, "Motorcycle", 1299.99, 6, 1, 50);
        Inventory.addProduct(GiantBike);

        Product Tricycle = new Product(20, "Moped", 200.99, 6, 1, 50);
        Inventory.addProduct(Tricycle);


    }
    public static void main(String[] args) {

        addTestData();

        launch();
    }
}