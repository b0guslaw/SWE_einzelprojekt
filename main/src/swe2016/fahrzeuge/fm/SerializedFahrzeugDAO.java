package swe2016.fahrzeuge.fm;

import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Ralph Dworzanski
 *
 * Die Klasse SerializedFahrzeugDAO ist eine Implementation des Interface FahrzeugDAO. Bei Initalisierung versucht sie
 * ein File zu laden des uebergeben Pfades, wird das File gefunden wird es deserialisiert und in eine ArrayList kopiert,
 * falls nicht wird das erstellen oder laden einer File  uebersprungen. FILE I/O geschieht in der Methode savefile()
 */
public class SerializedFahrzeugDAO implements FahrzeugDAO {

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
                fahrzeugList.remove(f);
                saveFile();
    }

    /**
     * Interne Methode zum Serialisieren der ArrayList
     *
     */
    private void saveFile(){
        try{

            if(fahrzeugList.isEmpty()){
                file.delete();
                return;
            }

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
