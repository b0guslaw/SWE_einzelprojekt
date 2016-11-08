package swe2016.fahrzeuge.fm;

import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;

import java.util.ArrayList;

public interface FahrzeugDAO {
    ArrayList<Fahrzeug> getFahrzeugList();
    Fahrzeug getFahrzeugById(int id);
    void speichereFahrzeug(Fahrzeug f);
    void loescheFahrzeug(Fahrzeug f);
}
