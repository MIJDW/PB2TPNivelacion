package ar.edu.unlam.pb2.info;

import java.util.ArrayList;
import ar.edu.unlam.pb2.enumsDatos.*;
import ar.edu.unlam.pb2.evaluaciones.*;
import ar.edu.unlam.pb2.portafolios.*;

public class GradoInfoAlumnoSecundaria extends GradoInfoAlumnoPrimaria{
	
	private ArrayList<Materias> materias = new ArrayList<Materias>();

	public GradoInfoAlumnoSecundaria(Grados grado) {
		super(grado);
		super.nivel = NivelesDeEducacion.SECUNDARIA;
		inicializarPortafolios();
	}
	
	@Override
	protected void inicializarPortafolios() {
		if(super.nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			for(Materias materia : Materias.values()) {
				this.materias.add(materia);
				super.portafolios.add(new PortafolioDeSecundaria(super.grado, materia));
			}
		}
	}
	
	public PortafolioDeSecundaria obtenerPortafolioDeSecundaria(Materias materia) {
		PortafolioDeSecundaria buscado = null;
		for(Portafolio portafolio : super.getPortafolios()) {
			PortafolioDeSecundaria secundaria = (PortafolioDeSecundaria) portafolio;
			if(secundaria.getMateria().equals(materia)) {
				buscado = secundaria;
			}
		}
		return buscado;
	}
	
	@Override
	public void comprobarSituacionAcademica() {
		Integer cantidadDeEvaluaciones = 0;
		Integer cantidadDeAprobadas = 0;
		for(Portafolio portafolio : getPortafolios()) {
			PortafolioDeSecundaria portafolioDeSecundaria = (PortafolioDeSecundaria) portafolio;
			if(portafolioDeSecundaria.getEvaluaciones().size() > 0) {
				for(Evaluacion evaluacion : portafolioDeSecundaria.getEvaluaciones()) {
					EvaluacionDeSecundaria evaluacionDeSecundaria = (EvaluacionDeSecundaria) evaluacion;
					cantidadDeEvaluaciones++;
					if(evaluacionDeSecundaria.getAprobado()) {
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
		return "GradoInfoAlumnoSecundaria [materias=" + materias + ", aprobado=" + aprobado + ", nivel=" + nivel
				+ ", grado=" + grado + "]";
	}
}
