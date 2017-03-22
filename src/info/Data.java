package info;

import KinectPV2.KinectPV2;
import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;
import setup.Pantalla;

import javax.swing.table.DefaultTableModel;

/**
 * Created by andre on 3/20/2017.
 */
public class Data {

    PApplet app = Pantalla.app;
    public static int puntaje;
    public static int monedasDesplegadas;
    public static int monedasRecogidas;
    public static int sentadillas;
    public static int obstaculosDesplegados;
    public static int obstaculosEfectivos;
    public static int combustibleDeplegado;
    public static int combustibleObtenido;
    public static int tiempoFueraDelCamino;
    private Table table;
    private static Data d;


    public static void resetData() {

        monedasRecogidas = 0;
        monedasDesplegadas = 0;
        sentadillas = 0;
        obstaculosDesplegados = 0;
        obstaculosEfectivos = 0;
        combustibleDeplegado = 0;
        combustibleObtenido = 0;
        tiempoFueraDelCamino = 0;
    }


    public void crearTablas() {

        table.addColumn("puntaje");
        table.addColumn("monedasDesplegadas");
        table.addColumn("monedasRecogidas");
        table.addColumn("sentadillas");
        table.addColumn("obstaculosDesplegados");
        table.addColumn("obstaculosEfectivos");
        table.addColumn("combustibleDeplegado");
        table.addColumn("combustibleObtenido");
        table.addColumn("tiempoFueraDelCamino");

    }

    public void saveData() {
        TableRow newRow = table.addRow();

        newRow.setFloat("puntaje", puntaje);
        newRow.setFloat("monedasDesplegadas", monedasDesplegadas);
        newRow.setFloat("monedasRecogidas", monedasRecogidas);
        newRow.setFloat("sentadillas", sentadillas);
        newRow.setFloat("obstaculosDesplegados", obstaculosDesplegados);
        newRow.setFloat("obstaculosEfectivos", obstaculosEfectivos);
        newRow.setFloat("combustibleDeplegado", combustibleDeplegado);
        newRow.setFloat("combustibleObtenido", combustibleObtenido);
        newRow.setFloat("tiempoFueraDelCamino", tiempoFueraDelCamino);

        app.saveTable(table, "../data/datosPartida.csv");
        System.out.println("guarda Partida!");
    }

    public static Data getInstance() {
        if (d == null) d = new Data();

        return d;
    }


    Data() {
        table = new Table();
        // table = app.loadTable("../data/datosPartidas.csv", "header");
        crearTablas();
    }
}

