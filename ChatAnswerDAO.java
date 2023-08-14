package br.com.ChatGPT3Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatAnswerDAO implements DAO {

	private Connection con;
	   PreparedStatement ps;
	   ResultSet rs;

	   public ChatAnswerDAO() {
	      con = new ConnectionManufacterer().getConnection();
	   }

	   // ****************************
	   // ********** insert the API answers ***
	   // ****************************
	   public void inserir(ChatAnswer chatAnswer) {
	      String insert = "INSERT INTO Answers (answer) VALUES (?)";
	      try {
	         ps = con.prepareStatement(insert);
	         ps.setString(1, chatAnswer.getChatAnswer());
	         ps.execute();
	         ps.close();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	   }

}
