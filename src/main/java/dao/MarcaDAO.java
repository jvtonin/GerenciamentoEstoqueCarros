/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Marca;

/**
 *
 * @author jvton
 */
public class MarcaDAO {
    
    private final Connection conexao;

    public MarcaDAO(Connection conexao) {
        this.conexao = conexao;
    }
 
public void insert(Marca marca) throws SQLException{
        
        String sql = "INSERT INTO marca(nome_da_marca, pais_de_origem) VALUES(?, ?);";
       
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setString(1, marca.getNome());
        statement.setString(2, marca.getPais_de_origem());
        
        statement.execute();
    }
    
    public void update (Marca marca) throws SQLException{
        
        String sql = "UPDATE marca SET nome_da_marca = ?, pais_de_origem = ? WHERE id_marca = ?";
       
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setString(1, marca.getNome());
        statement.setString(2, marca.getPais_de_origem());
        statement.setInt(3, marca.getId());
        
        statement.execute();
    }
    
    public void delete(Marca marca) throws SQLException{
        
        String sql = "DELETE FROM marca WHERE id_marca = ?";
       
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setInt(1, marca.getId());
        
        statement.execute();
        
    }
    
    
    
    public List<Marca> listar() throws SQLException{
        
        String sql = "SELECT * FROM marca";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        
        
        List<Marca> marcas = new ArrayList<>();

        
        while(rs.next()){
            
            Marca marca = new Marca();
                    
            marca.setId(rs.getInt("id_marca"));
            marca.setNome(rs.getString("nome_da_marca"));
            marca.setPais_de_origem(rs.getString("pais_de_origem"));
            
            marcas.add(marca);
        }
        
    return marcas;
                
    }
}
