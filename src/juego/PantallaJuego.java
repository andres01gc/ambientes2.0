package juego;


import info.Info;
import juego.sky.Sky;
import processing.core.PImage;
import revisar.KinectLink;
import setup.Pantalla;

import java.util.ArrayList;

public class PantallaJuego extends Pantalla {
    Path p;
    Jugador j;
    KinectLink k;


    int tiempoInicial = 5;
    int tiempoJuego = 20;


    private PImage fondo0, fondo1;


    boolean inicioJuego = false;

    public Sky sky;
    private float vibra = 3;
    private float tt;
    private float t;

    @Override
    public void iniciar() {
        Info.getInstance().cargarObstaculos();


        p = new Path();
        j = new Jugador();
        sky = new Sky();

        fondo0 = app.loadImage("../data/resources/juego/interfaz/47.png");
        fondo1 = app.loadImage("../data/resources/juego/interfaz/48.png");
        k = KinectLink.getInstance();

    }


    public void fondo() {
        if (j.isFuera()) {
            app.background(232, 12, 59);
        } else {
            app.background(51, 51, 86);
        }
        sky.pintar();
        tt = app.random(31568);
    }

    @Override
    public void pintar() {

        fondo();

        app.imageMode(app.CORNER);
        app.image(fondo0, app.map(app.noise(tt + tt + 624), 0, 1, -vibra, vibra), app.map(app.noise(tt + 6998), 0, 1, -vibra, vibra));
        app.image(fondo1, app.map(app.noise(tt + tt + 981), 0, 1, -vibra, vibra), app.map(app.noise(tt + 691), 0, 1, -vibra, vibra));

        if (inicioJuego) {
            game();
            // k.saveSkeletonByFrame();
        } else {

           // k.startRec();
            cuentaRegresiva();
        }

        tt += 0.01;

        //k.drawSkeleton();

    }

    public void cuentaRegresiva() {


        app.rectMode(app.CORNER);
        app.fill(0, 80);
        app.rect(0, 0, 1920, 1080);
        app.textSize(150);
        app.textAlign(app.CENTER, app.CENTER);
        app.fill(255);
        app.text(tiempoInicial, 1920 / 2, 1080 / 2);

        if (!inicioJuego) {
            if (app.frameCount % 60 == 0) {
                tiempoInicial--;

                if (tiempoInicial < 0) {
                    k.startRec();
                    inicioJuego = true;
                }
            }
        }
    }

    public void game() {
        app.textSize(50);
        app.text(tiempoJuego, 175, 135);

        //  k.drawSkeleton();
        p.mover();
        p.draw((app.width / 2) + (int) (app.map(app.noise(t), 0, 1, -100, 100)));
        j.pintar(900, false);
        j.comprobar(p);
        //j.follow(p);
        t += 0.002;
        detectarMonedas();
        detectarEnemigos();

        pintarHandRight();
        update();/**/

    }

    public void update() {

        if (app.frameCount % 60 == 0) {
            tiempoJuego--;
        }
    }

    public void detectarMonedas() {
        ArrayList<Moneda> m = p.monedas;
        for (int i = m.size() - 1; i > 0; i--) {

            if (app.dist(m.get(i).getRealPos().x, m.get(i).getRealPos().y, j.getLocation().x, j.getLocation().y) < 70) {
                // if (k.isSentadilla()) {
                //   m.remove(i);
                //   } else {

                if (m.get(i).getRealPos().y > app.height) m.remove(i);
                // }
            }
        }
    }

    public void detectarEnemigos() {
        ArrayList<Obstaculo> obs = p.enemigos;
        for (int i = obs.size() - 1; i > 0; i--) {

            if (app.dist(obs.get(i).getRealPos().x, obs.get(i).getRealPos().y, j.getLocation().x, j.getLocation().y) < 70) {
                // if (k.isSentadilla()) {
                //   m.remove(i);
                //   } else {


                obs.remove(i);
                // }
            }
        }
    }


    @Override
    public void pressHandRight() {

    }

    @Override
    public void run() {


    }

    @Override
    public void finalizar() {


    }


    @Override
    public void mousePressed() {
    }


}
