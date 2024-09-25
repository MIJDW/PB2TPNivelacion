package ar.edu.unlam.pb2.establecimiento;
import java.util.LinkedList;

import ar.edu.unlam.pb2.enumsDatos.*;
import ar.edu.unlam.pb2.info.*;
import ar.edu.unlam.pb2.personas.*;

public class DivisionJardin extends EstructuraEducativaDivisiones{
	
	private Salas sala;
	
	public DivisionJardin(Salas sala) {
		super.nivel = NivelesDeEducacion.JARDIN;
		this.sala = sala;
	}

	public Salas getSala() {
		return sala;
	}
	
	public void limpiarListaAlumno() {
		super.alumnos = new LinkedList<Alumno>();
	}
	
	public void limpiarListaDocente() {
		super.docentes = new LinkedList<Docente>();
	}
	
	public Boolean agregarAlumnoAJardin(Alumno alumno) {
		NivelInfoAlumno nivelInfoAlumno = alumno.obtenerNivelDeEducacion(super.nivel);
		if(alumno.getEdad().equals(this.sala.ordinal() + 2)) {
			if(alumno.agregarNivelDeEducacion(super.nivel) || nivelInfoAlumno != null) {
				if(agregarAlumno(alumno)) {
					alumno.agregarSala(this.sala);
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}
	public Boolean agregarAlumnoAJardinParaCombinar(Alumno alumno) {
		NivelInfoAlumno nivelInfoAlumno = alumno.obtenerNivelDeEducacion(super.nivel);
		if(alumno.agregarNivelDeEducacion(super.nivel) || nivelInfoAlumno != null) {
			if(agregarAlumno(alumno)) {
				alumno.agregarSala(this.sala);
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
	@Override
	public String toString() {
		return "DivisionJardin [sala=" + sala + ", nivel=" + nivel + "]";
	}
}
