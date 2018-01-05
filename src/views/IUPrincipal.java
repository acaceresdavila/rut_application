package views;

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
import javax.swing.JDialog;

import java.awt.datatransfer.*;
import java.awt.Toolkit;

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
			@Override
			public void run() {
				try {
					IUPrincipal frame = new IUPrincipal();
					frame.setLocationRelativeTo(null);
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(IUPrincipal.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		setTitle("Generador/validador de RUT");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 376, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		controlador = new ControladorValidarRut();
		
		JButton btnValidar = new JButton("Validar");
		btnValidar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean esValido = validarRut(textFieldRut.getText()+textFieldDigito.getText());
				String mensaje;
				if(esValido) {
					mensaje = "El RUT es valido";
				} else {
					mensaje = "El RUT no es valido";
				}
				JOptionPane msg = new JOptionPane(mensaje, JOptionPane.PLAIN_MESSAGE);
                JDialog dlg = msg.createDialog("Resultado validación");
                dlg.setVisible(true);
			}
		});
		btnValidar.setBounds(150, 134, 96, 27);
		contentPane.add(btnValidar);
		
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DTORut dtoRut = controlador.generarRut();
				String rut = dtoRut.getRut().split("-")[0];
				String digitoV = dtoRut.getRut().split("-")[1];
				textFieldRut.setText(rut);
				textFieldDigito.setText(digitoV);
			}
		});
		btnGenerar.setBounds(260, 134, 96, 27);
		contentPane.add(btnGenerar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(25, 134, 74, 27);
		contentPane.add(btnSalir);
		
		JLabel lblRut = new JLabel("RUT:");
		lblRut.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRut.setBounds(36, 71, 51, 17);
		contentPane.add(lblRut);
		
		textFieldRut = new JTextField();
		textFieldRut.setBounds(80, 70, 61, 21);
		contentPane.add(textFieldRut);
		textFieldRut.setColumns(10);
		
		JLabel labelGuion = new JLabel("-");
		labelGuion.setFont(new Font("Dialog", Font.BOLD, 16));
		labelGuion.setBounds(140, 71, 12, 17);
		contentPane.add(labelGuion);
		
		textFieldDigito = new JTextField();
		textFieldDigito.setBounds(150, 70, 12, 21);
		contentPane.add(textFieldDigito);
		textFieldDigito.setColumns(10);
		
		JButton btnCopiar = new JButton("Copiar");
		btnCopiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				copiarRut();
			}
		});
		btnCopiar.setBounds(191, 67, 69, 27);
		contentPane.add(btnCopiar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textFieldRut.setText("");
				textFieldDigito.setText("");
			}
		});
		btnLimpiar.setBounds(272, 67, 74, 27);
		contentPane.add(btnLimpiar);
		
		JLabel lblMLuna = new JLabel("lunamarcose © 2017");
		lblMLuna.setBounds(236, 184, 120, 17);
		contentPane.add(lblMLuna);
		
		textFieldRut.addKeyListener(new KeyAdapter() {
		    @Override
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
	
	// Método para copiar al clipboard
	public void copiarRut() {
		String rut = textFieldRut.getText() + "-" + textFieldDigito.getText();
		StringSelection stringSelection = new StringSelection(rut);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
	}
}
