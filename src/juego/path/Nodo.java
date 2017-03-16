package juego.path;

import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by andre on 3/10/2017.
 */
public class Nodo {

    PVector pos;
    private ArrayList<Particula> particles;
    PApplet app = Logica.getApp();

    public Nodo(PVector pos) {

        this.pos = pos;
        particles = new ArrayList<Particula>();
        int i = 0;
        while (i < 15) {
            particles.add(new Particula((int)app.random(255)));
            i++;
        }


    }


    public void pintar(int fixx) {
        PVector bufferPos = pos.copy();
        bufferPos.x += fixx;
        //   app.ellipse(pos.x + fixx, pos.y, 10, 10);
        Iterator<Particula> it = particles.iterator();
        for (int i = particles.size() - 1; i > 0; i--) {
            Particula p = particles.get(i);
            p.run();
            p.display(bufferPos);

        }

    }

    public void mover() {
        pos.y += 0.1;
    }


    public PVector getPos() {
        return pos;
    }

}
