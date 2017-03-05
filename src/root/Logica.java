package root;

import info.Info;
import pantallas.PantallaInicial;
import processing.core.PApplet;

import setup.AdministradorPantalla;

public class Logica {

    private static int tipoJ;
    private static PApplet app;


    static public int getTipoJ() {
        return tipoJ;
    }

    public Logica(PApplet app) {
        this.app = app;
        Info.getInstance();
        AdministradorPantalla.cambiarPantalla(new PantallaInicial());
        AdministradorPantalla.getCurrentScreen().iniciar();
    }

    public void pintar() {
        AdministradorPantalla.getCurrentScreen().pintar();

        if (AdministradorPantalla.getInterfazVisible() != null)
            AdministradorPantalla.getInterfazVisible().pintar();
    }

    public void mousePressed() {
        if (AdministradorPantalla.getInterfazVisible() != null)
            AdministradorPantalla.getInterfazVisible().mousePressed();
        AdministradorPantalla.getCurrentScreen().mousePressed();
    }

    public void mouseDragged() {
        if (AdministradorPantalla.getInterfazVisible() != null)
            AdministradorPantalla.getInterfazVisible().mouseDragged();
        AdministradorPantalla.getCurrentScreen().mouseDragged();
    }

    public void mouseReleased() {
        if (AdministradorPantalla.getInterfazVisible() != null)
            AdministradorPantalla.getInterfazVisible().mouseReleased();

        AdministradorPantalla.getCurrentScreen().mouseReleased();
    }

    public void keyPressed() {
        if (AdministradorPantalla.getInterfazVisible() != null)
            AdministradorPantalla.getInterfazVisible().KeyPressed();
        AdministradorPantalla.getCurrentScreen().KeyPressed();
    }

    public static PApplet getApp() {
        return app;
    }
}
