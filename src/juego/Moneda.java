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


    Moneda(float x, float y, float rango) {
        location = new PVector(x, y);
        velocity = new PVector();
        acceleration = new PVector();

        this.rango = rango;
        t = app.random(1000);
        velX = app.random(5);
        location.x+= app.random(-rango/2,rango/2);
    }

    public void pintar(float fixX) {

        app.fill(247, 199, 42);
        app.noStroke();
        app.pushMatrix();
        app.translate(location.x + movx + fixX, location.y);
        //app.rotate(app.PI / 4);
        // app.stroke(0, lifespan);
        /// app.strokeWeight(2);
        app.ellipse(0, 0,  app.sin(t)*20, 20);
        app.popMatrix();


        t += 0.1;
    }


    public void mover(float vel) {
        location.y += vel;

//        if (movx > (rango/2f)- velX) {
//            mov = false;
//            movx = (rango/2f) - velX;
//        } else if (movx < -(rango/2f) + velX) {
//            movx = -(rango/2f) + velX;
//            mov = true;
//        }
//
//        if (mov) movx += velX;
//
//        if (!mov) movx -= velX;
//
//
//     //   movx = rango/2;


    }


    public void applyForce(PVector force) {
        // We could add mass here if we want A = F / M
        acceleration.add(force);
    }


}
