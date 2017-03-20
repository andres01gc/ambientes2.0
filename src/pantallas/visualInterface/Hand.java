package pantallas.visualInterface;

import processing.core.PApplet;
import processing.core.PVector;
import revisar.KinectLink;
import root.Logica;
import setup.Pantalla;

/**
 * Created by andre on 3/10/2017.
 */
public class Hand {

    public static PApplet app = Logica.getApp();
    private static float t;
    public static PVector pos = new PVector(500, 500);

    private Hand() {


    }

    public static void pintar() {
pos.x=app.mouseX;
pos.y=app.mouseY;
        app.fill(255, 50);
        app.noStroke();
        int radio = 50;

        synchronized (pos) {
            app.ellipse(pos.x, pos.y, radio, radio);
        }

    }

    public static void cargarMano(Pantalla p) {
        app.noFill();
        app.stroke(255, 150);
        app.strokeWeight(20);
        int radio = 50;

        app.beginShape();

        synchronized (pos) {
            for (float i = 0; i < t; i += 0.1f) {
                float nx = pos.x + app.cos(i) * radio;
                float ny = pos.y + app.sin(i) * radio;
                app.vertex(nx, ny);
            }
        }
        app.endShape();
        t += 0.1;
        if (t > app.TWO_PI) {
            t = 0;
            p.pressHandRight();
        }
    }
}
