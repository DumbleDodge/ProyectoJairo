package Controller;

import Model.Producto;
import View.VistaListadoProductosController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.VistaEditarProductoController;

public class GestorVictorCarlosIvan extends Application {

    private ObservableList datosProducto = FXCollections.observableArrayList();

    private Stage escenarioPrincipal;
    private BorderPane layoutPrincipal;
    private AnchorPane vistaListadoProducto;
    private AnchorPane editarProducto;

    public GestorVictorCarlosIvan() {
        datosProducto.add(new Producto("Hola", "Martillo", "Azul", 15.2, 11, "hola",
                LocalDate.of(1974, 5, 14), LocalDate.of(2018, 6, 15)));
        datosProducto.add(new Producto("Hola", "Sierra", "Rojo", 20, 12, "hola",
                LocalDate.of(1974, 5, 14), LocalDate.of(2018, 6, 15)));
        datosProducto.add(new Producto("Hola", "Arpón", "azul", 85, 20, "hola",
                LocalDate.of(1974, 5, 14), LocalDate.of(2018, 6, 15)));
        datosProducto.add(new Producto("Hola", "Martillo", "azul", 85, 20, "hola",
                LocalDate.of(1974, 5, 14), LocalDate.of(2018, 6, 15)));
//        datosProducto.add(new Producto("Mazo", "Mazo", "azul"));
//        datosProducto.add(new Producto("Sierra", "Sierra", "verde"));
//        datosProducto.add(new Producto("Soplete", "Soplete", "amarillo"));

    }

    //Método para devolver los datos como lista observable de personas
    public ObservableList getDatosProducto() {
        return datosProducto;
    }

    @Override
    public void start(Stage escenarioPrincipal) {

        //Debo hacerlo para que luego me funcione en l carga de escenas
        this.escenarioPrincipal = escenarioPrincipal;

        //Establezco el título
        this.escenarioPrincipal.setTitle("Libreta de direcciones");

        //Inicializo el layout principal
        muestraVistaMenuOpciones();

        //Muestro la vista persona
        muestraVistaListadoProductos();
    }

    public void muestraVistaMenuOpciones() {

        //Cargo el layout principal a partir de la vista VistaPrincipal.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = GestorVictorCarlosIvan.class.getResource("../view/VistaMenuOpciones.fxml");
        loader.setLocation(location);
        try {
            layoutPrincipal = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GestorVictorCarlosIvan.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Cargo y muestro la escena que contiene ese layout principal
        Scene escena = new Scene(layoutPrincipal);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();

    }

    public void muestraVistaListadoProductos() {

        //Cargo la vista persona a partir de VistaPersona.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = GestorVictorCarlosIvan.class.getResource("../view/VistaListadoProductos.fxml");
        loader.setLocation(location);
        try {
            vistaListadoProducto = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GestorVictorCarlosIvan.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Añado la vista al centro del layoutPrincipal
        layoutPrincipal.setCenter(vistaListadoProducto);

        //Doy acceso al controlador VistaPersonaCOntroller a LibretaDirecciones
        VistaListadoProductosController controller = loader.getController();
        controller.setGestorVictorCarlosIvan(this);

    }

    public boolean muestraEditarProducto(Producto producto) {

        //Cargo la vista persona a partir de VistaPersona.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = GestorVictorCarlosIvan.class.getResource("../view/VistaEditarProducto.fxml");
        loader.setLocation(location);
        try {
            editarProducto = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GestorVictorCarlosIvan.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        //Creo el escenario de edición (con modal) y establezco la escena
        Stage escenarioEdicion = new Stage();
        escenarioEdicion.setTitle("Editar Producto");
        escenarioEdicion.initModality(Modality.WINDOW_MODAL);
        escenarioEdicion.initOwner(escenarioPrincipal);
        Scene escena = new Scene(editarProducto);
        escenarioEdicion.setScene(escena);

        //Asigno el escenario de edición y la persona seleccionada al controlador
        VistaEditarProductoController controller = loader.getController();
        controller.setEscenarioEdicion(escenarioEdicion);
        controller.setProducto(producto);

        //Muestro el diálogo ahjsta que el ussuario lo cierre
        escenarioEdicion.showAndWait();

        //devuelvo el botón pulsado
        return controller.isGuardarClicked();

    }

    //Invoco el método getPrimaryStage para que devuelva mi escenario principal
    public Stage getPrimaryStage() {
        return escenarioPrincipal;
    }

    //Método main
    public static void main(String[] args) {
        launch(args);
    }

}
