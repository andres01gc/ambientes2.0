package juego.particles.moneda;

import juego.Moneda;
import juego.particles.path.Particula;
import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by andre on 3/10/2017.
 */
public class AnimacionMonedaCogida {

    PVector pos;
    private ArrayList<ParticulaMoneda> particles;
    PApplet app = Logica.getApp();
    private boolean muerto;

    private String[] resps;
    private int r;
    private float angle;
    private int sizeText;
    float t = 255;

    public AnimacionMonedaCogida(int puntosM, Moneda moneda) {
        this.pos = moneda.getRealPos().copy();
        particles = new ArrayList<ParticulaMoneda>();

        resps = new String[]{
                "yeah!", "Si!", "excelente!", "woo oh!", "#yolo", "#putoAmo", "Nice!",
                "Easy", "LordMaste", "easyWeyzi", "jojo", "#Hashtag", "Swag", "necesito un 5!", ":v", ":3", "mwhahaha", "jijijiji"
        };

        angle = app.random(-.5f, .5f);
        sizeText = (int) app.random(40, 60);

        r = (int) app.random(resps.length - 1);

        int i = 0;

        while (i < 40) {
            particles.add(new ParticulaMoneda(pos.copy(), (int) app.random(10, 50)));
            i++;
        }

        this.pos = new PVector(1801, 120);
    }


    public void pintar() {

        PVector bufferPos = pos.copy();
        //   app.ellipse(pos.x + fixx, pos.y, 10, 10);
        for (int i = particles.size() - 1; i >= 0; i--) {
            ParticulaMoneda p = particles.get(i);
            p.run();
            p.display();
            if (p.isDead()) {
                particles.remove(p);
            }
        }
        app.pushMatrix();
        app.translate(pos.x, pos.y);
        app.rotate(angle);
        app.textSize(sizeText);
        app.fill(247, 199, 42, t);
        app.strokeWeight(3);
        app.text("+10!", 0, 0);
        pos.y += 5;
        app.popMatrix();

        t--;

        if (particles.size() == 0 || t < 0) {
            muerto = true;
        }


    }

    public boolean isMuerto() {
        return muerto;
    }


    public void mover() {
        pos.y += 0.1;
    }


    public PVector getPos() {
        return pos;
    }

}
