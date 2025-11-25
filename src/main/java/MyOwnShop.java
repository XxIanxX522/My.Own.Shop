import java.io.*;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;
public class  MyOwnShop {
    public static int i = 0;
    public static int e = 0;
    public static int[] cantidadventa = new int[100];
    public static int Log = 0;
    public static String[] producto = new String[100];
    public static String[] NomProd = new String[100];
    public static String[] arrventa = new String[100];
    public static double [] PrecProd = new double[100];
    public static double PrecProduc;
    public static String NomProduc;
    public static int[] ExistProd = new int[100];
    public static double[] PSug = new double[100];

    public static void main(String[] args) {
        // ------------------Menú------------------//
        cargararchivo();
        Scanner sc = new Scanner(System.in);
        System.out.println( " _____ ______       ___    ___      ________  ___       __   ________           ________  ___  ___  ________  ________   \n" +
                "|\\   _ \\  _   \\    |\\  \\  /  /|    |\\   __  \\|\\  \\     |\\  \\|\\   ___  \\        |\\   ____\\|\\  \\|\\  \\|\\   __  \\|\\   __  \\  \n" +
                "\\ \\  \\\\\\__\\ \\  \\   \\ \\  \\/  / /    \\ \\  \\|\\  \\ \\  \\    \\ \\  \\ \\  \\\\ \\  \\       \\ \\  \\___|\\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \n" +
                " \\ \\  \\\\|__| \\  \\   \\ \\    / /      \\ \\  \\\\\\  \\ \\  \\  __\\ \\  \\ \\  \\\\ \\  \\       \\ \\_____  \\ \\   __  \\ \\  \\\\\\  \\ \\   ____\\\n" +
                "  \\ \\  \\    \\ \\  \\   \\/  /  /        \\ \\  \\\\\\  \\ \\  \\|\\__\\_\\  \\ \\  \\\\ \\  \\       \\|____|\\  \\ \\  \\ \\  \\ \\  \\\\\\  \\ \\  \\___|\n" +
                "   \\ \\__\\    \\ \\__\\__/  / /           \\ \\_______\\ \\____________\\ \\__\\\\ \\__\\        ____\\_\\  \\ \\__\\ \\__\\ \\_______\\ \\__\\   \n" +
                "    \\|__|     \\|__|\\___/ /             \\|_______|\\|____________|\\|__| \\|__|       |\\_________\\|__|\\|__|\\|_______|\\|__|   \n" +
                "                  \\|___|/                                                         \\|_________|                           \n" +
                "                                                                                                           " );
        System.out.println( "----------------------------------------------------------------------------------------------------------------------------" );
        int opcMenu = 0;
        while ( opcMenu != 4 ) {
            System.out.println("¿A qué módulo deseas ingresar?\n1. Inventario\n2. Ventas\n3. Estadisticas\n4. Salir");
            opcMenu = sc.nextInt();
            switch (opcMenu) {
                case 1:
                    Inventario();
                    break;
                case 2:
                    Ventas();
                    break;
                case 3:
                    Estadisticas();
                    break;
                case 4:
                    System.out.println( "Saliendo..." );
                default:
                    break;
            }
        }
    }

    private static void Estadisticas() {
        //--------Ingreso al módulo de estadisticas----------------//
        //---Login básico para que solo el administrador pueda entrar---//
        while ( Log == 0 ){
            LoginBasico();
        }
        Scanner sc = new Scanner(System.in);
        int seleccion=0;
        while ( seleccion != 4) {
            System.out.println("¿A dónde quieres ir?");
            System.out.println("[1] Reporte de ventas.");
            System.out.println("[2] Datos del producto.");
            System.out.println("[3] Competidores.");
            System.out.println("[4] Salir.");
            seleccion = sc.nextInt();
            sc.nextLine();
            switch (seleccion) {
                case 1:
                    ReportaVentas();
                    break;
                case 2:
                    DatosDelProducto();
                    break;
                case 3:
                    Competidores();
                    break;
                case 4:
                    System.out.println("Saliendo.....");
                    break;
                default:
                    System.out.println("Elige una opción correcta.");
                    break;
            }
        }
    }

