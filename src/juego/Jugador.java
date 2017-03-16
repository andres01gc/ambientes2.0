package juego;

import juego.rastro.Rastro;
import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;
import root.Main;

/**
 * Created by andre on 3/5/2017.
 */

public class Jugador {


    // All the usual stuff
    public static PVector location;
    PVector velocity;
    PVector acceleration;
    PApplet app = Logica.getApp();
    PVector posFix = new PVector();
    // float r;
    float maxforce;    // Maximum steering force
    float maxspeed;    // Maximum speed

    Rastro r;

    Jugador() {
        location = new PVector(app.width / 2, 800);
        velocity = new PVector();
        acceleration = new PVector();
        r = new Rastro(location);
    }


    public PVector getLocation() {
        return location;
    }


    public void pintar(int rango, boolean sentadilla) {

        location.x = (1920/2) ;
        app.noStroke();
        //app.fill(app.random(255), app.random(255), app.random(255));

        if (sentadilla) {
            app.fill(255, 0, 0, 150);
        } else {
            app.fill(0,255,0, 150);
        }

        if (Main.valorFloats != null) {
            float bufferX = app.map(Main.valorFloats[1], -30, 30, -rango, rango);
            location.x+=bufferX;
            app.ellipse(location.x, location.y, 50, 50);
        } else {
            app.ellipse(location.x + app.random(-10, 10), location.y + app.random(-10, 10), 50 + app.random(-10, 10), 50 + app.random(-10, 10));
            app.ellipse(location.x + app.random(-10, 10), location.y + app.random(-10, 10), 50 + app.random(-10, 10), 50 + app.random(-10, 10));
            app.ellipse(location.x + app.random(-10, 10), location.y + app.random(-10, 10), 50 + app.random(-10, 10), 50 + app.random(-10, 10));
        }
        r.pintar();
    }


    public void comprobar(Path p) {
        boolean sale = false;
        for (PVector pv : p.getPosicionesSalida()) {
            app.ellipse(pv.x, pv.y, 50, 0);
            if (app.dist(location.x, location.y, pv.x, pv.y) < 100) {
                sale = true;
                System.out.println("se sale");
                //  break;
            }
        }
    }

    public void applyForce(PVector force) {
        // We could add mass here if we want A = F / M
        acceleration.add(force);
    }


}
