package ar.edu.unlam.pb2.portafolios;
import ar.edu.unlam.pb2.enumsDatos.*;
import ar.edu.unlam.pb2.evaluaciones.*;

public class PortafolioDeSecundaria extends Portafolio{
	
	private Materias materia;
	
	public PortafolioDeSecundaria(Grados grado, Materias materia) {
		super(grado);
		super.nivel = NivelesDeEducacion.SECUNDARIA;
		this.materia = materia;
	}

	public Materias getMateria() {
		return this.materia;
	}

	@Override
	public Boolean agregarEvaluacion(Evaluacion evaluacion) {
		EvaluacionDeSecundaria eva = (EvaluacionDeSecundaria)evaluacion;
		if(eva.getMateria().equals(this.materia)) {
			return super.agregarEvaluacion(evaluacion);
		}
		return Boolean.FALSE;
	}

	@Override
	public String toString() {
		return "PortafolioDeSecundaria [materia=" + materia + ", nivel=" + nivel + ", grado=" + grado + "]";
	}
}
