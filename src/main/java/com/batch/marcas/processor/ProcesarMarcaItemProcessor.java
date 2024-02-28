package com.batch.marcas.processor;



import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.marcas.model.*;
import com.batch.marcas.repositorio.FeriadosJPA;

//Aqui proceso las marcas
public class ProcesarMarcaItemProcessor implements ItemProcessor<Resultado, CalendarioEmpleado> {

	private static final Logger LOG = LoggerFactory.getLogger(ProcesarMarcaItemProcessor.class);
	
	
	@Autowired
	private FeriadosJPA repositoriFeriados;

	@Override
	public CalendarioEmpleado process(Resultado item) throws Exception {

		CalendarioEmpleado procesada = new CalendarioEmpleado();
		List<HorarioLaboral> horariosLaborales  = new ArrayList<HorarioLaboral>();
		LocalTime horaInicio= null;
		LocalTime horaFin=null;
		
		List<Feriados> lstFeriados=repositoriFeriados.findAll();
		
		if(item!=null) {
			String[] h= item.getHorastrabajo().split("-");
			String[] hInicio=h[0].split(":");
			String[] hFin=h[1].split(":");
			
			if(hInicio!=null) {
				 horaInicio = LocalTime.of(Integer.parseInt( hInicio[0]), Integer.parseInt( hInicio[1]));
			}
			if(hFin!=null) {
				 horaFin = LocalTime.of(Integer.parseInt( hFin[0]), Integer.parseInt( hFin[1]));

			}

			 // Obtener la fecha actual
	        LocalDate fechaActual = LocalDate.now();

	        // Obtener el mes siguiente
	        //LocalDate fechaSiguiente = fechaActual.plusMonths(1);

	        // Obtener el mes y año del mes siguiente
	        /*Month mesSiguiente = fechaSiguiente.getMonth();
	        int anoSiguiente = fechaSiguiente.getYear();*/
	        
	        Month mesSiguiente = fechaActual.getMonth();
	        int anoSiguiente = fechaActual.getYear();

	        LocalDate startDate = LocalDate.of(anoSiguiente, mesSiguiente.getValue(), 1); // Primer día del mes
	        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth()); // Último día del mes

	        LocalDate currentDate = startDate;
	        procesada.setIdCalendario(UUID.randomUUID().toString());
			procesada.setIdempleado(item.getIdempleado());
			procesada.setIdempleado(item.getIdempleado());

			
			
	        while (!currentDate.isAfter(endDate)) {
	            System.out.println("Día: " + currentDate.getDayOfMonth());
	            //Obtiene dia de la semana
	            DayOfWeek dayOfWeek = currentDate.getDayOfWeek(); // Obtener el día de la semana
		        Locale spanishLocale = new Locale("es", "ES");
		        String displayName = dayOfWeek.getDisplayName(TextStyle.FULL, spanishLocale); // Obtener el nombre del día en español
		        int dia= currentDate.getDayOfMonth();
		        int mes= currentDate.getMonthValue();
		        int anio= currentDate.getDayOfMonth();
		        
		        
	            String[] diaTrabajo= item.getValor().split(",");
	            boolean encontrado = Arrays.stream(diaTrabajo)
	                    .anyMatch(valor -> valor.equals(displayName));

	            
	            
	            Optional<Feriados> resultado = lstFeriados.stream()
	                    .filter(numero -> numero.getDia() == dia &&  numero.getMes()== mes && numero.getAnio()==anoSiguiente)
	                    .findFirst();
	            
	           
	            HorarioLaboral laboral= new HorarioLaboral();
	            if(encontrado) {
	            	 // Realizar las operaciones necesarias para cada día
		           
		            laboral.setHoraFin(horaFin);
		            laboral.setHoraInicio(horaInicio);
		            laboral.setIdHorario(UUID.randomUUID().toString());
		            laboral.setDiaSemana(displayName);
		            laboral.setTrabaja(1);
	            	
	            }else {
	            	 // Realizar las operaciones necesarias para cada día
		            laboral.setHoraFin(horaFin);
		            laboral.setHoraInicio(horaInicio);
		            laboral.setIdHorario(UUID.randomUUID().toString());
		            laboral.setDiaSemana(displayName);
		            laboral.setTrabaja(2);
	            }
	            if( resultado.isPresent()) {
		        	   laboral.setTrabaja(resultado.get().getEstado());
		           }
	            
	            
	            laboral.setAnnio(anoSiguiente);
	            laboral.setDia(currentDate.getDayOfMonth());
	            laboral.setMes( currentDate.getMonthValue());
	            laboral.setCalendarioEmpleado(procesada);

	            horariosLaborales.add(laboral);
	            
	           

	            currentDate = currentDate.plusDays(1); // Avanzar al siguiente día
	        }
			
	       
	        procesada.setHorariosLaborales(horariosLaborales);

		  
		  	
		}
		
		
		

		return procesada;
	}

}
