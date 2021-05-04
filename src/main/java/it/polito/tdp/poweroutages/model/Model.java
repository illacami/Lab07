package it.polito.tdp.poweroutages.model;


import java.util.LinkedList;
import java.util.List;


import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	private List<PowerOutage> partenza;
	private List<PowerOutage> soluzioneMigliore;
	private int maggiorNumeroCoinvolti;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<PowerOutage> calcolaWorstCase(Nerc nerc, int xAnni, int yOre) {
		
		partenza = podao.getPowerOutages(nerc);
		
		List<PowerOutage> parziale = new LinkedList<PowerOutage>();
		
		soluzioneMigliore = new LinkedList<PowerOutage>();
		
		maggiorNumeroCoinvolti = 0;
		
		//cerca(parziale, 0, numeroCrediti);
		cerca2(parziale, 0, xAnni, yOre);
		
		return soluzioneMigliore;
	}
	private void cerca2(List<PowerOutage> parziale, int L, int x, int y) {
		
		//casi terminali
		if(L == partenza.size()) {
			int numeroPersone = calcolaCoinvolti(parziale);
			if(numeroPersone > maggiorNumeroCoinvolti) {
				soluzioneMigliore = new LinkedList<>(parziale);
				maggiorNumeroCoinvolti = numeroPersone;
			}
			return;
		}
		
		//quando la somma degli anni sfora
		//quando la somma delle ore sfora
	
		int anni = calcolaAnni(parziale);
		int ore = calcolaOreTotali(parziale);
		
		if(anni > x || ore > y) {
			return;
		}
		
		if(anni == x || ore == y) {
			int numeroPersone = calcolaCoinvolti(parziale);
			if(numeroPersone > maggiorNumeroCoinvolti) {
				soluzioneMigliore = new LinkedList<>(parziale);
				maggiorNumeroCoinvolti = numeroPersone;
			}
			return;
		}
		
		//raggiungiamo la fine dei livelli (L=N) non ci sono più eventi da aggiungere
		
		
		//sottoproblemi
		parziale.add(partenza.get(L));
		cerca2(parziale, L+1, x, y);
		
		parziale.remove(partenza.get(L));
		cerca2(parziale, L+1, x, y);
	}

	private int calcolaCoinvolti(List<PowerOutage> parziale) {
		int persone = 0;
		
		for(PowerOutage po : parziale) {
			persone += po.getPersoneCoinvolte();
		}
		
		return persone;
	}

	private int calcolaOreTotali(List<PowerOutage> parziale) {
		
		int ore = 0;
		
		for(PowerOutage po : parziale) {
			ore += po.getNumeroOre();
		}
		
		return ore;
	}

	private int calcolaAnni(List<PowerOutage> parziale) {
		
		if(parziale.size() == 0 || parziale.size() == 1)
			return 0;
		
		int anni = parziale.get(parziale.size()-1).getDataIn().compareTo(parziale.get(0).getDataIn());
		
		return anni;
	}
}
