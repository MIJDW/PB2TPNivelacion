package ar.edu.unlam.pb2.establecimiento;

import java.util.ArrayList;

import ar.edu.unlam.pb2.enumsDatos.NivelesDeEducacion;

public class Institucion {
	
	private String nombreDeLaInstitucion;
	private ArrayList<EstructuraEducativaNivel> niveles = new ArrayList<EstructuraEducativaNivel>();
	
	public Institucion(String nombreDeLaInstitucion) {
		this.nombreDeLaInstitucion = nombreDeLaInstitucion;
	}
	
	public String getNombreDeLaInstitucion() {
		return nombreDeLaInstitucion;
	}
	public ArrayList<EstructuraEducativaNivel> getNiveles() {
		return niveles;
	}
	
	public Boolean agregarNivel(NivelesDeEducacion nivel) {
		EstructuraEducativaNivel nivelNuevo = new EstructuraEducativaNivel();
		if(nivel.equals(NivelesDeEducacion.JARDIN)) {
			nivelNuevo = new NivelJardin();
		}else if(nivel.equals(NivelesDeEducacion.PRIMARIA)) {
			nivelNuevo = new NivelPrimaria();
		}else if(nivel.equals(NivelesDeEducacion.SECUNDARIA)) {
			nivelNuevo = new NivelSecundaria();
		}
		if(!this.niveles.contains(nivelNuevo)) {
			return this.niveles.add(nivelNuevo);
		}
		return Boolean.FALSE;
	}
	
	public void ordenarNiveles() {
		EstructuraEducativaNivel auxiliar = null;
		
		for(Integer i = 0; i < this.niveles.size() - 1; i++) {
			for(Integer j = 0; j < this.niveles.size() - 1; j++) {
				if(this.niveles.get(j+1).getNivel().ordinal() < this.niveles.get(j).getNivel().ordinal()) {
					auxiliar = this.niveles.get(j+1);
					this.niveles.set(j+1, this.niveles.get(j));
					this.niveles.set(j, auxiliar);
				}
			}
		}
	}

	@Override
	public String toString() {
		return "Institucion [nombreDeLaInstitucion=" + nombreDeLaInstitucion + ", niveles=" + niveles + "]";
	}
}
