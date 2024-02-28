package com.batch.marcas.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.marcas.model.CalendarioEmpleado;
import com.batch.marcas.repositorio.CalendarioEmpleadoJpa;

public class MarcaProcesadaItemWriter implements ItemWriter<CalendarioEmpleado>{
	
	@Autowired
    private CalendarioEmpleadoJpa respository;

	@Override
	public void write(List<? extends CalendarioEmpleado> items) throws Exception {

		
        	 respository.saveAll(items);	
        
	}
	
	

}
