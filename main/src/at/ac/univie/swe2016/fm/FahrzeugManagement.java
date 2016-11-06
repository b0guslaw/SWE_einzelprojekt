package at.ac.univie.swe2016.fm;


import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;
import at.ac.univie.swe2016.fm.fahrzeuge.LKW;
import at.ac.univie.swe2016.fm.fahrzeuge.PKW;
import swe2016.fahrzeuge.fm.FahrzeugDao;
import swe2016.fahrzeuge.fm.SerializedFahrzeugDAO;

import java.util.ArrayList;

public class FahrzeugManagement {
    String path;
    FahrzeugDao dao;

    Fahrzeug f;

    public FahrzeugManagement(String path){
        this.path = path;
        dao = new SerializedFahrzeugDAO(this.path);
    }

    /**
     * Retoruniert die Instanz des FahrzeugDAO
     *
     * @return FahrzeugDAO
     */
    public FahrzeugDao getDao() {
        return dao;
    }

    /**
     * Setzt ein neues FahrzeugDAO
     *
     * @param dao
     */
    public void setDao(FahrzeugDao dao) {
        this.dao = dao;
    }

    /**
     * Gibt alle Daten des uebergeben Fahrzeugobjekts aus
     *
     * @param f FahrzeugObjekt
     */
    public void showFahrzeug(Fahrzeug f){
        System.out.println("ID: \t" + f.getId());
        System.out.println("Marke: \t" +f.getMarke());
        System.out.println("Modell: \t" +f.getModell());
        System.out.println("Bauahr: \t" +f.getBaujahr());
        System.out.println("Grundpreis: \t" +f.getGrundpreis());
        System.out.println("Preis: \t" +f.getPreis());
        if(f instanceof PKW){
            System.out.println(((PKW) f).getLetztePruefung());
        }
    }

    /**
     * Gibt alle Fahrzeuge in der Liste aus
     *
     */
    public void showFahrzeug(){
        for(Fahrzeug f : dao.getFahrzeugList()){
            System.out.println("ID: \t" + f.getId());
            System.out.println("Marke: \t" +f.getMarke());
            System.out.println("Modell: \t" +f.getModell());
            System.out.println("Bauahr: \t" +f.getBaujahr());
            System.out.println("Grundpreis: \t" +f.getGrundpreis());
            System.out.println("Preis: \t" +f.getPreis());
            if(f instanceof PKW) {
                System.out.println(((PKW) f).getLetztePruefung());
            }
        }
    }

    /**
     * Ubernimmt einen String als Parameter und generiert anhand diesem ein FahrzeugObjekt, dass der Liste
     * hinzugefügt wird
     *
     * @param args String Array
     */
    public void addFahrzeug(String args[]){
        try{
            if(args[2].toLowerCase().equals("lkw")){
                LKW l = new LKW(Integer.parseInt(args[3]),args[4],args[5],Double.parseDouble(args[6]),Double.parseDouble(args[7]));
                dao.speichereFahrzeug(l);
            } else if(args[3].toLowerCase().equals("pkw")){
                PKW p = new PKW(Integer.parseInt(args[3]),args[4],args[5],Double.parseDouble(args[6]),Double.parseDouble(args[7]),args[8]);
                dao.speichereFahrzeug(p);
            } else {
                throw new IllegalArgumentException("Man kann nur Fahrzeuge des Typs PKW oder LKW der Liste hinzufuegen");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Erstellt ein Dummy FahrzeugObjekt und uebergibt es der loescheFahrzeug Methode des DAO mit der entsprechenden ID
     *
     * @param arg
     */
    public void loescheFahrzeug(String arg){
        LKW l = new LKW(Integer.parseInt(arg),"","",0,0);
        dao.loescheFahrzeug(l);
        System.out.println("Fahrzeug erfolgreich geloescht");
    }

    /**
     * Zaehlt alle FahrzeugObjekte und gibt diese in der Konsole aus
     */
    public void fahrzeugAnzahl(){
        ArrayList<Fahrzeug> fList = new ArrayList<>(dao.getFahrzeugList());
        System.out.println("Die Liste beinhaltet derzeit " + fList.size()+ " Fahrzeuge");
    }

    public void countPKW(){
        int count = 0;
        ArrayList<Fahrzeug> fList = new ArrayList<>(dao.getFahrzeugList());
        for(Fahrzeug f : fList){
            if(f instanceof PKW){
                count++;
            }
        }
        System.out.println("Die Liste beinhaltet derzeit " + count+ " PKW");
    }

    public void countLKW(){
        int count = 0;
        ArrayList<Fahrzeug> fList = new ArrayList<>(dao.getFahrzeugList());
        for(Fahrzeug f : fList){
            if(f instanceof LKW){
                count++;
            }
        }
        System.out.println("Die Liste beinhaltet derzeit " + count+ " LKW");
    }

    public void meanPrice(){
        double meanPrice = 0;
        ArrayList<Fahrzeug> fList = new ArrayList<>(dao.getFahrzeugList());
        for(Fahrzeug f : fList){
            meanPrice =+ f.getPreis();
            }

        System.out.println("Der Durchschnittspreis aller Fahrzeuge betraegt " + meanPrice);
    }

    public void meanPricePKW(){
        double meanPrice = 0;
        ArrayList<Fahrzeug> fList = new ArrayList<>(dao.getFahrzeugList());
        for(Fahrzeug f : fList){
            if(f instanceof PKW) {
                meanPrice = +f.getPreis();
            }
        }
        System.out.println("Der Durchschnittspreis aller PKW betraegt " + meanPrice);
    }

    public void meanPriceLKW(){
        double meanPrice = 0;
        ArrayList<Fahrzeug> fList = new ArrayList<>(dao.getFahrzeugList());
        for(Fahrzeug f : fList){
            if(f instanceof LKW) {
                meanPrice = +f.getPreis();
            }
        }
        System.out.println("Der Durchschnittspreis aller LKW betraegt " + meanPrice);
    }

    public void meanAge(){
            double meanAge = 0;
            ArrayList<Fahrzeug> fList = new ArrayList<>(dao.getFahrzeugList());
            for(Fahrzeug f : fList){
                if(f instanceof PKW) {
                    meanAge = + f.getAlter();
                }
            }
            System.out.println("Das Durchschnittsalter aller Fahrzeuge betraegt " + meanAge);
        }

    public void findOldest(){
        Fahrzeug oldest = null;
        for(Fahrzeug f : dao.getFahrzeugList()){
            if(oldest == null || f.getAlter() > oldest.getAlter()){
                oldest = f;
            }
        }
        if (oldest != null) {
            System.out.println(oldest.getId());
        } else {
            throw new NullPointerException("Kein Fahrzeug gefunden");
        }
    }
}

