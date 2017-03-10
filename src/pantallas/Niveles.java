package pantallas;

import info.Info;
import setup.AdministradorPantalla;
import setup.Pantalla;

/**
 * Created by andre on 3/9/2017.
 */
public class Niveles extends Pantalla {

    Info info;
    private float t;
    private float vibra = 10;

    @Override
    public void iniciar() {
        info = Info.getInstance();
        info.loadLevel();
    }

    @Override
    public void pintar() {
        app.background(36, 31, 45);

        app.image(Info.getInstance().level1, app.map(app.noise(t + 7378), 0, 1, -vibra, vibra), app.map(app.noise(t + 457), 0, 1, -vibra, vibra));
        app.image(Info.getInstance().level2, app.map(app.noise(t + 500), 0, 1, -vibra, vibra), app.map(app.noise(t + 1618), 0, 1, -vibra, vibra));

        if (app.dist(app.mouseX, app.mouseY, 583, 592) < 300) {

            app.image(Info.getInstance().level4, app.map(app.noise(t + 594), 0, 1, -vibra, vibra), app.map(app.noise(t + 956), 0, 1, -vibra, vibra));

            if (app.mousePressed) {
                AdministradorPantalla.cambiarPantalla(new InicioRehabilirun());
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
