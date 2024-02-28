package com.batch.marcas.model;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "calendariohorariolaboral")
public class HorarioLaboral implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idHorario;

	private String diaSemana;

	private LocalTime horaInicio;

	private LocalTime horaFin;

	private int trabaja;

	private int dia;
	private int mes;
	private int annio;

	@ManyToOne
	@JoinColumn(name = "idCalendario")
	private CalendarioEmpleado calendarioEmpleado;

	public String getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(String idHorario) {
		this.idHorario = idHorario;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public CalendarioEmpleado getCalendarioEmpleado() {
		return calendarioEmpleado;
	}

	public void setCalendarioEmpleado(CalendarioEmpleado calendarioEmpleado) {
		this.calendarioEmpleado = calendarioEmpleado;
	}

	public int isTrabaja() {
		return trabaja;
	}

	public void setTrabaja(int trabaja) {
		this.trabaja = trabaja;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnnio() {
		return annio;
	}

	public void setAnnio(int annio) {
		this.annio = annio;
	}

}