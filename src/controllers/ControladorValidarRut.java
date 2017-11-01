package controllers;

import dto.DTORut;
import experts.ExpertoValidarRut;

public class ControladorValidarRut {
	private ExpertoValidarRut experto = new ExpertoValidarRut();
	// Método para validar
	public boolean validarRut(String rutIngresado) {
		return experto.validarRut(rutIngresado);
	}
	// Método para generar nuevo rut
	public DTORut generarRut() {
		return experto.generarRut();
	}
}
