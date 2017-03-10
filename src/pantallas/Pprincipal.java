package pantallas;

import info.Info;
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

        if (app.dist(app.mouseX, app.mouseY, 965, 637) < 300) {
            app.image(Info.getInstance().pp2, app.map(app.noise(t + t + 7378), 0, 1, -vibra, vibra), app.map(app.noise(t + t + 7378), 0, 1, -vibra, vibra));
            if (app.mousePressed) AdministradorPantalla.cambiarPantalla(new Niveles());
        }

        t += 0.01f;
    }

    @Override
    public void finalizar() {

    }
}
