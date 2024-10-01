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
import model.Veiculo;

/**
 *
 * @author jvton
 */
public class VeiculoDAO {
    
    private final Connection conexao;

    public VeiculoDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public void insert(Veiculo veiculo) throws SQLException{
        
        String sql = "INSERT INTO veiculo(nome_do_modelo, ano_de_fabricacao, quilometragem, valor, id_marca) VALUES(?, ?, ?, ?, ?);";
       
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setString(1, veiculo.getNome());
        statement.setInt(2, veiculo.getAno());
        statement.setInt(3, veiculo.getQuilometragem());
        statement.setDouble(4, veiculo.getValor());
        statement.setInt(5, veiculo.getMarca());
        
        statement.execute();
    }
    
    public void update (Veiculo veiculo) throws SQLException{
        
        String sql = "UPDATE veiculo SET nome_do_modelo = ?, ano_de_fabricacao = ?, quilometragem = ?, valor = ?, id_marca = ? WHERE id_veiculo = ?";
       
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setString(1, veiculo.getNome());
        statement.setInt(2, veiculo.getAno());
        statement.setInt(3, veiculo.getQuilometragem());
        statement.setDouble(4, veiculo.getValor());
        statement.setInt(5, veiculo.getMarca());
        statement.setInt(6, veiculo.getId());
        
        statement.execute();
    }
    
    public void delete(Veiculo veiculo) throws SQLException{
        
        String sql = "DELETE FROM veiculo WHERE id_veiculo = ?";
       
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setInt(1, veiculo.getId());
        
        statement.execute();
        
    }
    
    
    
    public List<Veiculo> listar() throws SQLException{
        
    String sql = "SELECT v.*, m.nome_da_marca, m.pais_de_origem " +
                 "FROM veiculo v " +
                 "JOIN marca m ON v.id_marca = m.id_marca";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        
        
        List<Veiculo> veiculos = new ArrayList<>();

        
        while(rs.next()){
            
            Veiculo veiculo = new Veiculo();
                    
            veiculo.setId(rs.getInt("id_veiculo"));
            veiculo.setNome(rs.getString("nome_do_modelo"));
            veiculo.setAno(rs.getInt("ano_de_fabricacao"));
            veiculo.setQuilometragem(rs.getInt("quilometragem"));
            veiculo.setValor(rs.getDouble("valor"));
            
            
            Marca marca = new Marca();
            
            marca.setId(rs.getInt("id_marca"));
            marca.setNome(rs.getString("nome_da_marca"));
            marca.setPais_de_origem(rs.getString("pais_de_origem"));
            veiculo.setMarca_obj(marca);
            
            
            veiculos.add(veiculo);
        }
        
    return veiculos;
                
    }
    
    

}
