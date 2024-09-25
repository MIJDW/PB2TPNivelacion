package ar.edu.unlam.pb2.establecimiento;
import ar.edu.unlam.pb2.enumsDatos.*;
import ar.edu.unlam.pb2.info.*;
import ar.edu.unlam.pb2.personas.*;

public class DivisionPrimaria extends EstructuraEducativaDivisiones {

	protected Grados grado;

	public DivisionPrimaria(Grados grado) {
		super.nivel = NivelesDeEducacion.PRIMARIA;
		this.grado = grado;
	}

	public Grados getGrado() {
		return grado;
	}

	public Boolean agregarAlumnoAPrimaria(Alumno alumno) {
		GradoInfoAlumnoPrimaria gradoInfoAlumnoPrimaria = new GradoInfoAlumnoPrimaria(this.grado);
		NivelInfoAlumno nivelInfoAlumno = alumno.obtenerNivelDeEducacion(super.nivel);
		if(alumno.agregarNivelDeEducacion(super.nivel) || nivelInfoAlumno != null) {
			if(alumno.obtenerGradoEnCurso(super.nivel, this.grado) != null || alumno.agregarGradoParaCursar(gradoInfoAlumnoPrimaria)) {
				return super.agregarAlumno(alumno);
			}
		}
		
		return Boolean.FALSE;
	}

	public Boolean agregarDocenteAPrimaria(Docente docente) {
		GradoInfoPrimaria gradoInfoPrimaria = docente.obtenerGradoDeCompetencia(super.nivel, this.grado);
		if(gradoInfoPrimaria != null) {
			return super.agregarDocente(docente);
		}
		return Boolean.FALSE;
	}

	@Override
	public String toString() {
		return "DivisionPrimaria [grado=" + grado + ", nivel=" + nivel + "]";
	}
}
