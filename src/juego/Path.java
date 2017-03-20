package juego;

import juego.particles.path.Nodo;
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
    PVector acel;
    public static PVector vel;
    public int tam = 300;
    float radius = 1;
    private float t;
    private boolean mover;
    private float angle;


    ArrayList<Obstaculo> enemigos = new ArrayList<>();
    ArrayList<Moneda> monedas = new ArrayList<>();
    ArrayList<Gasolina> gasolinas = new ArrayList<>();

    private ArrayList<PVector> posicionesSalida;
    private int rango = 600;


    public ArrayList<PVector> getPosicionesSalida() {
        return posicionesSalida;
    }


    public Path() {
        points = new ArrayList<>();
        vel = new PVector(0, 1);
        acel = new PVector();
        t = app.random(10000);
        startCamino();
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
        app.fill(80, 85, 127);
        drawLine(fixX);
        app.fill(80, 85, 127);
        drawParticles(fixX, rango);
        app.fill(80, 85, 127);
        drawForm(fixX, rango + 100);

        update();


        app.fill(255);

        drawEnemigos(fixX);
        drawMonedas(fixX);
        drawGasolina(fixX);


        for (int i = points.size() - 1; i > 0; i--) {
            if (points.get(i).getPos().y > app.height + 200) points.remove(i);
        }

        vel.y += 0.01f;

        //   System.out.println("points: " + points.size());
    }

    private void drawGasolina(int fixX) {

        for (int i = gasolinas.size() - 1; i > 0; i--) {
            Gasolina e = gasolinas.get(i);
            e.pintar(fixX);
            e.mover(vel.y);

            if (e.location.y > app.height + 100) {
                gasolinas.remove(e);
            }
        }
        //   System.out.println("enemigos " + gasolinas.size());

    }


    public void drawEnemigos(int posX) {

        for (int i = enemigos.size() - 1; i > 0; i--) {
            Obstaculo e = enemigos.get(i);
            e.pintar(posX);
            e.mover(vel.y);

            if (e.location.y > app.height + 100) {
                // System.out.println("eleiminia enemigos");

                enemigos.remove(e);
            }
        }
        //  System.out.println("enemigos " + enemigos.size());
    }

    public void drawMonedas(int posX) {

        for (int i = monedas.size() - 1; i > 0; i--) {
            Moneda e = monedas.get(i);
            e.pintar(posX);
            e.mover(vel.y);
            {
                if (e.location.y > app.height + 100) monedas.remove(e);
            }
        }
        //  System.out.println("monedas " + monedas.size());

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

        }

    }


    public void drawLine(int newX) {
        posicionesSalida = new ArrayList<>();

        app.beginShape();

        app.stroke(255);

        int fix = 100;
        for (Nodo v : points) {
            posicionesSalida.add(new PVector(v.getPos().x + newX - tam - fix, v.getPos().y));
            posicionesSalida.add(new PVector(v.getPos().x + newX + tam + fix, v.getPos().y));
            // app.vertex(v.getPos().x + newX, v.getPos().y);
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




    public void startCamino() {
        // updateData(vel.y);
        int tam = 300;
        int i = 0;
        while (i < 1920) {
            float bufferX = app.map(app.noise(t), 0, 1, -tam, tam);
            addPoint(bufferX, 1080 - i);
            i += 70;
            t += 0.01 * 5;
        }
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

    public void update() {
        updateData(vel.y);
        int tam = 400;
        if (app.frameCount % 5 == 0) {
            float bufferX = app.map(app.noise(t), 0, 1, -tam, tam);
            addPoint(bufferX, -800);


            if (app.random(1) < 0.08) {
                enemigos.add(new Obstaculo(bufferX, -800f, rango));
            }

            if (app.random(1) < 0.08) {
                monedas.add(new Moneda(bufferX, -800f, rango));
            }

            if (app.random(1) < 0.02) {
                gasolinas.add(new Gasolina(bufferX, -800f, rango));
            }

        }


        t += 0.006f;
    }


}
