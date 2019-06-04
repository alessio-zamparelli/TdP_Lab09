package it.polito.tdp.borders.db;

import java.util.List;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Border;

public class TestDAO {

	public static void main(String[] args) {

		BordersDAO dao = new BordersDAO();

		System.out.println("Lista di tutte le nazioni:");
		List<Country> countries = dao.loadAllCountries();
		countries.stream().forEach(a->System.out.println(a));

		System.out.println("Lista di tutti i confini:");
		List<Border> borders = dao.getCountryPairs(1900);
		borders.stream().forEach(a->System.out.println(a));
		
	}
}
