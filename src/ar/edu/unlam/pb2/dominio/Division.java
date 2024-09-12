package ar.edu.unlam.pb2.dominio;

import java.util.Arrays;

public class Division {

	private NivelesDeEducacion nivel;
	private Salas sala;
	private Grados grado;
	private Materias[] materias;
	
	private Alumno[] alumnos;
	private Docente[] docentes;
	
	public Division(NivelesDeEducacion nivel, Salas sala, Grados grado, Integer cantidadDeAlumnos) {
		this.nivel = nivel;
		agregarSala(sala);
		agregarGrado(grado);
		iniciarMaterias();
		this.alumnos = new Alumno[cantidadDeAlumnos];
		establecerCantidadDeDocentesAcordeANivel();
	}
	
	public NivelesDeEducacion getNivel() {
		return this.nivel;
	}
	public Salas getSala() {
		return this.sala;
	}
	public Grados getGrado() {
		return this.grado;
	}
	public Alumno[] getAlumnos() {
		return this.alumnos;
	}
	public Docente[] getDocentes() {
		return this.docentes;
	}
	
	private void agregarSala(Salas sala) {
		if(this.nivel.equals(NivelesDeEducacion.JARDIN)) {
			this.sala = sala;
		}
	}
	
	private void agregarGrado(Grados grado) {
		if(!this.nivel.equals(NivelesDeEducacion.JARDIN)) {
			this.grado = grado;
		}
	}
	
	private void iniciarMaterias() {
		if(this.nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			this.materias = new Materias[Materias.values().length];
			for(Integer i = 0; i < this.materias.length; i++) {
				this.materias[i] = Materias.values()[i];
			}
		}
	}
	
