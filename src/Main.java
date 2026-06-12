import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Fora sistema = new Fora();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== BIENVENIDO AL SISTEMA FORA ===");
        while (true) {
            mostrarMenu();
            int opcion = leerEntero("Opción: ");

            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> registrarAlojamiento();
                case 3 -> registrarLugarInteres();
                case 4 -> crearPublicacion();
                case 5 -> agregarResenia();
                case 6 -> agregarReporteSeguridad();
                case 7 -> mostrarPublicaciones();
                case 8 -> buscarLugaresPorCategoria();
                case 9 -> mostrarPublicacionesFiltradas();
                case 0 -> {
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    public static void mostrarMenu() {
        System.out.println("\n------------------------------------");
        System.out.println("1. Registrar usuario            7. Mostrar publicaciones");
        System.out.println("2. Registrar alojamiento        8. Buscar lugar de interes por categoria");
        System.out.println("3. Registrar lugar de interes   9. Mostrar publicaciones con filtros");
        System.out.println("4. Crear publicación            0. Salir");
        System.out.println("5. Agregar reseña");
        System.out.println("6. Agregar reporte de Seguridad");
        System.out.println("------------------------------------");
    }

    public static void registrarUsuario() {
        System.out.println("\n--- REGISTRAR USUARIO ---");
        String nombre = leerLinea("Nombre: ");
        String correo = leerLinea("Correo: ");
        int telefono = leerEntero("Teléfono: ");

        Usuario usuario = new Usuario(nombre, correo, telefono);
        System.out.println(sistema.agregarUsuario(usuario) ? "Usuario registrado correctamente." : "Ya existe un usuario con ese correo.");
    }

    public static void registrarAlojamiento() {
        System.out.println("\n--- REGISTRAR ALOJAMIENTO ---");
        if (sistema.getUsuarios().isEmpty()) {
            System.out.println("Primero debe registrar usuarios.");
            return;
        }

        String ubicacion = leerLinea("Ubicación: ");
        String categoria = leerLinea("Categoría: ");

        System.out.println("\nSeleccione propietario:");
        mostrarUsuarios();

        int indiceUsuario = leerEntero("Selección: ");
        if (invalidIndex(indiceUsuario, sistema.getUsuarios().size())) return;

        Usuario propietario = sistema.getUsuarios().get(indiceUsuario);
        int resWifi = leerEntero("¿Tiene WiFi?\n0) Sí\n1) No\nSelección: ");
        int precio = leerEntero("Precio del alojamiento: ");

        Alojamiento alojamiento = new Alojamiento(ubicacion, categoria, propietario, precio);
        if (resWifi == 0) alojamiento.agregarWifi();

        System.out.println(sistema.agregarAlojamiento(alojamiento)
                ? "Alojamiento registrado."
                : "Ya existe un alojamiento con ese ID.");
    }

    public static void registrarLugarInteres() {
        System.out.println("\n--- AGREGAR LUGAR DE INTERÉS ---");
        String nombreLugar = leerLinea("Nombre: ");
        String categoriaLugar = leerLinea("Categoría: ");
        String direccionLugar = leerLinea("Dirección: ");
        String horarioAtencionLugar = leerLinea("Horario: ");

        LugarInteres lugar = new LugarInteres(nombreLugar, categoriaLugar, direccionLugar, horarioAtencionLugar);
        sistema.agregarLugarInteres(lugar);
        System.out.println("Lugar de interés registrado con éxito.");
    }

    public static void crearPublicacion() {
        System.out.println("\n--- CREAR PUBLICACIÓN ---");
        if (sistema.getAlojamientos().isEmpty()) {
            System.out.println("No existen alojamientos.");
            return;
        }

        mostrarAlojamientosConsola();
        int indiceAlojamiento = leerEntero("Seleccione alojamiento: ");
        if (invalidIndex(indiceAlojamiento, sistema.getAlojamientos().size())) return;

        Alojamiento seleccionado = sistema.getAlojamientos().get(indiceAlojamiento);
        System.out.println(sistema.agregarPublicacion(new Publicacion(seleccionado))
                ? "Publicación creada."
                : "Ese alojamiento ya tiene publicación.");
    }

    public static void agregarReporteSeguridad() {
        System.out.println("\n--- AGREGAR REPORTE DE SEGURIDAD ---");
        if (sistema.getAlojamientos().isEmpty()) {
            System.out.println("No existen alojamientos para reportar.");
            return;
        }

        mostrarAlojamientosConsola();
        int indiceAlojamiento = leerEntero("Seleccionar Alojamiento: ");
        if (invalidIndex(indiceAlojamiento, sistema.getAlojamientos().size())) return;

        int puntajeSeguridad = leerEntero("Puntaje de seguridad (1-10): ");
        int iluminacion = leerEntero("Iluminación (1-10): ");

        ReporteSeguridad reporte = new ReporteSeguridad(puntajeSeguridad, iluminacion);
        System.out.println(sistema.agregarReporteSeguridad(indiceAlojamiento, reporte)
                ? "Reporte agregado."
                : "No se pudo agregar el reporte. Verifique si el alojamiento tiene publicación.");
    }

    public static void mostrarPublicaciones() {
        System.out.println("\n--- PUBLICACIONES ---");
        if (sistema.getPublicaciones().isEmpty()) {
            System.out.println("No hay publicaciones creadas.");
            return;
        }

        for (int i = 0; i < sistema.getPublicaciones().size(); i++) {
            Publicacion p = sistema.getPublicaciones().get(i);
            Alojamiento al = p.getAlojamiento();
            Usuario prop = al.getUsuario();

            System.out.printf("[%d] ================================\n", i);
            System.out.printf("PROPIETARIO: %s (%s) Tel: %d%n", prop.getNombre(), prop.getCorreo(), prop.getTelefono());

            String tieneWifi = al.getWifi() ? "Sí" : "No";
            System.out.printf("ALOJAMIENTO: %s [%s] - Precio: $%d - WiFi: %s - Zona: %s%n",
                    al.getUbicacion(), al.getCategoria(), al.getPrecio(), tieneWifi, p.clasificarZona());

            System.out.println("\nSEGURIDAD:");
            if (p.getReportes().isEmpty()) {
                System.out.println("No existen reportes de seguridad.");
            } else {
                System.out.printf("Índice promedio: %.2f%n", p.calcularIndicePromedio());
                System.out.printf("Categoría de la zona: %s%n", p.clasificarZona());
            }

            System.out.println("\nRESEÑAS:");
            System.out.println("Promedio: " + al.promedioDeResenias() + "⭐");
            if (p.getAlojamiento().getResenias().isEmpty()) {
                System.out.println("No existen reseñas para esta publicación.");
            } else {
                for (Resenia r : p.getAlojamiento().getResenias()) {
                    System.out.printf("- [%d/10] %s%n", r.getPuntaje(), r.getComentario());
                }
            }

            System.out.println("==================================");
        }
    }

    private static void agregarResenia() {
        System.out.println("\n--- ELIGE UNA PUBLICACIÓN ---");
        if (sistema.getPublicaciones().isEmpty()) {
            System.out.println("No hay publicaciones disponibles para reseñar.");
            return;
        }

        mostrarPublicaciones();
        int indice = leerEntero("Opción (Índice): ");
        if (invalidIndex(indice, sistema.getPublicaciones().size())) return;

        Publicacion p = sistema.getPublicaciones().get(indice);
        int puntaje = leerEntero("Dame un puntaje del 1 - 10: ");
        String comentario = leerLinea("Dame un comentario con respecto a este alojamiento: ");

        p.getAlojamiento().agregarResenia(new Resenia(puntaje, comentario));
        System.out.println("Reseña agregada.");
    }

    private static void buscarLugaresPorCategoria() {
        System.out.println("\n--- BUSCAR LUGARES POR CATEGORÍA ---");
        String categoriaBuscada = leerLinea("Categoría a buscar: ");
        sistema.mostrarInformacionLugar(categoriaBuscada);
    }

    private static void mostrarUsuarios() {
        if (sistema.getUsuarios().isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (int i = 0; i < sistema.getUsuarios().size(); i++) {
            Usuario u = sistema.getUsuarios().get(i);
            System.out.printf("%d. %s (%s)%n", i, u.getNombre(), u.getCorreo());
        }
    }

    private static void mostrarAlojamientosConsola() {
        if (sistema.getAlojamientos().isEmpty()) {
            System.out.println("No hay alojamientos registrados.");
            return;
        }
        for (int i = 0; i < sistema.getAlojamientos().size(); i++) {
            Alojamiento al = sistema.getAlojamientos().get(i);
            System.out.printf("%d. %s [%s] - Propietario: %s%n", i, al.getUbicacion(), al.getCategoria(), al.getUsuario().getNombre());
        }
    }

    private static String leerLinea(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. " + mensaje);
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static boolean invalidIndex(int index, int size) {
        if (index < 0 || index >= size) {
            System.out.println("Selección inválida. El índice no existe.");
            return true;
        }
        return false;
    }

    private static void mostrarPublicacionesFiltradas(){
        ArrayList<Publicacion> publicacionesFiltradas;
        System.out.println("Elija la opcion de filtrado");
        System.out.println("0) Filtrar por precio");
        System.out.println("1) Filtrar por categoria");
        int opcion = leerEntero("opcion: ");


        if(opcion == 0){
            int precio = leerEntero("Dame un precio maximo: ");
            publicacionesFiltradas = sistema.filtrarPorPrecio(precio);
        } else {
            String categoria = leerLinea("Escribe una categoria: ");
            publicacionesFiltradas = sistema.filtrarPorCategoria(categoria);
        }
        for (int i = 0; i < publicacionesFiltradas.size(); i++) {
            Publicacion p = publicacionesFiltradas.get(i);
            Alojamiento al = p.getAlojamiento();
            Usuario prop = al.getUsuario();

            System.out.printf("[%d] ================================\n", i);
            System.out.printf("PROPIETARIO: %s (%s) Tel: %d%n", prop.getNombre(), prop.getCorreo(), prop.getTelefono());

            String tieneWifi = al.getWifi() ? "Sí" : "No";
            System.out.printf("ALOJAMIENTO: %s [%s] - Precio: $%d - WiFi: %s - Zona: %s%n",
                    al.getUbicacion(), al.getCategoria(), al.getPrecio(), tieneWifi, p.clasificarZona());

            System.out.println("\nSEGURIDAD:");
            if (p.getReportes().isEmpty()) {
                System.out.println("No existen reportes de seguridad.");
            } else {
                System.out.printf("Índice promedio: %.2f%n", p.calcularIndicePromedio());
                System.out.printf("Categoría de la zona: %s%n", p.clasificarZona());
            }

            System.out.println("\nRESEÑAS:");
            System.out.println("Promedio: " + al.promedioDeResenias() + "⭐");
            if (p.getAlojamiento().getResenias().isEmpty()) {
                System.out.println("No existen reseñas para esta publicación.");
            } else {
                for (Resenia r : p.getAlojamiento().getResenias()) {
                    System.out.printf("- [%d/10] %s%n", r.getPuntaje(), r.getComentario());
                }
            }

            System.out.println("==================================");
        }

    }
}