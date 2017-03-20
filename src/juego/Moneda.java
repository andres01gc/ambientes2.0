package juego;

import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;

/**
 * Created by andre on 3/5/2017.
 */
public class Moneda {


    private float rango;
    // All the usual stuff
    PVector location;
    PVector realPos = new PVector();

    PVector velocity;
    PVector acceleration;
    PApplet app = Logica.getApp();


    public boolean mov;
    float r;
    float maxforce;    // Maximum steering force
    float maxspeed;    // Maximum speed
    private float movx = 0;
    private float velX = 2;
    private float t;
    private float tam;


    Moneda(float x, float y, float rango) {
        location = new PVector(x, y);
        velocity = new PVector();
        acceleration = new PVector();

        this.rango = rango;
        t = app.random(1000);
        velX = app.random(5);
        location.x += app.random(-rango / 2, rango / 2);
        tam = 40;
    }


    public float getTam() {
        return tam;
    }


    public PVector getRealPos() {
        return realPos;
    }

    public void pintar(float fixX) {

        realPos = new PVector(location.x + movx + fixX, location.y);


        app.strokeWeight(5);
        app.pushMatrix();
        app.translate(location.x + movx + fixX, location.y);

        app.fill(0, 20);
        app.stroke(0, 20);
        app.ellipse(0, tam - 30, app.sin(t) * tam, tam / 6);

        app.fill(247, 199, 42);
        app.stroke(247, 199, 42);
        app.ellipse(0, 0, app.sin(t) * tam, tam);

        app.popMatrix();
        app.noFill();
        t += 0.1;
    }

    public void mover(float vel) {
        location.y += vel;
    }


    public void applyForce(PVector force) {
        // We could add mass here if we want A = F / M
        acceleration.add(force);
    }


}
