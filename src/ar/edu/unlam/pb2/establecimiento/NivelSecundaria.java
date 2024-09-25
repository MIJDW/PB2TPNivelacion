package ar.edu.unlam.pb2.establecimiento;

import ar.edu.unlam.pb2.enumsDatos.Grados;
import ar.edu.unlam.pb2.enumsDatos.NivelesDeEducacion;

public class NivelSecundaria extends EstructuraEducativaNivel{

	public NivelSecundaria() {
		super.nivel = NivelesDeEducacion.SECUNDARIA;
		agregarDivisiones();
	}
	
	private void agregarDivisiones() {
		for(Grados grado : Grados.values()) {
			DivisionSecundaria divisionSecundaria = new DivisionSecundaria(grado);
			if(!getDivisiones().contains(divisionSecundaria)) {
				super.divisiones.add(divisionSecundaria);
			}
		}
	}
	
	public DivisionSecundaria obtenerDivisionSecundaria(Grados grado) {
		DivisionSecundaria divisionSecundaria = (DivisionSecundaria)getDivisiones().get(grado.ordinal());
		return divisionSecundaria;
	}

}
