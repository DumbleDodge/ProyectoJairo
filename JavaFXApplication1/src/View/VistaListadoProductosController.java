package View;

import Controller.GestorVictorCarlosIvan;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.Producto;
import java.time.LocalDate;
import util.UtilidadDeFechas;

public class VistaListadoProductosController {

    @FXML
    private TableView tablaProductos;
    @FXML
    private TableColumn nombreColumn;
    @FXML
    private TableColumn descripcionColum;

    @FXML
    private Label nombreLabel;
    @FXML
    private Label descripcionLabel;
    @FXML
    private Label precioLabel;
    @FXML
    private Label stockLabel;
    @FXML
    private Label codigoBarrasLabel;
    @FXML
    private Label fechaAltaLabel;
    @FXML
    private Label fechaModificacionLabel;

    // Referencia a la clase principal
    private GestorVictorCarlosIvan gestorvictorvarlosivan;

    //El constructor es llamado ANTES del método initialize
    public VistaListadoProductosController() {
    }

    //Inicializa la clase controller y es llamado justo después de cargar el archivo FXML
    @FXML
    private void initialize() {

        //Inicializo la tabla con las dos primera columnas
        String nombre = "nombre";
        String stock = "stock";
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>(nombre));
        descripcionColum.setCellValueFactory(new PropertyValueFactory<>(stock));

        //Borro los detalles de la persona
        mostrarDetallesProducto(null);

        //Escucho cambios en la selección de la tabla y muestro los detalles en caso de cambio
        tablaProductos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetallesProducto((Producto) newValue));
    }

    //Es llamado por la apliación principal para tener una referencia de vuelta de si mismo
    public void setGestorVictorCarlosIvan(GestorVictorCarlosIvan gestorvictorvarlosivan) {

        this.gestorvictorvarlosivan = gestorvictorvarlosivan;

        //Añado la lista obervable a la tabla
        tablaProductos.setItems(gestorvictorvarlosivan.getDatosProducto());
    }

    //Muestra los detalles de la persona seleccionada
    private void mostrarDetallesProducto(Producto producto) {

        if (producto != null) {
            //Relleno los labels desde el objeto persona
            nombreLabel.setText(producto.getNombre());
            descripcionLabel.setText(producto.getDescripcion());
            precioLabel.setText(Double.toString(producto.getPrecio()));
            stockLabel.setText(Integer.toString(producto.getStock()));
            codigoBarrasLabel.setText(producto.getCodigoBarras());
            fechaAltaLabel.setText(UtilidadDeFechas.formato((LocalDate) producto.getFechaAlta()));
            fechaModificacionLabel.setText(UtilidadDeFechas.formato((LocalDate) producto.getFechaModificacion()));
            //TODO: Tenemos que convertir la fecha de nacimiento en un String
            //fechaDeNacimientoLabel.setText(...);
        } else {
            //Persona es null, vacío todos los labels.
            nombreLabel.setText("");
            descripcionLabel.setText("");
            precioLabel.setText("");
            stockLabel.setText("");
            codigoBarrasLabel.setText("");
            fechaAltaLabel.setText("");
            fechaModificacionLabel.setText("");
        }
    }

//Borro la persona seleccionada cuando el usuario hace clic en el botón de Borrar
    @FXML
    private void borrarPersona() {
        //Capturo el indice seleccionado y borro su item asociado de la tabla
        int indiceSeleccionado = tablaProductos.getSelectionModel().getSelectedIndex();
        if (indiceSeleccionado >= 0) {
            //Borro item
            tablaProductos.getItems().remove(indiceSeleccionado);

        } else {
            //Muestro alerta
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Atención");
            alerta.setHeaderText("Persona no seleccionada");
            alerta.setContentText("Por favor, selecciona una persona de la tabla");
            alerta.showAndWait();

        }
    }

//Muestro el diálogo editar persona cuando el usuario hace clic en el botón de Crear
    @FXML
    private void crearPersona() {
        Producto temporal = new Producto();
        boolean guardarClicked = gestorvictorvarlosivan.muestraEditarProducto(temporal);
        if (guardarClicked) {
            gestorvictorvarlosivan.getDatosProducto().add(temporal);
        }
    }

    //Muestro el diálogo editar persona cuando el usuario hace clic en el botón de Editar
    @FXML
    private void editarPersona() {
        Producto seleccionada = (Producto) tablaProductos.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            boolean guardarClicked = gestorvictorvarlosivan.muestraEditarProducto(seleccionada);
            if (guardarClicked) {
                mostrarDetallesProducto(seleccionada);
            }

        } else {
            //Muestro alerta
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Alerta");
            alerta.setHeaderText("Producto no seleccionado");
            alerta.setContentText("Por favor, selecciona un Producto");
            alerta.showAndWait();
        }
    }
}
