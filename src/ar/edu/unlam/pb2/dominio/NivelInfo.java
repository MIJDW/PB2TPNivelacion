package ar.edu.unlam.pb2.dominio;

public class NivelInfo {
	
	private NivelesDeEducacion nivel;
	private Boolean aprobado = Boolean.FALSE;

	public NivelInfo(NivelesDeEducacion nivel) {	
		this.nivel = nivel;
	}
	
	public NivelesDeEducacion getNivel() {
		return this.nivel;
	}
	public Boolean getAprobado() {
		return this.aprobado;
	}
	
	public void aprobar() {
		this.aprobado = Boolean.TRUE;
	}

	@Override
	public String toString() {
		return "NivelInfo [nivel=" + nivel + ", aprobado=" + aprobado + "]";
	}
	
}
