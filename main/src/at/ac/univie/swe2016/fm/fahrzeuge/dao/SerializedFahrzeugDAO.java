/**
 * Created by Ralph Dworzanski on 11.10.16.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 */

package at.ac.univie.swe2016.fm.fahrzeuge.dao;

import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;
import at.ac.univie.swe2016.fm.fahrzeuge.LKW;

import java.io.*;
import java.util.ArrayList;

public class SerializedFahrzeugDAO implements FahrzeugDAO, Serializable {

    private String serPfad;

    private ArrayList<Fahrzeug> fahrzeugList;

    public SerializedFahrzeugDAO(String serPfad){
        this.serPfad = serPfad;

        File f = new File(serPfad);
        if(f.exists()){
            try {
                FileInputStream fin = new FileInputStream(serPfad);
                ObjectInputStream oin = new ObjectInputStream(fin);

                fahrzeugList = (ArrayList<Fahrzeug>) oin.readObject();

                oin.close();
                fin.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Fahrzeug> getFahrzeugList(){
        return fahrzeugList;
    }

    public void speichereFahrzeug(Fahrzeug f){
        if(fahrzeugList.contains(f)) { throw new IllegalArgumentException("Fahrzeug exisitiert bereits"); }
        fahrzeugList.add(f);
        try {
            saveData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loescheFahrzeug(Fahrzeug f){
        if(!fahrzeugList.contains(f)) { throw new IllegalArgumentException("Fahrzeug exisitiert nicht"); }
        fahrzeugList.remove(f);
    }

    public Fahrzeug getFahrzeugById(int id){
        for(Fahrzeug f : fahrzeugList){
            if(f.getId() == id){
                return f;
            }
        }
        return null;
    }

    private void saveData() throws FileNotFoundException {
        try {
            FileOutputStream stream = new FileOutputStream(serPfad);
            ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(fahrzeugList);
            oos.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
