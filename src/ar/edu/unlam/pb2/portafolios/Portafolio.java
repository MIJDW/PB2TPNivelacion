package ar.edu.unlam.pb2.portafolios;
import java.util.ArrayList;

import ar.edu.unlam.pb2.enumsDatos.*;
import ar.edu.unlam.pb2.evaluaciones.*;

public class Portafolio {

	protected NivelesDeEducacion nivel;
	protected Grados grado;
	protected ArrayList<Evaluacion> evaluaciones = new ArrayList<Evaluacion>();

	public Portafolio(Grados grado) {
		this.nivel = NivelesDeEducacion.PRIMARIA;
		this.grado = grado;
	}
	
	public NivelesDeEducacion getNivel() {
		return this.nivel;
	}
	public Grados getGrado() {
		return this.grado;
	}
	public ArrayList<Evaluacion> getEvaluaciones() {
		return this.evaluaciones;
	}

	public Boolean agregarEvaluacion(Evaluacion evaluacion) {
		if(evaluacion.getNivel().equals(this.nivel) && evaluacion.getGrado().equals(this.grado)) {
			if(!this.evaluaciones.contains(evaluacion) && evaluacion.getDocente() != null && evaluacion.getNota() != null) {
				return this.evaluaciones.add(evaluacion);
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public String toString() {
		return "Portafolio [nivel=" + nivel + ", grado=" + grado + "]";
	}
}
