package com.batch.marcas.read;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.marcas.model.Empleado;
import com.batch.marcas.model.Resultado;
import com.batch.marcas.repositorio.EmpleadoJpaSpring;

public class MarcasEmpleadosItemReader implements ItemReader<Resultado> {

	@Autowired
	private EmpleadoJpaSpring respository;

	private Iterator<Object> empleadoIterator;

	@BeforeStep
	public void before(StepExecution stepExecution) {

		empleadoIterator = respository.findAllEmpleadoHorarios().iterator();
	}

	@Override
	public Resultado read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (empleadoIterator != null && empleadoIterator.hasNext()) {
			Object[] objArray = (Object[]) empleadoIterator.next();
			Resultado resultado = new Resultado();

			resultado.setIdempleado(objArray[0].toString());
			resultado.setHoras((int) objArray[1]);
			resultado.setValor(objArray[2].toString());
			resultado.setHorastrabajo(objArray[3].toString());

			return resultado;
		} else {
			return null;
		}
	}

}
