package juego;

import juego.particles.rastro.Rastro;
import processing.core.PApplet;
import processing.core.PImage;
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
    PImage image;

    private boolean fuera;
    private float valor;


    public boolean isFuera() {
        return fuera;
    }

    Jugador() {
        location = new PVector(app.width / 2, 900);
        velocity = new PVector();
        acceleration = new PVector();
        r = new Rastro(location);
        image = app.loadImage("../data/resources/juego/jugador.png");
    }


    public PVector getLocation() {
        return location;
    }


    public void pintar(int rango, boolean sentadilla) {
        r.pintar();
        velocity.y = 0.2f;
        app.noStroke();


        app.pushMatrix();
        app.translate(location.x, location.y);
        app.rotate(velocity.heading() - app.PI / 2);
        app.imageMode(app.CENTER);

        if (sentadilla) {
            app.fill(0, 20);
            app.ellipse(0, 0, 200, 200);
        } else {

        }

        //location.x = app.mouseX;

        if (Main.valorFloats != null) {
            float bufferX = app.map(Main.valorFloats[1], -30, 20, -rango, rango);
            location.x = (1920 / 2) + bufferX;
            app.image(image, 0, -30);
        } else {
            app.image(image, 0, -30);
        }
        app.popMatrix();
        velocity.x = acceleration.x;
        acceleration.mult(0);
    }


    public void update() {


    }

    public void comprobar(Path p) {
        fuera = false;
        for (PVector pv : p.getPosicionesSalida()) {
            // app.ellipse(pv.x, pv.y, 100, 100);
            if (app.dist(location.x, location.y, pv.x, pv.y) < 100) {
                fuera = true;
                //  System.out.println("se fuera");
                //  break;
            }
        }
    }

    public void applyForce(PVector force) {
        // We could add mass here if we want A = F / M
        acceleration.add(force);
    }


}
