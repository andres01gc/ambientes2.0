package juego.particles.enemigo;

import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;

/**
 * Created by andre on 3/10/2017.
 */
public class ParticulaEnemigo {

    PVector location;
    PVector velocity;
    PVector acceleration;
    boolean muerto;
    float lifespan;
    PApplet app = Logica.getApp();


    public float tam = 0;
    private float angle;

    public ParticulaEnemigo(PVector pos, int lifespan) {

        acceleration = new PVector(app.random(-2, 2), app.random(-2, 2));
        velocity = acceleration.copy();
        location = new PVector(pos.x + app.random(-20, 20), pos.y, app.random(-20, 20));
        this.lifespan = lifespan;
        tam = app.random(5, 10);
        angle = app.random(3);
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
        app.rotate(angle);

        app.fill(255, 0, 0, lifespan + 150);
        app.rect(0, 0, tam, tam);

        app.popMatrix();

        if (lifespan < 0.0) muerto = true;

        angle += 0.1f;
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
