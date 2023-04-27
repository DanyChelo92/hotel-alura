package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.Usuario;
import views.Login;
import views.MenuUsuario;

public class ControllerUsuario implements ActionListener {
	
	private Login vista;
	
	public ControllerUsuario(Login vista){
		this.vista=vista;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String usuario= vista.getUsuario();
		String contrasena= vista.getContrasena();
		
		if ((boolean)Usuario.validar_usuario(usuario, contrasena)) {
			MenuUsuario menu= new MenuUsuario();
			menu.setVisible(true);
			vista.dispose();
		}else {
			JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos!");
		}
	}
}
