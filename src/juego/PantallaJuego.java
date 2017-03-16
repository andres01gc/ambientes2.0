package juego;


import juego.Path;
import juego.sky.Sky;
import revisar.KinectLink;
import setup.Pantalla;

import java.util.ArrayList;

public class PantallaJuego extends Pantalla {
    Path p;
    Jugador j;
    KinectLink k;
    float t;


    public Sky sky;

    @Override
    public void iniciar() {
        p = new Path();
        j = new Jugador();
        sky = new Sky();
        k = KinectLink.getInstance();
    }

    @Override
    public void pintar() {
        app.background(51, 51, 86);
         k.drawSkeleton();
        sky.pintar();
        p.mover();
        p.draw((app.width / 2) + (int) (app.map(app.noise(t), 0, 1, -100, 100)));
        j.pintar(900, k.isSentadilla());
        //j.comprobar(p);
        //j.follow(p);
        t += 0.002;
        detectarMonedas();
        pintarHandRight();
    }


    public void detectarMonedas() {
        ArrayList<Moneda> m = p.monedas;
        for (int i = m.size() - 1; i > 0; i--) {

            if (app.dist(m.get(i).getRealPos().x, m.get(i).getRealPos().y, j.getLocation().x, j.getLocation().y) < 50) {
                if (k.isSentadilla()) {
                    m.remove(i);
                } else {
                    if (m.get(i).getRealPos().y > app.height) m.remove(i);
                }
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
