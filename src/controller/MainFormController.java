package controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    public static void errorPopup(String input_error, String error_in_adding_part, String check_fields_for_correct_input) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(input_error);
        alert.setHeaderText(error_in_adding_part);
        alert.setContentText(check_fields_for_correct_input);
        alert.showAndWait();
    }

    /**
     * opens addPart form
     * @param event
     * */
    public void addPart(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        stage.setTitle("Add Part");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * searches list from PartTableView when enter key is hit
     * when entering a number that isn't a real ID it seems 'catch' doesn't pick it up
     * will try to revisit later on and fix this bug.
     * @param event
     * */
    @FXML
    public void partSearch(ActionEvent event){
        String search = PartSearch.getText();
        ObservableList<Part> partFound = Inventory.lookupPart(search);
        try {
            while(partFound.size() == 0){
                int partId = Integer.parseInt(search);
                partFound.add(Inventory.lookupPart(partId));
            }
            PartTableView.setItems(partFound);
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nothing Found");
            alert.setContentText("No Part was found in the list");
            alert.showAndWait();
        }

    }

    /**
     * opens modifyPart form if part on table is selected
     * @param actionEvent
     * */
    @FXML
    public void modifyPart(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPartForm.fxml"));
            loader.load();

            ModifyPartFormController Controller = loader.getController();
            Controller.sendPartToModify(PartTableView.getSelectionModel().getSelectedIndex(),PartTableView.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
    /**
     * deletes a part from inventory
     * @param actionEvent
     * */
    @FXML
    public void deletePart(ActionEvent actionEvent) {
        Part part = PartTableView.getSelectionModel().getSelectedItem();
        Inventory.deletePart(part);
    }
    /**
     * opens modifyProduct form if Product on table is selected
     * @param actionEvent
     * */
    public void modifyProduct(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProductForm.fxml"));
            loader.load();

            ModifyProductFormController Controller = loader.getController();
            Controller.sendProductToModify(ProductTableView.getSelectionModel().getSelectedIndex(),ProductTableView.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
    /**
     * deletes product from ProductTable view
     * @param actionEvent
     * */
    public void deleteProduct(ActionEvent actionEvent) {
        Product product = ProductTableView.getSelectionModel().getSelectedItem();
        if(product.getAllAssociatedParts().size()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Product contains associated parts, please remove before deletion");
            alert.showAndWait();
            return;
        }
        Inventory.deleteProduct(product);
    }
    /**
     * exits application
     * @param actionEvent
     * */
    public void exitApp(ActionEvent actionEvent) {
        System.exit(0);
    }
    /**
     * opens addProduct form
     * @param event
     * */
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

    /**
     * searches PartTable view by ID and Name
     * similar code to part search but unfortunately has same bug with product ID
     * @param event
     * */
    public void productSearch(ActionEvent event) {
        String search = ProductSearch.getText();
        ObservableList<Product> productFound = Inventory.lookupProduct(search);
        try {
            while(productFound.size() == 0){
                int productId = Integer.parseInt(search);
                productFound.add(Inventory.lookupProduct(productId));
            }
            ProductTableView.setItems(productFound);
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nothing Found");
            alert.setContentText("No Part was found in the list");
            alert.showAndWait();
        }
    }
}
