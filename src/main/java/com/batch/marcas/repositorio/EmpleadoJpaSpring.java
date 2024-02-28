package com.batch.marcas.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.batch.marcas.model.Empleado;


public interface EmpleadoJpaSpring extends JpaRepository<Empleado, String>{
	
		
	List<Empleado>findByFechaegresoIsNull();
	
	@Query(value="SELECT e.idempleado,c.horas, ht.valor,ht.horastrabajo FROM empleado e JOIN empleado_cargo ec ON (e.idempleado=ec.idempleado) JOIN cargos c ON (ec.idcargo=c.id) JOIN horario_trabajo ht ON (ht.id=ec.idhorario) AND e.fechaegreso IS NULL", nativeQuery=true)
    List<Object> findAllEmpleadoHorarios();
	
	@Query(value="SELECT e.idempleado,c.horas FROM empleado e JOIN empleado_cargo ec ON (e.idempleado=ec.idempleado) JOIN cargos c ON (ec.idcargo=c.id) JOIN horario_trabajo ht ON (ht.id=ec.idhorario) where e.idempleado=:id", nativeQuery=true)
    List<Object> findEmpleadoHorarios(String id);
	
}
