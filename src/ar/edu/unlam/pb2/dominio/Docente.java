package ar.edu.unlam.pb2.dominio;

public class Docente {
	
	private NivelInfoDocente[] nivelDeCompetencia = new NivelInfoDocente[NivelesDeEducacion.values().length];
	private GradoInfoDocente[] gradosDeCompetencia = new GradoInfoDocente[Grados.values().length * 2];

	private String nombre;
	private String apellido;
	private Integer edad;
	private Integer dni;
	
	public Docente(String nombre, String apellido, Integer edad, Integer dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.dni = dni;
	}
	
	public NivelInfoDocente[] getNivelDeCompetencia() {
		return nivelDeCompetencia;
	}
	public GradoInfoDocente[] getGradosDeCompetencia() {
		return gradosDeCompetencia;
	}
	public Integer getDni() {
		return dni;
	}
	
	public Boolean contieneNivelDeCompetencia(NivelesDeEducacion nivel) {
		Boolean contiene = Boolean.FALSE;
		for(Integer i = 0; i < this.nivelDeCompetencia.length && !contiene; i++){
			if(this.nivelDeCompetencia[i] != null) {
				if(this.nivelDeCompetencia[i].getNivel().equals(nivel)) {
					contiene = Boolean.TRUE;
				}
			}
		}
		return contiene;
	}
	
	public Boolean agregarNivelDeCompetencia(NivelesDeEducacion nivel) {
		Boolean agrego = Boolean.FALSE;
		if(!contieneNivelDeCompetencia(nivel)) {
			for(Integer i = 0; i < this.nivelDeCompetencia.length && !agrego; i++) {
				if(this.nivelDeCompetencia[i] == null) {
					this.nivelDeCompetencia[i] = new NivelInfoDocente(nivel);
					agrego = Boolean.TRUE;
				}
			}
		}
		return agrego;
	}
	
	public Boolean eliminarNivelDeCompetencia(NivelesDeEducacion nivel) {
		Boolean eliminar = Boolean.FALSE;
		if(contieneNivelDeCompetencia(nivel)) {
			for(Integer i = 0; i < this.nivelDeCompetencia.length && !eliminar; i++) {
				if(this.nivelDeCompetencia != null) {
					if(this.nivelDeCompetencia[i].getNivel().equals(nivel)) {
						this.nivelDeCompetencia[i] = null;
						eliminar = Boolean.TRUE;
					}
				}
			}
		}
		return eliminar;
	}
	
	private Integer indiceDeGradoSegunNivel(NivelesDeEducacion nivel, Grados grado) {
		Integer indice = 0;
		if(nivel.equals(NivelesDeEducacion.PRIMARIA)) {
			indice = grado.ordinal();
		}else if(nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			indice = grado.ordinal() + 6;
		}
		return indice;
	}
	
	public Boolean contieneGradoDeCompetencia(NivelesDeEducacion nivel, Grados grado) {
		Boolean contiene = Boolean.FALSE;
		Integer indice = indiceDeGradoSegunNivel(nivel, grado);
		if(contieneNivelDeCompetencia(nivel)) {
			if(this.gradosDeCompetencia[indiceDeGradoSegunNivel(nivel, grado)] != null) {
				if(this.gradosDeCompetencia[indice].getGrado().equals(grado) && this.gradosDeCompetencia[indice].getNivel().equals(nivel)) {
					contiene = Boolean.TRUE;
				}
			}
		}
		return contiene;
	}
	
	public Boolean agregarGradoDeCompetencia(NivelesDeEducacion nivel, Grados grado) {
		Boolean agregar = Boolean.FALSE;
		if(!contieneGradoDeCompetencia(nivel, grado)) {
			this.gradosDeCompetencia[indiceDeGradoSegunNivel(nivel, grado)] = new GradoInfoDocente(nivel,grado);
			agregar = Boolean.TRUE;
		}
		return agregar;
	}
	
	public Boolean eliminarGradoDeCompetencia(NivelesDeEducacion nivel , Grados grado) {
		Boolean eliminar = Boolean.FALSE;
		if(contieneGradoDeCompetencia(nivel, grado)) {
			this.gradosDeCompetencia[indiceDeGradoSegunNivel(nivel, grado)] = null;
			eliminar = Boolean.TRUE;
		}
		return eliminar;
	}
	
	public GradoInfoDocente obtenerGradoDeCompetencia(NivelesDeEducacion nivel, Grados grado) {
		GradoInfoDocente gradoInfo = null;
		if(contieneGradoDeCompetencia(nivel, grado)) {
			gradoInfo = this.gradosDeCompetencia[indiceDeGradoSegunNivel(nivel, grado)];
		}
		return gradoInfo;
	}
	
	@Override
	public String toString() {
		return "Docente [nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", dni=" + dni + "]";
	}
}
