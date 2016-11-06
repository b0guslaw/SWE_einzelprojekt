package at.ac.univie.swe2016.fm.fahrzeuge;


import java.util.Calendar;

public abstract class Fahrzeug {
    private String marke, modell;
    private double baujahr, grundpreis;
    private int id;

    double currentYear = Calendar.getInstance().get(Calendar.YEAR);


    /**
     * Konstruktor fuer alle Objekte des Typs Fahrzeug. Uebernimmt 5 Parameter und uebergibt diese
     * den Settern und Gettern der Klasse
     *
     * @param id int als eindeutige Identifikationsummer des Fahrzeugobjekts
     * @param marke String  Name des Herstellers
     * @param modell String Modellspezfische Bezeichnung
     * @param baujahr Double Wann das Fahrzeug gebaut wurde
     * @param grundpreis Double Grundpreis des Fahrzeugobjekts ohne Rabatt
     */
    public Fahrzeug(int id, String marke, String modell, double baujahr, double grundpreis){
        try {
            setId(id);
            setMarke(marke);
            setModell(modell);
            setBaujahr(baujahr);
            setGrundpreis(grundpreis);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Einer oder mehrerer der Parameter wurden falsch uebergeben");
        }
    }

    /**
     * Retourniert die Markes des Fahrzeugobjekts als String
     *
     * @return String
     */
    public String getMarke() {
        return marke;
    }

    /**
     * Setzt die Marke des Fahrzeugobjekts
     *
     * @param marke String
     */
    public void setMarke(String marke) {
        this.marke = marke;
    }

    /**
     * Retourniert das Modell des Fahrzeugobjekts als String
     *
     * @return String
     */
    public String getModell() {
        return modell;
    }

    /**
     * Setzt das Modell des Fahrzeugobjekts
     *
     * @param modell String
     */
    public void setModell(String modell) {
        this.modell = modell;
    }

    /**
     * Retoruniert das Baujahr des Fahrzeugobjekts
     *
     * @return Double
     */
    public double getBaujahr() {
        return baujahr;
    }

    /**
     * Setzt das Baujahr des Fahrzeugobjekts. Restriktion: Baujahr darf nicht größer sein als derzeitiges Jahr des Systems
     *
     * @param baujahr Double
     */
    public void setBaujahr(double baujahr) {
        if(currentYear < baujahr){
            throw new IllegalArgumentException("Baujahr dark nicht in der Zukunft liegen");
        }
        this.baujahr = baujahr;
    }

    /**
     * Retourniert den Grundpreis ohne Rabatt
     *
     * @return Double
      */
    public double getGrundpreis() {
        return grundpreis;
    }

    /**
     * Setzt den Grundpreis des Fahrzeugobjekts
     *
     * @param grundpreis Double
     */
    public void setGrundpreis(double grundpreis) {
        this.grundpreis = grundpreis;
    }

    /**
     * Retourniert den Preis mit Rücksichtnahme auf den Rabatt
     *
     * @return Double
     */
    public double getPreis(){
        return getGrundpreis() - getGrundpreis()/100*getRabatt();
    }

    /**
     * Retourniert die ID des Fahrzeugs
     *
     * @return Int
     */
    public int getId() {
        return id;
    }

    /**
     * Setzt ID des Fahrzeugs. Restriktion: ID muss einzigartig sein. Ist die ID bereits vorhanden wird eine
     * IllegalArgumentException geworfen
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    public double getAlter(){
        return currentYear - getBaujahr();
    }

    public abstract int getRabatt();

    public String toString() {
        return getMarke() + " " + getModell() + " " + getBaujahr() + " " + getGrundpreis() + " " + getId();
    }
}
