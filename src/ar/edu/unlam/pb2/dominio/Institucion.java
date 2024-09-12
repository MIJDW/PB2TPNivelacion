package ar.edu.unlam.pb2.dominio;

import java.util.Arrays;

public class Institucion {
	
	private final Integer CANTIDAD_MAXIMA_DE_DOCENTES = 100;
	
	private String nombre; 
	private Nivel[] niveles = new Nivel[NivelesDeEducacion.values().length];
	
	private Alumno[] alumnos;
	private Docente[] docentes = new Docente[this.CANTIDAD_MAXIMA_DE_DOCENTES];
	
	public Institucion(String nombre, Integer cantidadDeAlumnosTotalDeLaInstitucion) {
		this.nombre = nombre;
		alumnos = new Alumno[cantidadDeAlumnosTotalDeLaInstitucion];
	}
	
	public String getNombre() {
		return this.nombre;
	}
	public Nivel[] getNiveles() {
		return this.niveles;
	}
	public Alumno[] getAlumnos() {
		return this.alumnos;
	}
	public Docente[] getDocentes() {
		return this.docentes;
	}
	
	public Boolean contieneNivel(NivelesDeEducacion nivel) {
		Boolean contiene = Boolean.FALSE;
		if(this.niveles[nivel.ordinal()] != null) {
			contiene = Boolean.TRUE;
		}
		return contiene;
	}
	
	private Integer cantidadAlumnosParaNivel(NivelesDeEducacion nivel) {
		Integer cantidad = 0;
		if(nivel.equals(NivelesDeEducacion.JARDIN)) {
			cantidad = (this.alumnos.length / 5);
		}else if(!nivel.equals(NivelesDeEducacion.JARDIN)) {
			cantidad = (this.alumnos.length / 5) * 2;
		}
		return cantidad;
	}
	
	public Boolean agregarNivel(NivelesDeEducacion nivel) {
		Boolean agrego = Boolean.FALSE;
		if(!contieneNivel(nivel)) {
			this.niveles[nivel.ordinal()] = new Nivel(nivel, cantidadAlumnosParaNivel(nivel));
			agrego = Boolean.TRUE;
		}
		return agrego;
	}
	
	public Boolean eliminarNivel(NivelesDeEducacion nivel) {
		Boolean elimino = Boolean.FALSE;
		if(contieneNivel(nivel)) {
			this.niveles[nivel.ordinal()] = null;
			elimino = Boolean.TRUE;
		}
		return elimino;
	}
	
	public Nivel obtenerNivel(NivelesDeEducacion nivel) {
		Nivel niv = null;
		if(contieneNivel(nivel)) {
			niv = this.niveles[nivel.ordinal()];
		}
		return niv;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	private Boolean contieneAlumno(Alumno alumno) {
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
	
	public Boolean agregarAlumno(Alumno alumno) {
		Boolean agrego = Boolean.FALSE;
		if(!contieneAlumno(alumno)) {
			for(Integer i = 0; i < this.alumnos.length && !agrego; i++) {
				if(this.alumnos[i] == null) {
					this.alumnos[i] = alumno;
					agrego = Boolean.TRUE;
				}
			}
		}
		return agrego;
	}
	
	public void distribuirAlumnosANiveles() {
		for(Integer i = 0; i < this.alumnos.length; i++) {
			if(this.alumnos[i] != null) {
				for(Integer j = (this.niveles.length - 1); j >= 0; j--) {
					if(this.niveles[j] != null) {
						if(this.alumnos[i].elAlumnoEstaDentroDelRangoDeEdad(this.niveles[j].getNivel())) {
							if(this.alumnos[i].contieneNivelDeEducacion(this.niveles[j].getNivel())) {
								this.niveles[j].agregarAlumno(this.alumnos[i]);
							}
						}
					}
				}
			}
		}
	}
	
	private Nivel nivelQueContieneAlumno(Alumno alumno) {
		Nivel niv = null;
		for(Integer i = 0; i < this.niveles.length; i++) {
			if(this.niveles[i] != null) {
				if(this.niveles[i].contieneAlumno(alumno)) {
					niv = this.niveles[i];
				}
			}
		}
		return niv;
	}
	
	public Boolean eliminarAlumno(Alumno alumno) {
		Boolean eliminar = Boolean.FALSE;
		for(Integer i = 0; i < this.alumnos.length && !eliminar; i++) {
			if(this.alumnos[i] != null) {
				if(this.alumnos[i].equals(alumno)) {
					this.alumnos[i] = null;
					eliminar = Boolean.TRUE;
					Nivel niv = nivelQueContieneAlumno(alumno);
					if(niv != null) {
						niv.eliminarAlumno(alumno);
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
	
	public Alumno[] obtenerAlumnosOrdenadosPorEdad() {
		Alumno[] alumnosOrdenados = new Alumno[this.alumnos.length];
		for(Integer i = 0; i < alumnosOrdenados.length; i++) {
			alumnosOrdenados[i] = this.alumnos[i];
		}
		
		Alumno auxiliar = null;
		
		for(Integer i = 0; i < (alumnosOrdenados.length - 1); i++) {
			for(Integer j = 0; j < (alumnosOrdenados.length - 1); j++) {
				if(alumnosOrdenados[j + 1] != null) {
					if(alumnosOrdenados[j + 1].getEdad() <= alumnosOrdenados[j].getEdad()) {
						auxiliar = alumnosOrdenados[j + 1];
						alumnosOrdenados[j + 1] = alumnosOrdenados[j];
						alumnosOrdenados[j] = auxiliar;
					}
				}
			}
		}
		
		return alumnosOrdenados;
	}
	
	//-------------------------------------------------------------------------------------------------------------
	
	private Boolean contieneDocente(Docente docente) {
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
	
	public Boolean agregarDocente(Docente docente) {
		Boolean agrego = Boolean.FALSE;
		if(!contieneDocente(docente)) {
			for(Integer i = 0; i < this.docentes.length && !agrego; i++) {
				if(this.docentes[i] == null) {
					this.docentes[i] = docente;
					agrego = Boolean.TRUE;
				}
			}
		}
		return agrego;
	}
	
	private Nivel[] nivelesQueContienenDocente(Docente docente) {
		Nivel[] niveles = new Nivel[this.niveles.length];
		Integer contador = 0;
		for(Integer i = 0; i < this.niveles.length; i++) {
			if(this.niveles[i] != null) {
				if(this.niveles[i].contieneDocente(docente)) {
					niveles[contador++] = this.niveles[i];
				}
			}
		}
				
		return niveles;		
	}
	
	public Boolean eliminarDocente(Docente docente) {
		Boolean eliminar = Boolean.FALSE;
		if(contieneDocente(docente)) {
			for(Integer i = 0; i < this.docentes.length && !eliminar; i++) {
				if(this.docentes[i] != null) {
					if(this.docentes[i].equals(docente)) {
						this.docentes[i] = null;
						eliminar = Boolean.TRUE;
					}
				}
			}
			Nivel[] niv = nivelesQueContienenDocente(docente);
			for(Integer j = 0; j < niv.length; j++) {
				if(niv[j] != null) {
					niv[j].eliminarDocente(docente);
				}
			}
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

	@Override
	public String toString() {
		return "Institucion [nombre=" + nombre + ", niveles=" + Arrays.toString(niveles) + "]";
	}
	
}
