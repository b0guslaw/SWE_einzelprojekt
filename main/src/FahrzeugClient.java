import at.ac.univie.swe2016.fm.*;
import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;

/**
 * @author Ralph Dworzanski
 *
 *Die Klasse FahrzeugClient ist das Interface und handhabt die uebergebenen Parameter und Befehle
 */
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

        if(command.equalsIgnoreCase("show")){
            if(args.length > 2){
                int id = Integer.parseInt(args[2]);
                for (Fahrzeug fahrzeug : manager.getDao().getFahrzeugList()) {
                    if(fahrzeug.getId() == id) {
                        manager.showFahrzeug(fahrzeug);
                        return;
                    }
                }
                System.out.println("Fahrzeug nicht gefunden");
                return;
            }
            manager.showFahrzeug();
        } else if(command.equalsIgnoreCase("add")){
                if(args[2].toLowerCase().equals("pkw") || args[2].toLowerCase().equals("lkw")){
                    manager.addFahrzeug(args);
                }
        } else if(command.equalsIgnoreCase("del")){
            if(args.length > 3){
                throw new IllegalArgumentException("Beim loeschen eines Fahrzeuges muss eine ID angegeben werden");
            }
            manager.loescheFahrzeug(args[2]);

        } else if(command.equalsIgnoreCase("count")){
            if(args.length <= 3){
                {
                    if(args.length == 2){
                        manager.count();
                        return;
                    }
                    if(args[2].equalsIgnoreCase("pkw")){
                        manager.countPKW();
                    } else if(args[2].equalsIgnoreCase("lkw")){
                        manager.countPKW();
                    }
                }
            }
        } else if(command.equalsIgnoreCase("meanprice")){
           if(args.length <= 3){
               if(args.length <= 2){
                   manager.meanPrice();
               } else if(args[2].equalsIgnoreCase("lkw")){
                   manager.meanPriceLKW();
               } else if(args[2].equalsIgnoreCase("pkw")){
                   manager.meanPricePKW();
               } else {
                   showHelpMenu();
                   throw new IllegalArgumentException("Bitte beachten sie die Nutzung der Befehle");
               }
           }
        } else if(command.equalsIgnoreCase("meanage")){
            manager.meanAge();
        } else if(command.equalsIgnoreCase("oldest")){
            manager.findOldest();
        } else if(command.equalsIgnoreCase("help")){
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
        System.out.println("<show> \"id\" - zeigt das Fahrzeug mit der uebergebenen ID. \n");
        System.out.println("<show> \"id\" - zeigt alle Fahrzeuge an. \n");
        System.out.println("<add> pkw <id> <marke> <modell> <baujahr> <grundpreis> <ueberpruefungsdatum> - Fuegt ein Fahrzeug hinzu, parameter sind verpflichtend");
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