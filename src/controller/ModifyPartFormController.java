package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.Part;

import java.io.IOException;

public class ModifyPartFormController {
    @FXML
    private Label MachineId;
    @FXML
    private TextField ModifyPartID;
    @FXML
    private TextField PartName;
    @FXML
    private TextField PartInventory;
    @FXML
    private TextField PartPrice;
    @FXML
    private TextField PartMax;
    @FXML
    private TextField MachineOrCompany;
    @FXML
    private TextField PartMin;
    @FXML
    private ToggleGroup AddPartToggle;
    @FXML
    private Button SavePartButton;
    @FXML
    private Button CancelPartButton;

    public void savePart(ActionEvent actionEvent) {
    }

    public void cancelPart(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    public void sendPartToModify(int selectedIndex, Part selectedItem) {
    }
}
