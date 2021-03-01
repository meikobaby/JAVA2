package sample;

import com.mysql.cj.xdevapi.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;

public class Person {
    private StringProperty firstName;
    private StringProperty lastName;

    public void setFirstName(String value) { firstName.set(value); }
    public String getFirstName() { return firstName.get(); }

    public void setLastName(String value) { lastName.set(value); }
    public String getLastName() { return lastName.get(); }

    public Person(String fn, String ln){
        this.firstName = new SimpleStringProperty(this, "firstName");
        this.lastName = new SimpleStringProperty(this, "lastName");
        this.setFirstName(fn);
        this.setLastName(ln);
    }


    public static ObservableList<Person> get_name(TextField tf) {

        ObservableList<Person> ret_val = FXCollections.observableArrayList();
        String conn_url = "jdbc:mysql://localhost:3306/my_db?user=root&password=Sungjong0903";
        Connection conn = null;

        try {
            String cmd = tf.getText();
            System.out.println(cmd);
            conn = DriverManager.getConnection(conn_url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(cmd);

            while (rs != null && rs.next()) {
                String first = rs.getString(1);
                String last = rs.getString(2);

                ret_val.add(new Person(first, last));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret_val;

    }
}
