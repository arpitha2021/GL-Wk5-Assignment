package com.greatlearning.javapersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcCrudServices {

	public static void main(String[] args) throws Exception {
		int operation = 1;
		int id;
		String fName;
		String lName;
		String email;
		CrudServicesDAO crudServicesDAO = new CrudServicesDAO();
		Connection con = null;
		Statement stmt = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Users", "root", "password@121");
			while (operation > 0) {
				System.out.println("!!!! Welcome to User CRUD Services !!!! ");
				System.out.println("Enter the operation that you want to perform");
				System.out.println("1. Registration\n 2. Update\n 3. DisplayData\n 4. Delete\n 0.exit\n");
				Scanner scan = new Scanner(System.in);
				operation = scan.nextInt();
				switch (operation) {
				case 1:
					System.out.println("Enter the UserID");
					id = scan.nextInt();
					System.out.println("Enter the First Name");
					fName = scan.next();
					System.out.println("Enter the Last Name");
					lName = scan.next();
					System.out.println("Enter the EmailId");
					email = scan.next();
					crudServicesDAO.registerUser(con, id, fName, lName, email);
					break;
				case 2:
					System.out.println("Enter the the UserID you want to perform update operation");
					id = scan.nextInt();
					System.out.println("Enter the field you want to Update  ");
					System.out.println(" 1.FirstName\t 2. LastName\t 3.Email");
					int option = scan.nextInt();
					switch (option) {
					case 1:
						System.out.println("Enter the FirstName to be updated");
						fName = scan.next();
						crudServicesDAO.update(con, id, fName, option);
						break;
					case 2:
						System.out.println("Enter the LastName to be updated");
						lName = scan.next();
						crudServicesDAO.update(con, id, lName, option);
						break;
					case 3:
						System.out.println("Enter the Email to be updated");
						email = scan.next();
						crudServicesDAO.update(con, id, email, option);
						break;
					default:
						System.out.println("Invalid Option-No Such Field");
						break;
					}
					break;
				case 3:
					stmt = con.createStatement();
					crudServicesDAO.displayData(stmt);
					break;
				case 4:
					System.out.println("Enter the UserID");
					id = scan.nextInt();
					crudServicesDAO.delete(con, id);
					break;
				case 0:
					System.out.println("Invalid Operation");
					break;
				default:
					break;
				}
			}
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}
