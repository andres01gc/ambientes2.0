package juego;


import info.Info;
import juego.particles.enemigo.AnimaEnemigo;
import juego.particles.gasolina.AnimaGasolina;
import juego.particles.moneda.AnimacionMonedaCogida;
import juego.sky.Sky;
import processing.core.PImage;
import setup.Pantalla;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PantallaJuego extends Pantalla {
    private Path p;
    private Jugador j;
    // KinectLink k;


    private int tiempoRegresivo = 5;
    private int nivelGasolina = 100;

    private PImage fondo0, fondo1;
    private boolean inicioJuego = false;
    public Sky sky;
    private float vibra = 3;
    private float tt;
    private float t;
    private int pantalla = 2;
    private ArrayList<AnimacionMonedaCogida> particlesMoneda = new ArrayList<AnimacionMonedaCogida>();
    private ArrayList<AnimaGasolina> particlesG = new ArrayList<AnimaGasolina>();

    private int puntaje = 0;
    private ArrayList<AnimaEnemigo> particlesE = new ArrayList<>();

    @Override
    public void iniciar() {
        Info.getInstance().cargarObstaculos();

        p = new Path();
        j = new Jugador();
        sky = new Sky();


        fondo0 = app.loadImage("../data/resources/juego/interfaz/47.png");
        fondo1 = app.loadImage("../data/resources/juego/interfaz/48.png");
        // k = KinectLink.getInstance();
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

    public void gui() {
        app.imageMode(app.CORNER);
        pintarBarraEnergia();
        app.image(fondo0, app.map(app.noise(tt + tt + 624), 0, 1, -vibra, vibra), app.map(app.noise(tt + 6998), 0, 1, -vibra, vibra));
        app.image(fondo1, app.map(app.noise(tt + tt + 981), 0, 1, -vibra, vibra), app.map(app.noise(tt + 691), 0, 1, -vibra, vibra));

        app.textSize(40);
        app.fill(255);
        app.text(nivelGasolina, 262, 70);
        app.text(puntaje, 1801, 120);
        nivelGasolina = app.constrain(nivelGasolina, -1, 100);

        if (nivelGasolina == 0) {
            pantalla++;
        }
    }

    public void pintarBarraEnergia() {
        app.fill(62, 206, 27, 200);
        if (nivelGasolina < 20) {
            app.fill(232, 12, 200);
        } else if (nivelGasolina < 60) {
            app.fill(247, 199, 42, 200);
        }

        float tam = app.map(nivelGasolina, 0, 100, 0, 370);
        app.rect(-2 + app.map(app.noise(tt + tt + 951), 0, 1, -vibra, vibra), 105 + app.map(app.noise(tt + tt + 183), 0, 1, -vibra, vibra), tam, 62, 6, 6, 6, 6);

    }


    @Override
    public void pintar() {

        fondo();


        switch (pantalla) {
            case 0:
                validarEstadoSujeto();
                break;

            case 1:
                cuentaRegresiva();
                break;

            case 2:
                game();

                break;
        }

        gui();

        //k.drawSkeleton();

    }

    //aquí se deberá validar alzando las manos al cielito;
    private void validarEstadoSujeto() {


    }


    public void cuentaRegresiva() {
        app.rectMode(app.CORNER);
        app.fill(0, 80);
        app.rect(0, 0, 1920, 1080);
        app.textSize(150);
        app.textAlign(app.CENTER, app.CENTER);
        app.fill(255);
        app.text(tiempoRegresivo, 1920 / 2, 1080 / 2);

        if (!inicioJuego) {
            if (app.frameCount % 60 == 0) {
                tiempoRegresivo--;

                if (tiempoRegresivo < 0) {
                    //      k.startRec();
                    inicioJuego = true;
                    pantalla++;
                }
            }
        }
    }


    public void pintarParticulasMoneda() {


    }


    public void game() {
        //  k.drawSkeleton();
        p.mover();
        p.draw((app.width / 2) + (int) (app.map(app.noise(t), 0, 1, -100, 100)));
        j.pintar(900, true);
        j.comprobar(p);
        //j.follow(p);
        t += 0.002;

        detectarMonedas();
        detectarEnemigos();
        detectarGasolina();
        pintarParticulasM();
        pintarParticulasG();
        pintarParticulasE();
        pintarHandRight();
        update();

    }


    public void update() {

        if (app.frameCount % 20 == 0) {
            nivelGasolina--;
        }
    }

    public void detectarMonedas() {
        ArrayList<Moneda> m = p.monedas;
        for (int i = m.size() - 1; i > 0; i--) {

            if (app.dist(m.get(i).getRealPos().x, m.get(i).getRealPos().y, j.getLocation().x, j.getLocation().y) < 70) {
                if (/*k.isSentadilla()*/true) {

                    int puntos = (int) (m.get(i).getTam() / 4);
                    puntaje += puntos;
                    particlesMoneda.add(new AnimacionMonedaCogida(puntos, m.get(i)));
                    m.remove(i);

                } else {
                    if (m.get(i).getRealPos().y > app.height) {
                        m.remove(i);
                    }
                }
            }
        }


    }

    public void detectarGasolina() {
        ArrayList<Gasolina> g = p.gasolinas;
        for (int i = g.size() - 1; i > 0; i--) {

            if (app.dist(g.get(i).getRealPos().x, g.get(i).getRealPos().y, j.getLocation().x, j.getLocation().y) < 70) {
                if (/*k.isSentadilla()*/true) {

                    nivelGasolina += 5;
                    particlesG.add(new AnimaGasolina(50, g.get(i)));
                    g.remove(i);

                } else {
                    if (g.get(i).getRealPos().y > app.height) {
                        g.remove(i);
                    }
                }
            }
        }
    }

    public void pintarParticulasM() {
        for (int i = particlesMoneda.size() - 1; i >= 0; i--) {
            AnimacionMonedaCogida pm = particlesMoneda.get(i);
            pm.pintar();
            if (pm.isMuerto()) {
                particlesMoneda.remove(pm);
            }
        }
    }

    public void pintarParticulasG() {
        for (int i = particlesG.size() - 1; i >= 0; i--) {
            AnimaGasolina pm = particlesG.get(i);
            pm.pintar();
            if (pm.isMuerto()) {
                particlesG.remove(pm);
            }
        }
    }


    private void pintarParticulasE() {
        for (int i = particlesE.size() - 1; i >= 0; i--) {
            AnimaEnemigo pm = particlesE.get(i);
            pm.pintar();
            if (pm.isMuerto()) {
                particlesE.remove(pm);
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
                nivelGasolina -= 5;
                particlesE.add(new AnimaEnemigo(20, obs.get(i)));
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
