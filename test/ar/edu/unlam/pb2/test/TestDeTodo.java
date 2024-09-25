package ar.edu.unlam.pb2.test;
import static org.junit.Assert.*;
import org.junit.Test;
import ar.edu.unlam.pb2.enumsDatos.*;
import ar.edu.unlam.pb2.establecimiento.*;
import ar.edu.unlam.pb2.evaluaciones.*;
import ar.edu.unlam.pb2.info.*;
import ar.edu.unlam.pb2.personas.*;
import ar.edu.unlam.pb2.portafolios.*;


public class TestDeTodo {

	@Test
	public void queSePuedaAgregarDocenteYFecha(){
		//Preparacion
		String fecha = "21/07/1994";
		Docente docente = new Docente("Mijael", "Flores", 30, 38423265);
		EvaluacionDeSecundaria evaluacion = new EvaluacionDeSecundaria(Grados.PRIMERO, Materias.MATEMATICA);
		//Ejecucion
		Boolean resultadoEsperado = Boolean.TRUE;
		Boolean resultadoObtenido = evaluacion.agregarDocenteYFecha(docente, fecha);
		//Verificacion
		assertEquals(docente, evaluacion.getDocente());
		assertEquals(fecha, evaluacion.getFecha());
		assertEquals(NivelesDeEducacion.SECUNDARIA, evaluacion.getNivel());
		assertEquals(Materias.MATEMATICA, evaluacion.getMateria());
		assertEquals(resultadoEsperado, resultadoObtenido);
	}
	
	@Test
	public void queNoSePuedaAgregarDocenteYFechaSiYaSeAnAgregadoAnteriormente(){
		//Preparacion
		String fecha = "21/07/1994";
		Docente docente = new Docente("Mijael", "Flores", 30, 38423265);
		Evaluacion evaluacion = new Evaluacion(Grados.PRIMERO);
		//Ejecucion
		evaluacion.agregarDocenteYFecha(docente, fecha);
		Docente docente2 = new Docente("Kevin", "Flores", 30, 12388412);
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
		Evaluacion evaluacion = new Evaluacion(Grados.PRIMERO);
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
		EvaluacionDeSecundaria evaluacion = new EvaluacionDeSecundaria(Grados.PRIMERO, Materias.MATEMATICA);
		//Ejecucion
		Integer nota = 10;
		evaluacion.corregirEvaluacion(nota);
		//Verificacion
		assertNull(evaluacion.getNota());
		assertNull(evaluacion.getAprobado());
	}
	
