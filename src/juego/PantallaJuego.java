package juego;


import juego.Path;
import setup.Pantalla;

public class PantallaJuego extends Pantalla {

    Path p;
    Jugador j;

    @Override
    public void iniciar() {

        p = new Path();
        j = new Jugador();
   // start();
    }

    @Override
    public void pintar() {
        app.background(0);


        p.draw();
        p.mover();


        j.pintar();
        j.follow(p);


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
