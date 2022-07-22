package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.entities.Department;

public class DepartmentListController implements Initializable{

	@FXML
	private TableView<Department> tableViewDepartment;
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	@FXML
	private TableColumn<Department,String> tableColumnName;
	@FXML
	private Button btNew;
	
	public void onBtNewAction() {
		System.out.println("button works :D");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
}
