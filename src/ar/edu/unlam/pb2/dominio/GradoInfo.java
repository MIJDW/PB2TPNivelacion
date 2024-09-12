package ar.edu.unlam.pb2.dominio;

public class GradoInfo {
	
	private NivelesDeEducacion nivel;
	private Grados grado;
	private Materias[] materias;
	
	private Portafolio[] portafolios;
	private Integer cantidadDeEvaluaciones;
	
	private Boolean aprobado = Boolean.FALSE;

	public GradoInfo(NivelesDeEducacion nivel, Grados grado, Integer cantidadDeEvaluaciones) {
		this.nivel = nivel;
		this.grado = grado;
		this.cantidadDeEvaluaciones = cantidadDeEvaluaciones;
		inicializarMaterias();
	}
	
	public NivelesDeEducacion getNivel() {
		return this.nivel;
	}
	public Grados getGrado() {
		return this.grado;
	}
	public Materias[] getMaterias() {
		return this.materias;
	}
	public Portafolio[] getPortafolios() {
		return this.portafolios;
	}
	public Boolean getAprobado() {
		return this.aprobado;
	}

	private void inicializarMaterias() {
		if(verificarSiSePuedeInicializarMaterias()) {
			this.materias = new Materias[Materias.values().length];
			for(Integer i = 0; i < this.materias.length; i++) {
				this.materias[i] = Materias.values()[i];
			}
			inicializarPortafoliosParaMaterias();
		}else {
			InicializarPortafoliosIndividual();
		}
	}
	
	private Boolean verificarSiSePuedeInicializarMaterias() {
		Boolean puede = Boolean.FALSE;
		if(this.nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			puede = Boolean.TRUE;
		}
		return puede;
	}
	
	private void InicializarPortafoliosIndividual() {
		this.portafolios = new Portafolio[1];
		this.portafolios[0] = new Portafolio(this.nivel, this.grado, null, this.cantidadDeEvaluaciones); 
	}
	
	private void inicializarPortafoliosParaMaterias() {
		this.portafolios = new Portafolio[this.materias.length];
		for(Integer i = 0; i < this.portafolios.length; i++) {
			if(this.portafolios[i] == null) {
				this.portafolios[i] = new Portafolio(this.nivel, this.grado, this.materias[i], this.cantidadDeEvaluaciones); 
			}
		}
	}
	
	private Portafolio obtenerPortafolioParaMateria(Materias materia) {
		Portafolio portafolio = null;
		Boolean obtuvo = Boolean.FALSE;
		if(this.materias != null && materia != null) {
			for(Integer i = 0; i < this.portafolios.length && !obtuvo;i++) {
				if(this.portafolios[i].getMateria().equals(materia)) {
					portafolio = this.portafolios[i];
					obtuvo = Boolean.TRUE;
				}
			}
		}	
		return portafolio;
	}
	
	public Portafolio obtenerPortafolio(Materias materia) {
		Portafolio portafolio = null;
		if(this.nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			portafolio = obtenerPortafolioParaMateria(materia);	
		}else if(this.nivel.equals(NivelesDeEducacion.PRIMARIA)){
			portafolio = this.portafolios[0];
		}
		return portafolio;
	}
	
	private void comprobarSituacionAcademicaParaMaterias() {
		Integer necesario = (this.cantidadDeEvaluaciones * this.materias.length);
		Integer contador = 0;
		for(Integer i = 0; i < this.materias.length; i++) {
			Portafolio portafolio = obtenerPortafolioParaMateria(this.materias[i]);
			for(Integer j = 0; j < portafolio.getEvaluaciones().length; j++) {
				if(portafolio.getEvaluaciones()[j].getAprobado() != null) {
					if(portafolio.getEvaluaciones()[j].getAprobado()) {
						contador++;
					}
				}	
			}
		}
		if(necesario == contador) {
			this.aprobado = Boolean.TRUE;
		}
	}
	
	private void comprobarSituacionAcademicaUnica() {
		Integer contador = 0;
		for(Integer i = 0; i < this.portafolios[0].getEvaluaciones().length; i++) {
			if(this.portafolios[0].getEvaluaciones()[i].getAprobado() != null) {
				if(this.portafolios[0].getEvaluaciones()[i].getAprobado()) {
					contador++;
				}
			}
		}
		if(contador == this.cantidadDeEvaluaciones) {
			this.aprobado = Boolean.TRUE;
		}
	}
	
	public void comprobarSituacion() {
		if(this.nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			comprobarSituacionAcademicaParaMaterias();
		}else if(this.nivel.equals(NivelesDeEducacion.PRIMARIA)){
			comprobarSituacionAcademicaUnica();
		}
	}
	
	@Override
	public String toString() {
		return "GradoInfo [nivel=" + nivel + ", grado=" + grado + ", aprobado=" + aprobado + "]";
	}
	
}
