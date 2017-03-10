package juego;


import juego.Path;
import setup.Pantalla;

public class PantallaJuego extends Pantalla {

    Path p;
    Jugador j;

    float t;

    @Override
    public void iniciar() {
        p = new Path();
        j = new Jugador();

        // start();
    }

    @Override
    public void pintar() {
        app.background(37, 32, 45);


        p.draw((app.width / 2) + (int) (app.map(app.noise(t), 0, 1, -100, 100)));
        p.mover();

        j.pintar(400);
        j.comprobar(p);

        //j.follow(p);
        t += 0.002;

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
