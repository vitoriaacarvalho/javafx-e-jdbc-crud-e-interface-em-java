package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.exceptions.ValidationException;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable{
	private Department entity;
	private DepartmentService service;
	private List<DataChangeListener> dataChangeListeners=new ArrayList<>();
	
	
	@FXML
	private TextField txtId;

	@FXML
	private TextField txtName;

	@FXML
	private Label labelErrorName;

	@FXML
	private Button btSave;

	@FXML
	private Button btCancel;

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		try {
			entity=getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		}catch(ValidationException e) {
			setErrorMessages(e.getErrors());
		}catch(DbException e) {
			Alerts.showAlert("error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChange();
		}
		
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	public void setDepartment(Department entity) {
		this.entity=entity;
	}
	public void setDepartmentService(DepartmentService service) {
		this.service=service;	
	}
	private Department getFormData() {
		Department obj=new Department();
		ValidationException exception=new ValidationException("validaton error");
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		if(txtName.getText()==null||txtName.getText().trim().equals("")) {
			exception.addError("name", "Field can't be empty");
		}
		obj.setName(txtName.getText());
		if(exception.getErrors().size()>0) {
			throw exception;
		}
		return obj;
	}
	public void updateFormData() {
		if (entity==null) {
			throw new IllegalStateException("entity was null");
		}
		else {
			txtId.setText(String.valueOf(entity.getId()));
			txtName.setText(entity.getName());
		}
	}
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}

	public void subscribeDataChangeListeners(DataChangeListener listeners) {
		dataChangeListeners.add(listeners);
	}

	private void setErrorMessages(Map<String,String> errors) {
		Set<String> fields=errors.keySet();
		if(fields.contains("name")) {
			labelErrorName.setText(errors.get("name"));
		}
	}
	
	
	
	
	
	
	
	
	
}