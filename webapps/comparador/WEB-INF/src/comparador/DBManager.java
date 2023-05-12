package comparador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager implements AutoCloseable {

    private Connection connection;

    public DBManager() throws SQLException, NamingException {
        connect();
    }

    private void connect() throws SQLException, NamingException {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("jdbc/Comparador");
        connection = ds.getConnection();
    }

    /**
     * Close the connection to the database if it is still open.
     *
     */
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }

    /**
     * Return a list with all the 12 first computers in the DB.
     * @param start
     * @param limit
     * @return
     */
    public List<Ordenador> getOrdenadores(int start, int limit) {
        List<Ordenador> ordenadores = new ArrayList<>();
        String query = "SELECT ordenadores.id AS id, ordenadores.modelo, marcas.nombre AS marca_nombre, procesadores.nombre AS procesador_nombre, memorias.tipo AS memoria_tipo, memorias.capacidad AS capacidad_ram, discos.tipo AS disco_tipo, discos.capacidad AS capacidad_disco, puntos_de_venta.tienda, puntos_de_venta.precio FROM ordenadores INNER JOIN marcas ON marca_id = marcas.id INNER JOIN procesadores ON procesador_id = procesadores.id INNER JOIN discos ON disco_id = discos.id INNER JOIN memorias ON memoria_id = memorias.id LEFT JOIN puntos_de_venta ON ordenadores.id = puntos_de_venta.ordenador_id ORDER BY id DESC LIMIT "+start+", "+limit+"";
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                Ordenador ordenador = new Ordenador();
                ordenador.setId(resultSet.getInt("id"));
                ordenador.setMarca(resultSet.getString("marca_nombre"));
                ordenador.setModelo(resultSet.getString("modelo"));
                ordenador.setProcesador(resultSet.getString("procesador_nombre"));
                ordenador.setMemoriaTipo(resultSet.getString("memoria_tipo"));
                ordenador.setMemoriaCapacidad(resultSet.getInt("capacidad_ram"));
                ordenador.setDiscoTipo(resultSet.getString("disco_tipo"));
                ordenador.setDiscoCapacidad(resultSet.getInt("capacidad_disco"));
                ordenador.setTienda(resultSet.getString("tienda"));
                ordenador.setPrecio(resultSet.getDouble("precio"));
                ordenadores.add(ordenador);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ordenadores;
    }
    
    /**
     * Return a Computer from his id
     *
     * @param id The id of the computer.
     * @return A computer from the database.
     * @throws SQLException If something fails with the DB.
     */
    public Ordenador getOrdenadorById(int id) throws SQLException {
        Ordenador ordenador = new Ordenador();
        String query = "SELECT ordenadores.modelo, marcas.nombre AS marca_nombre, procesadores.nombre AS procesador_nombre, memorias.tipo, memorias.capacidad AS capacidad_ram, discos.tipo, discos.capacidad AS capacidad_disco FROM ordenadores INNER JOIN marcas ON marca_id = marcas.id INNER JOIN procesadores ON procesador_id = procesadores.id INNER JOIN discos ON disco_id = discos.id INNER JOIN memorias ON memoria_id = memorias.id WHERE ordenadores.id = "
                + id;

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                String modelo = resultSet.getString("modelo");
                String marca = resultSet.getString("marca_nombre");
                String procesador = resultSet.getString("procesador_nombre");
                String memoria = resultSet.getString("memorias.tipo");
                String capacidad_ram = resultSet.getString("capacidad_ram");
                String disco = resultSet.getString("discos.tipo");
                String capacidad_disco = resultSet.getString("capacidad_disco");

                ordenador = new Ordenador();
                ordenador.setId(id);
                ordenador.setModelo(modelo);
                ordenador.setMarca(marca);
                ordenador.setProcesador(procesador);
                ordenador.setMemoriaTipo(memoria);
                ordenador.setMemoriaCapacidad(Integer.parseInt(capacidad_ram));
                ordenador.setDiscoTipo(disco);
                ordenador.setDiscoCapacidad(Integer.parseInt(capacidad_disco));

                return ordenador;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("SQLState: " + ex.getSQLState());
        }

        return null;
    }

    /**
     * Return a list with all the computers from a brand.
     *
     * @return List with all the books.
     * @throws SQLException If something fails with the DB.
     */
    public List<Ordenador> listOrdenadoresPorMarca(String brand) throws SQLException {
        String query = "SELECT ordenadores.id AS id, ordenadores.modelo, marcas.nombre AS marca_nombre FROM ordenadores INNER JOIN marcas ON marca_id = marcas.id WHERE marcas.nombre = '"
                + brand + "'";

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            List<Ordenador> ordenadores = new ArrayList<Ordenador>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String modelo = resultSet.getString("modelo");
                String marca = resultSet.getString("marca_nombre");
                Ordenador ordenador = new Ordenador();

                ordenador.setId(id);
                ordenador.setModelo(modelo);
                ordenador.setMarca(marca);
                ordenadores.add(ordenador);
            }
            return ordenadores;
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }
        return new ArrayList<Ordenador>();
    }

    /**
     * Return a list with all the computers that the user is searching.
     *
     * @return List with all the books.
     * @throws SQLException If something fails with the DB.
     */

    public List<Ordenador> searchOrdenadores(String marca, String procesador, String memoriaTipo, int memoriaCapacidad,
            String discoTipo, int discoCapacidad) throws SQLException {
        List<Ordenador> ordenadores = new ArrayList<>();

        String query = "SELECT ordenadores.id AS id, ordenadores.modelo, marcas.nombre AS marca_nombre, procesadores.nombre AS procesador_nombre, memorias.tipo AS memoria_tipo, memorias.capacidad AS capacidad_ram, discos.tipo AS disco_tipo, discos.capacidad AS capacidad_disco, puntos_de_venta.tienda, puntos_de_venta.precio FROM ordenadores INNER JOIN marcas ON marca_id = marcas.id INNER JOIN procesadores ON procesador_id = procesadores.id INNER JOIN discos ON disco_id = discos.id INNER JOIN memorias ON memoria_id = memorias.id LEFT JOIN puntos_de_venta ON ordenadores.id = puntos_de_venta.ordenador_id WHERE (? = 'cualquiera' OR marcas.nombre = ?) AND (? = 'cualquiera' OR procesadores.nombre = ?) AND (? = 'cualquiera' OR memorias.tipo = ?) AND (? = -1 OR memorias.capacidad = ?) AND (? = 'cualquiera' OR discos.tipo = ?) AND (? = -1 OR discos.capacidad = ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            // Esto es por no value specified for parameter 1 indica que no has establecido
            // valores para los parámetros de la consulta en tu función searchOrdenadores.
            // Antes de ejecutar la consulta con stmt.executeQuery(), debes establecer los
            // valores para los parámetros utilizando el método set correspondiente de
            // PreparedStatement.
            stmt.setString(1, marca);
            stmt.setString(2, marca);
            stmt.setString(3, procesador);
            stmt.setString(4, procesador);
            stmt.setString(5, memoriaTipo);
            stmt.setString(6, memoriaTipo);
            stmt.setInt(7, memoriaCapacidad);
            stmt.setInt(8, memoriaCapacidad);
            stmt.setString(9, discoTipo);
            stmt.setString(10, discoTipo);
            stmt.setInt(11, discoCapacidad);
            stmt.setInt(12, discoCapacidad);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String modelo = resultSet.getString("modelo");
                String marca_nombre = resultSet.getString("marca_nombre");
                String procesador_nombre = resultSet.getString("procesador_nombre");
                String memoria_tipo = resultSet.getString("memoria_tipo");
                int capacidad_ram = resultSet.getInt("capacidad_ram");
                String disco_tipo = resultSet.getString("disco_tipo");
                int capacidad_disco = resultSet.getInt("capacidad_disco");
                String tienda = resultSet.getString("tienda");
                double precio = resultSet.getDouble("precio");

                Ordenador ordenador = new Ordenador();
                ordenador.setId(id);
                ordenador.setModelo(modelo);
                ordenador.setMarca(marca_nombre);
                ordenador.setProcesador(procesador_nombre);
                ordenador.setMemoriaTipo(memoria_tipo);
                ordenador.setMemoriaCapacidad(capacidad_ram);
                ordenador.setDiscoTipo(disco_tipo);
                ordenador.setDiscoCapacidad(capacidad_disco);
                ordenador.setTienda(tienda);
                ordenador.setPrecio(precio);

                ordenadores.add(ordenador);
            }
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());
        }

        return ordenadores;
    }

    /**
     * Return a list with all the RAMs in the DB.
     *
     * @return List with all the books.
     * @throws SQLException If something fails with the DB.
     */
    public List<Ordenador> tiposMemoria() {
        String query = "SELECT * FROM memorias";

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            List<Ordenador> ordenadores = new ArrayList<Ordenador>();
            while (resultSet.next()) {
                String tipo = resultSet.getString("tipo");
                String capacidad = resultSet.getString("capacidad");
                Ordenador ordenador = new Ordenador();

                ordenador.setMemoriaTipo(tipo);
                ordenador.setMemoriaCapacidad(Integer.parseInt(capacidad));
                ordenadores.add(ordenador);
            }
            return ordenadores;
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }
        return new ArrayList<Ordenador>();
    }

    /**
     * Return a list with all the processors in the DB.
     *
     * @return List with all the books.
     * @throws SQLException If something fails with the DB.
     */
    public List<Ordenador> tiposProcesador() {
        String query = "SELECT * FROM procesadores";

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            List<Ordenador> ordenadores = new ArrayList<Ordenador>();

            while (resultSet.next()) {
                String tipo = resultSet.getString("nombre");
                Ordenador ordenador = new Ordenador();

                ordenador.setProcesador(tipo);
                ordenadores.add(ordenador);
            }
            return ordenadores;
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());
        }
        return new ArrayList<Ordenador>();
    }

    public List<Ordenador> tiposDisco() {
        String query = "SELECT * FROM discos";

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            List<Ordenador> ordenadores = new ArrayList<Ordenador>();

            while (resultSet.next()) {
                String tipo = resultSet.getString("tipo");
                String capacidad = resultSet.getString("capacidad");
                Ordenador ordenador = new Ordenador();

                ordenador.setDiscoTipo(tipo);
                ordenador.setDiscoCapacidad(Integer.parseInt(capacidad));
                ordenadores.add(ordenador);
            }
            return ordenadores;
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());
        }
        return new ArrayList<Ordenador>();
    }


       //FUNCIONES PARA AÑADIR, MODIFICAR Y ELIMINAR ORDENADORES

       public List<String> listMarcas() {

        String query = "SELECT * FROM marcas";

        try (Statement stmt = connection.createStatement()) {

            ResultSet resultSet = stmt.executeQuery(query);

            List<String> marcas= new ArrayList<String>();

            while (resultSet.next()) {
                String marca = resultSet.getString("nombre");
                marcas.add(marca);
            }

            return marcas;
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());
        }
        return new ArrayList<String>();
    }


    public List<String> listProcesadores() {

        String query = "SELECT * FROM procesadores";
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);

            List<String> procesadores= new ArrayList<String>();

            while (resultSet.next()) {
                String procesador = resultSet.getString("nombre");
                procesadores.add(procesador);
            }
            return procesadores;
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());
        }
        return new ArrayList<String>();
    }

    public List<String> listMemorias() {

        String query = "SELECT * FROM memorias";
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);

            List<String> memorias= new ArrayList<String>();

            while (resultSet.next()) {
                String tipo = resultSet.getString("tipo");
                String capacidad = resultSet.getString("capacidad");
                String memoria = tipo + "-" + capacidad;
                memorias.add(memoria);
            }
            return memorias;
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());
        }
        return new ArrayList<String>();
    }

    public List<String> listDiscos() {

        String query = "SELECT * FROM discos";
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);

            List<String> discos= new ArrayList<String>();

            while (resultSet.next()) {
                String tipo = resultSet.getString("tipo");
                String capacidad = resultSet.getString("capacidad");
                String disco = tipo + "-" + capacidad;
                discos.add(disco);
            }
            return discos;
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());
        }
        return new ArrayList<String>();
    }

    public String getMarcaId(String nombreMarca) throws SQLException {
        String id = "";

        String query = "SELECT id FROM marcas WHERE nombre = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombreMarca);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getString("id");
            }
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());
        }

        return id;
    }

    public String getProcesadorId(String nombreProcesador) throws SQLException {
        String id = "";

        String query = "SELECT id FROM procesadores WHERE nombre = ? LIMIT 1";

        System.out.println("EL PROCESADOR ES: " + nombreProcesador);

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombreProcesador);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getString("id");
            }
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());
        }

        return id;
    }

    public String getMemoriaId(String memoria) throws SQLException {
        String memoriaId = "";

        String tipoMemoria = obtenerPrimeraCadena(memoria);

        int capacidadMemoria = Integer.parseInt(obtenerSegundaCadena(memoria));

        String query = "SELECT id FROM memorias WHERE tipo = ? AND capacidad = ? LIMIT 1";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tipoMemoria);
            stmt.setInt(2, capacidadMemoria);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                memoriaId = resultSet.getString("id");
            }
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());
        }
        return memoriaId;
    }

    public String getDiscoId(String disco) throws SQLException {
        String discoId = "";

        String tipoDisco = obtenerPrimeraCadena(disco);

        int capacidadDisco = Integer.parseInt(obtenerSegundaCadena(disco));

        String query = "SELECT id FROM discos WHERE tipo = ? AND capacidad = ? LIMIT 1";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tipoDisco);
            stmt.setInt(2, capacidadDisco);
            
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                discoId = resultSet.getString("id");
            }
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());
        }
        return discoId;
    }


    public List<Ordenador> getOrdenadoresDB() throws SQLException {
        List<Ordenador> ordenadores = new ArrayList<>();

        String query = "SELECT ordenadores.id AS id, ordenadores.modelo, marcas.nombre AS marca_nombre, procesadores.nombre AS procesador_nombre, memorias.tipo AS memoria_tipo, memorias.capacidad AS capacidad_ram, discos.tipo AS disco_tipo, discos.capacidad AS capacidad_disco, puntos_de_venta.tienda, puntos_de_venta.precio FROM ordenadores INNER JOIN marcas ON marca_id = marcas.id INNER JOIN procesadores ON procesador_id = procesadores.id INNER JOIN discos ON disco_id = discos.id INNER JOIN memorias ON memoria_id = memorias.id LEFT JOIN puntos_de_venta ON ordenadores.id = puntos_de_venta.ordenador_id";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String modelo = resultSet.getString("modelo");
                String marca_nombre = resultSet.getString("marca_nombre");
                String procesador_nombre = resultSet.getString("procesador_nombre");
                String memoria_tipo = resultSet.getString("memoria_tipo");
                int capacidad_ram = resultSet.getInt("capacidad_ram");
                String disco_tipo = resultSet.getString("disco_tipo");
                int capacidad_disco = resultSet.getInt("capacidad_disco");
                String tienda = resultSet.getString("tienda");
                double precio = resultSet.getDouble("precio");

                Ordenador ordenador = new Ordenador();
                ordenador.setId(id);
                ordenador.setModelo(modelo);
                ordenador.setMarca(marca_nombre);
                ordenador.setProcesador(procesador_nombre);
                ordenador.setMemoriaTipo(memoria_tipo);
                ordenador.setMemoriaCapacidad(capacidad_ram);
                ordenador.setDiscoTipo(disco_tipo);
                ordenador.setDiscoCapacidad(capacidad_disco);
                ordenador.setTienda(tienda);
                ordenador.setPrecio(precio);

                ordenadores.add(ordenador);
            }
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());
        }

        return ordenadores;
    }

        // hacer que devuelva un ordenador si todo okey
    public void insertOrdenadorDB(String modelo, String marcaId, String procesadorId, String memoriaId, String discoId) throws SQLException {

        System.out.println("ENTRO A INSERTAR");

        Ordenador ordenador = new Ordenador();
        
        String query = "INSERT INTO ordenadores (modelo, marca_id, procesador_id, memoria_id, disco_id) VALUES (?, ?, ?, ?, ?)";

        System.out.println("modelo:" + modelo + ", marcaid:" + marcaId + ", procesadorId:" + procesadorId + ", memoriaId:" + memoriaId + ", discoId:" + discoId);

        PreparedStatement stmt = null;
        try {

            stmt = connection.prepareStatement(query);
            stmt.setString(1, modelo);
            stmt.setString(2, marcaId);
            stmt.setString(3, procesadorId);
            stmt.setString(4, memoriaId);
            stmt.setString(5, discoId);

            System.out.println(stmt);

            stmt.executeUpdate();

            System.out.println("TODO CORRECTO PARA EL MODELO:" + modelo+ ", Y YO QUE ME ALEGRO");

        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }

    }

    public void updateOrdenadorModelo(String id, String modelo) throws SQLException {
        String query = "UPDATE ordenadores SET modelo = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, modelo);
            stmt.setString(2, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                ex.printStackTrace();
                System.out.println("VendorError: " + ex.getErrorCode());
                System.out.println("SQLState: " + ex.getSQLState());
        }
    }

    public void updateOrdenadorMarca(String ordenadorId, String marcaId) throws SQLException {

        String query = "UPDATE ordenadores SET marca_id = ? WHERE id = ?";

        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, marcaId);
            stmt.setString(2, ordenadorId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("SQLState: " + ex.getSQLState());
        }
    }

    public void updateOrdenadorProcesador(String ordenadorId, String marcaId) throws SQLException {

        String query = "UPDATE ordenadores SET procesador_id = ? WHERE id = ?";

        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, marcaId);
            stmt.setString(2, ordenadorId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("SQLState: " + ex.getSQLState());
        }
    }

    public void updateOrdenadorMemoria(String ordenadorId, String memoriaId) throws SQLException {

        String query = "UPDATE ordenadores SET memoria_id = ? WHERE id = ?";

        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, memoriaId);
            stmt.setString(2, ordenadorId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("SQLState: " + ex.getSQLState());
        }
    }

    public void updateOrdenadorDisco(String ordenadorId, String memoriaId) throws SQLException {

        String query = "UPDATE ordenadores SET disco_id = ? WHERE id = ?";

        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, memoriaId);
            stmt.setString(2, ordenadorId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("SQLState: " + ex.getSQLState());
        }
    }

