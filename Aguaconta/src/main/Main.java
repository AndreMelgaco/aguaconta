package main;


import java.sql.SQLException;

import banco.Banco;
import usuario.JFramePrincipal;

public class Main {

	public static void main(String[] args) {
		try {
			Banco.abrirConexao();
			new JFramePrincipal();
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			System.exit(1);
		}catch (Exception e){
			
			System.out.println("Não foi possível conectar com o banco. Erro: " + e);
			
		}

		
	}
	
}