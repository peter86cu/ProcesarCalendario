package com.batch.marcas;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.batch.marcas.listener.MarcaProcesadaItemWriterListener;
import com.batch.marcas.listener.MarcasEmpleadoItemReaderListener;
import com.batch.marcas.listener.ProcesarMarcaItemProcessListener;
import com.batch.marcas.listener.ProcesarMarcaJobExecutionListener;
import com.batch.marcas.model.*;
import com.batch.marcas.processor.ProcesarMarcaItemProcessor;
import com.batch.marcas.read.MarcasEmpleadosItemReader;
import com.batch.marcas.tasks.JobTask;
import com.batch.marcas.writer.MarcaProcesadaItemWriter;



@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
    public MarcasEmpleadosItemReader reader() {
        return new MarcasEmpleadosItemReader();
    }
	
	@Bean
    public ProcesarMarcaItemProcessor processor() {
        return new ProcesarMarcaItemProcessor();
    }
	
	 @Bean
	    public MarcaProcesadaItemWriter writer() {
	        return new MarcaProcesadaItemWriter();
	    }

	 
	 @Bean
	    public JobTask jobExecutionListener() {
	        return new JobTask();
	    }

	    @Bean
	    public MarcasEmpleadoItemReaderListener readerListener() {
	        return new MarcasEmpleadoItemReaderListener();
	    }

	    @Bean
	    public ProcesarMarcaItemProcessListener creditCardItemProcessListener() {
	        return new ProcesarMarcaItemProcessListener();
	    }

	    @Bean
	    public MarcaProcesadaItemWriterListener writerListener() {
	        return new MarcaProcesadaItemWriterListener();
	    }

	    @Bean
	    public Job job(Step step, ProcesarMarcaJobExecutionListener jobExecutionListener) {
	        Job job = jobBuilderFactory.get("job1")
	                .listener(jobExecutionListener)
	                .flow(step)
	                .end()
	                .build();
	        return job;
	    }

	    @Bean
	    public Step step(MarcasEmpleadosItemReader reader,
	    		         MarcaProcesadaItemWriter writer,
	    		         ProcesarMarcaItemProcessor processor,
	    		         MarcasEmpleadoItemReaderListener readerListener,
	    		         ProcesarMarcaItemProcessListener marcaEmpleadoItemProcessListener,
	    		         MarcaProcesadaItemWriterListener writerListener) {

	        TaskletStep step = stepBuilderFactory.get("step2")
	                .<Resultado, CalendarioEmpleado>chunk(100)
	                .reader(reader)
	                .processor(processor)
	                .writer(writer)
	                .listener(readerListener)
	                .listener(marcaEmpleadoItemProcessListener)
	                .listener(writerListener)
	                .build();
	        return step;
	    }

}