public void deleteOrdenadorDB(String id) throws SQLException {

        String query1 = "DELETE FROM puntos_de_venta WHERE ordenador_id = ? ";
        String query2 = "DELETE FROM ordenadores WHERE id = ? ";
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        try {

            stmt1 = connection.prepareStatement(query1);
            stmt1.setString(1, id);
            stmt1.executeUpdate();

            stmt2 = connection.prepareStatement(query2);
            stmt2.setString(1, id);
            stmt2.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }
       
    }

    public String obtenerPrimeraCadena(String memoria) {
        String tipo ="";
        String[] partes = memoria.split("-");
        tipo = partes[0];
        return tipo;
    }

    public String obtenerSegundaCadena(String memoria) {
        String capacidad ="";
        String[] partes = memoria.split("-");
        capacidad = partes[1];
        return capacidad;
    }


    /****************************************************************************
     * 
     * 
     * AQUI EMPIEZA LA PARTE DE USUARIOS
     * 
     * 
     *****************************************************************************/

    /**
     * Return a User account checking the name and the password
     *
     * @param contraseña and name of the user.
     * @return The user from the database.
     * @throws SQLException If something fails with the DB.
     */

    public Usuario getUsuarioDB(String nombre, String contrasena) throws SQLException {

        String query = "SELECT usuarios.id, usuarios.nombre, usuarios.correo, usuarios.contrasena FROM usuarios WHERE nombre = ? AND contrasena=PASSWORD(?)";
        Usuario usuario = new Usuario();
        PreparedStatement stmt = null;
        try {

            stmt = connection.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.setString(2, contrasena);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nombre");
                String cor = resultSet.getString("correo");
                String con = resultSet.getString("contrasena");
                usuario.setNombre(nom);
                usuario.setCorreo(cor);
                usuario.setContrasena(con);
                usuario.setId(id);

            }
            return usuario;
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }
        return new Usuario();
    }

    // hacer que devuelva un usuario si todo okey
    public Usuario insertUsuarioDB(String nombre, String contrasena, String email) throws SQLException {
        Usuario usuario = new Usuario();
        ;
        String query = "INSERT INTO usuarios (nombre, contrasena, correo) VALUES (?, PASSWORD(?), ?)";

        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.setString(2, contrasena);
            stmt.setString(3, email);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }
        usuario.setNombre(nombre);
        usuario.setCorreo(email);
        usuario.setContrasena(contrasena);
        usuario.setId(20);
        // cuando la otra funcion vaya llamamos a la consulta de usuario para que nos
        // devuelva un usuario completo con id correspondiente en vez de hacer nosotros
        // los set
        return usuario;

    }

    public List<Usuario> getUsuariosDB() throws SQLException {

        String query = "SELECT usuarios.id, usuarios.nombre, usuarios.correo, usuarios.contrasena FROM usuarios";

        List<Usuario> usuarios = new ArrayList<Usuario>();
        PreparedStatement stmt = null;
        try {

            stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nombre");
                String con = resultSet.getString("contrasena");
                String cor = resultSet.getString("correo");
                usuario.setNombre(nom);
                usuario.setCorreo(cor);
                usuario.setContrasena(con);
                usuario.setId(id);
                usuarios.add(usuario);

            }
            return usuarios;
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }
        return new ArrayList<Usuario>();
    }

    public void deleteUsuarioDB(String id) throws SQLException {

        String query = "DELETE FROM usuarios WHERE id = ? ";

        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }

    }

    public void changeNameUserDB(String id, String nombre) throws SQLException {

        String query = "UPDATE usuarios SET nombre = ? WHERE id = ? ";

        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.setString(2, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }

    }

    public void changePasswordUserDB(String id, String contrasena) throws SQLException {

        String query = "UPDATE usuarios SET contrasena = PASSWORD(?) WHERE id = ? ";

        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, contrasena);
            stmt.setString(2, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }

    }

    /****************************************************************************
     * 
     * 
     * AQUI EMPIEZA LA PARTE DE ADMINISTRADORES
     * 
     * 
     *****************************************************************************/

    public List<Usuario> getAdministradores() {
        String query = "SELECT * FROM usuarios u JOIN administradores a ON u.id = a.usuario_id ";
        List<Usuario> usuarios = new ArrayList<Usuario>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nombre");
                String con = resultSet.getString("contrasena");
                String cor = resultSet.getString("correo");
                usuario.setNombre(nom);
                usuario.setCorreo(cor);
                usuario.setContrasena(con);
                usuario.setId(id);
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());
        }

        return new ArrayList<Usuario>();
    }

    public void declararAdmin(String id){

        String query = "INSERT INTO administradores (usuario_id, es_admin) VALUES (?, 1);";

        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());
        }
    }

    public void deleteAdmin(String id){
        String query = "DELETE FROM administradores WHERE usuario_id = ? ";

        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }
    }


    public boolean isAdmin(String id){
        String query = "SELECT * FROM administradores WHERE usuario_id = ?";
    
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }
            }
    
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());
        }
        return false;
    }




      
    /****************************************************************************
     * 
     * 
     * AQUI EMPIEZA LA PARTE DE PUNTOS DE VENTA
     * 
     * 
     *****************************************************************************/

    /**
     * Return a User account checking the name and the password
     *
     * @param contraseña and name of the user.
     * @return The user from the database.
     * @throws SQLException If something fails with the DB.
     */

     
    public List<PuntosVenta> getPuntosVentaDB() throws SQLException {

        String query = "SELECT id, tienda, direccion FROM puntos_de_venta_sin_ordenador";

        List<PuntosVenta> puntos = new ArrayList<PuntosVenta>();
        PreparedStatement stmt = null;
        try {

            stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                PuntosVenta punto = new PuntosVenta();
                int id = resultSet.getInt("id");
                String tienda = resultSet.getString("tienda");
                String direccion = resultSet.getString("direccion");
                punto.setTienda(tienda);
                punto.setDireccion(direccion);
                punto.setId(id);
                puntos.add(punto);
                

            }
            return puntos;
        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }
        return new ArrayList<PuntosVenta>();
    }


    
    public void deletePuntoDB(String id) throws SQLException {

        String query = "DELETE FROM puntos_de_venta_sin_ordenador WHERE id = ? ";
      
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }
       
    }

    

    // hacer que devuelva un usuario si todo okey
    public PuntosVenta insertPuntoDB(String tienda, String direccion) throws SQLException {
        PuntosVenta punto = new PuntosVenta();
        
        String query = "INSERT INTO puntos_de_venta_sin_ordenador (tienda, direccion) VALUES (?, ?)";

        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, tienda);
            stmt.setString(2, direccion);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }
        punto.setTienda(tienda);
        punto.setDireccion(direccion);
        punto.setId(20);
        // cuando la otra funcion vaya llamamos a la consulta de usuario para que nos
        // devuelva un usuario completo con id correspondiente en vez de hacer nosotros
        // los set
        return punto;

    }

    
    public void changeNameShopDB(String id, String nombre) throws SQLException {

        String query = "UPDATE puntos_de_venta_sin_ordenador SET tienda = ? WHERE id = ? ";
      
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.setString(2, id);
            stmt.executeUpdate();
            System.out.println(" nombre tienda db" + nombre );


        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }
       
    }

    
    public void changeAddressShopDB(String id, String direccion) throws SQLException {

        String query = "UPDATE puntos_de_venta_sin_ordenador SET direccion = ? WHERE id = ? ";
      
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, direccion);
            stmt.setString(2, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(" SQLException : " + ex.getMessage());
            ex.printStackTrace();
            System.out.println(" VendorError : " + ex.getErrorCode());
            System.out.println(" SQLState : " + ex.getSQLState());

        }
       
    }
}


