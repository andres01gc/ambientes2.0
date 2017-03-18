package juego;

import info.Info;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import root.Logica;

import java.util.Random;

/**
 * Created by andre on 3/5/2017.
 */
public class Obstaculo {


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
    private boolean m;

    PImage imaEnemigo;

    private PVector realPos = new PVector();

    public PVector getRealPos() {

        return realPos;
    }

    Obstaculo(float x, float y, float rango) {
        location = new PVector(x, y);
        velocity = new PVector();
        acceleration = new PVector();
        this.rango = rango;
        velX = app.random(5);
        if (app.random(1) < 0.3) {
            m = true;
        }
        m = true;

        int tipoEnemigo = (int) app.random(0, 3);
        imaEnemigo = Info.getInstance().obstaculos[tipoEnemigo];
    }

    public void pintar(float fixX) {

        realPos.x = location.x + movx + fixX;
        realPos.y = location.y;

        app.pushMatrix();
        app.imageMode(app.CENTER);
        app.noStroke();
        app.translate(location.x + movx + fixX, location.y);
        //  app.rotate(app.PI / 4);
        // app.stroke(0, lifespan);
        /// app.strokeWeight(2);
        app.image(imaEnemigo, 0, 0);
        //pp.rect(0, 0, 60, 30, 1, 1, 1, 1);
        app.popMatrix();
    }

    public void mover(float vel) {
        location.y += vel;
        if (m) {
            if (movx > (rango / 2f) - velX) {
                mov = false;
                movx = (rango / 2f) - velX;
            } else if (movx < -(rango / 2f) + velX) {
                movx = -(rango / 2f) + velX;
                mov = true;
            }
            if (mov) movx += velX;
            if (!mov) movx -= velX;
        }
    }

    //codigo de The nature of code
    void seek(PVector target) {
        PVector desired = PVector.sub(target, location);  // A vector pointing from the location to the target
        // If the magnitude of desired equals 0, skip out of here
        // (We could optimize this to check if x and y are 0 to avoid mag() square root
        if (desired.mag() == 0) return;
        // Normalize desired and scale to maximum speed
        desired.normalize();
        desired.mult(maxspeed);
        // Steering = Desired minus Velocity
        PVector steer = PVector.sub(desired, velocity);
        steer.limit(maxforce);  // Limit to maximum steering force
        applyForce(steer);
    }

    // This function implements Craig Reynolds' path following algorithm
    // http://www.red3d.com/cwr/steer/PathFollow.html
    void follow(Path p) {
        // Predict location 25 (arbitrary choice) frames ahead
        PVector predictLoc = location;
        // Now we must find the normal to the path from the predicted location
        // We look at the normal for each line segment and pick out the closest one
        /**/
        PVector normal = null;
        PVector target = null;
        float worldRecord = 1000000;  // Start with a very high record distance that can easily be beaten
        // Loop through all points of the path
        for (int i = 0; i < p.points.size() - 1; i++) {
            // Look at a line segment
            PVector a = p.points.get(i).getPos();
            PVector b = p.points.get(i + 1).getPos();
            // Get the normal point to that line
            PVector normalPoint = getNormalPoint(predictLoc, a, b);
            // This only works because we know our path goes from left to right
            // We could have a more sophisticated test to tell if the point is in the line segment or not
            if (normalPoint.x < a.x || normalPoint.x > b.x) {
                // This is something of a hacky solution, but if it's not within the line segment
                // consider the normal to just be the end of the line segment (point b)
                normalPoint = b.copy();
            }

            // How far away are we from the path?
            float distance = PVector.dist(predictLoc, normalPoint);
            // Did we beat the record and find the closest line segment?
            if (distance < worldRecord) {
                worldRecord = distance;
                // If so the target we want to steer towards is the normal
                normal = normalPoint;

                // Look at the direction of the line segment so we can seek a little bit ahead of the normal
                PVector dir = PVector.sub(b, a);
                dir.normalize();
                // This is an oversimplification
                // Should be based on distance to path & velocity
                dir.mult(10);
                target = normalPoint.copy();
                target.add(dir);
            }
        }

        // Only if the distance is greater than the path's radius do we bother to steer
        if (worldRecord > p.radius) {
            seek(target);
        }

        // Draw the debugging stuff
        if (true) {
            // Draw predicted future location
            app.stroke(0);
            app.fill(0);
            app.line(location.x, location.y, predictLoc.x, predictLoc.y);
            app.ellipse(predictLoc.x, predictLoc.y, 4, 4);

            // Draw normal location
            app.stroke(0);
            app.fill(0);
            app.ellipse(normal.x, normal.y, 4, 4);
            // Draw actual target (red if steering towards it)
            app.line(predictLoc.x, predictLoc.y, normal.x, normal.y);


            if (worldRecord > p.radius) app.fill(255, 0, 0);
            app.noStroke();
            app.ellipse(target.x, target.y, 8, 8);
        }
    }

    // A function to get the normal point from a point (p) to a line segment (a-b)
    // This function could be optimized to make fewer new Vector objects

    PVector getNormalPoint(PVector p, PVector a, PVector b) {
        // Vector from a to p
        PVector ap = PVector.sub(p, a);
        // Vector from a to b
        PVector ab = PVector.sub(b, a);
        ab.normalize(); // Normalize the line
        // Project vector "diff" onto line by using the dot product
        ab.mult(ap.dot(ab));
        PVector normalPoint = PVector.add(a, ab);
        return normalPoint;
    }


    public void applyForce(PVector force) {
        // We could add mass here if we want A = F / M
        acceleration.add(force);
    }


}
