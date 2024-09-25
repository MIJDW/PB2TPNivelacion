package ar.edu.unlam.pb2.info;

import ar.edu.unlam.pb2.enumsDatos.*;

public class NivelInfoAlumno extends NivelInfo{
	
	private Boolean aprobado = Boolean.FALSE;

	public NivelInfoAlumno(NivelesDeEducacion nivel) {
		super(nivel);
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