    private static void Competidores()
    {
        //---Ingreso al submódulo de competidores---//
        Scanner sc = new Scanner(System.in);
        int seleccionado;
        System.out.println("¿Deseas comparar los precios de los competidores con los tuyos?");
        System.out.println("1. Sí\n2. No" );
        seleccionado = sc.nextInt();
        sc.nextLine();
        if (seleccionado == 1)
        {
          precioCOMP ();
        }
    }

    private static void precioCOMP()
    {
        //---Método para comparar los precios con los de los competidores---//
        Scanner sc = new Scanner(System.in);
        int ComPre;
        int MyPre;
        int[] PrecCom = new int[100];
        int[] PrecMY = new int[100];
        int CONT = 0;
        int otroprec;
        do {
            System.out.println("ingresa el precio del producto de tu competidor");
            ComPre = sc.nextInt();
            System.out.println("Ingresa el precio de tu producto ");
            MyPre = sc.nextInt();
            PrecCom[CONT] = ComPre - MyPre;
            CONT++;
            System.out.println("¿Deseas ingresar otro precio?\n1. Sí\n2.No");
            otroprec = sc.nextInt();
           }while(otroprec ==1);
        for (int h = 0; h < CONT; h++)
        {
            System.out.println("La diferencia que hay entre tu precio y el de tu competidor es de $"+PrecCom[h]+ " pesos");
        }
          Estadisticas();
    }

