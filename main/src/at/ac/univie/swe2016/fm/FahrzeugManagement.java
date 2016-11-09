package at.ac.univie.swe2016.fm;

import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;
import at.ac.univie.swe2016.fm.fahrzeuge.LKW;
import at.ac.univie.swe2016.fm.fahrzeuge.PKW;
import swe2016.fahrzeuge.fm.FahrzeugDAO;
import swe2016.fahrzeuge.fm.SerializedFahrzeugDAO;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.Iterator;

/**
 * @author Ralph Dworzanski
 *
 *Die Klasse FahrzeugManagement handhabt alle wichtigen Funktionen der ArrayList. Bei Initialisierung erstellt sie eine
 * Instanz des Interfaces FahrzeugDAO und uebergibt diesem den String den sie bekommen hat.
 */
public class FahrzeugManagement {
    private String path;
    private FahrzeugDAO dao;
    private DecimalFormat decFormatter = new DecimalFormat("#.##");

    public FahrzeugManagement(String path){
        this.path = path;
        dao = new SerializedFahrzeugDAO(this.path);
    }

    /**
     * Retoruniert die Instanz des FahrzeugDAO
     *
     * @return FahrzeugDAO
     */
    public FahrzeugDAO getDao() {
        return dao;
    }

    /**
     * Setzt ein neues FahrzeugDAO
     *
     * @param dao
     */
    public void setDao(FahrzeugDAO dao) {
        this.dao = dao;
    }

    /**
     * Gibt alle Daten des uebergeben Fahrzeugobjekts aus
     *
     * @param f FahrzeugObjekt
     */
    public void showFahrzeug(Fahrzeug f){
        System.out.println("---------");
        System.out.println("ID: \t" + f.getId());
        System.out.println("Marke: \t" +f.getMarke());
        System.out.println("Modell: \t" +f.getModell());
        System.out.println("Bauahr: \t" + decFormatter.format(f.getBaujahr()));
        System.out.println("Grundpreis: \t" + decFormatter.format(f.getGrundpreis()));
        System.out.println("Preis: \t" +decFormatter.format(f.getPreis()));
        if(f instanceof PKW){
            System.out.println(((PKW) f).getLetztePruefung());
        }
        System.out.println("\n");
    }

    /**
     * Gibt alle Fahrzeuge in der Liste aus
     *
     */
    public void showFahrzeug(){
        for(Fahrzeug f : dao.getFahrzeugList()){
            System.out.println("---------");
            System.out.println("ID: \t" + f.getId());
            System.out.println("Marke: \t" +f.getMarke());
            System.out.println("Modell: \t" +f.getModell());
            System.out.println("Bauahr: \t" + decFormatter.format(f.getBaujahr()));
            System.out.println("Grundpreis: \t" + decFormatter.format(f.getGrundpreis()));
            System.out.println("Preis: \t" +decFormatter.format(f.getPreis()));
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
            } else if(args[2].toLowerCase().equals("pkw")){
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
        int id = Integer.parseInt(arg);
        for (Fahrzeug fahrzeug : dao.getFahrzeugList()) {
            if(fahrzeug.getId() == id) {
                dao.loescheFahrzeug(fahrzeug);
                return;
            }
        }

        throw new IllegalArgumentException("Fahrzeug nicht in der Liste gefunden.");
    }

    /**
     * Zaehlt alle FahrzeugObjekte und gibt diese in der Konsole aus
     */
    public void count(){
        ArrayList<Fahrzeug> fList = new ArrayList<>(dao.getFahrzeugList());
        System.out.println("Die Liste beinhaltet derzeit " + fList.size()+ " Fahrzeuge");
    }

    /**
     * Zaehlt alle Objekte des Typs PKW in der Liste
     *
     */
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

    /**
     * Zaehlt alle Objekte des Typs LKW in der Liste
     *
     */
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


    /**
     * Berechnet den Durchschnittspreis aller Fahrzeuge
     *
     */
    public void meanPrice(){
        double meanPrice = 0;
        ArrayList<Fahrzeug> fList = new ArrayList<>(dao.getFahrzeugList());
        for(Fahrzeug f : fList){
            meanPrice =+ f.getPreis();
        }
        System.out.println("Der Durchschnittspreis aller Fahrzeuge betraegt " + decFormatter.format(meanPrice));
    }

    /**
     * Berechnet den Durchschnittspreis aller Fahrzeuge des Typs PKW
     *
     */
    public void meanPricePKW(){
        double meanPrice = 0;
        for(Fahrzeug f : dao.getFahrzeugList()){
            if(f instanceof PKW) {
                meanPrice = +f.getPreis();
            }
        }
        System.out.println("Der Durchschnittspreis aller PKW betraegt " + decFormatter.format(meanPrice));
    }

    /**
     * Berechnet den Durchschnittspreis aller Fahrzeuge des Typs LKW
     *
     */
    public void meanPriceLKW(){
        double meanPrice = 0;
        for(Fahrzeug f : dao.getFahrzeugList()){
            if(f instanceof LKW) {
                meanPrice = +f.getPreis();
            }
        }
        System.out.println("Der Durchschnittspreis aller LKW betraegt " + decFormatter.format(meanPrice));
    }

    /**
     * Berechnet das Durchschnittsalter aller Fahrzeuge
     *
     */
    public void meanAge(){
            double meanAge = 0;
            for(Fahrzeug f : dao.getFahrzeugList()){
                    meanAge = + f.getAlter();
            }
            System.out.println("Das Durchschnittsalter aller Fahrzeuge betraegt " + decFormatter.format(meanAge/dao.getFahrzeugList().size()));
        }

    /**
     * Findet das aelteste Fahrzeug in der Liste und gibt es aus
     *
     */
    public void findOldest() {
        if(dao.getFahrzeugList().isEmpty()){
            throw new IllegalArgumentException("Es befinden sich keine Fahrzeuge in der Liste");
        }

        ArrayList<Fahrzeug> fl = dao.getFahrzeugList();

        for(Iterator<Fahrzeug> iterator1 = dao.getFahrzeugList().iterator(); iterator1.hasNext();){
            Fahrzeug f = iterator1.next();
            for(Iterator<Fahrzeug> iterator2 = fl.iterator(); iterator2.hasNext();){
                Fahrzeug g = iterator2.next();
                if(f.getAlter() > g.getAlter()){
                    fl.remove(g);
                }
            }
        }

        for(Fahrzeug c : fl){
            showFahrzeug(c);
        }
    }
}

