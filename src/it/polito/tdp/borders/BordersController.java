/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BordersController {

	private static final int MIN_ANNO = 1816;
	private static final int MAX_ANNO = 2016;

	Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doCalcolaConfini(ActionEvent event) {
		String annoString = txtAnno.getText();
		int anno = 0;
		try {
			anno = Integer.parseInt(annoString);
		} catch (NumberFormatException e) {
			txtResult.setText("Valore anno non corretto");
			return;
		}
		if (anno > MAX_ANNO || anno < MIN_ANNO) {
			txtResult.setText(
					String.format("Anno non corretto, inserire un anno tra il %d ed il %d\n", MIN_ANNO, MAX_ANNO));
			return;
		}
		model.loadData(anno);
		List<Country> countries = model.getCountryList();
		for (Country c : countries)
			txtResult.appendText(
					String.format("%s confina con %d stati\n", c.getFullName(), model.getConfinanti(c).size()));

		txtResult.appendText(String.format("Le componenti connesse sono %d", model.getConnected()));
		txtResult.positionCaret(0);

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
		// Utilizzare questo font per incolonnare correttamente i dati
		txtResult.setStyle("-fx-font-family: monospace");
		txtResult.setFont(Font.font(null, FontWeight.BOLD, 12));
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
