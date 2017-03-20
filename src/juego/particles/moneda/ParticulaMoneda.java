package juego.particles.moneda;

import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;

/**
 * Created by andre on 3/10/2017.
 */
public class ParticulaMoneda {

    PVector location;
    PVector velocity;
    PVector acceleration;
    boolean muerto;
    float lifespan;
    PApplet app = Logica.getApp();


    public float tam = 0;

    public ParticulaMoneda(PVector pos, int lifespan) {
        acceleration = new PVector(app.random(-.2f, .2f), app.random(-.2f, .2f));
        velocity = acceleration.copy();
        location = new PVector(pos.x + app.random(-20, 20), pos.y, app.random(-20, 20));
        this.lifespan = lifespan;
        tam = app.random(2, 10);
    }

    void run() {
        update();
        //  display();
    }

    // Method to update location
    void update() {
        velocity.add(acceleration);
        location.add(velocity);
        lifespan -= 1f;
    }

    // Method to display
    void display() {
        //app.fill(0);
        app.noStroke();
        app.pushMatrix();
        app.translate(location.x, location.y);
        app.rotate(app.PI / 4);

        app.fill(247, 199, 42, lifespan+150);
        app.ellipse(0, 0, tam, tam);

        app.popMatrix();

        if (lifespan < 0.0) muerto = true;

    }

    public boolean isMuerto() {
        return muerto;
    }


    // Is the particle still useful?
    boolean isDead() {
        if (lifespan < 0.0) {
            return true;
        } else {
            return false;
        }
    }


}
