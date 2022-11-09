package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    @FXML
    private TableView<Part> PartTableView;
    @FXML
    private TableColumn<Part, Integer> PartIdColumn;
    @FXML
    private TableColumn<Part, String> PartNameColumn;
    @FXML
    private TableColumn<Part, Integer> PartInventoryColumn;
    @FXML
    private TableColumn<Part,Double> PartPriceColumn;
    @FXML
    private Button AddPartButton;
    @FXML
    private Button ModifyPartButton;
    @FXML
    private Button DeletePartButton;
    @FXML
    private TextField PartSearch;
    @FXML
    private TableView<Product> ProductTableView;
    @FXML
    private TableColumn<Product,Integer> ProductIdColumn;
    @FXML
    private TableColumn<Product,String> ProductNameColumn;
    @FXML
    private TableColumn<Product,Integer> ProductInventoryColumn;
    @FXML
    private TableColumn<Product,Double> ProductPriceColumn;
    @FXML
    private Button AddProductButton;
    @FXML
    private Button ModifyProductButton;
    @FXML
    private Button DeleteProductButton;
    @FXML
    private TextField ProductSearch;
    @FXML
    private Button ExitAppButton;

    //Opens AddPartForm fxml file
    public void addPart(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        stage.setTitle("Add Part");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void modifyPart(ActionEvent actionEvent) {
    }

    public void deletePart(ActionEvent actionEvent) {
    }

    public void modifyProduct(ActionEvent actionEvent) {
    }

    public void deleteProduct(ActionEvent actionEvent) {
    }
    // Exits the application
    public void exitApp(ActionEvent actionEvent) {
        System.exit(0);
    }
    public void addProduct(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        stage.setTitle("Add Product");
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     * populates both tables
     * @param url
     * @param resourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PartTableView.setItems(Inventory.getAllParts());
        PartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        ProductTableView.setItems(Inventory.getAllProducts());
        ProductIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProductPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));



    }


}
