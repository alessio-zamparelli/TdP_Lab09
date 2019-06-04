package it.polito.tdp.borders.model;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();

		model.loadData(2000);
		Random rand = new Random();
		List<Country> countries = model.getCountryList();
		Country c = countries.get(rand.nextInt(countries.size()));
		Set<Country> confinanti = model.getConfinanti(c);
		System.out.format("Confinanti a %s codice %d\n", c.getAb(), c.getCode());
		System.out.format("Ci sono %d confinanti a %s\n", confinanti.size(), c.getFullName());		
		confinanti.stream().sorted().forEach(a -> System.out.println(a));
		
		System.out.format("Le componenti connesse sono %d", model.getConnected());

//		System.out.println("Creo il grafo relativo al 2000");
//		model.createGraph(2000);

//		List<Country> countries = model.getCountries();
//		System.out.format("Trovate %d nazioni\n", countries.size());

//		System.out.format("Numero componenti connesse: %d\n", model.getNumberOfConnectedComponents());

//		Map<Country, Integer> stats = model.getCountryCounts();
//		for (Country country : stats.keySet())
//			System.out.format("%s %d\n", country, stats.get(country));		

	}

}
