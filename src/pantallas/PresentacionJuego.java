package pantallas;

import info.Info;
import juego.PantallaJuego;
import juego.sky.Sky;
import pantallas.visualInterface.Hand;
import root.Main;
import setup.AdministradorPantalla;
import setup.Interfaz;
import setup.Pantalla;

/**
 * Created by andre on 3/20/2017.
 */
public class PresentacionJuego extends Pantalla {

    Info info;
    Sky sky;

    @Override
    public void iniciar() {
        info = Info.getInstance();
        info.loadInicioJuego();
        sky = new Sky();
        Main.p.play();
    }

    @Override
    public void pintar() {

        app.background(51, 51, 86);
        sky.pintar();
        app.imageMode(app.CORNER);

        if (Hand.pos.y > 700 && Hand.pos.y < 800) {
            cargarMano();
            app.image(info.ij3, 0, 0);

        }

        if (Hand.pos.y > 820 && Hand.pos.y < 920) {
            app.image(info.ij4, 0, 0);
            cargarMano();
        }
        app.image(info.ij1, 0, 0);
        app.image(info.ij2, 0, 0);

        pintarHandRight();
    }

    @Override
    public void finalizar() {

    }

    @Override
    public void pressHandRight() {
        if (Hand.pos.y > 700 && Hand.pos.y < 800) {
            AdministradorPantalla.cambiarPantalla(new PantallaJuego());
        }

        if (Hand.pos.y > 820 && Hand.pos.y < 920) {
            AdministradorPantalla.cambiarPantalla(new InstruccionesJuego());
        }
    }
}
