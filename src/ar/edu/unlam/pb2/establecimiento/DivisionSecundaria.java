package ar.edu.unlam.pb2.establecimiento;
import java.util.ArrayList;
import java.util.LinkedList;
import ar.edu.unlam.pb2.enumsDatos.*;
import ar.edu.unlam.pb2.info.*;
import ar.edu.unlam.pb2.personas.*;

public class DivisionSecundaria extends DivisionPrimaria{

	private ArrayList<Materias> materias = new ArrayList<Materias>();
	private LinkedList<MateriaOcupada> materiasOcupadas = new LinkedList<MateriaOcupada>();
	
	public DivisionSecundaria(Grados grado) {
		super(grado);
		super.nivel = NivelesDeEducacion.SECUNDARIA;
		iniciarMaterias();
	}

	public ArrayList<Materias> getMaterias() {
		return materias;
	}
	public LinkedList<MateriaOcupada> getMateriasOcupadas() {
		return materiasOcupadas;
	}

	private void iniciarMaterias() {
		for(Materias materia : Materias.values()) {
			if(!this.materias.contains(materia)) {
				this.materias.add(materia);
			}
		}
	}
	
	public Boolean agregarAlumnoASecundaria(Alumno alumno) {
		GradoInfoAlumnoSecundaria gradoInfoAlumnoSecundaria = new GradoInfoAlumnoSecundaria(this.grado);
		NivelInfoAlumno nivelInfoAlumno = alumno.obtenerNivelDeEducacion(super.nivel);
		if(alumno.agregarNivelDeEducacion(super.nivel) || nivelInfoAlumno != null) {
			if(alumno.obtenerGradoEnCurso(super.nivel, this.grado) != null || alumno.agregarGradoParaCursar(gradoInfoAlumnoSecundaria)) {
				return super.agregarAlumno(alumno);
			}
		}
		return Boolean.FALSE;
	}
	
	public Boolean agregarDocenteASecundaria(Docente docente, Materias materia){
		MateriaOcupada materiaAOcupar = new MateriaOcupada(docente, materia);
		GradoInfoDocenteSecundaria gradoInfoDocenteSecundaria = (GradoInfoDocenteSecundaria)docente.obtenerGradoDeCompetencia(super.nivel, super.grado);
		if(!this.materiasOcupadas.contains(materiaAOcupar)) {
			for(Materias materiaBuscada : gradoInfoDocenteSecundaria.getMaterias()) {
				if(materiaBuscada.equals(materia)) {
					if(agregarDocente(docente)) {
						return this.materiasOcupadas.add(materiaAOcupar);
					}
				}
			}
		}
		return Boolean.FALSE;
	}
	
	public Boolean eliminarDocenteDeSecundaria(Docente docente) {
		Docente buscado = obtenerDocente(docente.getDni());
		if(buscado != null) {
			for(MateriaOcupada materiO : getMateriasOcupadas()) {
				if(materiO.getDocente().equals(docente)) {
					if(this.materiasOcupadas.remove(materiO)) {
						return eliminarDocente(docente.getDni());
					}
				}
			}	
		}
		return Boolean.FALSE;
	}
	
}
