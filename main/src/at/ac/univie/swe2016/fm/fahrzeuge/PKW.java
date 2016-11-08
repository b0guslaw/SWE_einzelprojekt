package at.ac.univie.swe2016.fm.fahrzeuge;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * @author Ralph Dworzanski
 *
 * Die Klasse PKW erweitert die Klasse Fahrzeug und implementiert die abstrakte Funktion getRabatt() und erweitert sie um eine
 * Funktion get- und setLetztePruefung()
 */
public class PKW extends Fahrzeug implements Serializable {
    private static final long serialVersionUID = 4536469476947546147L;
    private String letztePruefung;

    public PKW(int id, String marke, String modell, double baujahr, double grundpreis, String letztePruefung) {
        super(id, marke, modell, baujahr, grundpreis);
        setLetztePruefung(letztePruefung);
    }

    /**
     * Retourniert den Rabatt des Fahrzeugs basierend auf dem Baujahr und dem Jahr der letzten Prueufng
     *
     * @return
     */
    @Override
    public int getRabatt() {
        LocalDate date = LocalDate.parse(letztePruefung);
        int rabatt = (int)(5*(currentYear - getBaujahr()) + 2*(currentYear - date.getYear()));

        if(rabatt > 15){
            rabatt = 15;
        }

        return rabatt;
    }

    public String getLetztePruefung() {
        return letztePruefung;
    }

    public void setLetztePruefung(String letztePruefung) {
        this.letztePruefung = letztePruefung;
    }

    public String toString() {
        return getMarke() + " " + getModell() + " " + getBaujahr() + " " + getGrundpreis() + " " + getId() + " " + getLetztePruefung();
    }
}
