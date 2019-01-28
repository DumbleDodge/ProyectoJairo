package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Producto;
import java.time.LocalDate;
import util.UtilidadDeFechas;

public class VistaEditarProductoController {

    //TextField para los campos
    @FXML
    private TextField fotoTextField;
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField descripcionTextField;
    @FXML
    private TextField precioTextField;
    @FXML
    private TextField stockTextField;
    @FXML
    private TextField codigoBarrasTextField;
    @FXML
    private TextField fechaAltaTextField;
    @FXML
    private TextField fechaModificacionTextField;

    private Stage escenarioEdicion; //Escenario de edición
    private Producto producto; // Referencia a la clase persona
    private boolean guardarClicked = false;

    //Inicializa la clase controller y es llamado justo DESPUÉS de cargar el archivo FXML
    @FXML
    private void initialize() {
    }

    //Establece el escenario de edición
    public void setEscenarioEdicion(Stage escenarioEdicion) {
        this.escenarioEdicion = escenarioEdicion;
    }

    //Establece la persona a editar
    public void setProducto(Producto producto) {
        this.producto = producto;

        fotoTextField.setText(producto.getFoto());
        nombreTextField.setText(producto.getNombre());
        descripcionTextField.setText(producto.getDescripcion());
        precioTextField.setText(producto.getPrecio() + "");
        stockTextField.setText(producto.getStock() + "");
        codigoBarrasTextField.setText(producto.getCodigoBarras());
        fechaAltaTextField.setText(UtilidadDeFechas.formato((LocalDate) producto.getFechaAlta()));
        fechaModificacionTextField.setText(UtilidadDeFechas.formato((LocalDate) producto.getFechaModificacion()));
        fechaModificacionTextField.setPromptText("dd/mm/yyyy");
        fechaAltaTextField.setPromptText("dd/mm/yyyy");
    }

    //Devuelve true si se ha pulsado Guardar, si no devuelve false
    public boolean isGuardarClicked() {
        return guardarClicked;
    }

    //LLamado cuando se pulsa Guardar
    @FXML
    private void guardar() {

        if (datosValidos()) {

            //Asigno datos a propiedades de persona
            producto.setfoto(producto.getFoto());
            producto.setNombre(nombreTextField.getText());
            producto.setDescripcion(descripcionTextField.getText());
            producto.setPrecio(Double.parseDouble(precioTextField.getText()));
            producto.setStock(Integer.parseInt(stockTextField.getText()));
            producto.setcCodigoBarras(codigoBarrasTextField.getText());
            producto.setFechaAlta(fechaAltaTextField.getText());
            producto.setFechaModificacion(fechaModificacionTextField.getText());

            guardarClicked = true; //Cambio valor booleano
            escenarioEdicion.close(); //Cierro el escenario de edición

        }
    }

    //LLamado cuando se pulsa Cancelar
    @FXML
    private void cancelar() {
        escenarioEdicion.close();
    }

    //Validación de datos
    private boolean datosValidos() {

        //Inicializo string para mensajes
        int cont = 0;

        //Compruebo los campos      
        if (nombreTextField.getText() == null || nombreTextField.getText().length() == 0) {
            cont++;
        }

        if (descripcionTextField.getText() == null || descripcionTextField.getText().length() == 0) {
            cont++;
        }
        if (precioTextField.getText() == null || precioTextField.getText().length() == 0) {
            cont++;
        }
        //
        //        if (codigoPostalTextField.getText() == null || codigoPostalTextField.getText().length() == 0) {
        //            mensajeError += "Código postal no válido.\n";
        //        } else {
        //            //Convierto el código postal a entero
        //            try {
        //                Integer.parseInt(codigoPostalTextField.getText());
        //            } catch (NumberFormatException e) {
        //                mensajeError += "Código postal no válido (debe ser un entero).\n";
        //            }
        //        }
        //
        //        if (ciudadTextField.getText() == null || ciudadTextField.getText().length() == 0) {
        //            mensajeError += "Ciudad no válida.\n";
        //        }
        //
        //        if (fechaDeNacimientoTextField.getText() == null || fechaDeNacimientoTextField.getText().length() == 0) {
        //            mensajeError += "Fecha de nacimiento no válida.\n";
        //        } else {
        //            if (!UtilidadDeFechas.fechaValida(fechaDeNacimientoTextField.getText())) {
        //                mensajeError += "Fecha de nacimiento no válida (debe tener formato dd/mm/yyyy).\n";
        //            }
        //        }
        //Si no hay errores devuelvo true, si no, una alerta con los errores y false
        if (cont == 0) {
            return true;
        } else {
            //Muestro alerta y devuelvo false
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Datos no válidos");
            alerta.setContentText("Por favor, corrige los errores");
            alerta.showAndWait();
            return false;
        }

    }

}
