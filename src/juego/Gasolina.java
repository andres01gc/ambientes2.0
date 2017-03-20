package juego;

import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;

/**
 * Created by andre on 3/5/2017.
 */
public class Gasolina {

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
    private float angle;

    Gasolina(float x, float y, float rango) {
        location = new PVector(x, y);
        velocity = new PVector();
        acceleration = new PVector();

        this.rango = rango;
        t = app.random(1000);
        velX = app.random(5);
        location.x += app.random(-rango / 2, rango / 2);
    }

    public PVector getRealPos() {
        return realPos;
    }

    public void pintar(float fixX) {

        realPos = new PVector(location.x + movx + fixX, location.y);

        app.stroke(62, 206, 27, 100);
        app.strokeWeight(3);
        app.noFill();
        app.pushMatrix();
        int i = 0;
        app.translate(location.x + movx + fixX, location.y);
        //    while (i< 2) {
        app.rotate(angle);
        app.ellipse(0, 0, app.sin(t) * 40, 40);

        app.ellipse(-2 + (app.noise(t + 145) * 4), -2 + (app.noise(t + 91896) * 4), 5, 5);


        app.rotate(.5f);
        //app.ellipse(app.sin(t + 200) * 20, app.sin(t + 200) * 20, 10, 10);
        app.ellipse(0, 0, 40, app.sin(t + 200) * 40);
        app.rotate(.5f);
        //app.ellipse(app.sin(t + 4900) * 30, app.sin(t + 4900) * 30, 10, 10);

        app.ellipse(0, 0, 60, app.sin(t + 4900) * 60);
        app.rotate(.5f);
        //  app.ellipse(app.sin(t + 200) * 10, app.sin(t + 200) * 10, 10, 10);

        app.ellipse(0, 0, 20, app.sin(t + 514) * 20);

        angle += 0.03f;
        // }

        app.popMatrix();
        app.noFill();
        t += 0.1f;

    }


    public void mover(float vel) {
        location.y += vel;


    }


    public void applyForce(PVector force) {
        // We could add mass here if we want A = F / M
        acceleration.add(force);
    }


}
