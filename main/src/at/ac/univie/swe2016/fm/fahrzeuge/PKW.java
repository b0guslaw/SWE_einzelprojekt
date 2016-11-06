package at.ac.univie.swe2016.fm.fahrzeuge;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class PKW extends Fahrzeug {
    private String letztePruefung;
    SimpleDateFormat parser = new SimpleDateFormat("YYYY-MM-DD");

    public PKW(int id, String marke, String modell, double baujahr, double grundpreis, String letztePruefung) {
        super(id, marke, modell, baujahr, grundpreis);
        setLetztePruefung(letztePruefung);
    }

    @Override
    public int getRabatt() {
        int rabatt = 0;
        LocalDate date = LocalDate.parse(letztePruefung);

        rabatt = (int)(5*(currentYear - getBaujahr()) + 2*(currentYear - date.getYear()));

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
