package com.example.sensor.repositories;
import com.example.sensor.Models.SensorData;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseDAO {

  Properties properties = new Properties();

  static final String QUERY_READ_ALL = "SELECT * FROM sensor_data ORDER BY id DESC";
  static final String QUERY_ADD_DATA = "INSERT INTO sensor_data (temperature, humidity, timestamp) values (?, ?, ?)";

  public DatabaseDAO() {
    try {
      properties.load(new FileInputStream("src/main/resources/application.properties"));
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
  public List<SensorData> readSamples() {

    List<SensorData> samples = new ArrayList<>();

    try (Connection connection = DriverManager.getConnection(
            properties.getProperty("connectionString"),
            properties.getProperty("spring.datasource.username"),
            properties.getProperty("spring.datasource.password"));
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(QUERY_READ_ALL)) {

      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        float temperature = resultSet.getFloat("temperature");
        float humidity = resultSet.getFloat("humidity");
        String timestamp = resultSet.getString("timestamp");
        samples.add(new SensorData(id, temperature, humidity, timestamp));
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return samples;
  }

  public void writeSample(Fetcher fetcher) {
    // Skriver senast uppmätta värde till databasen.

    int rowChanged = 0;

    try (Connection connection = DriverManager.getConnection(
            properties.getProperty("connectionString"),
            properties.getProperty("spring.datasource.username"),
            properties.getProperty("spring.datasource.password"));

         PreparedStatement statement = connection.prepareStatement(QUERY_ADD_DATA)) {

      statement.setFloat(1, fetcher.getTemperature());
      statement.setFloat(2, fetcher.getHumidity());
      statement.setString(3, fetcher.getTimestamp());

      rowChanged = statement.executeUpdate();

    } catch (Exception exception){
      exception.printStackTrace();
    }
  }
}