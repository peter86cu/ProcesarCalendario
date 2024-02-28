package com.batch.marcas.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

import com.batch.marcas.model.Resultado;
import com.batch.marcas.model.HorarioLaboral;


@Component
public class MarcaProcesadaItemWriterListener implements ItemProcessListener<Resultado, HorarioLaboral> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MarcaProcesadaItemWriterListener.class);


	

	   @Override
	    public void beforeProcess(Resultado creditCard) {
	        LOGGER.info("beforeProcess");
	    }

	    @Override
	    public void afterProcess(Resultado creditCard, HorarioLaboral creditCardRisk) {
	        LOGGER.info("afterProcess: " + creditCard + " ---> " + creditCardRisk);
	    }

	    @Override
	    public void onProcessError(Resultado creditCard, Exception e) {
	        LOGGER.info("onProcessError");
	    }
}