    private static void DatosDelProducto()
    {
        int elegProd = 0;
        while ( elegProd != 3) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Elige qué productos quieres visualizar");
            System.out.println("[1] Más vendidos");
            System.out.println("[2] Menos vendidos");
            System.out.println("[3] Regresar");
            elegProd = sc.nextInt();

            switch (elegProd) {
                case 1:
                    MasVendidos();
                    break;
                case 2:
                    MenosVendidos();
                    break;
                case 3:
                    Estadisticas();
                default:
                    System.out.println("Ingresa opciones válidas");
                    break;
            }
        }
    }

    private static void MenosVendidos()
    {
        System.out.println("Elegir que productos quieres ver 1.");

    }

    private static void MasVendidos() {
        System.out.println("Mostrando productos más vendidos...");
    }

    private static void ReportaVentas() {
        Scanner sc = new Scanner(System.in);
        int opcDeTiempo = 0;
        while ( opcDeTiempo != 3) {
            System.out.println("¿En qué rango de tiempo deseas ver?");
            System.out.println("[1] Semanal");
            System.out.println("[2] Mensual");
            System.out.println("[3] Salir");
            opcDeTiempo = sc.nextInt();
            sc.nextLine();
            switch (opcDeTiempo) {
                case 1:
                    ReporteSemanal();
                    break;
                case 2:
                    ReporteMensual();
                    break;
                default:
                    System.out.println("Ingresa opciones válidas.");
            }
        }
    }

    private static void ReporteMensual()
    {
        Scanner sc = new Scanner(System.in);
        int vent = 0;
        int[] ventasemanal = new int[ 4 ];
        int TotalVenta = 0;
        System.out.println("Ingrese el código del producto que desee regsitrar para la venta mensual");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.println("Producto: " + NomProd[codigo]);
        System.out.println("----------------------------------------");
        String[] semanas = {"Semana 1", "Semana 2", "Semana 3", "Semana 4"};
        for (int j = 0; j < semanas.length; j++) {
            System.out.println("Ingrese la venta del producto durante la " + semanas[j]);
            ventasemanal[vent] = sc.nextInt();
            sc.nextLine();
            TotalVenta += ventasemanal[vent];
            vent++;
        }
        System.out.println( "La venta semanal del producto: " + NomProd [ codigo ] + " es de: " + TotalVenta);
    }

    private static void ReporteSemanal() {
        Scanner sc = new Scanner(System.in);
        int vent = 0;
        int[] ventadiaria = new int[ 7 ];
        int TotalVenta = 0;
        System.out.println("Ingrese el código del producto que desee regsitrar la venta semanal");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.println("Producto: " + NomProd[codigo]);
        System.out.println("----------------------------------------");
        String[] dia = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        for (int j = 0; j < dia.length; j++) {
            System.out.println("Ingrese la venta del producto durante el día " + dia[ j ]);
            ventadiaria[ vent ] = sc.nextInt();
            sc.nextLine();
            TotalVenta += ventadiaria [ vent ];
            vent++;
        }
        System.out.println( "La venta semanal del producto: " + NomProd [ codigo ] + " es de: " + TotalVenta);
    }

    private static void Ventas() {
        Scanner sc = new Scanner(System.in);
        int opcVentas = 0;
        while ( opcVentas != 3 ) {
            System.out.println("Bienvenido al módulo de ventas, ¿qué desea realizar?\n 1. Ir al reporte de ventas\n 2. Ir a la calculadora\n 3. Salir");
            opcVentas = sc.nextInt();
            sc.nextLine();
            switch (opcVentas) {
                case 1:
                    ReportedeVentas();
                    break;
                case 2:
                    Calculadora();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    private static void Calculadora() {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        double[] suma =  new double[ 30 ];
        int o = 0;
        while ( o == 0 ){
            System.out.print("Ingrese el valor del producto: ");
            suma[ count ] = sc.nextDouble();
            count++;
            System.out.println( "¿Deseas agregar más productos?\n1. Si\n2. No" );
            int opcCalculadora = sc.nextInt();
            if ( opcCalculadora == 2 ){
                o = 1;
            }
        }
        double total = 0;
        for (int j = 0; j < count; j++) {
            total += suma[ j ];
        }
        System.out.println( "El total a pagar es: " + total);
    }

    private static void ReportedeVentas()
    {
        int vent = 0;
        int Código = 0;
        System.out.println("Bienvenido al reporte de ventas");
        int desci;
        do
        {
            System.out.println("Por favor ingresa el código del producto vendido:");
            Scanner scanner = new Scanner(System.in);
            Código = scanner.nextInt();
            scanner.nextLine();
            NomProduc = NomProd [ Código ];
            PrecProduc = PSug[ Código ];
            System.out.println("¿deseas ingresar otro producto?\n1. si\n2. no");
            desci = scanner.nextInt();
            scanner.nextLine();
            arrventa[ vent ] = NomProduc +" "+ PrecProduc + ";";
            vent++;
        }
        while (desci==1);
        for (e = 0; e < vent; e++)
        {
            System.out.println(arrventa[ e ]);
        }
        cantidadventa [ Código ]++;
        guardarreporte();
    }

    private static void Inventario() {
        Scanner sc = new Scanner(System.in);
        while ( Log == 0 ){
            LoginBasico();
        }
        int opcInventario = 0;
        while (opcInventario != 4 ) {
            System.out.println("¿Qué desea realizar?\n1. Ver productos\n2. Eliminar productos\n3. Gestionar inventario\n4. Salir");
            opcInventario = sc.nextInt();
            switch (opcInventario) {
                case 1:
                    Productos();
                    break;
                case 2:
                    EliminarProductos();
                    break;
                case 3:
                    GestionarInventario();
                    break;
                case 4:
                    System.out.println( "Saliendo..." );
                    break;
                default:
                    break;
            }
        }
    }

    private static void GestionarInventario(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el código del producto:");
        int codigo= sc.nextInt();
        sc.nextLine();
        System.out.println(producto[ codigo]);
        if (producto [codigo] ==null)
        {
            System.out.println( "Producto no encontrado." );
            return;
        }
        System.out.println("¿Deseas actualizar el precio del producto?\n[1] Sí\n[2] No");
        int opcactualizar= sc.nextInt();
        sc.nextLine();
        switch (opcactualizar)
        {
            case 1: System.out.print( "Ingrese el nuevo precio del producto: ");
                PrecProd[ codigo ] = sc.nextDouble();
                break; case 2: System.out.println("Saliendo.........");
            break;
            default:
                break;
        }
        guardararchivo();
    }

    private static void EliminarProductos() {
        Scanner sc = new Scanner(System.in);
        System.out.print( "Ingresa el código del producto que desees borrar: ");
        int codigo = sc.nextInt();
        sc.nextLine();
            if (codigo < 0 || codigo >= i){
                System.out.println( "Producto no encontrado." );
                return;
            }
        System.out.println( "¿Estas seguro?\n1. Sí\n2. No" );
        int estado = sc.nextInt();
        sc.nextLine();
        if (estado == 1){
        for (int j = codigo; j < i - 1; j++) {
            NomProd[ j ] = NomProd[ j + 1 ];
            PrecProd[ j ] = PrecProd[ j + 1 ];
            ExistProd[ j ] = ExistProd[ j + 1 ];
            PSug[ j ] = PSug[ j + 1 ];
            producto[ j ] = "Código: "+ j + " | " + "Nombre: " + NomProd[ j ] + " | " + "Precio: " + PrecProd[ j ] + " | " + "Cantidad: " + ExistProd[ j ] + " | " + "Precio sugerido: " + PSug[ j ];
        }
            NomProd[i - 1] = null;
            PrecProd[i - 1] = 0;
            ExistProd[i - 1] = 0;
            PSug[i - 1] = 0;
            producto[ i - 1] = null;
            i--;
        }
        System.out.println( "Producto eliminado correctamente" );
        guardararchivo();
    }

    private static void Productos() {
        Scanner sc = new Scanner(System.in);
        int j = 0;
        int k = 0;
        System.out.println("¿Qué deseas hacer?\n1. Ver los productos registrados\n2. Agregar productos");
        int opcInventario = sc.nextInt();
        sc.nextLine();
        if (opcInventario == 2) {
            while (j == 0) {
                System.out.println("Ingresa el nombre del producto: ");
                NomProd[i] = sc.nextLine();
                System.out.println("Ingresa el precio del producto: ");
                PrecProd[i] = sc.nextInt();
                sc.nextLine();
                PSug[i] = (double) (PrecProd[i] + (PrecProd[i] * 0.30));
                System.out.println("Ingresa el número de existencias del producto: ");
                ExistProd[i] = sc.nextInt();
                sc.nextLine();
                producto[i] = "Código: " + i + " | " + "Nombre: " + NomProd[i] + " | " + "Precio: " + PrecProd[i] + " | " + "Cantidad: " + ExistProd[i] + " | " + "Precio sugerido: " + PSug[i] + " | " + "Veces vendido: " + cantidadventa [ i ];
                System.out.println("¿Desea agregar más prodcutos?\n1. Si\n2. No");
                int Elec = sc.nextInt();
                sc.nextLine();
                if (Elec == 2) {
                    j++;
                }
                i++;
            }
            for (k = 0; k < i; k++) {
                System.out.println(producto[ k ]);
            }
            guardararchivo();
        } else {
            if ( producto [ k ] == null){
                System.out.println( "No hay nngún producto registrado" );
            } else {
                for (k = 0; k < i; k++) {
                    System.out.println(producto[ k ]);
                }
            }
        }
        if ( opcInventario != 1  && opcInventario != 2){
            System.out.println( "Opción inválida" );
            return;
        }
    }

    private static void LoginBasico( ) {
        Scanner sc = new Scanner(System.in);
        Log = 0;
        String usuarioCorrecto = "admin";
        String passwordCorrecta = "1234";
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();
        if (usuario.equals(usuarioCorrecto) && pass.equals(passwordCorrecta)) {
            System.out.println("Acceso permitido.");
            Log = 1;
            
        }
        else
        {
            BadPasword();

        }
    }

    private static void BadPasword()
    {
        System.out.println("Usuario o contraseña incorrectos.");
        System.out.println("¿Deseas salir?\n[1] Sí\n[2] No\"");
        Scanner scanner = new Scanner(System.in);
        int z = scanner.nextInt();
        if (z == 1)
        {
            int opcmenu;
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("¿A qué módulo deseas ingresar?\n1. Inventario\n2. Ventas\n3. Estadisticas\n4. Salir");
            opcmenu = scanner1.nextInt();
            switch (opcmenu)
            {
                case 1:
                    Inventario();
                    break;
                case 2:
                    Ventas();
                    break;
                case 3:
                    Estadisticas();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                default:
                    break;
            }

        }
    }

    private static void cargararchivo(){
        try {
            File file = new File("Productos.txt");
            if (!file.exists()) {
                System.out.println("No hay archivo de productos aún.");
                return;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String linea;
            i = 0;
            br.mark(500);
            linea = br.readLine();
            if (linea != null && linea.startsWith("Código:")) {
                br.reset();
            }
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(" \\| ");
                if (partes.length != 5) continue;
                int codigo = Integer.parseInt(partes[0].replace("Código:", "").trim());
                String nombre = partes[1].replace("Nombre:", "").trim();
                double precio = Double.parseDouble(partes[2].replace("Precio:", "").trim());
                int cantidad = Integer.parseInt(partes[3].replace("Cantidad:", "").trim());
                double sugerido = Double.parseDouble(partes[4].replace("Precio sugerido:", "").trim());
                NomProd[i] = nombre;
                PrecProd[i] = precio;
                ExistProd[i] = cantidad;
                PSug[i] = sugerido;
                producto[i] = linea;
                i++;
            }
            br.close();
            System.out.println("Productos cargados correctamente desde el archivo.");
        } catch (Exception e) {
            System.out.println("Error al cargar productos: " + e.getMessage());
        }
    }

    private static void guardararchivo() {
        try {
            FileWriter fw = new FileWriter("Productos.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println("=== LISTA DE PRODUCTOS REGISTRADOS ===");
            for (int j = 0; j < i; j++) {
                if (producto[j] != null && !producto[j].isEmpty())
                    pw.println(producto[j]);
            }
            pw.close();
            System.out.println("Producto guardado correctamente");
        } catch (Exception e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    private static void cargarreporte(){
        try {
            File file = new File( "Reporte.txt" );
            if ( !file.exists() ){
                System.out.println( "Archivo no encontrado" );
                return;
            }
            BufferedReader bf = new BufferedReader( new FileReader( file ) );
            String linea;
            linea = bf.readLine();
            while ( linea != null ) {
                if (linea.trim().isEmpty()) continue;
                String[] parts = linea.split(";");
                if (parts.length != 2) continue;
                String nombre = parts[0].replace("Nombre: ", "").trim();
                double precio = Double.parseDouble( parts [ 1 ].replace( "Precio: ", "").trim());
                NomProduc = nombre;
                PrecProduc = precio;
                arrventa [ e ] = linea;
                e++;
            }
            bf.close();
            System.out.println( "Registro cargado correctamente" );
        } catch ( Exception e){
            System.out.println( "Error al cargar el reporte: " + e.getMessage());
        }
    }

    private static void guardarreporte(){
        try {
            FileWriter fw = new FileWriter( "Reporte.txt" );
            PrintWriter pw = new PrintWriter( fw );
            for (int j = 0; j < e; j++) {
                if ( arrventa[ j ] != null && !arrventa[ j ].isEmpty())
                    pw.println(arrventa[ j ]);
            }
            pw.close();
        } catch ( Exception e){
            System.out.println( "Error: " + e.getMessage());
        }
    }
}