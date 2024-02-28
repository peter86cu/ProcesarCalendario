package com.batch.marcas.tasks;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobTask {

	private static final Logger LOG = LoggerFactory.getLogger(JobTask.class);
	
	private final long SEGUNDO = 100000;   
	  private final long MINUTO = SEGUNDO * 60;   
	  private final long HORA = MINUTO * 60;
	  private final long DIA= HORA*24;
	  

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Scheduled(fixedDelay  = SEGUNDO)
	public void executeJob() throws Exception {
		
		
		
		 try {
			 LOG.info("Inicio: " + new Date());
			 java.sql.Date fecha = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
			String fechaC= fecha.toLocalDate().getDayOfMonth()+"-"+fecha.toLocalDate().getYear();
				JobParameters jobParameters = new JobParametersBuilder()
						.addString("JobID", fechaC).toJobParameters();
				jobLauncher.run(job, jobParameters);
	        } catch (Exception e) {
	            System.out.println("Estado del job: " +  e.getMessage());
 
	        }

	}

}
