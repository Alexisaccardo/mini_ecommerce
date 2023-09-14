import java.sql.*;
import java.util.Scanner;

public class ecommerce {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("*****WELCOME*****");

        String product = " ";

        String code = "";
        String code_buy = " ";
        String name = " ";
        String name_product = "";
        String direction = "";
        int count = 0;

        boolean aux = true;
        Select();

        while (aux) {

            System.out.println("1. Comprar: ");
            System.out.println("2. Visualizar estado: ");
            System.out.println("3. Modificar productos: ");
            System.out.println("4. Terminar");

            System.out.println("Ingrese un numero entre 1 - 4: ");
            int result = Integer.parseInt(scanner.nextLine());

            switch (result) {
                case 1:

                    System.out.println("Deseas realizar una compra?: ");
                    String buy = scanner.nextLine();

                    String status = null;
                    while (buy.equals("si")) {

                        System.out.print("Ingrese codigo del producto: ");
                        code = scanner.nextLine();

                        System.out.println("Ingrese codigo de compra: ");
                        code_buy = scanner.nextLine();

                        System.out.print("Ingrese nombre del producto: ");
                        name_product = scanner.nextLine();

                        product = product + name_product;

                        System.out.print("Ingrese su nombre: ");
                        name = scanner.nextLine();

                        System.out.print("Ingrese su direccion: ");
                        direction = scanner.nextLine();

                        System.out.print("Ingrese la cantidad a comprar: ");
                        count = Integer.parseInt(scanner.nextLine());

                        System.out.println("Ingrese el estado del pedido: ");
                        status = scanner.nextLine();

                        System.out.println("Deseas agregar otro producto?: ");
                        buy = scanner.nextLine();

                    }
                    int value2 = Select_two(code);

                    int count_avaliable = Select_One1(code);
                    int buy_avaliable = count_avaliable - count;
                    if (buy_avaliable >= 0) {
                        System.out.println(" El valor a pagar es: " + value2*count);
                        Editar2(code, buy_avaliable);
                    } else {
                        System.out.println("La compra excede la cantidad disponible");
                    }
                    Insert(code_buy, product, name, direction, count, status); //

                    break;

                case 2:
                    System.out.println("Ingrese codigo de consulta: ");
                    String code_consultation = scanner.nextLine();

                    Select_One(code_consultation);

                    break;

                case 3:
                    System.out.print("Ingrese codigo del producto: ");
                    code = scanner.nextLine();

                    System.out.print("Ingrese nuevo nombre del producto: ");
                    name_product = scanner.nextLine();

                    System.out.print("Ingrese nueva cantidad disponible: ");
                    String count_available = scanner.nextLine();

                    System.out.print("Ingrese nuevo su valor: ");
                    String value = scanner.nextLine();

                    Editar(code, name_product, count_available, value);

                    break;

                case 4:
                    System.out.println("Finalizando...");

                    aux = false;

                    break;

                default:
                    System.out.println("Ingrese un numero valido");
            }
        }
    }

    private static int Select_two(String code) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/productos";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM productos_disponibles WHERE codigo = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, code); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet.next()) {
            String codigo = resultSet.getString("codigo");
            int value2 = resultSet.getInt("valor");

            return value2;

        }

        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();

        return 0;
    }

    private static void Editar2(String code, int buy_avaliable) throws ClassNotFoundException, SQLException {


        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/productos";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consulta = "UPDATE productos_disponibles SET cantidad = ? WHERE codigo = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setInt(1, buy_avaliable);
        preparedStatement.setString(2, code);

        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Producto actualizado exitosamente");
        } else {
            System.out.println("No se encontró el registro para actualizar");
        }

        preparedStatement.close();
        connection2.close();
    }

    private static int Select_One1(String code) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/productos";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM productos_disponibles WHERE codigo = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, code); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet.next()) {
            String codigo = resultSet.getString("codigo");
            int cantidad = resultSet.getInt("cantidad");

            return cantidad;

        }

        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();

        return 0;

    }

    private static void Editar(String code, String name_product, String count_available, String value) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/productos";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consulta = "UPDATE productos_disponibles SET nombre = ?, cantidad = ?, valor = ? WHERE codigo = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, name_product);
        preparedStatement.setString(2, count_available);
        preparedStatement.setString(3, value);
        preparedStatement.setString(4, code);

        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Producto actualizado exitosamente");
        } else {
            System.out.println("No se encontró el registro para actualizar");
        }

        preparedStatement.close();
        connection2.close();
    }

    private static void Select_One(String code_consultation) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/productos";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM compra WHERE codigo = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, code_consultation); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet.next()) {
            String codigo = resultSet.getString("codigo");
            String estado = resultSet.getString("estado");

            System.out.println("Este es el codigo del producto a consultar: " + codigo + " y su estado es: " + estado);

        } else {
            System.out.println("No se encontró un registro con el codigo especificado.");
        }

        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();

    }

    private static void Insert(String code_buy, String name_product, String name, String direction, int count, String status) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/productos";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM compra");


            // Sentencia INSERT
            String sql = "INSERT INTO compra (codigo, producto, cliente, direccion, cantidad, estado) VALUES (?, ?, ?, ?, ?, ?)";

            // Preparar la sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code_buy);
            preparedStatement.setString(2, name_product);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, direction);
            preparedStatement.setInt(5, count);
            preparedStatement.setString(6, status);

            // Ejecutar la sentencia
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("producto agregado exitosamente.");
            } else {
                System.out.println("No se pudo agregar el producto.");
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void Select() throws ClassNotFoundException, SQLException {
        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/productos";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM productos_disponibles");

        while (resultSet2.next()) {
            String codigo = resultSet2.getString("codigo");
            String nombre = resultSet2.getString("nombre");
            int cantidad = resultSet2.getInt("cantidad");
            String valor = resultSet2.getString("valor");

            System.out.println("Este es el codigo: " + codigo + " Nombre: " + nombre + " Cantidad disponible: " + cantidad + " valor: " + valor);
        }
    }
    }
