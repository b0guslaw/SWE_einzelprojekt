package at.ac.univie.swe2016.fm.fahrzeuge;

import java.io.Serializable;

/**
 * @author Ralph Dworzanski
 *
 * Die Klasse LKW erweitert die Klasse Fahrzeug und implementiert die abstrakte Funktion getRabatt()
 */
public class LKW extends Fahrzeug implements Serializable {
    private static final long serialVersionUID = 4536469595692546147L;

    public LKW(int id, String marke, String modell, double baujahr, double grundpreis){
        super(id,marke,modell,baujahr,grundpreis);
    }

    /**
     * Retourniert den Rabatt des Fahrzeugs basierend auf dem Baujahr
     *
     * @return
     */
    @Override
    public int getRabatt() {
        int rabatt = (int)(currentYear - getBaujahr())*5;

        if(rabatt > 20){
            rabatt = 20;
        }

        return rabatt;
    }
}
