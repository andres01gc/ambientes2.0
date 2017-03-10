package juego.Particle;

import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;

/**
 * Created by andre on 3/10/2017.
 */
public class Particula {

    PVector location;
    PVector velocity;
    PVector acceleration;
    float lifespan;
    PApplet app = Logica.getApp();


    public float tam = 0;

    public Particula() {
        reiniciar();
    }

    public Particula(int lifespan) {
        reiniciar();


    }

    void run() {
        update();
        //  display();
    }

    // Method to update location
    void update() {

        velocity.x += acceleration.x;
        // velocity.y+= acceleration.y;

        // velocity.add(acceleration);

        location.add(velocity);

        lifespan -= 0.5f;
    }

    // Method to display
    void display(PVector fixPos) {
        app.noStroke();

        app.pushMatrix();
        app.translate(location.x + fixPos.x, location.y + fixPos.y);
        app.rotate(app.PI / 4);
        // app.stroke(0, lifespan);
        /// app.strokeWeight(2);
        app.rect(0, 0, lifespan, lifespan);
        app.popMatrix();
        if (lifespan < 0.0)
        reiniciar();


    }


    public void reiniciar() {
            acceleration = new PVector(0, 0.08f);
            velocity = new PVector(app.random(-2, 2),0);
            location = new PVector(app.random(-1, 1), (app.random(-100, 100)));
            lifespan = (int) app.random(100);
            tam = app.random(20, 60);


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
