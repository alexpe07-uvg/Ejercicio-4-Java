import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    private static void printMenu() {
        System.out.println("\nBienvenido al programa de alquiler de equipos de EnEscena");
        System.out.println("""
            =========MENU PRINCIPAL=========
            1. Registro de nuevo equipo
            2. Consulta de inventario
            3. Cotización de equipo para alquiler
            4. Alquiler de equipo
            5. Registro de devolución de equipo
            6. Reporte general
            7. Salir
            ================================
            """);
        System.out.println("Seleccione una opción:");
    }

    public static void main (String []args) {

        Scanner sc = new Scanner (System.in);
        Inventario inventario = new Inventario();

        int codeCotizar = 0; // variable usada tanto en cotización como en alquiler, se declara aquí para que sea accesible en ambos casos
        int diasCot = 0; // variable usada tanto en cotización como en alquiler, se declara aquí para que sea accesible en ambos casos
        boolean salir = false;

        while(!salir) {
            printMenu();
            int option = 0;
            boolean opcionValida = false;

            while (!opcionValida) {
                try {
                    option = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer del scanner
                    opcionValida = true;
                } catch (InputMismatchException e) {
                    System.out.println("Error: Debe ingresar un número entero. Intente de nuevo.");
                    sc.nextLine(); // Limpiar la entrada invalida
                    printMenu();
                }
            }

            switch(option) {
                case 1:
                    System.out.println("\n=========REGISTRO DE NUEVO EQUIPO=========");

                    int tipo = 0;
                    while (tipo < 1 || tipo > 3) {
                        System.out.println("\nIngrese el tipo de equipo (1: Camara, 2: Proyector, 3: Sonido):");
                        try {
                            tipo = sc.nextInt();
                            sc.nextLine(); // Limpiar el buffer del scanner

                            if (tipo < 1 || tipo > 3) {
                                System.out.println("Opción incorrecta. Ingrese 1, 2 o 3.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Tipo incorrecto. Seleccione una opción entre 1 y 3");
                            sc.nextLine(); // Limpia entrada inválida
                        } 
                    }      

                    int code = 0;
                    boolean codeValido = false;
                    while (!codeValido) {
                        System.out.println("\nIngrese el código del equipo (en números enteros positivos):");
                        try {
                            code = sc.nextInt();
                            sc.nextLine(); // Limpiar el buffer del scanner
                            
                            if (code <= 0) {
                                System.out.println("Código no válido. Este debe ser mayor a 0.");
                            } else if(inventario.existeCodigo(code)) {
                                System.out.println("Error: Ya existe un equipo con el código " + code + ". Pruebe ingresando otro.");
                            } else {
                                codeValido = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error. Vuelva a ingresar un codigo válido (solo números enteros positivos).");
                            sc.nextLine(); // Limpia entrada inválida
                        }
                    }

                    System.out.println("\nIngrese la marca del equipo:");
                    String brand = sc.nextLine();

                    System.out.println("\nIngrese el modelo del equipo:");
                    String model = sc.nextLine();

                    boolean available = true; // cualquier equipo nuevo comienza disponible

                    Equipo nuevoEquipo = null;
                    switch(tipo) {
                        case 1:
                            Camara camara = new Camara(code, brand, model, available);
                            boolean resolucionValida = false;

                            while (!resolucionValida) {
                                System.out.println("\nIngrese la resolución de la cámara en pixeles verticales (ej. 720, 1080, 2160):");
                                try {
                                    int resolution = sc.nextInt();
                                    sc.nextLine();
                                    // El setter devuelve true si es válida o false si imprime error
                                    resolucionValida = camara.setResolution(resolution);
                                    if (!resolucionValida) {
                                        System.out.println("Error: La resolución debe ser un número entero mayor a 0.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Resolución inválida. Ingrese solo números enteros para la resolución");
                                    sc.nextLine(); // limpia la entrada inválida
                                }
                            }
                            nuevoEquipo = camara;
                            break;

                        case 2:
                            Proyector proyector = new Proyector(code, brand, model, available);
                            boolean lumensValidos = false;

                            while(!lumensValidos) {
                                System.out.println("\nIngrese los lúmenes del proyector (número entero positivo):");
                                try{
                                    int lumens = sc.nextInt();
                                    sc.nextLine();
                                    lumensValidos = proyector.setLumens(lumens);
                                    if (!lumensValidos) {
                                        System.out.println("Error: Los lúmenes deben ser un número entero mayor a 0.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Error. Debe ingresar entradas enteras para los lúmenes");
                                    sc.nextLine(); // limpia entrada inválida
                                }
                            }

                            boolean wirelessValido = false;
                            while(!wirelessValido) {
                                System.out.println("\n¿Es inalámbrico? (true/false):");
                                try {
                                    boolean wireless = sc.nextBoolean();
                                    sc.nextLine();
                                    proyector.setWireless(wireless);
                                    wirelessValido = true;
                                } catch (InputMismatchException e) {
                                    System.out.println("Error: Debe ingresar 'true' o 'false' para indicar si es inalámbrico.");
                                    sc.nextLine(); // limpia entrada inválida
                                }
                            }
                            
                            nuevoEquipo = proyector;
                            break;

                        case 3:
                            Sonido sonido = new Sonido(code, brand, model, available);
                            boolean potenciaValida = false;

                            while(!potenciaValida) {
                                System.out.println("\nIngrese la potencia en kW del sonido (valor positivo):");
                                try {
                                    double powerKw = sc.nextDouble();
                                    sc.nextLine();
                                    potenciaValida = sonido.setPowerKw(powerKw);
                                    if (!potenciaValida) {
                                        System.out.println("Error: La potencia en kW debe ser un valor positivo.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Error: Debe ingresar un número válido para la potencia en kW.");
                                    sc.nextLine(); // limpia entrada inválida
                                }
                            }

                            nuevoEquipo = sonido;
                            break;

                        default:
                            System.out.println("Tipo de equipo no válido.");
                    }

                    if(nuevoEquipo != null) { // si no es null, se almacena el objeto en el inventario
                        if (inventario.registrarEquipo(nuevoEquipo)) {
                            System.out.println("Equipo registrado con éxito.");
                        } else {
                            System.out.println("Error: El equipo no puede ser nulo o ya existe en el inventario.");
                        }
                    } 
                    break;
        
                case 2:
                    System.out.print(inventario.mostrarInventario());
                    break;

                case 3:
                    System.out.println("\n=========COTIZACIÓN DE EQUIPO=========");

                    Equipo equipoCotizar = null;
                    boolean codeValido2 = false;
                    while(!codeValido2) {
                        System.out.println("Ingrese el código del equipo que desea cotizar:");
                        try {
                            codeCotizar = sc.nextInt();
                            sc.nextLine();
                            
                            if (codeCotizar <= 0) {
                                System.out.println("Código no válido. Este debe ser mayor a 0.");
                            } else {
                                equipoCotizar = inventario.buscarPorCodigo(codeCotizar);
                                if (equipoCotizar == null) {
                                    System.out.println("Error: No existe un equipo con el código " + codeCotizar + ". Pruebe ingresando otro.");
                                } else {
                                    codeValido2 = true;
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar un número entero para el código.");
                            sc.nextLine(); // Limpia entrada inválida
                        }
                    }
                    
                    boolean diasValidos = false;
                    while(!diasValidos) {
                        System.out.println("\nIngrese la cantidad de días para cotizar el alquiler:");
                        try {
                            diasCot = sc.nextInt();
                            sc.nextLine();
                            if (diasCot <= 0) {
                                System.out.println("Error: La cantidad de días debe ser un número entero positivo.");
                            } else {
                                diasValidos = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar un número entero para los días.");
                            sc.nextLine(); // Limpia entrada inválida
                        }
                    }

                    double costoTotal = inventario.cotizar(codeCotizar, diasCot);

                    System.out.println("\n=========RESUMEN DE COTIZACION DEL CLIENTE=========");
                    System.out.println("Detalles del equipo: " + equipoCotizar.toString());
                    System.out.println("Días de alquiler: " + diasCot);
                    
                    if (equipoCotizar.isAvailable()) {
                        System.out.println("Disponibilidad: Disponible inmediatamente para alquiler");
                    } else {
                        System.out.println("Disponibilidad: Ocupado. No disponible para alquiler en este momento.");
                    }

                    System.out.println("\nCosto total estimado Q: " + String.format("%.2f", costoTotal));
                    System.out.println("\nSi desea continuar con el alquiler (solo si hay disponibilidad), por favor diríjase a la sección de Alquiler de equipo en el menú principal.");
                    break;

                case 4:
                    System.out.println("=========ALQUILER DE EQUIPO=========");

                    int codeAlquilar = 0;
                    Equipo eqAlquilar = null;
                    boolean codeAlqValido = false;

                    while (!codeAlqValido) {
                        System.out.println("Ingrese el código del equipo que desea alquilar:");
                        try {
                            codeAlquilar = sc.nextInt();
                            sc.nextLine();

                            if (codeAlquilar <= 0) {
                                System.out.println("Código no válido. Este debe ser mayor a 0.");
                            } else {
                                eqAlquilar = inventario.buscarPorCodigo(codeAlquilar);
                                if (eqAlquilar == null) {
                                    System.out.println("Error: No existe un equipo con el código " + codeAlquilar + ". Pruebe ingresando otro.");
                                } else {
                                    codeAlqValido = true;
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar un número entero válido para el código.");
                            sc.nextLine(); // Limpiar la entrada incorrecta
                        }
                    }

                    if (!eqAlquilar.isAvailable()) {
                        System.out.println("Error: El equipo no existe o no está disponible para alquilar.");
                    } else {
                        int diasAlquilar = 0;
                        boolean diasAlqValidos = false;

                        while (!diasAlqValidos) {
                            System.out.println("\nIngrese la cantidad de días de alquiler:");
                            try {
                                diasAlquilar = sc.nextInt();
                                sc.nextLine();

                                if (diasAlquilar <= 0) {
                                    System.out.println("Error: La cantidad de días debe ser un número entero positivo.");
                                } else {
                                    diasAlqValidos = true;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Los días de alquiler deben ser un número entero.");
                                sc.nextLine(); // Limpiar la entrada incorrecta
                            }
                        }

                        double costoEstimado = inventario.cotizar(codeAlquilar, diasAlquilar);
                        System.out.println("Monto total a pagar: Q " + String.format("%.2f", costoEstimado));
                        
                        int confirmacion = 0;
                        boolean confValida = false;
                        while (!confValida) {
                            System.out.println("\n¿Desea confirmar el alquiler? (1: Sí, 2: No)");
                            try {
                                confirmacion = sc.nextInt();
                                sc.nextLine(); // Limpiar el buffer del scanner
                                if (confirmacion == 1 || confirmacion == 2) {
                                    confValida = true;
                                } else {
                                    System.out.println("Opción no válida. Ingrese 1 para Sí o 2 para No.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Debe ingresar 1 o 2.");
                                sc.nextLine(); // Limpiar la entrada incorrecta
                            }
                        }

                        if (confirmacion == 1) {
                            double costoFinal = inventario.alquilarEquipo(codeAlquilar, diasAlquilar);
                            if (costoFinal >= 0) {
                                System.out.println("\nSe ha confirmado su alquiler del equipo por Q " + String.format("%.2f", costoFinal) + " por " + diasAlquilar + " días.");
                            } else {
                                System.out.println("Error: El equipo no existe o no está disponible para alquilar.");
                            }
                        } else {
                            System.out.println("\nAlquiler cancelado. El equipo continúa disponible y no se generó cobro");
                        }
                    }
                    break;

                case 5:
                    System.out.println("=========REGISTRO DE DEVOLUCIÓN DE EQUIPO=========");

                    Equipo equipoDevolver = null;
                    int codeDevolver = 0;
                    boolean codeValido3 = false;

                    while(!codeValido3) {
                        System.out.println("Ingrese el código del equipo que se está devolviendo:");
                        try {
                            codeDevolver = sc.nextInt();
                            sc.nextLine();
                            
                            if (codeDevolver <= 0) {
                                System.out.println("Código no válido. Este debe ser mayor a 0.");
                            } else {
                                equipoDevolver = inventario.buscarPorCodigo(codeDevolver);
                                if (equipoDevolver == null) {
                                    System.out.println("El equipo con código " + codeDevolver + " no existe en el inventario.");
                                } else {
                                    codeValido3 = true;
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar un número entero válido para el código.");
                            sc.nextLine(); // Limpiar la entrada incorrecta
                        }
                    }

                    if (equipoDevolver.isAvailable()) {
                        System.out.println("El equipo con código " + codeDevolver + " ya está disponible en el inventario. Actualmente no cuenta con ningun alquiler activo.");
                    } else {
                        boolean devueltoExitoso = inventario.devolverEquipo(codeDevolver);
                        if (devueltoExitoso) {
                            System.out.println("Devolución registrada exitosamente. El equipo con código " + codeDevolver + " ahora está disponible para alquilar.");
                        }
                    }
                    break;

                case 6:
                    System.out.println("=========REPORTE GENERAL=========");
                    System.out.print(inventario.generarReporte());
                    break;

                case 7:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    return;
                
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        sc.close();
    }
}


