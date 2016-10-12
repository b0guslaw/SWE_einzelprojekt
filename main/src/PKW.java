/**
 * Created by Ralph Dworzanski on 11.10.16.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 */

import java.util.Calendar;

public abstract class PKW extends Fahrzeug {
    private int letztePruefung;
    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    public PKW(String marke, String modell, int baujahr, double grundpreis, int id, int letztePruefung){
        super(marke,modell,baujahr,grundpreis,id);
        setLetztePruefung(letztePruefung);
    }

    double getRabatt() {
        double rate = 0;

        for(int j = getBaujahr(); j < currentYear; j++){
            rate = rate+5;
        }

        for(int i = letztePruefung; i < currentYear; i++){
            rate += 2;
        }

        if(rate > 15){ rate = 15;}

        return rate;
    }

    public void setLetztePruefung(int letztePruefung){
        this.letztePruefung = letztePruefung;
    }

    public int getLetztePruefung(){
        return letztePruefung;
    }

    public String toString(){
        String data = getMarke() + " " + getModell() + " " + getBaujahr() + " " + getGrundpreis() + " " + getId();
        return data;
    }
}
