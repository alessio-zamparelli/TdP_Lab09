package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	private Map<Integer, Country> idMapAllCountry;

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c = new Country(rs.getInt("ccode"), rs.getString("stateabb"), rs.getString("statenme"));
				result.add(c);
			}

			idMapAllCountry = result.parallelStream().collect(Collectors.toMap(Country::getCode, Country::identity));

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {
		String sql;
		String sql1 = "SELECT state1no, state2no, year FROM contiguity AS c WHERE c.year <= ? AND c.conttype=1";
		String sql2 = "SELECT state1no, state2no, year FROM contiguity2006 AS c WHERE c.year <= ? AND c.conttype=1";
		if(anno>=2006)
			sql = sql2;
		else 
			sql = sql1;
		
		List<Border> result = new ArrayList<Border>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c1 = idMapAllCountry.get(rs.getInt("state1no"));
				Country c2 = idMapAllCountry.get(rs.getInt("state2no"));
				Border b = new Border(c1, c2, rs.getInt("year"));
				result.add(b);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}

	}
}
