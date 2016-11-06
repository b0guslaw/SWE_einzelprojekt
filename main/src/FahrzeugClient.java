import at.ac.univie.swe2016.fm.*;
import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;
import at.ac.univie.swe2016.fm.fahrzeuge.PKW;

public class FahrzeugClient {

    public static void main(String[] args){

        String pfad = args[0];
        String command = args[1];

        if(args.length < 2){
            System.out.println("Bitte beachten Sie die Anleitung zu dem Programm");
            showHelpMenu();
            System.exit(1);
        }

        FahrzeugManagement manager = new FahrzeugManagement(pfad);

        if(args[1].toLowerCase().equals("show")){
            if(!args[2].equals(null)){
                Fahrzeug f = new PKW(Integer.parseInt(args[2]),null,null,0,0,null);
                manager.showFahrzeug(f);
            }
            manager.showFahrzeug();
        } else if(args[1].toLowerCase().equals("add")){
            if(args[2].equals(null) || args[3].equals(null)){
                if(args[2].toLowerCase().equals("pkw") || args[2].toLowerCase().equals("lkw")){
                    manager.addFahrzeug(args);
                }
            }

        } else if(args[1].equals("del")){
            manager.loescheFahrzeug(args[2]);

        } else if(args[1].equals("count")){
            if(args[2].toLowerCase().equals("lkw")){
                manager.countLKW();
            } else if(args[2].toLowerCase().equals("PKW")){
                manager.countPKW();
            } else {
                manager.fahrzeugAnzahl();
            }
        } else if(args[1].equals("meanprice")){
            if(args[2].toLowerCase().equals("lkw")){
                manager.meanPriceLKW();
            } else if(args[2].toLowerCase().equals("PKW")){
                manager.meanPricePKW();
            } else {
                manager.meanPrice();
            }
        } else if(args[1].equals("meanage")){
            manager.meanAge();
        } else if(args[1].equals("oldest")){
            manager.findOldest();
        } else if(args[1].equals("help")){
            showHelpMenu();
        } else {
            System.out.println("Unbekannter Befehl");
            showHelpMenu();
        }

    }

    public static void showHelpMenu(){
        System.out.println("------------------------------------------------------------------------------------------\n");
        System.out.println("Um das Programm korrekt zu bedeienen, koennen Sie folgende Befehle verwenden.\n");
        System.out.println("Befehle sind wie folgt markiert und verpflichtend: <befehl>.\n");
        System.out.println("Argumente koennen dem Programm übergeben werden, sind in den meisten Faellen aber optional.\n");
        System.out.println("Ein Argument sieht wie folgt aus \"Argument\".\n");
        System.out.println("Allen Befehlen muss eine Datenquelle vorangestellt werden.\n");
        System.out.println("Befehlsliste mit moeglichen Argumenten:\n");
        System.out.println("<show> - zeigt alle gespeicherten Fahrzeuge an.\n");
        System.out.println("<show> \"id\" - zeigt das at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug mit der uebergebenen ID. \n");
        System.out.println("<add> pkw <id> <marke> <modell> <baujahr> <grundpreis> <ueberpruefungsdatum> - Fuegt ein at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug hinzu, parameter sind verpflichtend");
        System.out.println("<del> \"id\" - loescht das at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug mit der uebergebenen ID aus der Liste.\n");
        System.out.println("<count> - gibt die Anzahl der gespeicherten Fahrzeuge zurueck.\n");
        System.out.println("<meanprice> - gibt den Durchschnittspreis der gespeicherten Fahrzeuge zurueck.\n");
        System.out.println("<meanprice> \"lkw/pkw\" - gibt den Durchnschnittspreis der gespeicherten LKW/PKW zurueck.\n");
        System.out.println("<meanage> - gibt das Durschnittsalter der gespeicherten Fahrzeuge zurueck.\n");
        System.out.println("<meanage> \"lkw/pkw\" - gibt das Durchschnittsalter der gespeicherten LKW/PKW zurueck.\n");
        System.out.println("<oldest> - zeigt das aelteste gespeicherte Fahrzeug.\n");
        System.out.println("<help> - zeigt dieses menu.\n");
        System.out.println("------------------------------------------------------------------------------------------\n");
    }
}