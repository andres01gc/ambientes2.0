package juego.rastro;

import juego.Jugador;
import juego.Path;
import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;

/**
 * Created by andre on 3/10/2017.
 */
public class RastroParticula {

    private float opacity;
    PVector location;
    PVector velocity;
    PVector acceleration;
    float lifespan;
    PApplet app = Logica.getApp();

    public float tam = 0;
    private boolean muerto;

    int r, g, b;
//    private PVector vel;

    public RastroParticula() {
        acceleration = new PVector(0, 0.08f);
        location = new PVector(app.random(-20, 20) + Jugador.location.x, Jugador.location.y);
        lifespan = (int) app.random(10, 30);
        //     tam = app.random(20, 500);
        opacity = app.random(50, 400);

        r = 255;
        g = (int) app.random(64, 192);
        b = (int) app.random(34, 64);

        //color = app.color(255, 192, 34);
    }

    public RastroParticula(int lifespan, PVector loc) {
        acceleration = new PVector(0, 0.08f);
        location = new PVector(app.random(-20, 20) + Jugador.location.x, Jugador.location.y+app.random(-20, 20));
        lifespan = (int) app.random(20, 50);
        //   tam = app.random(20, 500);
        this.location = loc;
    }

    public boolean isMuerto() {
        return muerto;
    }

    // Method to update location
    void update() {
        velocity = Path.vel;
        // velocity.y+= acceleration.y;
        // velocity.add(acceleration);
        location.add(velocity);
        lifespan -= 0.5f;
        opacity--;
    }

    // Method to display
    void display() {

        app.noStroke();
        app.pushMatrix();
        app.translate(location.x, location.y);
        //app.rotate(app.PI / 4);

        // app.stroke(0, lifespan);
        /// app.strokeWeight(2);
        app.fill(r, g, b, opacity);
        app.ellipse(0, 0, lifespan, lifespan);
        app.popMatrix();

        if (lifespan < 0.0)
            muerto = true;

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
