/**
 * Created by Ralph Dworzanski on 11.10.16.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 */
package at.ac.univie.swe2016.fm.fahrzeuge;


import java.io.Serializable;
import java.util.Calendar;

public abstract class Fahrzeug implements Serializable{

    private String marke;
    private String modell;
    private int baujahr;
    private double grundpreis;
    private int id;

    public Fahrzeug(int id, String marke, String modell, int baujahr, double grundpreis) {
        setId(id);
        setMarke(marke);
        setModell(modell);
        setBaujahr(baujahr);
        setGrundpreis(grundpreis);
    }

    private void setMarke(String marke) {
        this.marke = marke;
    }

    public String getMarke(){
    return marke;
    }

    private void setModell(String modell) {
        this.modell = modell;
    }

    public String getModell(){
        return modell;
    }

    public double getAlter(){
        double alter = Calendar.getInstance().get(Calendar.YEAR) - baujahr;
        return alter;
    }

    private void setBaujahr(int baujahr){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR); try {
            if (baujahr > currentYear) {
                throw new IllegalArgumentException("Baujahr darf nicht in der Zukunft liegen");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.baujahr = baujahr;
    }

    public int getBaujahr(){
        return baujahr;
    }

    private void setGrundpreis(double grundpreis){
        this.grundpreis = grundpreis;
    }

    public double getGrundpreis(){
        return grundpreis;
    }

    public double getPreis(){
        return grundpreis - getRabatt();
    }

    private void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    abstract double getRabatt();
}
