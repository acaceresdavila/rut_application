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
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class IUPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldRut;
	private ControladorValidarRut controlador;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		controlador = new ControladorValidarRut();
		
		JButton btnValidar = new JButton("Validar");
		btnValidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean esValido = validarRut(textFieldRut.getText());
				System.out.println(esValido);
			}
		});
		btnValidar.setBounds(226, 224, 96, 27);
		contentPane.add(btnValidar);
		
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DTORut dtoRut = controlador.generarRut();
				System.out.println(dtoRut.getRut());
			}
		});
		btnGenerar.setBounds(334, 224, 96, 27);
		contentPane.add(btnGenerar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(12, 224, 96, 27);
		contentPane.add(btnSalir);
		
		JLabel lblRut = new JLabel("RUT:");
		lblRut.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRut.setBounds(96, 111, 51, 17);
		contentPane.add(lblRut);
		
		textFieldRut = new JTextField();
		textFieldRut.setBounds(165, 110, 168, 21);
		contentPane.add(textFieldRut);
		textFieldRut.setColumns(10);
		
		JLabel lblGuion = new JLabel("GUIÓN:");
		lblGuion.setFont(new Font("Dialog", Font.BOLD, 16));
		lblGuion.setBounds(96, 154, 67, 17);
		contentPane.add(lblGuion);
		
		JCheckBox chckbxGuion = new JCheckBox("");
		chckbxGuion.setBounds(165, 151, 105, 25);
		contentPane.add(chckbxGuion);
	}
	
	// Método para validar
	public boolean validarRut(String rutIngresado) {
		return controlador.validarRut(rutIngresado);
	}
}
