package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class PowerOutage {
	
	private int id;
	private LocalDateTime dataIn;
	private LocalDateTime dataFine;
	private int numeroOre;
	private int personeCoinvolte;
	
	
	
	public PowerOutage(int id, LocalDateTime dataIn, LocalDateTime dataFine,  int personeCoinvolte) {
		super();
		this.id = id;
		this.dataIn = dataIn;
		this.dataFine = dataFine;
		this.numeroOre = (int) ((Duration.between(dataIn, dataFine).getSeconds())/3600);
		this.personeCoinvolte = personeCoinvolte;
	}

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public LocalDateTime getDataIn() {
		return dataIn;
	}

	public void setDataIn(LocalDateTime dataIn) {
		this.dataIn = dataIn;
	}

	public LocalDateTime getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}

	public int getNumeroOre() {
		return numeroOre;
	}

	public void setNumeroOre(int numeroOre) {
		this.numeroOre = numeroOre;
	}

	public int getPersoneCoinvolte() {
		return personeCoinvolte;
	}

	public void setPersoneCoinvolte(int personeCoinvolte) {
		this.personeCoinvolte = personeCoinvolte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return this.getDataIn().getYear()+" "+this.getDataIn()+" "+this.getDataFine()+" "+this.getNumeroOre()+" "+this.getPersoneCoinvolte()+"\n";
	}

	
	
	
	

}
