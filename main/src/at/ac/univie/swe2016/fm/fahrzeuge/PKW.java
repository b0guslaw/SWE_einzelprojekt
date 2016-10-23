/**
 * Created by Ralph Dworzanski on 11.10.16.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 */

package at.ac.univie.swe2016.fm.fahrzeuge;

import java.util.Calendar;

public class PKW extends Fahrzeug {
    private String letztePruefung;
    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    public PKW( int id,String marke, String modell, int baujahr, double grundpreis, String letztePruefung){
        super(id,marke,modell,baujahr,grundpreis);
        setLetztePruefung(letztePruefung);
    }

    double getRabatt() {
        double rate = 0;

        for(int j = getBaujahr(); j < currentYear; j++){
            rate = rate+5;
        }

        /*for(int i = letztePruefung; i < currentYear; i++){
            rate += 2;
        }*/

        if(rate > 15){ rate = 15;}

        return rate;
    }

    private void setLetztePruefung(String letztePruefung){
        this.letztePruefung = letztePruefung;
    }

    public String getLetztePruefung(){
        return letztePruefung;
    }

    public String toString(){
        return getMarke() + " " + getModell() + " " + getBaujahr() + " " + getGrundpreis() + " " + getId();
    }
}
