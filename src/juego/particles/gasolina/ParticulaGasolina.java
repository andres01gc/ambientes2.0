package juego.particles.gasolina;

import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;

/**
 * Created by andre on 3/10/2017.
 */
public class ParticulaGasolina {

    PVector location;
    PVector velocity;
    PVector acceleration;
    boolean muerto;
    float lifespan;
    PApplet app = Logica.getApp();


    public float tam = 0;
    private float angle;
    private float t;

    public ParticulaGasolina(PVector pos, int lifespan) {
        acceleration = new PVector(app.random(-.5f, .5f), app.random(-.5f, .5f));
        velocity = acceleration.copy();
        velocity.mult(2);
        location = new PVector(pos.x + app.random(-20, 20), pos.y, app.random(-20, 20));
        this.lifespan = lifespan;
        tam = app.random(2, 5);
        angle = app.random(100);
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
        app.pushMatrix();
        app.translate(location.x, location.y);
        app.rotate(angle);
        app.noFill();
        app.stroke(62, 206, 27, lifespan + 50);
        app.ellipse(0, 0, app.sin(t) * (tam * 3), (tam * 3));

        app.popMatrix();

        if (lifespan < 0.0) muerto = true;
        angle += 0.01f;
        t += 1;
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
