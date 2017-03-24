package pantallas;

import info.Data;
import info.Info;
import setup.Pantalla;

/**
 * Created by andre on 3/23/2017.
 */
public class PantallaFinal extends Pantalla {
    private Info info;


    int segundos, minutos;
    private float vibra = 10;
    private float t;

    @Override
    public void iniciar() {
        info = Info.getInstance();
        info.cargarPantallaFinal();

        int buffer = Data.tiempo;

        while (buffer - 60 > 0) {
            buffer -= 60;
            minutos++;
        }

        segundos = buffer;
        app.textSize(40);

    }

    @Override
    public void pintar() {
        app.image(info.fin1, 0, 0);
        app.image(info.fin2, app.map(app.noise(t + 7378), 0, 1, -vibra, vibra), app.map(app.noise(t + 457), 0, 1, -vibra, vibra));
        app.image(info.fin3, app.map(app.noise(t + 123), 0, 1, -vibra, vibra), app.map(app.noise(t + 4314), 0, 1, -vibra, vibra));

        app.text(Data.puntaje, 512 - 30, 569);
        app.text(minutos + ":" + segundos, 701 - 30, 668);
        app.text(Data.monedasRecogidas, 777 - 30, 773);
Math.random();
        //  app.text
        t += 0.01;
        app.text(app.mouseX + " " + app.mouseY, app.mouseX, app.mouseY);
    }

    @Override
    public void finalizar() {


    }
}