	private void establecerCantidadDeDocentesAcordeANivel() {
		Integer cantidadDocentes = 0;
		if(this.nivel.equals(NivelesDeEducacion.JARDIN)) {
			cantidadDocentes = 2;
		}else if(this.nivel.equals(NivelesDeEducacion.PRIMARIA)) {
			cantidadDocentes = 1;
		}else {
			cantidadDocentes = this.materias.length;
		}
		this.docentes = new Docente[cantidadDocentes];
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public Boolean contieneAlumno(Alumno alumno) {
		Boolean contiene = Boolean.FALSE;
		for(Integer i = 0; i < this.alumnos.length && !contiene; i++) {
			if(this.alumnos[i] != null) {
				if(this.alumnos[i].getDni().equals(alumno.getDni())) {
					contiene = Boolean.TRUE;
				}
			}
		}
		return contiene;
	}
	
	public Integer edadDelAlumnoMinima() {
		Integer edad = 0;
		if(this.nivel.equals(NivelesDeEducacion.JARDIN)) {
			edad = this.sala.ordinal() + 2;
		}else if(this.nivel.equals(NivelesDeEducacion.PRIMARIA)) {
			edad = this.grado.ordinal() + 6;
		}else if(this.nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			edad = this.grado.ordinal() + 12;
		}
		return edad;
	}
	
	private Boolean alumnoCumpleConEdad(Alumno alumno) {
		Boolean cumple = Boolean.FALSE;
		if(edadDelAlumnoMinima() <= alumno.getEdad()) {
			cumple = Boolean.TRUE;
		}
		return cumple;
	}
	
	private Boolean alumnoQueNoEsDeJardin(Alumno alumno) {
		Boolean noJardin = Boolean.FALSE;
		if(!this.nivel.equals(NivelesDeEducacion.JARDIN) && alumno.contieneGrado(this.nivel, this.grado)) {
			noJardin = Boolean.TRUE;
		}
		return noJardin;
	}
	
	private Boolean alumnoDeJardin(Alumno alumno) {
		Boolean jardin = Boolean.FALSE;
		if(this.nivel.equals(NivelesDeEducacion.JARDIN) && alumno.contieneSala(this.sala)) {
			jardin = Boolean.TRUE;
		}
		return jardin;
	}
	
	public Boolean agregarAlumno(Alumno alumno) {
		Boolean agregar = Boolean.FALSE;
		if(!contieneAlumno(alumno) && alumnoCumpleConEdad(alumno)) {
			if(alumnoQueNoEsDeJardin(alumno) || alumnoDeJardin(alumno)) {
				for(Integer i = 0; i < this.alumnos.length && !agregar; i++) {
					if(this.alumnos[i] == null) {
						this.alumnos[i] = alumno;
						agregar = Boolean.TRUE;
					}
				}
			}
		}
		return agregar;
	}
	
	public Boolean eliminarAlumno(Alumno alumno) {
		Boolean eliminar = Boolean.FALSE;
		if(contieneAlumno(alumno)) {
			for(Integer i = 0; i < this.alumnos.length && !eliminar; i++) {
				if(this.alumnos[i] != null) {
					if(this.alumnos[i].equals(alumno)) {
						this.alumnos[i] = null;
						eliminar = Boolean.TRUE;
					}
				}
			}
		}
		return eliminar;
	}
	
	public Alumno obtenerAlumno(Integer dni) {
		Alumno alumno = null;
		Boolean encontro = Boolean.FALSE;
		for(Integer i = 0; i < this.alumnos.length && !encontro; i++) {
			if(this.alumnos[i] != null) {
				if(this.alumnos[i].getDni().equals(dni)) {
					alumno = this.alumnos[i];
					encontro = Boolean.TRUE;
				}
			}
		}
		return alumno;
	}
	
	public Alumno[] obtenerListaOrdenadaDeAlumnosPorNombre() {
		Alumno[] alumnosOrdenados = new Alumno[this.alumnos.length];
		for(Integer i = 0; i < this.alumnos.length; i++) {
			alumnosOrdenados[i] = this.alumnos[i];
		}
		
		Alumno auxiliar = null;
		
		for(Integer i = 0; i < (alumnosOrdenados.length - 1); i++) {
			for(Integer j = 0; j < (alumnosOrdenados.length -1); j++) {
				if(alumnosOrdenados[j + 1] != null) {
					if(alumnosOrdenados[j + 1].getNombre().compareToIgnoreCase(alumnosOrdenados[j].getNombre()) < 0) {
						auxiliar = alumnosOrdenados[j + 1];
						alumnosOrdenados[j + 1] = alumnosOrdenados[j];
						alumnosOrdenados[j] = auxiliar;
					}
				}
			}
		}
		return alumnosOrdenados;
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public Boolean contieneDocente(Docente docente) {
		Boolean contiene = Boolean.FALSE;
		for(Integer i = 0; i < this.docentes.length && !contiene; i++) {
			if(this.docentes[i] != null) {
				if(this.docentes[i].equals(docente)) {
					contiene = Boolean.TRUE;
				}
			}
		}
		return contiene;
	}
	
	private Boolean docenteParaJardin(Docente docente) {
		Boolean jardin = Boolean.FALSE;
		if(this.nivel.equals(NivelesDeEducacion.JARDIN) && docente.contieneNivelDeCompetencia(this.nivel)) {
			jardin = Boolean.TRUE;
		}
		return jardin;
	}
	
	private Boolean docenteParaPrimaria(Docente docente) {
		Boolean primaria = Boolean.FALSE;
		if(this.nivel.equals(NivelesDeEducacion.PRIMARIA) && docente.contieneGradoDeCompetencia(this.nivel, this.grado)) {
				primaria = Boolean.TRUE;
		}
		return primaria;
	}
	
	private Boolean docenteDeSecundaria(Docente docente) {
		Boolean secundaria = Boolean.FALSE;
		if(this.nivel.equals(NivelesDeEducacion.SECUNDARIA) && this.materias != null) {
			if(docente.contieneGradoDeCompetencia(this.nivel, this.grado)) {
				secundaria = Boolean.TRUE;
			}
		}
		return secundaria;
	}
	
	public Boolean agregarDocente(Docente docente) {
		Boolean agregar = Boolean.FALSE;
		if(!contieneDocente(docente)) {
			if(docenteParaJardin(docente) || docenteParaPrimaria(docente)) {
				for(Integer i = 0; i < this.docentes.length && !agregar; i++) {
					if(this.docentes[i] == null) {
						this.docentes[i] = docente;
						agregar = Boolean.TRUE;
					}
				}
			}
		}
		return agregar;
	}
	
	public Boolean agregarDocenteDeSecundariaSegunMateria(Docente docente, Materias materia) {
		Boolean agregar = Boolean.FALSE;
		if(docenteDeSecundaria(docente)) {
			if(docente.obtenerGradoDeCompetencia(this.nivel, this.grado).contieneMateria(materia)) {
				this.docentes[materia.ordinal()] = docente;
				agregar = Boolean.TRUE;
			}
		}
		return agregar;
	}
	
	public Boolean eliminarDocente(Docente docente) {
		Boolean eliminar = Boolean.FALSE;
		if(contieneDocente(docente)) {
			for(Integer i = 0; i < this.docentes.length; i++) {
				if(this.docentes[i] != null) {
					if(this.docentes[i].equals(docente)) {
						this.docentes[i] = null;
						eliminar = Boolean.TRUE;
					}
				}
			}
		}
		return eliminar;
	}
	
	public Boolean eliminarDocenteDeSecundariaSegunMateria(Materias materia) {
		Boolean eliminar = Boolean.FALSE;
		if(this.docentes[materia.ordinal()] != null) {
			this.docentes[materia.ordinal()] = null;
			eliminar = Boolean.TRUE;
		}
		return eliminar;
	}
	
	public Docente obtenerDocente(Integer dni) {
		Docente docente = null;
		for(Integer i = 0; i < this.docentes.length; i++) {
			if(this.docentes[i] != null) {
				if(this.docentes[i].getDni().equals(dni)) {
					docente = this.docentes[i];
				}
			}
		}
		return docente;
	}
	
	public Docente obtenerDocenteDeSecundariaSegunMateria(Materias materia) {
		Docente docente = null;
		if(this.docentes[materia.ordinal()] != null) {
			docente = this.docentes[materia.ordinal()];
		}
		return docente;
	}

	@Override
	public String toString() {
		return "Division [nivel=" + nivel + ", sala=" + sala + ", grado=" + grado + ", materias="
				+ Arrays.toString(materias) + "]";
	}
	
}
