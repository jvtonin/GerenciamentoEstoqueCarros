/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Conexao;
import dao.MarcaDAO;
import dao.VeiculoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Marca;
import model.Veiculo;
import view.TelaAdVeiculo;

/**
 *
 * @author jvton
 */
public class TelaAdVeiculoController {
    
    private TelaAdVeiculo view;

    public TelaAdVeiculoController(TelaAdVeiculo view) {
        this.view = view;
    }
    
    public void salvaVeiculo(){
        
        String nome = view.getTxtNome().getText();
        int ano = Integer.parseInt(view.getTxtAno().getText());
        int quilometragem = Integer.parseInt(view.getTxtKm().getText());
        double valor = Double.parseDouble(view.getTxtValor().getText());
        Marca marca = (Marca) (view.getJcMarca().getSelectedItem());
        
        int marcaID = marca.getId();
        
        Veiculo veiculo = new Veiculo(nome, ano, quilometragem, valor, marcaID);
        
        try {
            Connection conexao = new Conexao().getConnection();
            VeiculoDAO veiculoDao = new VeiculoDAO(conexao);
            veiculoDao.insert(veiculo);
            
            JOptionPane.showMessageDialog(null, "O registro foi salvo com sucesso.");
            view.dispose();
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaAdVeiculo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar o registro: " + ex.getMessage());
        }
    }
    
    
    public void arrumar_opcoes(){
           
        try {
            Connection conexao = new Conexao().getConnection();
            MarcaDAO mdao = new MarcaDAO(conexao);
        
            for(Marca m: mdao.listar()){
                view.getJcMarca().addItem(m);
            }    
        } catch (SQLException ex) {
            Logger.getLogger(TelaAdVeiculoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
