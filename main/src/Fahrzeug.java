/**
 * Created by Ralph Dworzanski on 11.10.16.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 */
import java.util.Calendar;


public abstract class Fahrzeug {

    public String marke;
    public String modell;
    public int baujahr;
    public double grundpreis;
    public int id;

    public Fahrzeug(String marke, String modell, int baujahr, double grundpreis, int id){

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        try {
            if (baujahr > currentYear) {
                throw new IllegalArgumentException();
            }

            this.marke = marke;
            this.modell = modell;
            this.baujahr = baujahr;
            this.grundpreis = grundpreis;
            this.id = id;

        } catch(Exception e) {
            System.out.println("Baujahr darf nicht in der Zukunft liegen");
            e.printStackTrace();
        }
    }

    public Fahrzeug(){
        throw new IllegalArgumentException();
    }

    public double getAlter(){
        double alter = Calendar.getInstance().get(Calendar.YEAR) - baujahr;
        return alter;
    }

    public double getPreis(){
        return grundpreis - getRabatt();
    }

    abstract double getRabatt();
}
