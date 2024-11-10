package org.example.Model;

import org.example.View.Messages;

import java.sql.*;

public class SQLPeliculas {

    Messages msg = new Messages();

    public void showAllFilms(Connection con) throws SQLException {
        String id, titulo, actor, guion;
        Boolean disponible;
        Pelicula.Tematica tematica;

        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM pelicula");

            while (resultSet.next()) {
                id = resultSet.getString("identificador");
                titulo = resultSet.getString("titulo");
                actor = resultSet.getString("actor");
                guion = resultSet.getString("guion");
                disponible = resultSet.getBoolean("disponible");
                String tematicaStr = resultSet.getString("tematica");

                // Convertir la cadena de tematica a la enumeración
                tematica = Pelicula.Tematica.valueOf(tematicaStr);

                System.out.println(msg.showAllPeliculas(
                        Integer.parseInt(id),
                        titulo,
                        actor,
                        guion,
                        disponible,
                        tematica
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error en la consulta a la base de datos: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error al convertir la temática: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }


    public void showFilm(Connection con, int idNumerico) {
        String query = "SELECT * FROM pelicula WHERE identificador = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, idNumerico);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("identificador");
                String titulo = resultSet.getString("titulo");
                String actor = resultSet.getString("actor");
                String guion = resultSet.getString("guion");
                boolean disponible = resultSet.getBoolean("disponible");
                String tematicaStr = resultSet.getString("tematica");

                // Convertir la cadena de temática a la enumeración
                Pelicula.Tematica tematica = Pelicula.Tematica.valueOf(tematicaStr);

                System.out.println(msg.showAllPeliculas(
                        id,
                        titulo,
                        actor,
                        guion,
                        disponible,
                        tematica
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error en la consulta a la base de datos: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error al convertir la temática: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    public int cntFilms(Connection con) {
        int count = 0;
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM pelicula");

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return count;
    }

    public boolean delFilm(Connection con, int idNumerico) {
        String query = "DELETE FROM pelicula WHERE identificador = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, idNumerico);

            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println("Se han borrado: " + rowsAffected + " filas!");
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error en la consulta a la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
        return false;
    }

    public boolean updateFilm(Connection con, int idNumerico, String titulo, String actor,
                               Pelicula.Tematica tematica, String guion, Boolean disponible) {
        StringBuilder query = new StringBuilder("UPDATE pelicula SET ");
        boolean setClauseAdded = false;

        if (titulo != null) {
            query.append("titulo = ?, ");
            setClauseAdded = true;
        }
        if (actor != null) {
            query.append("actor = ?, ");
            setClauseAdded = true;
        }
        if (tematica != null) {
            query.append("tematica = ?, ");
            setClauseAdded = true;
        }
        if (guion != null) {
            query.append("guion = ?, ");
            setClauseAdded = true;
        }
        if (disponible != null) {
            query.append("disponible = ?, ");
            setClauseAdded = true;
        }

        if (!setClauseAdded) {
            return false;
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE identificador = ?");

        try (PreparedStatement preparedStatement = con.prepareStatement(query.toString())) {
            int index = 1;

            if (titulo != null) {
                preparedStatement.setString(index++, titulo);
            }
            if (actor != null) {
                preparedStatement.setString(index++, actor);
            }
            if (tematica != null) {
                preparedStatement.setString(index++, tematica.toString());
            }
            if (guion != null) {
                preparedStatement.setString(index++, guion);
            }
            if (disponible != null) {
                preparedStatement.setBoolean(index++, disponible);
            }

            preparedStatement.setInt(index, idNumerico);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Se han actualizado: " + rowsAffected + " filas!");
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean insertFilm(Connection c, String titulo, String actor, Pelicula.Tematica tematica, String guion, Boolean disponible) throws SQLException {
        String query = "INSERT INTO pelicula (titulo, actor, tematica, guion, disponible) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
            preparedStatement.setString(1, titulo);
            preparedStatement.setString(2, actor);
            preparedStatement.setString(3, tematica.name());
            preparedStatement.setString(4, guion);
            preparedStatement.setBoolean(5, disponible);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Se han actualizado: " + rowsAffected + " filas!");
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar la película: " + e.getMessage());
            return false;
        }
    }
}
