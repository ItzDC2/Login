package login;

import auth.AuthService;
import auth.FileAuthService;
import auth.LdapAuthService;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogEvent;
import javafx.stage.WindowEvent;
import loginApp.LoginApp;

enum Tipo {
	
	EXITO,ERROR_LOGIN,ERROR_USUARIO_LENGTH,ERROR_CONTRASEÑA_LENGTH,ERROR_NULL
	
}

public class LoginController {

	private LoginModel model = new LoginModel();
	private LoginView view = new LoginView();
	
	public LoginController() {
		
		model.usuarioProperty().bind(view.getUsuarioText().textProperty());
		model.passwordProperty().bind(view.getPasswordText().textProperty());
		
		view.getAccederButton().setOnAction(this::onAccederAction);
		view.getCancelarButton().setOnAction(this::onCancelAction);
		view.getPasswordText().textProperty().bindBidirectional(model.passwordProperty());
	
	}

	private void onAccederAction(ActionEvent e) {
		
		boolean usarLDAP = model.getLdapProperty();
		
		AuthService auth = usarLDAP ? new LdapAuthService() : new FileAuthService();
		
		try {
			if(model.getUsuarioProperty() != null && model.getPasswordProperty() != null) {
				if(model.getUsuarioProperty().length() > 0 && model.getPasswordProperty().length() > 0) {
					if(auth.login(model.usuarioProperty().get(), model.passwordProperty().get()))
						abrirPanel(Tipo.EXITO);
					else
						abrirPanel(Tipo.ERROR_LOGIN);
				} else if(model.getUsuarioProperty().length() == 0)
					abrirPanel(Tipo.ERROR_USUARIO_LENGTH);
				else if(model.getPasswordProperty().length() == 0)
					abrirPanel(Tipo.ERROR_CONTRASEÑA_LENGTH);
			} else 
				abrirPanel(Tipo.ERROR_NULL);
		} catch (Exception e1) {
			abrirPanel(e1.getStackTrace().toString());
		}
	
	}
	
	private void onCancelAction(ActionEvent e) {
		LoginApp.primaryStage.close();
	}
	
	public LoginView getView() {
		return view;
	}
	
	public LoginModel getModel() {
		return model;
	}
	
	private void abrirPanel(String mensaje) {
		Alert a = new Alert(AlertType.ERROR);
		a.setHeaderText("¡Error!");
		a.setContentText(mensaje);
	}
	
	private void abrirPanel(Tipo tipo) {
		if(tipo != null) {
			
			Alert a;
			a = new Alert(AlertType.CONFIRMATION);
			
			switch(tipo) {
				case EXITO:
					a.setHeaderText("¡Has accedido!");
					a.setContentText("El usuario y la contraseña coinciden.");
					LoginApp.primaryStage.close();
				break;
				case ERROR_LOGIN:
					a = new Alert(AlertType.ERROR);
					a.setHeaderText("¡ERROR!");
					a.setContentText("Los credenciales introducidos no son válidos.");
					view.getPasswordText().setText(null);
				break;
				case ERROR_USUARIO_LENGTH:
					a = new Alert(AlertType.ERROR);
					a.setHeaderText("¡ERROR!");
					a.setContentText("La longitud del usuario no es válida.");
					view.getPasswordText().setText(null);
				break;
			}
			
			if(tipo.equals(Tipo.EXITO)) {
				a.setHeaderText("¡Has accedido!");
				a.setContentText("El usuario y la contraseña coinciden.");
				LoginApp.primaryStage.close();
			} else if(tipo.equals(Tipo.ERROR_LOGIN)) {
				a = new Alert(AlertType.ERROR);
				a.setHeaderText("¡ERROR!");
				a.setContentText("Los credenciales introducidos no son válidos.");
				view.getPasswordText().setText(null);
			} else if(tipo.equals(Tipo.ERROR_USUARIO_LENGTH)) {
				a = new Alert(AlertType.ERROR);
				a.setHeaderText("¡ERROR!");
				a.setContentText("La longitud del usuario no es válida.");
				view.getPasswordText().setText(null);
			} else if(tipo.equals(Tipo.ERROR_CONTRASEÑA_LENGTH)) {
				a = new Alert(AlertType.ERROR);
				a.setHeaderText("¡ERROR!");
				a.setContentText("La longitud de la contraseña no es válida.");
				view.getPasswordText().setText(null);
			} else if(tipo.equals(Tipo.ERROR_NULL)) {
				a = new Alert(AlertType.ERROR);
				a.setHeaderText("¡ERROR!");
				a.setContentText("Algun parámetro recibido es nulo.");
				view.getPasswordText().setText(null);
			}
			a.show();
		}
	}
	
}
