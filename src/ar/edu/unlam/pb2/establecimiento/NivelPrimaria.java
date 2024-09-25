package ar.edu.unlam.pb2.establecimiento;
import ar.edu.unlam.pb2.enumsDatos.*;

public class NivelPrimaria extends EstructuraEducativaNivel{

	public NivelPrimaria() {
		super.nivel = NivelesDeEducacion.PRIMARIA;
		agregarDivisiones();
	}
	
	private void agregarDivisiones() {
		for(Grados grado : Grados.values()) {
			DivisionPrimaria divisionPrimaria = new DivisionPrimaria(grado);
			if(!getDivisiones().contains(divisionPrimaria)) {
				super.divisiones.add(divisionPrimaria);
			}
		}
	}
	
	public DivisionPrimaria obtenerDivisionPrimaria(Grados grado) {
		DivisionPrimaria divisionPrimaria = (DivisionPrimaria)getDivisiones().get(grado.ordinal());
		return divisionPrimaria;
	}

}
