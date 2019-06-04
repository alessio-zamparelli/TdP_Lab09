package it.polito.tdp.borders.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.util.NeighborCache;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	private BordersDAO dao;
	private List<Country> countries;
	private List<Border> borders;
	private NeighborCache<Country, DefaultEdge> neighborCache;

	private Graph<Country, DefaultEdge> grafo;

	public Model() {

		dao = new BordersDAO();
		grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);

	}

	public void loadData(int year) {
		// carico tutti i vertici
		countries = dao.loadAllCountries().stream().sorted().collect(Collectors.toList());
		Graphs.addAllVertices(grafo, countries);
		// carico tutti gli archi
		borders = dao.getCountryPairs(year);
		for (Border b : borders) {
			grafo.addEdge(b.getC1(), b.getC2());
		}
		neighborCache = new NeighborCache<>(grafo);

	}

	public int getConnected() {
		ConnectivityInspector<Country, DefaultEdge> cInspector = new ConnectivityInspector<>(grafo);
		List<Set<Country>> connectedListSet = cInspector.connectedSets();
		int res = 0;
		for(Set<Country>s:connectedListSet) 
			res+=s.size();
		
		return res;
	}

	public List<Country> getCountryList() {
		return this.countries;
	}

	public Set<Country> getConfinanti(Country c) {
		return neighborCache.neighborsOf(c);
	}

}
