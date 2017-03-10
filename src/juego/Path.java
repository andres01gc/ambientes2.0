package juego;

import juego.Particle.Nodo;
import processing.core.PApplet;
import processing.core.PVector;
import root.Logica;

import java.util.ArrayList;

/**
 * Created by andre on 3/5/2017.
 */
public class Path {
    PApplet app = Logica.getApp();

    ArrayList<Nodo> points;
    PVector vel, acel;
    private int tam = 300;
    float radius = 1;
    private float t;
    private boolean mover;
    private float angle;


    ArrayList<Enemigo> enemigos = new ArrayList<>();
    ArrayList<Moneda> monedas = new ArrayList<>();

    private ArrayList<PVector> posicionesSalida;
    private int rango = 400;


    public ArrayList<PVector> getPosicionesSalida() {
        return posicionesSalida;
    }


    public Path() {
        points = new ArrayList<>();
        vel = new PVector(0, 10);
        acel = new PVector();
        t = app.random(10000);
    }


//    public void iniHilo() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//
//                while (true) {
//                    t += 1;
//
//
//                    tam += 0.001f;
//
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//
//
//    }

    public void addPoint(float x, float y) {
        points.add(new Nodo(new PVector(x, y))
        );
    }

    public void draw(int fixX) {
        app.noStroke();
        app.fill(0, 50);
        drawSombra(fixX - 40, rango + 10);
        app.fill(24, 22, 30);
        drawParticles(fixX, rango);
        //  app.stroke(0);
        drawForm(fixX, rango + 100);
        update();
        app.stroke(0);
        app.noFill();
        drawLine(fixX);

        app.fill(255);

        drawEnemigos(fixX);
        drawMonedas(fixX);
    }

    public void drawEnemigos(int posX) {

        for (int i = enemigos.size() - 1; i > 0; i--) {
            Enemigo e = enemigos.get(i);
            e.pintar(posX);
            e.mover(vel.y);

            if (e.location.y > app.width + 100) enemigos.remove(e);
        }

    }

    public void drawMonedas(int posX) {

        for (int i = monedas.size() - 1; i > 0; i--) {
            Moneda e = monedas.get(i);
            e.pintar(posX);
            e.mover(vel.y);
            if (e.location.y > app.width + 100) enemigos.remove(e);
        }

    }


    private void drawParticles(int posX, int ancho) {
        for (Nodo v : points) {
            v.pintar(posX - (ancho / 2));
        }

        for (Nodo v : points) {
            v.pintar(posX + (ancho / 2));
        }

    }

    private void drawSombra(int posX, int ancho) {


        for (Nodo v : points) {
            v.pintar(posX - (ancho / 2));
        }

        for (Nodo v : points) {
            v.pintar(posX + (ancho / 2));
            posicionesSalida.add(new PVector(posX + (ancho / 2), v.getPos().y));

        }

    }


    public void drawLine(int newX) {
        posicionesSalida = new ArrayList<>();

        app.beginShape();

        app.stroke(255);
        for (Nodo v : points) {
            posicionesSalida.add(new PVector(v.getPos().x + newX - tam, v.getPos().y));
            posicionesSalida.add(new PVector(v.getPos().x + newX + tam, v.getPos().y));
            app.vertex(v.getPos().x + newX, v.getPos().y);
        }


        app.endShape();
    }


    public void updateData(float vel) {
        for (Nodo v : points) {
            if (mover) {
                // v.x += vel.x;
                v.getPos().y += vel;
            }
        }
    }


    public void drawRect(int newX, float ancho) {
        app.rectMode(app.CENTER);
        angle += 0.01;
        for (Nodo v : points) {

            app.pushMatrix();
            app.translate(v.getPos().x + newX - (ancho / 2) - 20, v.getPos().y);

            app.rotate(angle);
            app.ellipse(0, 0, 20, 20);
            app.popMatrix();
        }

        for (int i = points.size() - 1; i > 0; i--) {
            PVector v = points.get(i).getPos();
            app.pushMatrix();
            app.translate(v.x + newX + (ancho / 2) + 20, v.y);
            app.rotate(angle);
            app.ellipse(0, 0, 20, 20);
            app.popMatrix();
        }


    }


    public void drawForm(int newX, float ancho) {

        app.beginShape();
        for (Nodo v : points) {
            app.vertex(v.getPos().x + newX - (ancho / 2), v.getPos().y);
        }

        for (int i = points.size() - 1; i > 0; i--) {
            PVector v = points.get(i).getPos();
            app.vertex(v.x + newX + (ancho / 2), v.y);
        }
        app.endShape(app.CLOSE);

    }


    public void update() {
        updateData(vel.y);
        int tam = 400;
        if (app.frameCount % 5 == 0) {
            float bufferX = app.map(app.noise(t), 0, 1, -tam, tam);
            addPoint(bufferX, -800);


            if (app.random(1) < 0.08) {
                enemigos.add(new Enemigo(bufferX, -800f, rango));
            }

            if (app.random(1) < 0.08) {
                monedas.add(new Moneda(bufferX, -800f, rango));
            }
        }
        t += 0.01;
    }


    public void updateLine(float vel) {
        for (Nodo v : points) {
            if (mover) {
                v.getPos().y += vel;
            }
        }


    }

    public void mover() {
        mover = true;
    }
}
