# Ejercicio 4

### Requisitos Funcionales

1. No pueden haber dos equipos con el mismo código en el inventario
2. Al registrar un equipo, este está inmediatamente disponible
3. Poder buscar equipos por su código
4. Poder cotizar un equipo en base al equipo y la duración
6. Confirmar una cotización (confirmar alquiler)
5. No se puede alquilar un equipo que ya esté apartado
7. Registrar devoluciones
8. Obtener un reporte general
9. Tarifas, luminosidad, resoluución, potencia: todas deben ser mayor a cero




abstract class Equipo
- código
- marca
- modelo
- tarifa diaria
- disponible
- 

Proyector extends Equipo
- lumenes
- inalambrico

Cámara
- resolución

Sonido
- potencia

Inventario
- lista<Equipo>
- Map<String, Equipo> equipos // nombre común de diccionarios
- registrarEquipo()
- consultarEquipo(codigoEquipo)
- cotizar(codigoEquipo, duracionDias)
- confirmarCotizacion()
- devolverEquipo(codigoEquipo)


Compilación y ejecución
Para compilar se utiliza el código: javac -d bin src/*.java
Para ejecutar el programa se utiliza el código : java -cp bin Main



