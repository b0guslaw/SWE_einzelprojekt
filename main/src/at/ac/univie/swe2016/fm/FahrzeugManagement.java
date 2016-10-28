/**
 * Created by Ralph Dworzanski on 11.10.16.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 */

package at.ac.univie.swe2016.fm;

import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;
import at.ac.univie.swe2016.fm.fahrzeuge.LKW;
import at.ac.univie.swe2016.fm.fahrzeuge.PKW;
import at.ac.univie.swe2016.fm.fahrzeuge.dao.FahrzeugDAO;
import at.ac.univie.swe2016.fm.fahrzeuge.dao.SerializedFahrzeugDAO;

import java.util.ArrayList;

public class FahrzeugManagement {
    private FahrzeugDAO fahrzeugDAO;

    public FahrzeugManagement(String path){
        fahrzeugDAO = new SerializedFahrzeugDAO(path);
    }

    public void setFahrzeugDao(FahrzeugDAO fahrzeugDAO){
        this.fahrzeugDAO = fahrzeugDAO;
    }

    public FahrzeugDAO getFahrzeugDAO() {
        return fahrzeugDAO;
    }

    public void showFahrzeuge(){
        ArrayList<Fahrzeug> fList = fahrzeugDAO.getFahrzeugList();

        if(fList.isEmpty()){
            return;
        }

        for(Fahrzeug f : fList){
            System.out.println(f.toString());
        }
    }

    public void addFahrzeug(String args[]){
        if(args[2].equals("lkw")){
            LKW f = new LKW(Integer.parseInt(args[3]),args[4],args[5],Integer.parseInt(args[6]),Double.parseDouble(args[7]));
            //C:\Users\ralph\Desktop\data.ser add lkw 1 Iveco Eurocargo 2014 18000.5
            fahrzeugDAO.speichereFahrzeug(f);
       }
    }

    public void removeFahrzeug(String args[]){
        if(args[1].toLowerCase().equals("lkw")){
            Fahrzeug f = new LKW(Integer.parseInt(args[2]),args[3],args[4],Integer.parseInt(args[5]),Double.parseDouble(args[6]));
            fahrzeugDAO.loescheFahrzeug(f);
        }
    }

    public void getFahrzeugAnzahl() {
        System.out.println(fahrzeugDAO.getFahrzeugList().size());
    }

    public void getPKWAnzahl(){
        int i = 0;
        for(Fahrzeug f : fahrzeugDAO.getFahrzeugList()){
            if(f instanceof PKW){
                i++;
            }
        }
        System.out.println(i);
    }

    public void getLKWAnzahl(){
        int i = 0;
        for(Fahrzeug f : fahrzeugDAO.getFahrzeugList()){
            if(f instanceof LKW){
                i++;
            }
        }
        System.out.println(i);
    }

    public void getMeanPrice(){
        double meanprice = 0;
        int counter = 0;
        for(Fahrzeug f : fahrzeugDAO.getFahrzeugList()){
                counter++;
                meanprice = meanprice + f.getPreis();
        }
        System.out.println(meanprice/counter);
    }

    public void getMeanAge(){
        double meanage = 0;
        int counter = 0;
        for(Fahrzeug f : fahrzeugDAO.getFahrzeugList()){
                counter++;
                meanage = meanage + f.getAlter();
            }
        System.out.println(meanage/counter);
    }

    public void getMeanPricePKW(){
        double meanage = 0;
        int counter = 0;
        for(Fahrzeug f : fahrzeugDAO.getFahrzeugList()){
            if(f instanceof PKW){
                counter++;
                meanage = meanage + f.getAlter();
            }
        }
        System.out.println(meanage/counter);
    }

    public void getMeanPriceLKW() {
        double meanage = 0;
        int counter = 0;
        for (Fahrzeug f : fahrzeugDAO.getFahrzeugList()) {
            if (f instanceof LKW) {
                counter++;
                meanage = meanage + f.getAlter();
            }
        }
        System.out.println(meanage / counter);
    }

    public void getOldestVehicle(){
        Fahrzeug oldest = null;
        for(Fahrzeug f : fahrzeugDAO.getFahrzeugList()){
            if(oldest == null || f.getAlter() > oldest.getAlter()){
                oldest = f;
            }
        }
        System.out.println(oldest.toString());
    }
}
