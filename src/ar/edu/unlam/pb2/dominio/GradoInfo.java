package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;

public class GradoInfo {
	
	private NivelesDeEducacion nivel;
	private Grados grado;
	private ArrayList<Materias> materias = new ArrayList<Materias>();
	private ArrayList<Portafolio> portafolios = new ArrayList<Portafolio>();
	private Boolean aprobado;

	public GradoInfo(NivelesDeEducacion nivel, Grados grado) {
		this.nivel = nivel;
		this.grado = grado;
		inicializarMaterias();
	}
	
	public NivelesDeEducacion getNivel() {
		return this.nivel;
	}
	public Grados getGrado() {
		return this.grado;
	}
	public ArrayList<Materias> getMaterias() {
		return this.materias;
	}
	public ArrayList<Portafolio> getPortafolios() {
		return this.portafolios;
	}
	public Boolean getAprobado() {
		return this.aprobado;
	}
	
	private void inicializarMaterias() {
		if(this.nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			for(Materias materia : Materias.values()) {
				this.materias.add(materia);
			}
			inicializarPortafoliosParaMaterias();
		}else {
			inicializarPortafolioIndividual();
		}
	}
	
	private void inicializarPortafolioIndividual() {
		this.portafolios.add(new Portafolio(this.nivel, this.grado, null));
	}
	
	private void inicializarPortafoliosParaMaterias() {
		for(Materias materia : getMaterias()) {
			this.portafolios.add(new Portafolio(this.nivel, this.grado, materia));
		}
	}
	
	private Portafolio buscarPortafolioDeMateria(Materias materia) {
		Portafolio buscado = null;
		for(Portafolio portafolio : getPortafolios()) {
			if(portafolio.getMateria().equals(materia)) {
				buscado = portafolio;
			}
		}
		return buscado;
	}
	
	public Portafolio obtenerPortafolio(Materias materia) {
		Portafolio portafolio = null;
		if(this.nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			portafolio = buscarPortafolioDeMateria(materia);	
		}else if(this.nivel.equals(NivelesDeEducacion.PRIMARIA)){
			portafolio = this.portafolios.get(0);
		}
		return portafolio;
	}
	
	public void comprobarSituacionAcademica() {
		Integer cantidadDeEvaluaciones = 0;
		Integer cantidadDeAprobadas = 0;
		for(Portafolio portafolio : getPortafolios()) {
			if(portafolio.getEvaluaciones().size() > 0) {
				for(Evaluacion evaluacion : portafolio.getEvaluaciones()) {
					cantidadDeEvaluaciones++;
					if(evaluacion.getAprobado()) {
						cantidadDeAprobadas++;
					}
				}
			}
		}
		if(cantidadDeEvaluaciones == cantidadDeAprobadas) {
			this.aprobado = Boolean.TRUE;
		}else {
			this.aprobado = Boolean.FALSE;
		}
	}
	
	@Override
	public String toString() {
		return "GradoInfo [nivel=" + nivel + ", grado=" + grado + ", aprobado=" + aprobado + "]";
	}
}
