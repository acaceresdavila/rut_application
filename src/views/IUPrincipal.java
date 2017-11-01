package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.ControladorValidarRut;
import dto.DTORut;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

public class IUPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldRut;
	private ControladorValidarRut controlador;
	private JTextField textFieldDigito;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IUPrincipal frame = new IUPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IUPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 376, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		controlador = new ControladorValidarRut();
		
		JButton btnValidar = new JButton("Validar");
		btnValidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean esValido = validarRut(textFieldRut.getText()+textFieldDigito.getText());
				String mensaje;
				if(esValido) {
					mensaje = "Es valido";
				} else {
					mensaje = "No es valido";
				}
				JOptionPane msg = new JOptionPane(mensaje, JOptionPane.PLAIN_MESSAGE);
                JDialog dlg = msg.createDialog("Resultado validación");
                dlg.setVisible(true);
			}
		});
		btnValidar.setBounds(152, 153, 96, 27);
		contentPane.add(btnValidar);
		
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DTORut dtoRut = controlador.generarRut();
				String rut = dtoRut.getRut().split("-")[0];
				String digitoV = dtoRut.getRut().split("-")[1];
				textFieldRut.setText(rut);
				textFieldDigito.setText(digitoV);
			}
		});
		btnGenerar.setBounds(260, 153, 96, 27);
		contentPane.add(btnGenerar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(12, 153, 96, 27);
		contentPane.add(btnSalir);
		
		JLabel lblRut = new JLabel("RUT:");
		lblRut.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRut.setBounds(51, 87, 51, 17);
		contentPane.add(lblRut);
		
		textFieldRut = new JTextField();
		textFieldRut.setBounds(105, 86, 61, 21);
		contentPane.add(textFieldRut);
		textFieldRut.setColumns(10);
		
		JLabel labelGuion = new JLabel("-");
		labelGuion.setFont(new Font("Dialog", Font.BOLD, 16));
		labelGuion.setBounds(169, 87, 6, 17);
		contentPane.add(labelGuion);
		
		textFieldDigito = new JTextField();
		textFieldDigito.setBounds(178, 86, 12, 21);
		contentPane.add(textFieldDigito);
		textFieldDigito.setColumns(10);
		
		JButton btnCopiar = new JButton("Copiar");
		btnCopiar.setBounds(232, 83, 69, 27);
		contentPane.add(btnCopiar);
		
		textFieldRut.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (textFieldRut.getText().length() >= 8 ) // limit textfield to 3 characters
		            e.consume(); 
		    }  
		});
	}
	
	// Método para validar
	public boolean validarRut(String rutIngresado) {
		return controlador.validarRut(rutIngresado);
	}
}