	//TEST PORTAFOLIO-----------------------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void queSePuedaAgregarUnaEvaluacionAlPortafolioSiCumpleConLosRequisitos() {
		//Preparacion
		EvaluacionDeSecundaria evaluacion = new EvaluacionDeSecundaria(Grados.PRIMERO, Materias.MATEMATICA);
		Docente docente = new Docente("Mijael", "Flores", 30, 38423265);
		String fecha = "21/07/1994";
		Integer nota = 10;
		PortafolioDeSecundaria portafolio;
		//Ejecucion
		portafolio = new PortafolioDeSecundaria(Grados.PRIMERO, Materias.MATEMATICA);
		evaluacion.agregarDocenteYFecha(docente, fecha);
		evaluacion.corregirEvaluacion(nota);
		portafolio.agregarEvaluacion(evaluacion);
		//Verificacion
		assertEquals(Materias.MATEMATICA, portafolio.getMateria());
		assertEquals(evaluacion, portafolio.getEvaluaciones().get(0));
	}
	
	@Test
	public void queNoSePuedaAgregarDosEvaluacionesConFechasIguales() {
		//Preparacion
		Evaluacion evaluacion = new Evaluacion(Grados.PRIMERO);
		Evaluacion evaluacion2 = new Evaluacion(Grados.PRIMERO);
		
		Docente docente = new Docente("Mijael", "Flores", 30, 38423265);
		String fecha = "21/07/1994";
		Integer nota = 10;
		Portafolio portafolio;
		//Ejecucion
		portafolio = new Portafolio(Grados.PRIMERO);
		
		evaluacion.agregarDocenteYFecha(docente, fecha);
		evaluacion.corregirEvaluacion(nota);
		
		evaluacion2.agregarDocenteYFecha(docente, fecha);
		evaluacion2.corregirEvaluacion(nota);
		//Verificacion
		assertTrue(portafolio.agregarEvaluacion(evaluacion));
		assertFalse(portafolio.agregarEvaluacion(evaluacion2));
	}
	
	//TEST GRADO INFO DOCENTE SECUNDARIA------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void queSePuedaAgregarYEliminarMaterias() {
		//Preparacion
		GradoInfoDocenteSecundaria gradoDocenteSecundaria = new GradoInfoDocenteSecundaria(Grados.PRIMERO);
		//Ejecucion
		gradoDocenteSecundaria.agregarMateria(Materias.MATEMATICA);
		gradoDocenteSecundaria.agregarMateria(Materias.FISICA);
		gradoDocenteSecundaria.agregarMateria(Materias.INGLES);
		//Verificacion
		assertEquals(3, gradoDocenteSecundaria.getMaterias().size());
		assertTrue(gradoDocenteSecundaria.eliminarMateria(Materias.FISICA));
	}
	
	@Test
	public void queNoSePuedaAgregarMateriasRepetidas() {
		//Preparacion
		GradoInfoDocenteSecundaria gradoDocenteSecundaria = new GradoInfoDocenteSecundaria(Grados.PRIMERO);
		//Ejecucion
		gradoDocenteSecundaria.agregarMateria(Materias.BIOLOGIA);
		gradoDocenteSecundaria.agregarMateria(Materias.HISTORIA);
		//Verificacion
		assertFalse(gradoDocenteSecundaria.agregarMateria(Materias.BIOLOGIA));
		assertFalse(gradoDocenteSecundaria.agregarMateria(Materias.HISTORIA));
	}
	
	//TEST GRADO INFO ALUMNO------------------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void queSePuedaCrearUnGradoInfoConSusRespectivasMateriasYOPortafoliosYObtenerPortafolios() {
		//Preparacion
		GradoInfoAlumnoPrimaria gradoInfo = new GradoInfoAlumnoPrimaria(Grados.PRIMERO);
		GradoInfoAlumnoSecundaria gradoInfo1 = new GradoInfoAlumnoSecundaria(Grados.PRIMERO);
		//Ejecucion
		PortafolioDeSecundaria secundaria = (PortafolioDeSecundaria)gradoInfo1.getPortafolios().get(Materias.INGLES.ordinal());
		//Verificacion
		assertEquals(1, gradoInfo.getPortafolios().size());
		assertEquals(Materias.values().length, gradoInfo1.getPortafolios().size());
		assertEquals(Materias.INGLES, secundaria.getMateria());
		assertEquals(gradoInfo.getPortafolios().get(0), gradoInfo.obtenerPortafolio());
		assertEquals(secundaria, gradoInfo1.obtenerPortafolioDeSecundaria(Materias.INGLES));
	}
	
	@Test
	public void queSePuedaComprobarLaSituacionAcademica() {
		//Preparacion
		GradoInfoAlumnoPrimaria gradoInfo1 = new GradoInfoAlumnoPrimaria(Grados.PRIMERO);
		GradoInfoAlumnoSecundaria gradoInfo2 = new GradoInfoAlumnoSecundaria(Grados.PRIMERO);
		
		Docente docente = new Docente("Mijael", "Flores", 30, 38423265);
		//Ejecucion
		crearEvaluaciones(gradoInfo1, docente, 2, 7);
		
		crearEvaluaciones(gradoInfo2, docente, Materias.MATEMATICA, 2, 8);
		crearEvaluaciones(gradoInfo2, docente, Materias.INGLES, 3, 6);
		
		gradoInfo1.comprobarSituacionAcademica();
		gradoInfo2.comprobarSituacionAcademica();
		//Verificacion
		assertTrue(gradoInfo1.getAprobado());
		assertFalse(gradoInfo2.getAprobado());
	}
	
	private static void crearEvaluaciones(GradoInfoAlumnoPrimaria gradoInfo, Docente docente,Integer cantidad, Integer nota) {
		Portafolio portafolio = gradoInfo.obtenerPortafolio() ;
		Integer anio = 1990;
		for(Integer i = 0; i < cantidad; i++) {
			String fecha = "21/07"+(anio++);
			Evaluacion evaluacion = new Evaluacion(gradoInfo.getGrado());
			evaluacion.agregarDocenteYFecha(docente, fecha);
			evaluacion.corregirEvaluacion(nota++);
			portafolio.agregarEvaluacion(evaluacion);
		}
	}
	
	private static void crearEvaluaciones(GradoInfoAlumnoSecundaria gradoInfo, Docente docente, Materias materia ,Integer cantidad, Integer nota) {
		PortafolioDeSecundaria portafolio = gradoInfo.obtenerPortafolioDeSecundaria(materia);
		Integer anio = 1990;
		for(Integer i = 0; i < cantidad; i++) {
			String fecha = "21/07"+(anio++);
			EvaluacionDeSecundaria evaluacion = new EvaluacionDeSecundaria(gradoInfo.getGrado(), materia);
			evaluacion.agregarDocenteYFecha(docente, fecha);
			evaluacion.corregirEvaluacion(nota++);
			portafolio.agregarEvaluacion(evaluacion);
		}
	}
	
	//TEST DOCENTE-----------------------------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void queEnElDocenteSePuedaAgregarYEliminarNivelesDeCompetencia() {
		//Preparacion
		Docente docente = new Docente("Mijael", "Flores", 30, 38423265);
		//Ejecucion
		docente.agregarNivelDeCompetencia(NivelesDeEducacion.PRIMARIA);
		//Verificacion
		assertEquals(1, docente.getNiveles().size());
		assertFalse(docente.agregarNivelDeCompetencia(NivelesDeEducacion.PRIMARIA));
		assertTrue(docente.agregarNivelDeCompetencia(NivelesDeEducacion.SECUNDARIA));
		assertEquals(2, docente.getNiveles().size());
		assertTrue(docente.eliminarNivelDeCompetencia(NivelesDeEducacion.PRIMARIA));
		assertEquals(1, docente.getNiveles().size());
	}
	
	@Test
	public void queEnElDocenteSePuedaAgregarYEliminarGradosDeCompetenciaAdemasDeObtenerlos() {
		//Preparacion
		Docente docente = new Docente("Mijael", "Flores", 30, 38423265);
		GradoInfoPrimaria gradoInfoPrimaria = new GradoInfoPrimaria(Grados.PRIMERO);
		GradoInfoDocenteSecundaria gradoInfoDocenteSecundaria = new GradoInfoDocenteSecundaria(Grados.PRIMERO);
		//Ejecucion
		docente.agregarNivelDeCompetencia(NivelesDeEducacion.PRIMARIA);
		docente.agregarNivelDeCompetencia(NivelesDeEducacion.SECUNDARIA);
		docente.agregarGradoDeCompetencia(gradoInfoPrimaria);
		docente.agregarGradoDeCompetencia(gradoInfoDocenteSecundaria);
		//Verificacion
		assertEquals(2, docente.getGrados().size());
		assertEquals(gradoInfoPrimaria, docente.obtenerGradoDeCompetencia(NivelesDeEducacion.PRIMARIA, Grados.PRIMERO));
		assertEquals(gradoInfoDocenteSecundaria, docente.obtenerGradoDeCompetencia(NivelesDeEducacion.SECUNDARIA, Grados.PRIMERO));
	}
	
	//TEST ALUMNO-----------------------------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void queSePuedaAgregarYEliminarYObtenerUnNivelDeEducacion() {
		//Preparacion
		Alumno alumno = new Alumno("Mijael", "Flores", 6, 38423265);
		//Ejecucion
		alumno.agregarNivelDeEducacion(NivelesDeEducacion.JARDIN);
		alumno.agregarNivelDeEducacion(NivelesDeEducacion.PRIMARIA);
		alumno.agregarNivelDeEducacion(NivelesDeEducacion.PRIMARIA);
		alumno.agregarNivelDeEducacion(NivelesDeEducacion.SECUNDARIA);
		//Verificacion
		assertEquals(1, alumno.getNiveles().size());
		assertEquals(alumno.getNiveles().get(0).getNivel(), NivelesDeEducacion.PRIMARIA);
		assertEquals(alumno.obtenerNivelDeEducacion(NivelesDeEducacion.PRIMARIA), alumno.getNiveles().get(0));
		assertTrue(alumno.getEvaluable());
		assertFalse(alumno.eliminarNivelEducacion(NivelesDeEducacion.JARDIN));
		assertTrue(alumno.eliminarNivelEducacion(NivelesDeEducacion.PRIMARIA));
	}
	
	@Test
	public void queSePuedaAgregarPrimariaYLuegoDeSubirEdadSecundaria() {
		//Preparacion
		Alumno alumno = new Alumno("Mijael", "Flores", 6, 38423265);
		//Ejecucion
		alumno.agregarNivelDeEducacion(NivelesDeEducacion.PRIMARIA);
		for(Integer i = 0; i < 6; i++) {
			alumno.subirEdad();
		}
		//Verificacion
		assertEquals(1, alumno.getNiveles().size());
		assertTrue(alumno.verificarSiAproboNivel(alumno.getNiveles().get(0).getNivel()));
		assertTrue(alumno.agregarNivelDeEducacion(NivelesDeEducacion.SECUNDARIA));
		assertEquals(2, alumno.getNiveles().size());
	}
	
	@Test
	public void queSePuedaAgregarYEliminarUnaSala() {
		//Preparacion
		Alumno alumno = new Alumno("Mijael", "Flores", 3, 38423265);
		//Ejecucion
		alumno.agregarNivelDeEducacion(NivelesDeEducacion.JARDIN);
		//Verificacion
		assertTrue(alumno.agregarSala(Salas.CELESTE));
		assertTrue(alumno.eliminarSala(Salas.CELESTE));
	}
	
	@Test
	public void queSePuedaAgregarYEliminarYObtenerUnGradoParaCursar() {
		//Preparacion
		Alumno alumno = new Alumno("Mijael", "Flores", 6, 38423265);
		//Ejecucion
		alumno.agregarNivelDeEducacion(NivelesDeEducacion.PRIMARIA);
		GradoInfoAlumnoPrimaria gradoInfoAlumnoPrimaria = new GradoInfoAlumnoPrimaria(Grados.PRIMERO);
		alumno.agregarGradoParaCursar(gradoInfoAlumnoPrimaria);
		gradoInfoAlumnoPrimaria.aprobar();
		alumno.subirEdad();
		GradoInfoAlumnoPrimaria gradoInfoAlumnoPrimaria2 = new GradoInfoAlumnoPrimaria(Grados.SEGUNDO);
		alumno.agregarGradoParaCursar(gradoInfoAlumnoPrimaria2);
		//Verificacion
		assertEquals(2, alumno.getGrados().size());
		assertEquals(gradoInfoAlumnoPrimaria, alumno.getGrados().get(0));
		assertTrue(alumno.eliminarGradoParaCursar(gradoInfoAlumnoPrimaria2));
		assertEquals(1, alumno.getGrados().size());
		assertEquals(gradoInfoAlumnoPrimaria, alumno.obtenerGradoEnCurso(NivelesDeEducacion.PRIMARIA, Grados.PRIMERO));	
	}
	
	@Test
	public void queSePuedaAgregarYEliminarUnaAsistencia() {
		//Preparacion
		Alumno alumno = new Alumno("Mijael", "Flores", 3, 38423265);
		//Ejecucion
		alumno.agregarAsistencia("21/07/1994", OpcionesDeAsistencia.PRESENTE);
		//Verificacion
		assertEquals(1, alumno.getAsistencias().size());
		assertTrue(alumno.agregarAsistencia("22/07/1994", OpcionesDeAsistencia.AUSENTE));
		assertEquals(2, alumno.getAsistencias().size());
		assertTrue(alumno.eliminarAsistencia("21/07/1994"));
	}
	
	//TEST DIVISION-----------------------------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void queSePuedaAgregarYEliminarAlumnosYDocentesEnLaDivisionJardin() {
		//Preparacion
		Alumno alumno1 = new Alumno("Mijael", "Flores", 5, 50237191);
		Alumno alumno2 = new Alumno("Mijael", "Flores", 3, 50235491);
		Alumno alumno3 = new Alumno("Mijael", "Flores", 6, 50237591);
		Docente docente1 = new Docente("Mijael", "Flores", 30, 38423265);
		Docente docente2 = new Docente("Mijael", "Flores", 30, 38123265);
		Docente docente3 = new Docente("Mijael", "Flores", 30, 38554265);
		DivisionJardin divisionJardin1 = new DivisionJardin(Salas.ROJA);
		DivisionJardin divisionJardin2 = new DivisionJardin(Salas.VERDE);
		//Ejecucion
		docente1.agregarNivelDeCompetencia(NivelesDeEducacion.JARDIN);
		docente2.agregarNivelDeCompetencia(NivelesDeEducacion.JARDIN);
		docente3.agregarNivelDeCompetencia(NivelesDeEducacion.JARDIN);
		
		divisionJardin1.agregarAlumnoAJardin(alumno1);
		divisionJardin1.agregarDocente(docente1);
		divisionJardin1.agregarDocente(docente2);
		//Verificacion
		assertEquals(alumno1.getSalasCursadas().get(0), divisionJardin1.getSala());
		assertFalse(divisionJardin1.agregarAlumnoAJardin(alumno2));
		assertTrue(divisionJardin2.agregarAlumnoAJardin(alumno2));
		assertFalse(divisionJardin1.agregarAlumnoAJardin(alumno3));
		assertFalse(divisionJardin2.agregarAlumnoAJardin(alumno3));
		assertEquals(1, divisionJardin1.getAlumnos().size());
		assertFalse(divisionJardin1.agregarDocente(docente3));
		assertEquals(2, divisionJardin1.getDocentes().size());
		assertTrue(divisionJardin1.eliminarAlumno(50237191));
		assertEquals(0, divisionJardin1.getAlumnos().size());
		assertEquals(1, divisionJardin2.getAlumnos().size());
		assertTrue(divisionJardin1.eliminarDocente(38423265));
		assertTrue(divisionJardin1.agregarDocente(docente3));
	}
	
	@Test
	public void queSePuedaAgregarYEliminarAlumnosYDocentesEnLaDivisionPrimaria() {
		//Preparacion
		Alumno alumno1 = new Alumno("Mijael", "Flores", 6, 50237191);
		Alumno alumno2 = new Alumno("Mijael", "Flores", 7, 50235491);
		Alumno alumno3 = new Alumno("Mijael", "Flores", 8, 50237591);
		Docente docente1 = new Docente("Mijael", "Flores", 30, 38423265);
		Docente docente2 = new Docente("Mijael", "Flores", 30, 38123265);
		DivisionPrimaria divisionPrimaria1 = new DivisionPrimaria(Grados.PRIMERO);
		DivisionPrimaria divisionPrimaria2 = new DivisionPrimaria(Grados.SEGUNDO);
		//Ejecucion
		docente1.agregarNivelDeCompetencia(NivelesDeEducacion.PRIMARIA);
		docente2.agregarNivelDeCompetencia(NivelesDeEducacion.PRIMARIA);
		
		GradoInfoPrimaria gradoInfoPrimaria1 = new GradoInfoPrimaria(Grados.PRIMERO);
		docente1.agregarGradoDeCompetencia(gradoInfoPrimaria1);
		docente2.agregarGradoDeCompetencia(gradoInfoPrimaria1);
		
		GradoInfoPrimaria gradoInfoPrimaria2 = new GradoInfoPrimaria(Grados.SEGUNDO);
		docente2.agregarGradoDeCompetencia(gradoInfoPrimaria2);
		
		divisionPrimaria1.agregarAlumnoAPrimaria(alumno3);
		GradoInfoAlumnoPrimaria gradoInfoAlumnoPrimaria = (GradoInfoAlumnoPrimaria) alumno3.obtenerGradoEnCurso(divisionPrimaria1.getNivel(), divisionPrimaria1.getGrado());
		gradoInfoAlumnoPrimaria.aprobar();
		
		divisionPrimaria1.agregarDocenteAPrimaria(docente1);
		divisionPrimaria1.agregarDocenteAPrimaria(docente2);
		//Verificacion
		assertEquals(alumno3.getGrados().get(0).getGrado(), divisionPrimaria1.getGrado());
		assertEquals(alumno3.getGrados().get(0).getNivel(), divisionPrimaria1.getNivel());
		assertEquals(1, divisionPrimaria1.getDocentes().size());
		assertTrue(divisionPrimaria1.agregarAlumnoAPrimaria(alumno2));
		assertEquals(2, divisionPrimaria1.getAlumnos().size());
		assertTrue(divisionPrimaria1.eliminarDocente(38423265));
		assertEquals(0, divisionPrimaria1.getDocentes().size());
		assertTrue(divisionPrimaria1.agregarDocenteAPrimaria(docente2));
		assertEquals(1, divisionPrimaria1.getDocentes().size());
		assertTrue(divisionPrimaria1.eliminarAlumno(50237591));
		assertTrue(divisionPrimaria1.agregarAlumnoAPrimaria(alumno1));
		assertEquals(2, divisionPrimaria1.getAlumnos().size());
		assertTrue(divisionPrimaria2.agregarAlumnoAPrimaria(alumno3));
		assertTrue(divisionPrimaria2.agregarDocenteAPrimaria(docente2));
	}
	
	@Test
	public void queSePuedaAgregarYEliminarAlumnosYDocentesEnLaDivisionSecundaria() {
		//Preparacion
		Alumno alumno1 = new Alumno("Mijael", "Flores", 12, 50237191);
		Alumno alumno2 = new Alumno("Mijael", "Flores", 13, 50235491);
		Alumno alumno3 = new Alumno("Mijael", "Flores", 11, 50237591);
		
		Docente docente1 = new Docente("Mijael", "Flores", 30, 38423265);
		Docente docente2 = new Docente("Mijael", "Flores", 30, 38123265);
		
		DivisionSecundaria divisionSecundaria1 = new DivisionSecundaria(Grados.PRIMERO);
		DivisionSecundaria divisionSecundaria2 = new DivisionSecundaria(Grados.SEGUNDO);
		//Ejecucion
		docente1.agregarNivelDeCompetencia(NivelesDeEducacion.SECUNDARIA);
		docente2.agregarNivelDeCompetencia(NivelesDeEducacion.SECUNDARIA);
		
		GradoInfoDocenteSecundaria gradoInfoDocenteSecundaria1A = new GradoInfoDocenteSecundaria(Grados.PRIMERO);
		gradoInfoDocenteSecundaria1A.agregarMateria(Materias.MATEMATICA);
		gradoInfoDocenteSecundaria1A.agregarMateria(Materias.FISICA);
		docente1.agregarGradoDeCompetencia(gradoInfoDocenteSecundaria1A);
		
		GradoInfoDocenteSecundaria gradoInfoDocenteSecundaria1B = new GradoInfoDocenteSecundaria(Grados.PRIMERO);
		gradoInfoDocenteSecundaria1B.agregarMateria(Materias.INGLES);
		docente2.agregarGradoDeCompetencia(gradoInfoDocenteSecundaria1B);
		
		GradoInfoDocenteSecundaria gradoInfoDocenteSecundaria2 = new GradoInfoDocenteSecundaria(Grados.SEGUNDO);
		gradoInfoDocenteSecundaria2.agregarMateria(Materias.HISTORIA);
		docente2.agregarGradoDeCompetencia(gradoInfoDocenteSecundaria2);
		
		divisionSecundaria1.agregarAlumnoASecundaria(alumno1);
		divisionSecundaria2.agregarAlumnoASecundaria(alumno2);
		
		divisionSecundaria1.agregarAlumnoASecundaria(alumno3);
		alumno3.agregarNivelDeEducacion(NivelesDeEducacion.PRIMARIA);
		alumno3.subirEdad();
		
		//Verificacion
		assertEquals(Materias.values().length, divisionSecundaria1.getMaterias().size());
		assertTrue(divisionSecundaria1.agregarDocenteASecundaria(docente1, Materias.MATEMATICA));
		assertFalse(divisionSecundaria1.agregarDocenteASecundaria(docente1, Materias.FISICA));
		assertTrue(divisionSecundaria1.agregarDocenteASecundaria(docente2, Materias.INGLES));
		assertTrue(divisionSecundaria1.eliminarDocenteDeSecundaria(docente1));
		assertEquals(1,divisionSecundaria1.getDocentes().size());
		assertEquals(1,divisionSecundaria1.getMateriasOcupadas().size());
		assertEquals(divisionSecundaria1.getMateriasOcupadas().get(0).getMateria(), Materias.INGLES);
		assertTrue(divisionSecundaria1.agregarDocenteASecundaria(docente1, Materias.FISICA));
		assertEquals(2,divisionSecundaria1.getMateriasOcupadas().size());
		assertEquals(1, divisionSecundaria1.getAlumnos().size());
		assertEquals(1, divisionSecundaria2.getAlumnos().size());
		assertTrue(divisionSecundaria1.agregarAlumnoASecundaria(alumno3));
		assertEquals(2, divisionSecundaria1.getAlumnos().size());
		assertTrue(divisionSecundaria2.agregarDocenteASecundaria(docente2, Materias.HISTORIA));
	}
	
	//TEST NIVEL---------------------------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void queSePuedaCargarLasDivisionesYObtenerlas() {
		//Preparacion
		NivelJardin nivelJardin = new NivelJardin();
		NivelPrimaria nivelPrimaria = new NivelPrimaria();
		NivelSecundaria nivelSecundaria = new NivelSecundaria();
		//Ejecucion
		DivisionJardin divisionJardin = nivelJardin.obtenerDivisionJardin(Salas.values()[Salas.values().length-1]);
		DivisionPrimaria divisionPrimaria = nivelPrimaria.obtenerDivisionPrimaria(Grados.values()[Grados.values().length-1]);
		DivisionSecundaria divisionSecundaria = nivelSecundaria.obtenerDivisionSecundaria(Grados.values()[0]);
		//Verificacion
		assertEquals(Salas.values().length, nivelJardin.getDivisiones().size());
		assertEquals(Grados.values().length, nivelPrimaria.getDivisiones().size());
		assertEquals(Grados.values().length, nivelSecundaria.getDivisiones().size());
		assertEquals(divisionJardin.getSala(), Salas.ROJA);
		assertEquals(divisionPrimaria.getGrado(), Grados.SEXTO);
		assertEquals(divisionSecundaria.getGrado(), Grados.PRIMERO);
	}
	
	@Test
	public void queSePuedaJuntarAlumnosDeSalasConMenosDeDiezAlumnos() {
		//Preparacion
		NivelJardin nivelJardin = new NivelJardin();
		
		Alumno alumno1 = new Alumno("Mijael", "Flores", 2, 38423265);
		Alumno alumno1A = new Alumno("Miguel", "Flor", 2, 38423266);
		Alumno alumno2 = new Alumno("Mijael", "Flores", 3, 38561269);
		
		Docente docente1 = new Docente("Kevin", "Flores", 30, 98662931);
		Docente docente2 = new Docente("Kevin", "Flores", 30, 99672731);
		//Ejecucion
		docente1.agregarNivelDeCompetencia(NivelesDeEducacion.JARDIN);
		docente2.agregarNivelDeCompetencia(NivelesDeEducacion.JARDIN);
		
		DivisionJardin salaCeleste = nivelJardin.obtenerDivisionJardin(Salas.CELESTE);
		DivisionJardin salaVerde = nivelJardin.obtenerDivisionJardin(Salas.VERDE);
		
		salaCeleste.agregarAlumnoAJardin(alumno1);
		salaCeleste.agregarAlumnoAJardin(alumno1A);
		salaCeleste.agregarDocente(docente1);
		
		salaVerde.agregarAlumnoAJardin(alumno2);
		salaVerde.agregarDocente(docente2);
		
		nivelJardin.juntarAlumnosDeSalasConMenosDeDiezAlumnos();
		//Verificacion
		assertEquals(3, salaCeleste.getAlumnos().size());
		assertEquals(2, salaCeleste.getDocentes().size());
		assertEquals(0, salaVerde.getAlumnos().size());
		assertEquals(0, salaVerde.getDocentes().size());
	}
	
	//TEST INSTITUCION-----------------------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void queSePuedaCrearUnaInstitucionYAgregarNiveles() {
		//Preparacion
		Institucion institucion = new Institucion("Colegio");
		//Ejecucion
		institucion.agregarNivel(NivelesDeEducacion.SECUNDARIA);
		institucion.agregarNivel(NivelesDeEducacion.PRIMARIA);
		institucion.agregarNivel(NivelesDeEducacion.JARDIN);
		institucion.agregarNivel(NivelesDeEducacion.JARDIN);
		institucion.ordenarNiveles();
		//Verificacion
		assertEquals(3, institucion.getNiveles().size());
		assertEquals(NivelesDeEducacion.JARDIN, institucion.getNiveles().get(0).getNivel());
		assertEquals(NivelesDeEducacion.PRIMARIA, institucion.getNiveles().get(1).getNivel());
		assertEquals(NivelesDeEducacion.SECUNDARIA, institucion.getNiveles().get(2).getNivel());
	}
}
