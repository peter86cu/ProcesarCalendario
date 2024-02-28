package com.batch.marcas;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Temp {
	
	/* public static void main(String[] args) {
	
		        LocalTime horaInicio = LocalTime.of(8, 30); // 08:00
		        LocalTime horaFin = LocalTime.of(17, 00); // 17:00
		        
		        LocalTime horaActual = horaInicio;
		        
		        LocalDate currentDate = LocalDate.now();
		        LocalDate localDate = currentDate.atTime(horaActual).toLocalDate(); // Convertir a LocalDate
		        
		        DayOfWeek dayOfWeek = localDate.getDayOfWeek(); // Obtener el día de la semana
		        Locale spanishLocale = new Locale("es", "ES");
		        String displayName = dayOfWeek.getDisplayName(TextStyle.FULL, spanishLocale); // Obtener el nombre del día en español

		        System.out.println("Día de la semana: " + displayName);

		        
		        while (horaActual.isBefore(horaFin) || horaActual.equals(horaFin)) {
		            System.out.println(horaActual);
		            horaActual = horaActual.plusHours(1);
		        }
		    
	    }*/
	
	
	 public static void main(String[] args) {
	        int year = 2023; // Año
	        int month = 6; // Mes (1 para enero, 2 para febrero, etc.)

	        LocalDate startDate = LocalDate.of(year, month, 1); // Primer día del mes
	        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth()); // Último día del mes

	        LocalDate currentDate = startDate;
	        while (!currentDate.isAfter(endDate)) {
	            System.out.println("Día: " + currentDate.getDayOfMonth());

	            // Realizar las operaciones necesarias para cada día

	            currentDate = currentDate.plusDays(1); // Avanzar al siguiente día
	        }
	    }

}
