package sample;

import com.mysql.cj.xdevapi.Client;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class Controller {
    @FXML // fx:id="tableview";
    TableView<Person> tableView;

    @FXML // fx:id="tf";
    TextField tf;

    public void doLoad(){

        ObservableList<Person> values = Person.get_name(tf);

        TableColumn<Person,String> first
                = new TableColumn<>("First");
        first.setCellValueFactory(new PropertyValueFactory("firstName"));
        TableColumn<Person, String> last
                = new TableColumn<>("Last");
        last.setCellValueFactory(new PropertyValueFactory("lastName"));

        tableView.getColumns().setAll(first,last);
        tableView.setItems(values);

    }
}



