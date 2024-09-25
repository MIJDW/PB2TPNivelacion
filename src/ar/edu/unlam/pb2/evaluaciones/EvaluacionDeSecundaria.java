package ar.edu.unlam.pb2.evaluaciones;
import ar.edu.unlam.pb2.enumsDatos.*;
import ar.edu.unlam.pb2.personas.*;

public class EvaluacionDeSecundaria extends Evaluacion{
	private Materias materia;

	public EvaluacionDeSecundaria(Grados grado, Materias materia) {
		super(grado);
		super.nivel = NivelesDeEducacion.SECUNDARIA;
		this.materia = materia;
	}
	
	public Materias getMateria() {
		return this.materia;
	}
	
	@Override
	public Boolean agregarDocenteYFecha(Docente docente, String fecha) {
		if(this.materia != null) {
			return super.agregarDocenteYFecha(docente, fecha);
		}
		return Boolean.FALSE;
	}

	@Override
	public String toString() {
		return "EvaluacionDeSecundaria [materia=" + materia + ", fecha=" + fecha + ", nivel=" + nivel + ", grado="
				+ grado + ", docente=" + docente + ", nota=" + nota + ", aprobado=" + aprobado + "]";
	}
	
}
