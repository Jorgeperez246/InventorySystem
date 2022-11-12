package controller;

import javafx.collections.FXCollections;
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
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductFormController implements Initializable {
    @FXML
    private TextField SearchPart;
    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();
    @FXML
    private TextField ProductId;
    @FXML
    private TextField ProductName;
    @FXML
    private TextField ProductInventory;
    @FXML
    private TextField ProductPrice;
    @FXML
    private TextField ProductMax;
    @FXML
    private TextField ProductMin;
    @FXML
    private TableView<Part> PartTableView;
    @FXML
    private TableColumn<Part,Integer> PartIdColumn;
    @FXML
    private TableColumn<Part,String> PartNameColumn;
    @FXML
    private TableColumn<Part,Integer> PartInventoryColumn;
    @FXML
    private TableColumn<Part,Double> PartPriceColumn;
    @FXML
    private TableView<Part> AssociatedPartTableView;
    @FXML
    private TableColumn<Part,Integer> AssociatedPartIdColumn;
    @FXML
    private TableColumn<Part,Double> AssociatedPartPriceColumn;
    @FXML
    private TableColumn<Part,String> AssociatedPartNameColumn;
    @FXML
    private TableColumn<Part,Integer> AssociatedPartInventoryColumn;
    @FXML
    private Button RemovePartButton;
    @FXML
    private Button SaveProductButton;
    @FXML
    private Button CancelProductButton;

    /**
     * adds part to observable list and displays error if nothing is selected
     * @param actionEvent
     * */
    @FXML
    public void addPartToProduct(ActionEvent actionEvent) {
        Part part = PartTableView.getSelectionModel().getSelectedItem();

        if(part != null) {
            associatedPart.add(part);
            AssociatedPartTableView.setItems(associatedPart);
        }

        else if(!associatedPart.contains(null)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("part not selected from list");
            alert.showAndWait();
        }
    }

    /**
     * removes associated part from list and displays error if nothing is selected
     * @param actionEvent
     * */
    public void removePart(ActionEvent actionEvent) {
        Part part = AssociatedPartTableView.getSelectionModel().getSelectedItem();

        if(part != null) {
            associatedPart.remove(part);
            AssociatedPartTableView.setItems(associatedPart);
        }

        else if(!associatedPart.contains(null)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("part not selected from list");
            alert.showAndWait();
        }

    }

    /**
     * FUTURE ENHANCEMENT: Would like to add a section that asks user if he would like to
     * link the created part to an existed product to make it an associated Part
     * @param actionEvent
     * */

    public void saveProduct(ActionEvent actionEvent) throws IOException {
        try {

            // random function used to prevent same number for PartId
            int ProductRandomId = (int) (Math.random() * 100);

            String Name = ProductName.getText();
            int Stock = Integer.parseInt(ProductInventory.getText());
            double Price = Double.parseDouble(ProductPrice.getText());
            int Max = Integer.parseInt(ProductMax.getText());
            int Min = Integer.parseInt(ProductMin.getText());


            //checks whether min is less than max, if not then displays error
            if (Max < Min) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Max is not greater than Min");
                alert.showAndWait();
                return;
            }
            //checks whether inventory is within min and max range, if not then displays error
            else if (Stock < Min || Max < Stock) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory be in Min to Max Range");
                alert.showAndWait();
                return;
            }

            Product product = new Product(ProductRandomId,Name,Price,Stock,Min,Max);
            for (Part part: associatedPart) {
                if (part != associatedPart)
                    product.addAssociatedPart(part);
            }
            Inventory.getAllProducts().add(product);
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
            // catch used to diplay incorrect values entered into the form
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("Incorrect values");
            alert.showAndWait();
        }
    }



    /**
     * sends you back to main Form
     * @param event
     * */
    public void cancelProduct(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("Inventory System");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PartTableView.setItems(Inventory.getAllParts());
        PartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        AssociatedPartTableView.setItems(associatedPart);
        AssociatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * searches parts list within AddProductForm
     * @param event
     * */
    public void searchPart(ActionEvent event) {
        String search = SearchPart.getText();
        ObservableList<Part> partFound = Inventory.lookupPart(search);
        try {
            while(partFound.size() == 0){
                int productId = Integer.parseInt(search);
                partFound.add(Inventory.lookupPart(productId));
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
}
