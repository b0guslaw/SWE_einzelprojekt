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

import java.util.ArrayList;

public interface FahrzeugDAO {
    public void speichereFahrzeug(Fahrzeug f);
    public void loescheFahrzeug(Fahrzeug f);
    public Fahrzeug getFahrzeugById(int id);
    public ArrayList<Fahrzeug> getFahrzeugList();
}
