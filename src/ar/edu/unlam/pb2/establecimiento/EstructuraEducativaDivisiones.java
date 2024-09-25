package ar.edu.unlam.pb2.establecimiento;
import java.util.LinkedList;
import ar.edu.unlam.pb2.enumsDatos.*;
import ar.edu.unlam.pb2.info.*;
import ar.edu.unlam.pb2.personas.*;

public class EstructuraEducativaDivisiones {
	
	protected NivelesDeEducacion nivel;
	protected LinkedList<Alumno> alumnos = new LinkedList<Alumno>();
	protected LinkedList<Docente> docentes = new LinkedList<Docente>();
	
	protected static final Integer CANTIDAD_MAXIMA_DE_ALUMNOS = 30;
	protected static Integer cantidadMaximaDeDocentes = 0;
	
	public EstructuraEducativaDivisiones() {
	}
	
	public NivelesDeEducacion getNivel() {
		return nivel;
	}
	public LinkedList<Alumno> getAlumnos() {
		return alumnos;
	}
	public LinkedList<Docente> getDocentes() {
		return docentes;
	}
	public static Integer getCantidadMaximaDeDocentes() {
		return cantidadMaximaDeDocentes;
	}

	private Integer establecerCantidadDeDocentesAcordeANivel() {
		Integer cantidadDocentes = 0;
		if(this.nivel.equals(NivelesDeEducacion.JARDIN)) {
			cantidadDocentes = 2;
		}else if(this.nivel.equals(NivelesDeEducacion.PRIMARIA)) {
			cantidadDocentes = 1;
		}else {
			cantidadDocentes = Materias.values().length;
		}
		return cantidadDocentes;
	}
	
	public Boolean agregarAlumno(Alumno alumno) {
		if(this.alumnos.size() < CANTIDAD_MAXIMA_DE_ALUMNOS) {
			if(!this.alumnos.contains(alumno) && alumno.elAlumnoEstaDentroDelRangoDeEdad(this.nivel)) {
				return this.alumnos.add(alumno);
			}
		}
		return Boolean.FALSE;
	}
	
	public Boolean eliminarAlumno(Integer dni) {
		return this.alumnos.remove(obtenerAlumno(dni));
	}
	
	private Alumno obtenerAlumno(Integer dni) {
		Alumno buscado = null;
		for(Alumno alumno : getAlumnos()) {
			if(alumno.getDni().equals(dni)) {
				buscado = alumno;
			}
		}
		return buscado;
	}
	
	public Boolean agregarDocente(Docente docente) {
		cantidadMaximaDeDocentes = establecerCantidadDeDocentesAcordeANivel();
		if(this.docentes.size() < cantidadMaximaDeDocentes) {
			if(!this.docentes.contains(docente)) {
				for(NivelInfo nivelInfo : docente.getNiveles()) {
					if(nivelInfo.getNivel().equals(this.nivel)) {
						return this.docentes.add(docente);
					}
				}
			}
		}
		return Boolean.FALSE;
	}
	
	public Boolean eliminarDocente(Integer dni) {
		return this.docentes.remove(obtenerDocente(dni));
	}
	
	protected Docente obtenerDocente(Integer dni) {
		Docente buscado = null;
		for(Docente docente : getDocentes()) {
			if(docente.getDni().equals(dni)) {
				buscado = docente;
			}
		}
		return buscado;
	}

	@Override
	public String toString() {
		return "EstructuraEducativaDivisiones [nivel=" + nivel + ", CANTIDAD_MAXIMA_DE_ALUMNOS="
				+ CANTIDAD_MAXIMA_DE_ALUMNOS + ", cantidadMaximaDeDocentes=" + cantidadMaximaDeDocentes + "]";
	}
}
