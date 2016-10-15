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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializedFahrzeugDAO implements FahrzeugDAO {

    private String path;

    ArrayList<Fahrzeug> fahrzeugList = new ArrayList<Fahrzeug>();

    public SerializedFahrzeugDAO(String path){
        this.path = path;
    }

    public void speichereFahrzeug(Fahrzeug f){
        if(fahrzeugList.contains(f)) { throw new IllegalArgumentException("Fahrzeug exisitiert bereits"); }
        fahrzeugList.add(f);
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
            FileOutputStream stream = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(_data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
