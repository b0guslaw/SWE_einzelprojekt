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

import java.io.*;
import java.util.ArrayList;

public class SerializedFahrzeugDAO implements FahrzeugDAO {

    private String serPfad;
    private ArrayList<Fahrzeug> fList = new ArrayList<Fahrzeug>();

    ArrayList<Fahrzeug> fahrzeugList = new ArrayList<Fahrzeug>();

    public SerializedFahrzeugDAO(String serPfad){
        this.serPfad = serPfad;

        File f = new File(serPfad);
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fin = new FileInputStream(serPfad);
                ObjectInputStream oin = new ObjectInputStream(fin);

                fList = (ArrayList<Fahrzeug>) oin.readObject();
                fahrzeugList = fList;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void speichereFahrzeug(Fahrzeug f){
        if(fahrzeugList.contains(f)) { throw new IllegalArgumentException("Fahrzeug exisitiert bereits"); }
        fahrzeugList.add(f);
        try {
            saveData(fList);
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

    public ArrayList<Fahrzeug> getFahrzeugList(){
        return fahrzeugList;
    }

    public void saveData(ArrayList<Fahrzeug> _data) throws FileNotFoundException {
        try {
            FileOutputStream stream = new FileOutputStream(serPfad);
            ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(_data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
