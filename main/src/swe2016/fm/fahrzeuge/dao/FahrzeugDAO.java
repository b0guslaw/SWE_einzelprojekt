package swe2016.fm.fahrzeuge.dao; /**
 * Created by Ralph Dworzanski on 11.10.16.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 */
import java.util.ArrayList;

public class FahrzeugDAO {

    private String path;
    ArrayList fahrzeugList<Fahrzeug> = new ArrayList<Fahrzeug>();

    public FahrzeugDAO(String path){
        this.path = path;
    }

    public void speichereFahrzeug(Fahrzeug f){
        if(fahrzeugList.contains(f)) { throw new IllegalArgumentException("at.ac.univie.swe2016.fm.at.ac.univie.swe2016.swe.fm.fahrzeuge.Fahrzeug exisitiert bereits"); }
        fahrzeugList.add(f);
    }

    public void loescheFahrzeug(Fahrzeug f){
        fahrzeugList.remove(f);
    }

    public Fahrzeug getFahrzeugById(int id){
        for(Fahrzeug f : fahrzeugList){
            if(id == f.getId()){ return f; }
        }

        return null;
    }
}
