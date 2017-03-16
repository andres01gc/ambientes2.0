package juego.rastro;

import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by andre on 3/10/2017.
 */
public class Rastro {


    private PVector pos;
    // PVector pos;
    private ArrayList<RastroParticula> particles;
    PApplet app = Logica.getApp();


    public Rastro(PVector pos) {
        this.pos = pos;
        particles = new ArrayList<RastroParticula>();
        int i = 0;

    }


    public void pintar() {
        PVector bufferPos;
        Iterator<RastroParticula> it = particles.iterator();

        for (int i = particles.size() - 1; i > 0; i--) {
            RastroParticula p = particles.get(i);
            p.update();
            p.display();
            if (p.isMuerto()) {
                particles.remove(p);
            }
        }

        // if (app.frameCount % 1 == 0)
        particles.add(new RastroParticula());
        particles.add(new RastroParticula());

        //    }

    }

    public void mover() {
        //    pos.y += 0.1;
    }


}
