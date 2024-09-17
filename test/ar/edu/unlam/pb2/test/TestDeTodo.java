package ar.edu.unlam.pb2.test;
import static org.junit.Assert.*;
import org.junit.Test;
import ar.edu.unlam.pb2.dominio.*;

public class TestDeTodo {

	//TEST EVALUACION------------------------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void queSePuedaCrearUnaEvaluacion() {
		//Preparacion
		Evaluacion evaluacion;
		//Ejecucion
		evaluacion = new Evaluacion(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO, Materias.MATEMATICA);
		//Verificacion
		assertEquals(NivelesDeEducacion.SECUNDARIA, evaluacion.getNivel());
		assertEquals(Grados.PRIMERO, evaluacion.getGrado());
		assertEquals(Materias.MATEMATICA, evaluacion.getMateria());
	}
	
	@Test
	public void queSePuedaAgregarDocenteYFecha(){
		//Preparacion
		String fecha = "21/07/1994";
		Docente docente = new Docente("Mijael", "Flores", 30, 38423265);
		Evaluacion evaluacion = new Evaluacion(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO, Materias.MATEMATICA);
		//Ejecucion
		Boolean resultadoEsperado = Boolean.TRUE;
		Boolean resultadoObtenido = evaluacion.agregarDocenteYFecha(docente, fecha);
		//Verificacion
		assertEquals(docente, evaluacion.getDocente());
		assertEquals(fecha, evaluacion.getFecha());
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	
	@Test
	public void queNoSePuedaAgregarDocenteYFechaSiYaSeAnAgregadoAnteriormente(){
		//Preparacion
		String fecha = "21/07/1994";
		Docente docente = new Docente("Mijael", "Flores", 30, 38423265);
		Evaluacion evaluacion = new Evaluacion(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO, Materias.MATEMATICA);
		//Ejecucion
		evaluacion.agregarDocenteYFecha(docente, fecha);
		Docente docente2 = new Docente("Kevin", "Flores", 30, 38423265);
		Boolean resultadoEsperado = Boolean.FALSE;
		Boolean resultadoObtenido = evaluacion.agregarDocenteYFecha(docente2, fecha);
		//Verificacion
		assertEquals(docente, evaluacion.getDocente());
		assertEquals(fecha, evaluacion.getFecha());
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	
	@Test
	public void queSePuedaCorregirEvaluacion(){
		//Preparacion
		String fecha = "21/07/1994";
		Docente docente = new Docente("Mijael", "Flores", 30, 38423265);
		Evaluacion evaluacion = new Evaluacion(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO, Materias.MATEMATICA);
		//Ejecucion
		evaluacion.agregarDocenteYFecha(docente, fecha);
		Integer nota = 10;
		evaluacion.corregirEvaluacion(nota);
		//Verificacion
		assertEquals(nota, evaluacion.getNota());
		assertTrue(evaluacion.getAprobado());
	}
	
	@Test
	public void queNoSePuedaCorregirEvaluacionSiNoSeAgregoDocenteYFecha(){
		//Preparacion
		Evaluacion evaluacion = new Evaluacion(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO, Materias.MATEMATICA);
		//Ejecucion
		Integer nota = 10;
		evaluacion.corregirEvaluacion(nota);
		//Verificacion
		assertNull(evaluacion.getNota());
		assertNull(evaluacion.getAprobado());
	}
	
	//TEST PORTAFOLIO-----------------------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void queSePuedaCrearUnPortafolio() {
		//Preparacion
		Portafolio portafolio;
		//Ejecucion
		portafolio = new Portafolio(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO, Materias.MATEMATICA);
		//Verificacion
		assertEquals(NivelesDeEducacion.SECUNDARIA, portafolio.getNivel());
		assertEquals(Grados.PRIMERO, portafolio.getGrado());
		assertEquals(Materias.MATEMATICA, portafolio.getMateria());
	}
	
	@Test
	public void queSePuedaAgregarUnaEvaluacionAlPortafolioSiCumpleConLosRequisitos() {
		//Preparacion
		Evaluacion evaluacion = new Evaluacion(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO, Materias.MATEMATICA);
		Docente docente = new Docente("Mijael", "Flores", 30, 38423265);
		String fecha = "21/07/1994";
		Integer nota = 10;
		Portafolio portafolio;
		//Ejecucion
		portafolio = new Portafolio(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO, Materias.MATEMATICA);
		evaluacion.agregarDocenteYFecha(docente, fecha);
		evaluacion.corregirEvaluacion(nota);
		portafolio.agregarEvaluacion(evaluacion);
		//Verificacion
		assertEquals(evaluacion, portafolio.getEvaluaciones().get(0));
	}
	
	@Test
	public void queNoSePuedaAgregarUnaEvaluacionAlPortafolioConDatosIncorrectos() {
		//Preparacion
		Evaluacion evaluacion = new Evaluacion(NivelesDeEducacion.PRIMARIA, Grados.PRIMERO, null);
		Docente docente = new Docente("Mijael", "Flores", 30, 38423265);
		String fecha = "21/07/1994";
		Integer nota = 10;
		Portafolio portafolio;
		//Ejecucion
		portafolio = new Portafolio(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO, Materias.MATEMATICA);
		evaluacion.agregarDocenteYFecha(docente, fecha);
		evaluacion.corregirEvaluacion(nota);
		//Verificacion
		assertFalse(portafolio.agregarEvaluacion(evaluacion));
	}
	
	@Test
	public void queNoSePuedaAgregarDosEvaluacionesConFechasIguales() {
		//Preparacion
		Evaluacion evaluacion = new Evaluacion(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO, Materias.MATEMATICA);
		Evaluacion evaluacion2 = new Evaluacion(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO, Materias.MATEMATICA);
		
		Docente docente = new Docente("Mijael", "Flores", 30, 38423265);
		String fecha = "21/07/1994";
		Integer nota = 10;
		Portafolio portafolio;
		//Ejecucion
		portafolio = new Portafolio(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO, Materias.MATEMATICA);
		
		evaluacion.agregarDocenteYFecha(docente, fecha);
		evaluacion.corregirEvaluacion(nota);
		
		evaluacion2.agregarDocenteYFecha(docente, fecha);
		evaluacion2.corregirEvaluacion(nota);
		//Verificacion
		assertTrue(portafolio.agregarEvaluacion(evaluacion));
		assertFalse(portafolio.agregarEvaluacion(evaluacion2));
	}
	
	//TEST GRADO INFO DOCENTE---------------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void queSePuedaCrearUnGradoInfoDocente() {
		//Preparacion
		GradoInfoDocente infoDocente;
		//Ejecucion
		infoDocente = new GradoInfoDocente(NivelesDeEducacion.PRIMARIA, Grados.PRIMERO);
		//Verificacion
		assertEquals(NivelesDeEducacion.PRIMARIA, infoDocente.getNivel());
		assertEquals(Grados.PRIMERO, infoDocente.getGrado());
	}
	
	@Test
	public void queSePuedaAgregarUnaMateriaSoloSiElNivelEsSecundaria() {
		//Preparacion
		GradoInfoDocente infoDocente;
		GradoInfoDocente infoDocente1;
		//Ejecucion
		infoDocente = new GradoInfoDocente(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO);
		infoDocente1 = new GradoInfoDocente(NivelesDeEducacion.PRIMARIA, Grados.PRIMERO);
		//Verificacion
		assertTrue(infoDocente.agregarMateria(Materias.MATEMATICA));
		assertFalse(infoDocente1.agregarMateria(Materias.MATEMATICA));
	}
	
	
	@Test
	public void queNoSePuedaAgregarUnaMateriaRepetida() {
		//Preparacion
		GradoInfoDocente infoDocente;
		//Ejecucion
		infoDocente = new GradoInfoDocente(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO);
		//Verificacion
		assertTrue(infoDocente.agregarMateria(Materias.MATEMATICA));
		assertFalse(infoDocente.agregarMateria(Materias.MATEMATICA));
	}
	
	@Test
	public void queNoSePuedaEliminarUnaMateria() {
		//Preparacion
		GradoInfoDocente infoDocente;
		//Ejecucion
		infoDocente = new GradoInfoDocente(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO);
		//Verificacion
		assertTrue(infoDocente.agregarMateria(Materias.MATEMATICA));
		assertTrue(infoDocente.agregarMateria(Materias.FISICA));
		assertTrue(infoDocente.agregarMateria(Materias.INGLES));
		assertTrue(infoDocente.eliminarMateria(Materias.FISICA));
	}
	
	//TEST GRADO INFO-------------------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void queSePuedaCrearUnGradoInfoConSusRespectivasMateriasYPortafolios() {
		//Preparacion
		GradoInfo gradoInfo;
		GradoInfo gradoInfo1;
		//Ejecucion
		gradoInfo = new GradoInfo(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO);
		gradoInfo1 = new GradoInfo(NivelesDeEducacion.PRIMARIA, Grados.PRIMERO);
		//Verificacion
		assertEquals(Materias.values().length, gradoInfo.getMaterias().size());
		assertEquals(Materias.values().length, gradoInfo.getPortafolios().size());
		assertEquals(0, gradoInfo1.getMaterias().size());
		assertEquals(1, gradoInfo1.getPortafolios().size());
	}
	
	@Test
	public void queSePuedaObtenerUnPortafolioSegunMateria() {
		//Preparacion
		GradoInfo gradoInfo;
		GradoInfo gradoInfo1;
		//Ejecucion
		gradoInfo = new GradoInfo(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO);
		gradoInfo1 = new GradoInfo(NivelesDeEducacion.PRIMARIA, Grados.PRIMERO);
		//Verificacion
		assertEquals(gradoInfo.getPortafolios().get(Materias.values().length - 1), gradoInfo.obtenerPortafolio(Materias.INGLES));
		assertNull(gradoInfo.obtenerPortafolio(null));
		assertEquals(gradoInfo1.getPortafolios().get(0), gradoInfo1.obtenerPortafolio(Materias.MATEMATICA));
		assertEquals(gradoInfo1.getPortafolios().get(0), gradoInfo1.obtenerPortafolio(null));
	}
	
	@Test
	public void queSePuedaComprobarLaSituacionAcademica() {
		//Preparacion
		GradoInfo gradoInfo1 = new GradoInfo(NivelesDeEducacion.PRIMARIA, Grados.PRIMERO);
		GradoInfo gradoInfo2 = new GradoInfo(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO);
		
		Docente docente = new Docente("Mijael", "Flores", 30, 38423265);
		//Ejecucion
		crearEvaluaciones(gradoInfo1, docente, null, 2, 7);
		crearEvaluaciones(gradoInfo2, docente, Materias.MATEMATICA, 2, 8);
		crearEvaluaciones(gradoInfo2, docente, Materias.INGLES, 3, 6);
		gradoInfo1.comprobarSituacionAcademica();
		gradoInfo2.comprobarSituacionAcademica();
		//Verificacion
		assertTrue(gradoInfo1.getAprobado());
		assertFalse(gradoInfo2.getAprobado());
	}
	
	private static void crearEvaluaciones(GradoInfo gradoInfo, Docente docente, Materias materia ,Integer cantidad, Integer nota) {
		Portafolio portafolio = gradoInfo.obtenerPortafolio(materia);
		Integer anio = 1990;
		for(Integer i = 0; i < cantidad; i++) {
			String fecha = "21/07"+(anio++);
			Evaluacion evaluacion;
			if(materia != null) {
				evaluacion = new Evaluacion(gradoInfo.getNivel(), gradoInfo.getGrado(), materia);
			}else {
				evaluacion = new Evaluacion(gradoInfo.getNivel(), gradoInfo.getGrado(), null);
			}
			evaluacion.agregarDocenteYFecha(docente, fecha);
			evaluacion.corregirEvaluacion(nota++);
			portafolio.agregarEvaluacion(evaluacion);
		}
	}
}
