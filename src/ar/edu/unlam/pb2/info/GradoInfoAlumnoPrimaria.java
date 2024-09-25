package ar.edu.unlam.pb2.info;
import java.util.ArrayList;

import ar.edu.unlam.pb2.enumsDatos.*;
import ar.edu.unlam.pb2.evaluaciones.*;
import ar.edu.unlam.pb2.portafolios.*;

public class GradoInfoAlumnoPrimaria extends GradoInfoPrimaria{
	
	protected ArrayList<Portafolio> portafolios = new ArrayList<Portafolio>();
	protected Boolean aprobado;

	public GradoInfoAlumnoPrimaria(Grados grado) {
		super(grado);
		inicializarPortafolios();
	}
	
	public ArrayList<Portafolio> getPortafolios() {
		return this.portafolios;
	}
	public Boolean getAprobado() {
		return this.aprobado;
	}
	
	public void aprobar() {
		this.aprobado = Boolean.TRUE;
	}
	
	protected void inicializarPortafolios() {
		if(super.nivel.equals(NivelesDeEducacion.PRIMARIA)) {
			this.portafolios.add(new Portafolio(this.grado));
		}
	}
	
	public Portafolio obtenerPortafolio() {
		Portafolio portafolio = this.portafolios.get(0);
		return portafolio;
	}
	
	public void comprobarSituacionAcademica() {
		Integer cantidadDeEvaluaciones = 0;
		Integer cantidadDeAprobadas = 0;
		for(Portafolio portafolio : getPortafolios()) {
			if(portafolio.getEvaluaciones().size() > 0) {
				for(Evaluacion evaluacion : portafolio.getEvaluaciones()) {
					cantidadDeEvaluaciones++;
					if(evaluacion.getAprobado()) {
						cantidadDeAprobadas++;
					}
				}
			}
		}
		if(cantidadDeEvaluaciones == cantidadDeAprobadas) {
			this.aprobado = Boolean.TRUE;
		}else {
			this.aprobado = Boolean.FALSE;
		}
	}
	
	@Override
	public String toString() {
		return "GradoInfoAlumnoPrimaria [aprobado=" + aprobado + ", nivel=" + nivel + ", grado=" + grado + "]";
	}
}
