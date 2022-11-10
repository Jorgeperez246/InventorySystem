package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import javax.crypto.Mac;
import java.io.IOException;

public class AddPartFormController {
    public Label MachineId;
    public RadioButton PartInHouseRadio;
    public RadioButton PartOutsourcedRadio;
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

    @FXML
    public void savePart(ActionEvent actionEvent) throws IOException {
        try {

            // random function used to prevent same number for PartId
            int PartRandomId = (int) (Math.random() * 100);

            String Name = PartName.getText();
            int Stock = Integer.parseInt(PartInventory.getText());
            double Price = Double.parseDouble(PartPrice.getText());
            int Max = Integer.parseInt(PartMax.getText());
            int Min = Integer.parseInt(PartMin.getText());


            int machineID;
            String companyName;

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

            //checks if part outsourced or inhouse is selected
            if (PartOutsourcedRadio.isSelected()) {
                companyName = MachineOrCompany.getText();
                Outsourced addPart = new Outsourced(PartRandomId, Name, Price, Stock, Min, Max, companyName);
                Inventory.addPart(addPart);
            }
            if (PartInHouseRadio.isSelected()) {
                machineID = Integer.parseInt(MachineOrCompany.getText());
                InHouse addPart = new InHouse(PartRandomId, Name, Price, Stock, Min, Max, machineID);
                Inventory.addPart(addPart);
            }
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
     * sets machineId label to machine id
     * @param event
     * */
    @FXML
    public void setMachine(ActionEvent event) {
        MachineId.setText("Machine ID");
    }

    /**
     * sets machineId label to comapany name
     * @param event
     * */
    @FXML
    public void setCompany(ActionEvent event) {
        MachineId.setText("Company Name");
    }
    public void cancelPart(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("Inventory System");
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
