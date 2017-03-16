package juego.sky;

import java.util.ArrayList;

/**
 * Created by andre on 3/14/2017.
 */
public class Sky {

    ArrayList<Estrella> estrellas = new ArrayList<>();

    public Sky() {
        int i = 0;
        while (i < 50) {
            estrellas.add(new Estrella());
            i++;
        }

    }


    public void pintar() {


        for (Estrella e : estrellas) {
            e.pintar();
        }


    }


    public void update() {


    }


}
