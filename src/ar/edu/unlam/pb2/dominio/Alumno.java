package ar.edu.unlam.pb2.dominio;

public class Alumno {

	private NivelInfo[] nivelDeEducacionEnCurso = new NivelInfo[NivelesDeEducacion.values().length];
	private GradoInfo[] gradosCursados = new GradoInfo[Grados.values().length * 2];
	
	private Salas[] salasCursadas = new Salas[Salas.values().length];
	
	private String nombre;
	private String apellido;
	private Integer edad;
	private Integer dni;
	private Integer diasDelCicloEscolar;
	private Integer cantidadDeEvaluaciones;
	
	private Boolean evaluable = Boolean.FALSE;
	
	private String[][] asistencias;

	public Alumno(String nombre, String apellido, Integer edad, Integer dni, Integer diasDelCicloEscolar, Integer cantidadDeEvaluaciones) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.dni = dni;
		this.diasDelCicloEscolar = diasDelCicloEscolar;
		this.cantidadDeEvaluaciones = cantidadDeEvaluaciones;
		this.asistencias = new String[diasEscolaresPorAniosTotalesDeEducacion()][2];
	}
	
	public NivelInfo[] getNivelDeEducacionEnCurso() {
		return this.nivelDeEducacionEnCurso;
	}
	public GradoInfo[] getGradosCursados() {
		return this.gradosCursados;
	}
	public Salas[] getSalasCursadas() {
		return this.salasCursadas;
	}
	public String getNombre() {
		return this.nombre;
	}
	public Integer getDni() {
		return this.dni;
	}
	public Integer getEdad() {
		return this.edad;
	}
	public Boolean getEvaluable() {
		return this.evaluable;
	}
	public String[][] getAsistencias() {
		return this.asistencias;
	}

	private Integer diasEscolaresPorAniosTotalesDeEducacion() {
		return this.diasDelCicloEscolar * (this.salasCursadas.length + this.gradosCursados.length);		
	}
	
	private Boolean contieneFecha(String fecha) {
		Boolean contiene = Boolean.FALSE;
		for(Integer i = 0; i < this.asistencias.length && !contiene; i++) {
			if(this.asistencias[i][0] != null) {
				if(this.asistencias[i][0].equals(fecha)) {
					contiene = Boolean.TRUE;
				}
			}
		}
		return contiene;
	}
	
	public Boolean agregarAsistencia(String fecha, String situacion) {
		Boolean agrego = Boolean.FALSE;
		if(!contieneFecha(fecha)) {
			for(Integer i = 0; i < this.asistencias.length && !agrego; i++) {
				for(Integer j = 0; j < this.asistencias[i].length; j++) {
					if(this.asistencias[i][j] == null) {
						if(j == 0) {
							this.asistencias[i][j] = fecha;
						}else {
							this.asistencias[i][j] = situacion;
							agrego = Boolean.TRUE;
						}	
					}
				}
			}
		}
		return agrego;
	}
	
	public Boolean eliminarAsistencia(String fecha) {
		Boolean eliminar = Boolean.FALSE;
		if(contieneFecha(fecha)) {
			for(Integer i = 0; i < this.asistencias.length && !eliminar; i++) {
				if(this.asistencias[i] != null) {
					if(this.asistencias[i][0].equals(fecha)) {
						this.asistencias[i][0] = null;
						this.asistencias[i][1] = null;
						eliminar = Boolean.TRUE;
					}
				}
			}
		}
		return eliminar;
	}
	
	public Boolean modificarFechaExistente(String fecha, String situacion) {
		Boolean modifico = Boolean.FALSE;
		if(contieneFecha(fecha)) {
			for(Integer i = 0; i < this.asistencias.length && !modifico; i++) {
				if(this.asistencias[i][0] != null) {
					if(this.asistencias[i][0].equals(fecha)) {
						this.asistencias[i][1] = situacion;
						modifico = Boolean.TRUE;
					}
				}
			}
		}
		return modifico;
	}
	
	//---------------------------------------------------------------------------NIVEL-----------------------------------------------------------------------------
	
	public Boolean contieneNivelDeEducacion(NivelesDeEducacion nivel) {
		Boolean verificado = Boolean.FALSE;
		for(Integer i = 0; i < this.nivelDeEducacionEnCurso.length && !verificado; i++) {
			if(this.nivelDeEducacionEnCurso[i] != null) {
				if(this.nivelDeEducacionEnCurso[i].getNivel().equals(nivel)) {
					verificado = Boolean.TRUE;
				}
			}
		}
		return verificado;
	}
	
	private void alumnoEvaluable(){
		if(contieneNivelDeEducacion(NivelesDeEducacion.PRIMARIA) || contieneNivelDeEducacion(NivelesDeEducacion.SECUNDARIA)) {
			this.evaluable = Boolean.TRUE;	
		}
	}
	
	private Integer cantidadDeNivelesDeEducacion() {
		Integer cantidad = 0;
		for(Integer i = 0; i < this.nivelDeEducacionEnCurso.length; i++) {
			if(this.nivelDeEducacionEnCurso[i] != null) {
				cantidad++;
			}
		}
		return cantidad;
	}
	
	private Integer indiceDelNivelDeEducacionMasAlto() {
		Integer indice = 0;
		if(cantidadDeNivelesDeEducacion() > 0) {
			for(Integer i = 0; i < this.nivelDeEducacionEnCurso.length; i++) {
				if(this.nivelDeEducacionEnCurso[i] != null) {
					if(indice < this.nivelDeEducacionEnCurso[i].getNivel().ordinal()) {
						indice = this.nivelDeEducacionEnCurso[i].getNivel().ordinal();
					}
				}
			}
		}
		return indice;
	}
	
	private Integer indiceDeGradoMenorPorNivel(NivelesDeEducacion nivel) {
		Integer indice = -1;
		Boolean encontro = Boolean.FALSE;
		for(Integer i = 0; i < this.gradosCursados.length && !encontro; i++) {
			if(this.gradosCursados[i] != null) {
				if(this.gradosCursados[i].getNivel().equals(nivel)) {
					indice = i;
					encontro = Boolean.TRUE;
				}
			}
		}
		return indice;
	}
	
	private Integer indiceTopePorNivel(NivelesDeEducacion nivel) {
		Integer longitud = 0;
		if(nivel.equals(NivelesDeEducacion.PRIMARIA)) {
			longitud = Grados.values().length;
		}else if(nivel.equals(NivelesDeEducacion.SECUNDARIA)){
			longitud = this.nivelDeEducacionEnCurso.length;
		}
		return longitud;
	}
	
	private void verificarSiAproboNivel(NivelesDeEducacion nivel) {
		Integer contador = 0;
		if(!nivel.equals(NivelesDeEducacion.JARDIN)) {
			for(Integer i = 0; i < indiceTopePorNivel(nivel); i++) {
				if(this.gradosCursados[i] != null) {
					if(i == indiceDeGradoMenorPorNivel(nivel)) {
						if(this.gradosCursados[i].getAprobado()) {
							contador++;
						}
					}
				}
			}
		}
		if(contador == (indiceTopePorNivel(nivel) - (indiceDeGradoMenorPorNivel(nivel) + 1))) {
			obtenerNivelDeEducacion(nivel).aprobar();
		}
	}
	
	private Boolean aproboPreviamenteNivelAnterior() {
		Boolean aprobo = Boolean.FALSE;
		for(Integer i = 0; i < this.nivelDeEducacionEnCurso.length && !aprobo; i++) {
			if(this.nivelDeEducacionEnCurso[i] != null) {
				if(this.nivelDeEducacionEnCurso[i].getNivel().ordinal() == indiceDelNivelDeEducacionMasAlto()) {
					if(this.nivelDeEducacionEnCurso[i].getAprobado()) {
						aprobo = Boolean.TRUE;
					}
				}
			}
		}
		return aprobo;
	}
	
	public Boolean elAlumnoEstaDentroDelRangoDeEdad(NivelesDeEducacion nivel) {
		Boolean dentro = Boolean.FALSE;
		if(nivel.equals(NivelesDeEducacion.JARDIN)) {
			if(this.edad >= 2 && this.edad <= 5) {
				dentro = Boolean.TRUE;
			}
		}else if(nivel.equals(NivelesDeEducacion.PRIMARIA)) {
			if(this.edad >= 6 && this.edad <= 11) {
				dentro = Boolean.TRUE;
			}
		}else if(nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			if(this.edad >= 12 && this.edad <= 17) {
				dentro = Boolean.TRUE;
			}
		}
		return dentro;
	}
	
	public Boolean agregarNivelDeEducacion(NivelesDeEducacion nivel) {
		Boolean agrego = Boolean.FALSE;
		if(nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			verificarSiAproboNivel(NivelesDeEducacion.PRIMARIA);
		}
		if(!contieneNivelDeEducacion(nivel) && indiceDelNivelDeEducacionMasAlto() <= nivel.ordinal() && elAlumnoEstaDentroDelRangoDeEdad(nivel)) {
			if(aproboPreviamenteNivelAnterior() || indiceDelNivelDeEducacionMasAlto() == 0 ) {
				for(Integer i = 0; i < this.nivelDeEducacionEnCurso.length && !agrego; i++) {
					if(this.nivelDeEducacionEnCurso[i] == null) {
						this.nivelDeEducacionEnCurso[i] = new NivelInfo(nivel);
						agrego = Boolean.TRUE;
						alumnoEvaluable();
					}
				}
			}		
		}
		return agrego;
	}
	
	public Boolean eliminarNivelDeEducacion(NivelesDeEducacion nivel) {
		Boolean elimino = Boolean.FALSE;
		if(contieneNivelDeEducacion(nivel)) {
			for(Integer i = 0; i < this.nivelDeEducacionEnCurso.length && !elimino; i++) {
				if(this.nivelDeEducacionEnCurso[i] != null) {
					if(this.nivelDeEducacionEnCurso[i].getNivel().equals(nivel)) {
						this.nivelDeEducacionEnCurso[i] = null;
						elimino = Boolean.TRUE;
						alumnoEvaluable();
					}
				}
			}
		}
		return elimino;
	}
	
	public NivelInfo obtenerNivelDeEducacion(NivelesDeEducacion nivel) {
		NivelInfo niv = null;
		Boolean encontro = Boolean.FALSE;
		if(contieneNivelDeEducacion(nivel)) {
			for(Integer i = 0; i < this.nivelDeEducacionEnCurso.length && !encontro; i++) {
				if(this.nivelDeEducacionEnCurso[i] != null) {
					if(this.nivelDeEducacionEnCurso[i].getNivel().equals(nivel)) {
						niv = this.nivelDeEducacionEnCurso[i];
						encontro = Boolean.TRUE;
					}
				}
			}
		}
		return niv;
	}
	
	//---------------------------------------------------------------------SALA------------------------------------------------------------------------------------
	
	private Integer edadDelMinimaParaSala(Salas sala) {
		Integer edad = sala.ordinal() + 2;
		return edad;
	}
	
	private Boolean cumpleConEdadMinimaParaSala(Salas sala) {
		Boolean cumple = Boolean.FALSE;
		if(edadDelMinimaParaSala(sala) <= this.edad) {
			cumple = Boolean.TRUE;
		}
		return cumple;
	}
	
	public Boolean poseeElNivelAdecuadoParaAgregarSala() {
		Boolean posee = Boolean.FALSE;
		if(contieneNivelDeEducacion(NivelesDeEducacion.JARDIN)) {
			posee = Boolean.TRUE;
		}
		return posee;
	}
	
	public Boolean contieneSala(Salas sala) {
		Boolean contiene = Boolean.FALSE;
		for(Integer i = 0; i < this.salasCursadas.length && !contiene; i++) {
			if(this.salasCursadas[i] != null) {
				if(this.salasCursadas[i].equals(sala)) {
					contiene = Boolean.TRUE;
				}
			}
		}
		return contiene;
	}
	
	private Integer cantidadDeSalasCursadas() {
		Integer cantidad = 0;
		for(Integer i = 0; i < this.salasCursadas.length; i++) {
			if(this.salasCursadas[i] != null) {
				cantidad++;
			}
		}
		return cantidad;
	}
	
	private Integer indiceDeLaSalaCursadaMasAlta() {
		Integer indice = 0;
		if(cantidadDeSalasCursadas() > 0) {
			for(Integer i = 0; i < this.salasCursadas.length; i++) {
				if(this.salasCursadas[i] != null) {
					if(indice <= this.salasCursadas[i].ordinal()) {
						indice = this.salasCursadas[i].ordinal();
					}
				}
			}
		}
		return indice;
	}
	
	public Boolean agregarSala(Salas sala) {
		Boolean agregar = Boolean.FALSE;
		if(!contieneSala(sala) && poseeElNivelAdecuadoParaAgregarSala() && indiceDeLaSalaCursadaMasAlta() <= sala.ordinal() && cumpleConEdadMinimaParaSala(sala)) {
			for(Integer i = 0; i < this.salasCursadas.length && !agregar; i++) {
				if(this.salasCursadas[i] == null) {
					this.salasCursadas[i] = sala;
					agregar = Boolean.TRUE;
				}
			}
		}
		return agregar;
	}
	
	public Boolean eliminarSala(Salas sala) {
		Boolean eliminar = Boolean.FALSE;
		if(contieneSala(sala)) {
			for(Integer i = 0; i < this.salasCursadas.length && !eliminar; i++) {
				if(this.salasCursadas[i] != null) {
					if(this.salasCursadas[i].equals(sala)) {
						this.salasCursadas[i] = null;
						eliminar = Boolean.TRUE;
					}
				}
			}
		}
		return eliminar;
	}
	
	public Salas obtenerSalaEnCurso() {
		return Salas.values()[indiceDeLaSalaCursadaMasAlta()];
	}
	
	//--------------------------------------------------------------------------GRADO-------------------------------------------------------------------------------
	
	private Integer edadDelAlumnoMinimaParaAgregarGrado(NivelesDeEducacion nivel, Grados grado) {
		Integer edad = 0;
		if(nivel.equals(NivelesDeEducacion.PRIMARIA)) {
			edad = grado.ordinal() + 6;
		}else if(nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			edad = grado.ordinal() + 12;
		}
		return edad;
	}
	
	private Boolean alumnoCumpleConEdadMinimaParaAgregarGrado(NivelesDeEducacion nivel, Grados grado) {
		Boolean cumple = Boolean.FALSE;
		if(edadDelAlumnoMinimaParaAgregarGrado(nivel, grado) <= this.edad) {
			cumple = Boolean.TRUE;
		}
		return cumple;
	}
	
	public Boolean contieneGrado(NivelesDeEducacion nivel, Grados grado) {
		Boolean verificado = Boolean.FALSE;
		for(Integer i = 0; i < this.gradosCursados.length && !verificado; i++) {
			if(this.gradosCursados[i] != null) {
				if(this.gradosCursados[i].getNivel().equals(nivel) && this.gradosCursados[i].getGrado().equals(grado)) {
					verificado = Boolean.TRUE;
				}
			}
		}
		return verificado;
	}
	
	private Integer cantidadDeGradosCursados() {
		Integer cantidad = 0;
		for(Integer i = 0; i < this.gradosCursados.length; i++) {
			if(this.gradosCursados[i] != null) {
				cantidad++;
			}
		}
		return cantidad;
	}
	
	private Integer indiceDelGradoCursadoMasAlto() {
		Integer indice = -1;
		if(cantidadDeGradosCursados() > 0) {
			for(Integer i = 0; i < this.gradosCursados.length; i++) {
				if(this.gradosCursados[i] != null) {
					if(indice <= i) {
						indice = i;
					}
				}
			}
		}
		return indice;
	}
	
	public Integer indiceDeGradoACursar(NivelesDeEducacion nivel, Grados grado) {
		Integer indice = -1;
		for(Integer i = 0; i < Grados.values().length; i++) {
			if(nivel.equals(NivelesDeEducacion.PRIMARIA) && Grados.values()[i].equals(grado)) {
				indice = i;
			}else if(nivel.equals(NivelesDeEducacion.SECUNDARIA) && Grados.values()[i].equals(grado)){
				indice = i + 6;
			}
		}
		return indice;
	}
	
	private Boolean aproboGradoAnterior() {
		Boolean aprobo = Boolean.FALSE;
		if(this.gradosCursados[indiceDelGradoCursadoMasAlto()].getAprobado()) {
			aprobo = Boolean.TRUE;
		}
		return aprobo;
	}
	
	
	public Boolean agregarGrado(NivelesDeEducacion nivel, Grados grado) {
		Boolean agregar = Boolean.FALSE;
		if(contieneNivelDeEducacion(nivel) && alumnoCumpleConEdadMinimaParaAgregarGrado(nivel, grado)) {
			if(!contieneGrado(nivel, grado)) {
				if(cantidadDeGradosCursados() == 0 || (indiceDelGradoCursadoMasAlto() == (indiceDeGradoACursar(nivel, grado) - 1) && aproboGradoAnterior())) {
					this.gradosCursados[indiceDeGradoACursar(nivel, grado)] = new GradoInfo(nivel, grado);
					agregar = Boolean.TRUE;
				}
			}
		}
		return agregar;
	}
	
	public Boolean eliminarGrado(NivelesDeEducacion nivel, Grados grado) {
		Boolean elimino = Boolean.FALSE;
		if(contieneGrado(nivel, grado)) {
			this.gradosCursados[indiceDeGradoACursar(nivel, grado)] = null;
			elimino = Boolean.TRUE;
		}
		return elimino;
	}
	
	public GradoInfo obtenerGrado(NivelesDeEducacion nivel, Grados grado) {
		return this.gradosCursados[indiceDeGradoACursar(nivel, grado)];
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", dni=" + dni + "]";
	}
}
