package ar.edu.unlam.pb2.dominio;

public class Nivel {

	private NivelesDeEducacion nivel;
	private Division[] divisiones;
	
	private Alumno[] alumnos;
	private Docente[] docentes;
	
	public Nivel(NivelesDeEducacion nivel, Integer cantidadDeAlumnosDelNivel) {
		this.nivel = nivel;
		this.alumnos = new Alumno[cantidadDeAlumnosDelNivel];
		this.docentes = new Docente[cantidadDeProfesoresTotalesParaNivel(nivel)];
		inicializarDivisiones();
		crearDivisiones(cantidadDeAlumnosDelNivel);
	}
	
	public NivelesDeEducacion getNivel() {
		return this.nivel;
	}
	public Division[] getDivisiones() {
		return this.divisiones;
	}
	public Alumno[] getAlumnos() {
		return this.alumnos;
	}
	public Docente[] getDocentes() {
		return this.docentes;
	}
	
	private void inicializarDivisiones() {
		if(!this.nivel.equals(NivelesDeEducacion.JARDIN)) {
			this.divisiones = new Division[Grados.values().length];
		}else {
			this.divisiones = new Division[Salas.values().length];
		}
	}
	
	private void crearDivisiones(Integer cantidadDeAlumnosTotalDelNivel) {
		for(Integer i = 0; i < this.divisiones.length; i++) {
			if(!this.nivel.equals(NivelesDeEducacion.JARDIN)) {
				this.divisiones[i] = new Division(this.nivel, null, Grados.values()[i], (cantidadDeAlumnosTotalDelNivel / this.divisiones.length));
			}else {
				this.divisiones[i] = new Division(this.nivel, Salas.values()[i], null, (cantidadDeAlumnosTotalDelNivel / this.divisiones.length));
			}
		}
	}
	
	private Integer cantidadDeProfesoresTotalesParaNivel(NivelesDeEducacion nivel) {
		Integer total = 0;
		if(nivel.equals(NivelesDeEducacion.JARDIN)) {
			total = (Salas.values().length * 2);
		}else if(nivel.equals(NivelesDeEducacion.PRIMARIA)) {
			total = Grados.values().length;
		}else if(nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			total = (Grados.values().length * Materias.values().length);
		}
		return total;
	}
	
	public Division obtenerDivisionDeNivelPrimarioOSecundario(Grados grado) {
		Division division = null;
		if(!this.nivel.equals(NivelesDeEducacion.JARDIN)) {
			for(Integer i = 0; i < this.divisiones.length; i++) {
				if(this.divisiones[i].getGrado().equals(grado)) {
					division = this.divisiones[i];
				}
			}
		}
		return division;
	}
	
	public Division obtenerDivisionDeJardin(Salas sala) {
		Division division = null;
		if(this.nivel.equals(NivelesDeEducacion.JARDIN)) {
			for(Integer i = 0; i < this.divisiones.length; i++) {
				if(this.divisiones[i].getSala().equals(sala)) {
					division = this.divisiones[i];
				}
			}
		}
		return division;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	private Boolean tieneEdadAdecuadaParaNivel(Alumno alumno) {
		Boolean tiene = Boolean.FALSE;
		if(alumno.elAlumnoEstaDentroDelRangoDeEdad(this.nivel)) {
			tiene = Boolean.TRUE;
		}
		return tiene;
	}
	
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
	
	public Boolean agregarAlumno(Alumno alumno) {
		Boolean agrego = Boolean.FALSE;
		if(tieneEdadAdecuadaParaNivel(alumno) && !contieneAlumno(alumno)) {
			for(Integer i = 0; i < this.alumnos.length && !agrego; i++) {
				if(this.alumnos[i] == null) {
					this.alumnos[i] = alumno;
					agrego = Boolean.TRUE;
				}
			}
		}
		return agrego;
	}
	
	private Division divisionQueContieneAlumno(Alumno alumno) {
		Division division = null;
		for(Integer i = 0; i < this.divisiones.length; i++) {
			if(this.divisiones[i] != null) {
				if(this.divisiones[i].contieneAlumno(alumno)) {
					division = this.divisiones[i];
				}
			}
		}
		return division;
	}
	
	public Boolean eliminarAlumno(Alumno alumno) {
		Boolean eliminar = Boolean.FALSE;
		if(contieneAlumno(alumno)) {
			for(Integer i = 0; i < this.alumnos.length && !eliminar; i++) {
				if(this.alumnos[i] != null) {
					if(this.alumnos[i].equals(alumno)) {
						this.alumnos[i] = null;
						eliminar = Boolean.TRUE;
						divisionQueContieneAlumno(alumno).eliminarAlumno(alumno);
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
	
	private Boolean edadDeAlumnoDentroDelRangoDeRepetidores(Division division, Alumno alumno) {
		Boolean rango = Boolean.FALSE;
		if(alumno.getEdad() >= division.edadDelAlumnoMinima() && alumno.getEdad() <= (division.edadDelAlumnoMinima() + 2)) {
			rango = Boolean.TRUE;
		}
		return rango;
	}
	
	public void distribuirAlumnosADivisiones() {
		for(Integer i = 0; i < this.alumnos.length; i++) {
			if(this.alumnos[i] != null) {
				Boolean encontro = Boolean.FALSE;
				for(Integer j = (this.divisiones.length - 1); j >= 0 && !encontro; j--) {
					if(edadDeAlumnoDentroDelRangoDeRepetidores(this.divisiones[j] , this.alumnos[i])) {
						if(this.divisiones[j].agregarAlumno(this.alumnos[i])) {
							encontro = Boolean.TRUE;
						}
					}
				}
			}
		}
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
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
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
	
	public Boolean agregarDocente(Docente docente) {
		Boolean agrego = Boolean.FALSE;
		if(!contieneDocente(docente) && docente.contieneNivelDeCompetencia(this.nivel)) {
			for(Integer i = 0; i < this.docentes.length && !agrego; i++) {
				if(this.docentes[i] == null) {
					this.docentes[i] = docente;
					agrego = Boolean.TRUE;
				}
			}
		}
		return agrego;
	}
	
	private Division[] divisionesQueContienenDocente(Docente docente) {
		Division[] division = new Division[this.divisiones.length];
		Integer contador = 0;
		for(Integer i = 0; i < this.divisiones.length; i++) {
			if(this.divisiones[i] != null) {
				if(this.divisiones[i].contieneDocente(docente)) {
					division[contador++] = this.divisiones[i];
				}
			}
		}
		return division;
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
			Division[] div = divisionesQueContienenDocente(docente);
			for(Integer j = 0; j < div.length; j++) {
				if(div[j] != null) {
					div[j].eliminarDocente(docente);
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
		return "Nivel [nivel=" + nivel + "]";
	}
	
}
