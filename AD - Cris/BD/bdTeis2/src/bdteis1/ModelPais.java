package bdteis1;

import java.sql.*;

public class ModelPais {

    private String nombrePais, nombreCapital, moneda;
    private int numHabitantes;

    public ModelPais(){}

    public ModelPais(String nombrePais, String nombreCapital, String moneda, int numHabitantes) {
        this.nombrePais = nombrePais;
        this.nombreCapital = nombreCapital;
        this.moneda = moneda;
        this.numHabitantes = numHabitantes;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public String getNombreCapital(){
        return nombreCapital;
    }

    public String getMoneda(){
        return moneda;
    }

    public int getNumHabitantes(){
        return numHabitantes;
    }

    protected void showPaises(Connection connection){
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from Paises");
            System.out.println("ID \t País \tHabitantes \tCapital \tMoneda");

            while (resultSet.next()) {
                System.out.print(resultSet.getString("idPais"));
                System.out.print("\t" + resultSet.getString("nombrePais"));
                System.out.print("\t" + resultSet.getString("numHabitantes"));
                System.out.print("\t" + resultSet.getString("nombreCapital"));
                System.out.print("\t" + resultSet.getString("moneda") + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected boolean addPais(Connection connection, String nombrePais, int numHabitantes, String nombreCapital, String moneda) {
        String query = "INSERT INTO PAISES (nombrePais, numHabitantes, nombreCapital, moneda) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nombrePais);
            preparedStatement.setInt(2, numHabitantes);
            preparedStatement.setString(3, nombreCapital);
            preparedStatement.setString(4, moneda);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    protected boolean updatePais(Connection connection, int id, String nombrePais, Integer numHabitantes, String nombreCapital, String moneda) {
        StringBuilder query = new StringBuilder("UPDATE paises SET ");
        boolean setClauseAdded = false;

        if (nombrePais != null) {
            query.append("nombrePais = ?, ");
            setClauseAdded = true;
        }
        if (numHabitantes != null) {
            query.append("numHabitantes = ?, ");
            setClauseAdded = true;
        }
        if (nombreCapital != null) {
            query.append("nombreCapital = ?, ");
            setClauseAdded = true;
        }
        if (moneda != null) {
            query.append("moneda = ?, ");
            setClauseAdded = true;
        }

        if (!setClauseAdded) {
            return false;
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE idPais = ?");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;

            if (nombrePais != null) {
                preparedStatement.setString(index++, nombrePais);
            }
            if (numHabitantes != null) {
                preparedStatement.setInt(index++, numHabitantes);
            }
            if (nombreCapital != null) {
                preparedStatement.setString(index++, nombreCapital);
            }
            if (moneda != null) {
                preparedStatement.setString(index++, moneda);
            }

            preparedStatement.setInt(index, id);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    protected boolean deletePais(Connection connection, int idPais) {
        String deleteQuery = "DELETE FROM paises WHERE idPais = ?";
        String resetAutoIncrementQuery = "ALTER TABLE paises AUTO_INCREMENT = " + idPais;

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setInt(1, idPais);
                int rowsAffected = deleteStatement.executeUpdate();
                if (rowsAffected > 0) {
                    try (Statement resetStatement = connection.createStatement()) {
                        resetStatement.executeUpdate(resetAutoIncrementQuery);
                    }
                    connection.commit();
                    System.out.println("Filas afectadas: " + rowsAffected);
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
