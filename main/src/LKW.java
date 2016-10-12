/**
 * Created by Ralph Dworzanski on 11.10.16.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 */

import java.util.Calendar;

public abstract class LKW extends Fahrzeug {

    int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    double getRabatt(){
        double rate = 0;

        for (int j = baujahr; j < currentYear; j++) {
            rate = rate + 5;
        }

        return rate;
    }
}
