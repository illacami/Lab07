package it.polito.tdp.poweroutages.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println(model.getNercList());
		
		Nerc nerc = model.getNercList().get(3);
		List<PowerOutage> trovata =model.calcolaWorstCase(nerc, 4, 200);
		
		System.out.println("Calcola Worst Case di "+nerc.getValue());
		
		System.out.println(trovata);

		int countPeople=0;
		int countHours=0;
		for(PowerOutage po : trovata) {
			countPeople += po.getPersoneCoinvolte();
			countHours += po.getNumeroOre();
		}
		
		System.out.println("Persone coinvolte = "+countPeople+"\nOre = "+countHours);
	}

}
