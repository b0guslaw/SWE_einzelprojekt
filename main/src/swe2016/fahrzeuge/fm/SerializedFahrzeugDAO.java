package swe2016.fahrzeuge.fm;

import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;

import java.io.*;
import java.util.ArrayList;

public class SerializedFahrzeugDAO implements FahrzeugDao, Serializable {

    private String path;
    ArrayList<Fahrzeug> fahrzeugList = new ArrayList<>();
    File file;

    /**
     * Konstruktor der SerializedFahrzeugDAO Klasse. Versucht beim Aufruf ein bestehendes File in eine ArrayList
     * einzulesen, gibt es kein File bleibt die ArrayList leer.
     *
     * @param path Bekommt den Pfad des Serialized files und speichert diesen
     */
    public SerializedFahrzeugDAO(String path){
        this.path = path;
        file = new File(path);
        if(file.exists()){
            try {
                FileInputStream fileIn = new FileInputStream(path);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);

                fahrzeugList = (ArrayList<Fahrzeug>) objectIn.readObject();

                objectIn.close();
                fileIn.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retourniert eine ArrayListe mit allen gespeicherten Fahrzeugen. Ist die Liste leer liefert die Methode
     * null zurück.
     *
     * @return ArrayList oder null
     */
    @Override
    public ArrayList<Fahrzeug> getFahrzeugList() {
        if(fahrzeugList.isEmpty()){
            return null;
        } else {
            return fahrzeugList;
        }
    }

    /**
     * Iteriert so lange durch die Fahrzeugliste bis das Fahrzeug mit der entsprechenden ID gefunden wurde. Falls
     * das Fahrzeug in der Liste nicht exisitert wird null zurueckgegeben.
     *
     * @param id Uebernimmt eine ID als parameter
     * @return FahrzeugObjekt
     */
    @Override
    public Fahrzeug getFahrzeugById(int id) {
        for(Fahrzeug f : fahrzeugList){
            if(id == f.getId()){
                return f;
            }
        }
        return null;
    }

    /**
     * Durchsucht zuerst die Liste ob ein Fahrzeug mit derselben ID vorhanden ist, wenn nein wird das Fahrzeug
     * der Liste hinzugefügt, andernfalls wird eine Illegal ArgumentException ausgeloest
     *
     * @param f FahrzeugObjekt
     */
    @Override
    public void speichereFahrzeug(Fahrzeug f) {
        for(Fahrzeug c : fahrzeugList){
            if(f.getId() == c.getId()){
                throw new IllegalArgumentException("Fahrzeug ID bereits vorhanden");
            }
        }
        fahrzeugList.add(f);
        saveFile();
    }

    /**
     * Durchsucht zuerst die Liste ob ein Fahrzeug mit der ID des uebergebenen Fahrzeugs vorhanden ist,
     * wenn nicht wird das Fahrzeug wird eine Illegal ArgumentException ausgeloest, andernfalls wird das Fahrzeug
     * geloescht
     *
     * @param f FahrzeubObjekt
     */
    @Override
    public void loescheFahrzeug(Fahrzeug f) {
        for(Fahrzeug c : fahrzeugList){
            if(c.getId() == f.getId()){
                fahrzeugList.remove(f);
                saveFile();
            }
        }

        throw new IllegalArgumentException("Fahrzeug nicht in der Liste gefunden.");
    }

    /**
     * Interne Methode zum Serialisieren der ArrayList
     *
     */
    private void saveFile(){
        try{
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(fahrzeugList);
            objectOut.close();
            fileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
