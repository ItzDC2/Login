package login;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginModel {

	private StringProperty usuarioProperty = new SimpleStringProperty();
	private StringProperty passwordProperty = new SimpleStringProperty();
	private BooleanProperty ldapProperty = new SimpleBooleanProperty();
	
	public final StringProperty usuarioProperty() {
		return this.usuarioProperty;
	}
	
	public final String getUsuarioProperty() {
		return this.usuarioProperty().get();
	}
	
	public final void setUsuarioProperty(final String usuarioProperty) {
		this.usuarioProperty().set(usuarioProperty);
	}
	
	public final StringProperty passwordProperty() {
		return this.passwordProperty;
	}
	
	public final String getPasswordProperty() {
		return this.passwordProperty().get();
	}
	
	public final void setPasswordProperty(final String passwordProperty) {
		this.passwordProperty().set(passwordProperty);
	}

	public final BooleanProperty ldapProperty() {
		return this.ldapProperty;
	}
	
	public final boolean getLdapProperty() {
		return ldapProperty.get();
	}
	
	
	
}
