package ar.edu.unlam.pb2.dominio;

public class NivelInfoDocente {
	
	private NivelesDeEducacion nivel;

	public NivelInfoDocente(NivelesDeEducacion nivel) {
		this.nivel = nivel;
	}
	
	public NivelesDeEducacion getNivel() {
		return this.nivel;
	}

	@Override
	public String toString() {
		return "NivelInfoDocente [nivel=" + nivel + "]";
	}
	
}
