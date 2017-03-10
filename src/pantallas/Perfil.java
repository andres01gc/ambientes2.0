package pantallas;

import info.Info;
import setup.AdministradorPantalla;
import setup.Pantalla;

/**
 * Created by andre on 3/9/2017.
 */
public class Perfil extends Pantalla {


    private float t = 1000;
    private float vibra = 4;

    @Override
    public void iniciar() {
        Info.getInstance().loadPerfil();
    }

    @Override
    public void pintar() {
        app.background(36, 31, 45);

        app.image(Info.getInstance().perfil1, app.map(app.noise(t + 7378), 0, 1, -vibra, vibra), app.map(app.noise(t + 457), 0, 1, -vibra, vibra));
        app.image(Info.getInstance().perfil2, 0, 0);
        app.image(Info.getInstance().perfil3, 0, 0);

        t += 0.02f;


        if (app.dist(app.mouseX, app.mouseY, 1827, 1002) < 100) {
            app.image(Info.getInstance().onF, app.map(app.noise(t + 120), 0, 1, -vibra, vibra)-20,10+ app.map(app.noise(t + 120 ), 0, 1, -vibra, vibra));
            if (app.mousePressed) AdministradorPantalla.cambiarPantalla(new Pprincipal());
        }
    }


    @Override
    public void finalizar() {


    }
}
