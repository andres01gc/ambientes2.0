package pantallas;

import info.Info;
import pantallas.visualInterface.Hand;
import setup.AdministradorPantalla;
import setup.Pantalla;

/**
 * Created by andre on 3/9/2017.
 */

public class Pprincipal extends Pantalla {
    private int t;
    private float vibra = 10;

    @Override
    public void iniciar() {
        Info.getInstance().loadPprincipal();
    }

    @Override
    public void pintar() {
        app.background(36, 31, 45);
        app.image(Info.getInstance().pp1, app.map(app.noise(t + t + 7378), 0, 1, -vibra, vibra), app.map(app.noise(t + 457), 0, 1, -vibra, vibra));
        app.image(Info.getInstance().pp3, 0, 0);

        if (app.dist(Hand.pos.x, Hand.pos.y, 965, 637) < 150) {
            app.image(Info.getInstance().pp2, app.map(app.noise(t + t + 7378), 0, 1, -vibra, vibra), app.map(app.noise(t + t + 7378), 0, 1, -vibra, vibra));
            cargarMano();
        }

        t += 0.01f;
        pintarHandRight();
    }


    @Override
    public void pressHandRight() {
        if (app.dist(Hand.pos.x, Hand.pos.y, 965, 637) < 150) {
            app.image(Info.getInstance().pp2, app.map(app.noise(t + t + 7378), 0, 1, -vibra, vibra), app.map(app.noise(t + t + 7378), 0, 1, -vibra, vibra));
            if (app.mousePressed) AdministradorPantalla.cambiarPantalla(new Niveles());
        }


    }

    @Override
    public void finalizar() {

    }
}
