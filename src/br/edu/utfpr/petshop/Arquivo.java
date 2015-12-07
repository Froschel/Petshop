/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.petshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 *
 * @author Breno
 */
public class Arquivo{
 
    private Connection con;
    
    private JTextField exclude;
    
    public Arquivo(){
    
        try{
            
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/petshopdata", "adm", "123");
            
            //System.out.println("Conectado");
         
            
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }catch(SQLException e){
            System.out.println(e.getMessage());           
        }
        
    }
    
    public void Cadastro(String Especie, String Raca, String Quant) throws SQLException{
        
        Statement stm = con.createStatement();
        stm.executeUpdate("INSERT INTO dados VALUES('"+Especie+"', '"+Raca+"', '"+Quant+"')");
        
    }
    
    public void Listar() throws Exception{
        
        Statement stm;
        stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT Especie, Raca, Quantidade FROM dados");
        System.out.println("--/--/--/--/--/--/--/--");
        while(rs.next()){
            System.out.println("\nEspecie: " + rs.getString(1) + "\nRaca: " + rs.getString(2) + "\nQuantidade: " + rs.getString(3));
        }
        JOptionPane.showMessageDialog(null, "Veja a aba 'Petshop(run)' para checar os dados.");
        
    }
    
    public void Excluir(String Raca) throws Exception{
        
         /*try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            
            exclude = new JTextField("Lassie");
 
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/petshopdata", "adm", "123");
 
            java.sql.Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM dados WHERE Nome='"+exclude.getText()+"'");
            JOptionPane.showMessageDialog(null, "Dado excluído");
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        }*/
        
        try{
            Statement stm = con.createStatement();
            stm.executeUpdate("DELETE FROM dados WHERE Raca=('"+Raca+"')");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
       
    }

    public void Update(String Quantidade){
         try {
            Statement stm = con.createStatement();
            stm.executeUpdate("UPDATE dados SET Quantidade=('"+Quantidade+"')");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
