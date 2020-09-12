package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class BuildingListController {

    public Button addBuilding;
    public Button changeBuilding;
    public Button deleteBuilding;
    public Button exit;

    public TableView<Building> buidlingList;
    public TableColumn<Building,Integer> id;
    public TableColumn<Building,String> adress;
    public TableColumn<Building,Integer> flats;
    public TableColumn<Building,String> type;

    private ObservableList<Building> buildingObservableList;

    private BuildingManagementDAO dao;

    public BuildingListController(ArrayList<Building> buildings) {
        dao = BuildingManagementDAO.getInstance();
        buildingObservableList= FXCollections.observableArrayList(buildings);
    }

    @FXML
    public void initialize(){
        buidlingList.setItems(buildingObservableList);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        adress.setCellValueFactory(new PropertyValueFactory("adress"));
        flats.setCellValueFactory(new PropertyValueFactory("numberOfFlats"));
        type.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getTypeByString()));

    }

    public void addAction(){
        try {
            Stage stage = new Stage();
            Parent root;
            FXMLLoader loader = null;
            loader = new FXMLLoader(getClass().getResource("/fxml/AddBuilding.fxml"));
            AddBuildingController muncipalityController = new AddBuildingController();
            loader.setController(muncipalityController);
            root = loader.load();
            stage.setTitle("");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            //stage.setResizable(false);
            stage.show();

            stage.setOnHiding(windowEvent -> {
                buildingObservableList.setAll(dao.getAllBuildings());
                buidlingList.setItems(buildingObservableList);
            });


        }catch (IOException e){
            e.printStackTrace();
        }
    }




}