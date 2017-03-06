package juego;

import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by andre on 3/5/2017.
 */
public class Path {
    PApplet app = Logica.getApp();

    ArrayList<PVector> points;

    PVector vel, acel;
    private float tam = 50;
    float radius = 100;
    private float t;
    private boolean mover;

    public Path() {
        points = new ArrayList<>();
        vel = new PVector(0, 10);
        acel = new PVector();
        addPoint((app.width / 2), -500);
        addPoint((app.width / 2), -1000);

        t = app.random(10000);

    }


    public void addPoint(float x, float y) {
        PVector point = new PVector(x, y);
        points.add(point);
    }

    public void draw() {
        app.stroke(255);
        app.strokeWeight(radius * 2);
        app.noFill();
        boolean nPoint = true;

        app.beginShape();
        for (PVector v : points) {
            app.vertex(v.x, v.y);
            if (mover) {
                v.x += vel.x;
                v.y += vel.y;
            }
            if (v.x < -100) nPoint = false;

        }
        if (nPoint) {
            update();
        }
        mover = false;
        app.endShape();
    }

    public void update() {
        radius = app.map(app.noise(t + 1000), 0, 1, 300, 500);
        float buffer = app.map(app.noise(t), 0, 1, radius * 2, app.width - (radius * 2));

        if (app.frameCount % 60 == 0) {
            addPoint(buffer + app.random(-tam, tam), -800);
        }

        tam += 0.001f;
        t += 0.01;
    }


    public void mover() {
        mover = true;
    }
}
