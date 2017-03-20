package juego.sky;

import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;
import root.Main;

/**
 * Created by andre on 3/14/2017.
 */
public class Estrella {
    PApplet app = Logica.getApp();
    PVector pos, vel, acel;
    private float tam;


    public Estrella() {
        this.tam = app.random(5, 10);

        pos = new PVector(app.random(app.width), app.random(app.height));
        vel = new PVector();
        acel = new PVector();

    }


    public void pintar() {
        app.noStroke();
        app.fill(255, 100);
        app.pushMatrix();
        app.translate(pos.x, pos.y);
        app.ellipse(0, 0, tam, tam);
        app.popMatrix();
        mover();
        limites();
    }


    public void limites() {
        if (pos.x > app.width + tam) {
            pos.x = -tam;
        } else if (pos.x < -tam) {
            pos.x = app.width + tam;
        }


        if (pos.y > app.height + tam) {
            pos.y = -tam;
        } else if (pos.z < -tam) {
            pos.y = app.height + tam;
        }
    }

    //private limites()ñ

    private void mover() {
        acel = PVector.random2D();
        acel.limit(0.1f / tam);
        vel.add(acel);
        pos.add(vel);
        acel.mult(0);
    }


}
