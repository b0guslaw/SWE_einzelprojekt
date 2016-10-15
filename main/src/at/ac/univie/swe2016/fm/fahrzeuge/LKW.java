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

public abstract class LKW extends Fahrzeug {

    int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    public LKW(String marke, String modell, int baujahr, double grundpreis, int id){
        super(marke,modell,baujahr,grundpreis,id);
    }

    double getRabatt(){
        double rate = 0;

        for (int j = getBaujahr(); j < currentYear; j++) {
            rate = rate + 5;
        }

        if(rate > 20){ rate = 20;}
        return rate;
    }

    public String toString(){
        String data = getMarke() + " " + getModell() + " " + getBaujahr() + " " + getGrundpreis() + getId();
        return data;
    }
}
