package juego.sky;

import info.Data;
import info.Info;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import root.Logica;

/**
 * Created by andre on 3/14/2017.
 */
public class Meteoro {
    PApplet app = Logica.getApp();
    PVector pos, vel, acel;
    private float tam;
    private PImage[] imagenes;
    private int tipo;

    public Meteoro() {
        this.tam = 200;
        imagenes = Info.getInstance().meteoros;
        pos = new PVector(app.random(app.width), app.random(app.height));
        vel = new PVector();
        acel = PVector.random2D();
        tipo = (int) app.random(0, imagenes.length - 1);
    }


    public void pintar() {
        app.pushMatrix();
        app.noStroke();
        app.tint(255, 50);
        app.fill(255, 100);
        app.imageMode(app.CENTER);
        app.translate(pos.x, pos.y);
        app.rotate(vel.heading() + app.PI / 2);
        app.image(imagenes[tipo], 0, 0);
        app.popMatrix();
        app.noTint();
        mover();
        limites();
    }


    public void limites() {

        if (pos.x > app.width + tam) {
            pos.x = -tam;
            acel = PVector.random2D();

        } else if (pos.x < -tam) {
            pos.x = app.width + tam;
            acel = PVector.random2D();
            vel.limit(1);
        }


        if (pos.y > app.height + tam) {
            pos.y = -tam;
            acel = PVector.random2D();
            vel.limit(1);

        } else if (pos.z < -tam) {
            pos.y = app.height + tam;
            acel = PVector.random2D();
            vel.limit(1);

        }

    }

    //private limites()ñ

    private void mover() {
        // acel = PVector.random2D();
        acel.limit(0.1f / tam);
        vel.add(acel);
        pos.add(vel);
        //acel.mult(0);

    }


}
