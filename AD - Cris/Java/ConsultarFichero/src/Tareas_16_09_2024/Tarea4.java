package Tareas_16_09_2024;

/***
 * La profesora de Acceso a Datos necesita ayuda para corregir un cuestionario de 20 preguntas tipo true/false. 
 * La respuesta correcta, los identificadores de los alumnos y sus respuestas se han guardado en un fichero de texto.

Este sería el contenido de un fichero de ejemplo test.txt:

TTTFFFTTTFTFTFTFTFTF

ABC76543 TTTFFFTTTFTFTFTFTFTF

ABC43526 TTTTTTTTTTTTTTTTTTTT

ABC12423 TTTFF TTTFTFTFTFTFTT

ABC12345 FFFFFFFFFFFFFFFFFFFF

Donde la 1ª fila contiene las respuestas correctas del cuestionario y las siguientes filas contienen el código de 
cada alumno y sus respuestas, una por fila. Cada respuesta correcta puntuará 0,5, cada respuesta incorrecta restará 0,15 y 
las respuestas en blanco no puntuarán ni penalizarán.

Escribe un programa que procese el fichero y para cada alumno (código identificador) se indique el resultado en su cuestionario.

Mejoras:

En lugar de mostrar la nota, indica su calificación dentro de esta escala:

    • Entre 10 y 8.5: excelente

    • Entre 8.49-7: notable

    • Entre 6-6.99: bien

    • Entre 5-5.99: aprobado

    • Entre 0-4.99: suspenso

 Añade una tabla resumen con el porcentaje de alumnos con cada calificación.

 * 
 */
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tarea4 {

    public static void main(String[] args) {

        Path p = Path.of("res/test.txt");

        int totalAlumnos = 0;
        int excelente = 0, notable = 0, bien = 0, aprobado = 0, suspenso = 0;

        String respuestasCorrectas = "TTTFFFTTTFTFTFTFTFTF";

        try (var lector = new Scanner(p)) {

            // Procesar las respuestas de los alumnos
            List<String> resultados = new ArrayList<>();
            while (lector.hasNextLine()) {
                String linea = lector.nextLine().trim(); // Elimina espacios al principio y final
                if (linea.isEmpty()) {
                    continue; // Saltar las líneas vacías
                }
                // Dividir la línea en dos partes: ID y respuestas del examen
                String[] partes = linea.split(" ", 2); // Dividir en dos, permitiendo espacios en las respuestas
                if (partes.length != 2) {
                    System.err.println("El alumno dejó el examen en blanco: " + linea);
                    continue; // Ignorar líneas mal formadas
                }

                String studentID = partes[0].trim();
                String studentRespuestas = partes[1].replaceAll("\\s", ""); // Reemplazar espacios en medio con cadenas vacías
                // Asegurarse de que las respuestas tengan 20 caracteres, rellenar respuestas
                // faltantes como incorrectas
                if (studentRespuestas.length() < respuestasCorrectas.length()) {
                    studentRespuestas = String.format("%-" + respuestasCorrectas.length() + "s", studentRespuestas)
                            .replace(' ', ' ');
                }

                double score = calcularNota(respuestasCorrectas, studentRespuestas);

                String calificacion = asignarCalificacion(score);

                resultados.add(studentID + " - Nota: " + String.format("%.2f", score) + " (" + calificacion + ")");

                totalAlumnos++;
                switch (calificacion) {
                    case "excelente":
                        excelente++;
                        break;
                    case "notable":
                        notable++;
                        break;
                    case "bien":
                        bien++;
                        break;
                    case "aprobado":
                        aprobado++;
                        break;
                    case "suspenso":
                        suspenso++;
                        break;
                }
            }

            // Mostrar los resultados de cada alumno
            System.out.println("\nResultados de los alumnos:");
            for (String resultado : resultados) {
                System.out.println(resultado);
            }

            // Mostrar el resumen porcentual de las calificaciones
            System.out.println("\nResumen de calificaciones:");
            if (totalAlumnos > 0) {
                System.out.println("Excelente: " + String.format("%.2f", (100.0 * excelente / totalAlumnos)) + "%");
                System.out.println("Notable: " + String.format("%.2f", (100.0 * notable / totalAlumnos)) + "%");
                System.out.println("Bien: " + String.format("%.2f", (100.0 * bien / totalAlumnos)) + "%");
                System.out.println("Aprobado: " + String.format("%.2f", (100.0 * aprobado / totalAlumnos)) + "%");
                System.out.println("Suspenso: " + String.format("%.2f", (100.0 * suspenso / totalAlumnos)) + "%");
            } else {
                System.out.println("No se encontraron alumnos en el archivo.");
            }

        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    public static double calcularNota(String correctas, String respuestasAlumno) {
        double puntos = 0;
        for (int i = 0; i < correctas.length(); i++) {
            char respuestaCorrecta = correctas.charAt(i);
            char respuestaAlumno = respuestasAlumno.charAt(i);

            if (respuestaAlumno == ' ') {
                puntos -= 0;
            } else if (respuestaCorrecta == respuestaAlumno) {
                puntos += 0.5;
            } else {
                puntos -= 0.15;
            }
        }
        return Math.max(0, puntos); // No se permiten puntuaciones negativas
    }

    public static String asignarCalificacion(double score) {
        if (score >= 8.5) {
            return "excelente";
        } else if (score >= 7.0) {
            return "notable";
        } else if (score >= 6.0) {
            return "bien";
        } else if (score >= 5.0) {
            return "aprobado";
        } else {
            return "suspenso";
        }
    }
}
