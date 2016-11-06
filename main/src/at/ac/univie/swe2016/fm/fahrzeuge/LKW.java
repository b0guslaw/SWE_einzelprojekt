package at.ac.univie.swe2016.fm.fahrzeuge;

public class LKW extends Fahrzeug{

    public LKW(int id, String marke, String modell, double baujahr, double grundpreis){
        super(id,marke,modell,baujahr,grundpreis);
    }

    @Override
    public int getRabatt() {
        int rabatt = 0;

        rabatt = (int)(currentYear - getBaujahr())*5;

        if(rabatt > 20){
            rabatt = 20;
        }

        return rabatt;
    }
}
