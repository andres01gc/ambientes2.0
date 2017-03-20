package juego.particles.enemigo;

import juego.Moneda;
import juego.Obstaculo;
import juego.particles.gasolina.ParticulaGasolina;
import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;

import java.util.ArrayList;

/**
 * Created by andre on 3/10/2017.
 */
public class AnimaEnemigo {

    PVector pos;
    private ArrayList<ParticulaEnemigo> particles;
    PApplet app = Logica.getApp();
    private boolean muerto;

    private String[] resps;
    private int r;
    private float angle;
    private int sizeText;
    private float t;

    public AnimaEnemigo(int puntosM, Obstaculo obst) {
        this.pos = obst.getRealPos().copy();
        particles = new ArrayList<ParticulaEnemigo>();

        resps = new String[]{
                "yeah!", "Si!", "excelente!", "woo oh!", "#yolo", "#putoAmo", "Nice!",
                "Easy", "LordMaste", "easyWeyzi", "jojo", "#Hashtag", "Swag", "necesito un 5!", ":v", ":3", "mwhahaha", "jijijiji"
        };

        angle = app.random(-.5f, .5f);
        sizeText = (int) app.random(50, 70);

        r = (int) app.random(resps.length - 1);

        int i = 0;

        while (i < 30) {
            particles.add(new ParticulaEnemigo(pos.copy(), (int) app.random(10, 50)));
            i++;
        }
        pos = new PVector(369, 136);
        t = 350;
    }


    public void pintar() {

        PVector bufferPos = pos.copy();
        //   app.ellipse(pos.x + fixx, pos.y, 10, 10);
        for (int i = particles.size() - 1; i >= 0; i--) {
            ParticulaEnemigo p = particles.get(i);
            p.run();
            p.display();

            if (p.isDead()) {
                particles.remove(p);
            }

        }

        app.fill(255, 0, 0, t);

        app.pushMatrix();
        app.translate(pos.x, pos.y);
        app.rotate(angle);
        app.textSize(sizeText);
        app.strokeWeight(5);
        app.text("-5", 0, 0);
        pos.y += 5;
        app.popMatrix();

        t -= 1;

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
