package juego.particles.gasolina;

import juego.Gasolina;
import juego.Moneda;
import juego.particles.moneda.ParticulaMoneda;
import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;

import java.util.ArrayList;

/**
 * Created by andre on 3/10/2017.
 */
public class AnimaGasolina {

    PVector pos;
    private ArrayList<ParticulaGasolina> particles;
    PApplet app = Logica.getApp();
    private boolean muerto;

    private String[] resps;
    private int r;
    private float angle;
    private int sizeText;
    float t = 255;

    public AnimaGasolina(int puntosM, Gasolina ga) {
        this.pos = ga.getRealPos().copy();
        particles = new ArrayList<ParticulaGasolina>();

        resps = new String[]{
                "yeah!", "Si!", "excelente!", "woo oh!", "#yolo", "#putoAmo", "Nice!",
                "Easy", "LordMaste", "easyWeyzi", "jojo", "#Hashtag", "Swag", "necesito un 5!", ":v", ":3", "mwhahaha", "jijijiji"
        };

        angle = app.random(-.5f, .5f);
        sizeText = (int) app.random(40, 60);

        r = (int) app.random(resps.length - 1);

        int i = 0;

        while (i < 30) {
            particles.add(new ParticulaGasolina(pos.copy(), (int) app.random(10, 50)));
            i++;
        }


        pos = new PVector(369, 136);
    }


    public void pintar() {

        PVector bufferPos = pos.copy();
        //   app.ellipse(pos.x + fixx, pos.y, 10, 10);
        for (int i = particles.size() - 1; i >= 0; i--) {
            ParticulaGasolina p = particles.get(i);
            p.run();
            p.display();
            if (p.isDead()) {
                particles.remove(p);
            }
        }

        app.fill(62, 206, 27, t);
        app.pushMatrix();
        app.translate(pos.x, pos.y);
        app.rotate(angle);
        app.textSize(sizeText);
        app.strokeWeight(3);

        app.text("+5", 0, 0);
        pos.y += 2;
        app.popMatrix();

        t -= 0.5;
        if (particles.size() ==0  || t<0) {
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
