package Model;

import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Producto {

    //////////////////////////////////////

    private final StringProperty foto;
    //////////////////////////////////////
    private final StringProperty nombre;
    private final StringProperty descripcion;
    private final DoubleProperty precio;
    private final IntegerProperty stock;
    //////////////////////////////////////
    private final StringProperty codigoBarras;
    //////////////////////////////////////
    private final ObjectProperty fechaAlta;
    private final ObjectProperty fechaModificacion;

    public Producto() {
        this("" , "", "", 0, 0, "", LocalDate.of(1974, 5, 14), LocalDate.of(2018, 6, 15));
    }

    public Producto(String foto, String nombre, String descripcion,
            double precio, int stock, String codigoBarras,
            Object fechaAlta, Object fechaModificacion) {
        this.foto = new SimpleStringProperty(foto);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.precio = new SimpleDoubleProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
        this.codigoBarras = new SimpleStringProperty(codigoBarras);
        this.fechaAlta = new SimpleObjectProperty(fechaAlta);
        this.fechaModificacion = new SimpleObjectProperty(fechaModificacion);
    }

    public String getFoto() {
        return foto.get();
    }

    public void setfoto(String foto) {
        this.foto.set(foto);
    }

    public StringProperty fotoProperty() {
        return foto;
    }

    ///////////////////////////////////////////////////////////////
    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    ///////////////////////////////////////////////////////////////
    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    ///////////////////////////////////////////////////////////////
    public double getPrecio() {
        return precio.get();
    }

    public void setPrecio(double precio) {
        this.precio.set(precio);
    }

    public DoubleProperty precioProperty() {
        return precio;
    }

    ///////////////////////////////////////////////////////////////
    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    ///////////////////////////////////////////////////////////////
    public String getCodigoBarras() {
        return codigoBarras.get();
    }

    public void setcCodigoBarras(String codigoBarras) {
        this.codigoBarras.set(codigoBarras);
    }

    public StringProperty codigoBarrasProperty() {
        return codigoBarras;
    }

    ///////////////////////////////////////////////////////////////
    public Object getFechaAlta() {
        return fechaAlta.get();
    }

    public void setFechaAlta(Object fechaAlta) {
        this.fechaAlta.set(fechaAlta);
    }

    public ObjectProperty fechaAltaProperty() {
        return fechaAlta;
    }

    ///////////////////////////////////////////////////////////////
    public Object getFechaModificacion() {
        return fechaModificacion.get();
    }

    public void setFechaModificacion(Object fechaModificacion) {
        this.fechaModificacion.set(fechaModificacion);
    }

    public ObjectProperty fechaModificacionProperty() {
        return fechaModificacion;
    }
}
