package pantallas;

import info.Info;
import juego.PantallaJuego;
import processing.core.PApplet;
import setup.AdministradorPantalla;
import setup.Pantalla;

/**
 * Created by andre on 3/10/2017.
 */
public class Instrucciones extends Pantalla {


    private float vibra=10;
    private float t;

    @Override
    public void iniciar() {
        Info.getInstance().loadInstrucciones();
    }

    @Override
    public void pintar() {
        app.background(36, 31, 45);

        app.image(Info.getInstance().inst1, app.map(app.noise(t + 7378), 0, 1, -vibra, vibra), app.map(app.noise(t + 457), 0, 1, -vibra, vibra));
        app.image(Info.getInstance().inst2, app.map(app.noise(t + 500), 0, 1, -vibra, vibra), app.map(app.noise(t + 1618), 0, 1, -vibra, vibra));


        if (app.dist(app.mouseX, app.mouseY, 583, 592) < 300) {
            app.image(Info.getInstance().level4, app.map(app.noise(t + 594), 0, 1, -vibra, vibra), app.map(app.noise(t + 956), 0, 1, -vibra, vibra));

            if (app.mousePressed) {
                AdministradorPantalla.cambiarPantalla(new PantallaJuego());
                //   app.new
            }

        }
        app.image(Info.getInstance().level3, 0, 0);

        t += 0.01;
    }

    @Override
    public void finalizar() {

    }
}
