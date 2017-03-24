package juego.sky;

import info.Info;

import java.util.ArrayList;

/**
 * Created by andre on 3/14/2017.
 */
public class Sky {

    private final ArrayList<Meteoro> meteoros = new ArrayList<>();
    ArrayList<Estrella> estrellas = new ArrayList<>();

    public Sky() {
        Info.getInstance().loadMeteoros();

        int i = 0;
        while (i < 50) {
            estrellas.add(new Estrella());
            i++;
        }

        i = 0;
        while (i < 4) {
            meteoros.add(new Meteoro());
            i++;
        }

    }


    public void pintar() {


        for (Estrella e : estrellas) {
            e.pintar();
        }

        for (Meteoro m : meteoros) {
            m.pintar();
        }
    }


    public void update() {


    }


}
