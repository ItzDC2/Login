package login;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class LoginView extends GridPane {

	private TextField usuarioText;
	private PasswordField passwordText;
	private CheckBox ldapCheck;
	
	private Button accederButton;
	private Button cancelarButton;
	
	public LoginView() {
		super();

		usuarioText = new TextField();
		passwordText = new PasswordField();
		ldapCheck = new CheckBox("¿Usar LDAP?");
		
		accederButton = new Button("Acceder");
		
		cancelarButton = new Button("Cancelar");
		
		setHgap(5);
		setVgap(5);		
		setPadding(new Insets(5));
		setAlignment(Pos.CENTER);
		
//		setStyle("-fx-background-color: red;");
		
		//Node child, int columnIndex, int rowIndex, int colspan, int rowspan
		
		addRow(0, new Label("Usuario:"), usuarioText);
		addRow(1, new Label("Contraseña:"), passwordText);
		
		add(ldapCheck, 1, 2, 1, 1); 
	
		HBox buttonsBox = new HBox(10, accederButton, cancelarButton);
		buttonsBox.setAlignment(Pos.CENTER);
		
		add(buttonsBox, 0, 3, 2, 1);
		
		ColumnConstraints cols [] = {
			new ColumnConstraints(),
			new ColumnConstraints(),
		};
		
		setGridLinesVisible(false);

		cols[0].setMinWidth(Control.USE_PREF_SIZE);		
		
		getColumnConstraints().setAll(cols);
		
	}
	
	public PasswordField getPasswordText() {
		return passwordText;
	}
	
	public TextField getUsuarioText() {
		return usuarioText;
	}
	
	public Button getAccederButton() {
		return accederButton;
	}
	
	public Button getCancelarButton() {
		return cancelarButton;
	}
	
	public CheckBox getLdapCheck() {
		return ldapCheck;
	}
	
}
