package pantallas;

import info.Info;
import juego.PantallaJuego;
import juego.sky.Sky;
import pantallas.visualInterface.Hand;
import setup.AdministradorPantalla;
import setup.Pantalla;

/**
 * Created by andre on 3/20/2017.
 */
public class InstruccionesJuego extends Pantalla {


    private Sky sky;
    private Info info;
    private float vibra = 5;
    private float t;

    @Override
    public void iniciar() {
        info = Info.getInstance();
        info.loadInstruccionesJuego();
        sky = new Sky();
        t = app.random(15058);
    }

    @Override
    public void pintar() {
        app.background(51, 51, 86);

        app.imageMode(app.CORNER);

        app.image(info.insJ3, 0, 0);
        sky.pintar();
        app.image(info.insJ1, app.map(app.noise(t + 951), 0, 1, -vibra, vibra), app.map(app.noise(t + 258), 0, 1, -vibra, vibra));
        app.image(info.insJ2, app.map(app.noise(t + 624), 0, 1, -vibra, vibra), app.map(app.noise(t + 6998), 0, 1, -vibra, vibra));
        t += 0.1f;

        if (app.dist(1920 / 2, 900, Hand.pos.y, Hand.pos.y) < 300) {
            app.fill(255);
            cargarMano();
        } else {
            app.fill(255, 100);
        }


        app.textAlign(
                app.CENTER, app.CENTER
        );

        app.textSize(40);
        app.text("Continuar", 1920 / 2, 900);
        pintarHandRight();
    }


    @Override
    public void pressHandRight() {

        if (app.dist(1920 / 2, 900, Hand.pos.y, Hand.pos.y) < 300) {
            AdministradorPantalla.cambiarPantalla(new PantallaJuego());
        }


    }

    @Override
    public void finalizar() {

    }

}
