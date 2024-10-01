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
import view.TelaEditVeiculo;

/**
 *
 * @author jvton
 */
public class TelaEditVeiculoController {
    
    private TelaEditVeiculo view;

    public TelaEditVeiculoController(TelaEditVeiculo view) {
        this.view = view;
    }
    
    
    public void abrirRegistro(){
        int linha = view.getTelaVeiculos().getTbVeiculos().getSelectedRow();
        
            if (linha != -1) {
                String nome = (String) view.getTelaVeiculos().getTbVeiculos().getValueAt(linha, 1);
                int ano = (Integer) view.getTelaVeiculos().getTbVeiculos().getValueAt(linha, 2);
                int km = (Integer) view.getTelaVeiculos().getTbVeiculos().getValueAt(linha, 3);
                double valor =(Double) view.getTelaVeiculos().getTbVeiculos().getValueAt(linha, 4);
                String marca = (String) view.getTelaVeiculos().getTbVeiculos().getValueAt(linha, 5);

                view.setTxtNome(nome);
                view.setTxtAno(String.valueOf(ano));
                view.setTxtKm(String.valueOf(km));
                view.setTxtValor(String.valueOf(valor));
                view.getJcMarca().setSelectedItem(marca);
                
            }
            
    }
    
    public void salvarVeiculo(){

        String nome = view.getTxtNome().getText();
        int ano = Integer.parseInt(view.getTxtAno().getText());
        int quilometragem = Integer.parseInt(view.getTxtKm().getText());
        double valor = Double.parseDouble(view.getTxtValor().getText());
        Marca marca = (Marca) view.getJcMarca().getSelectedItem();
        
        int marcaID = marca.getId();
        
        int linha = view.getTelaVeiculos().getTbVeiculos().getSelectedRow();

        
        Veiculo veiculo = new Veiculo(nome, ano, quilometragem, valor, marcaID);
        veiculo.setId((int) view.getTelaVeiculos().getTbVeiculos().getValueAt(linha, 0));
        
        try {
            Connection conexao = new Conexao().getConnection();
            VeiculoDAO veiculoDao = new VeiculoDAO(conexao);
            veiculoDao.update(veiculo);
            
            JOptionPane.showMessageDialog(null, "O registro foi salvo com sucesso.");
            view.dispose();
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaEditVeiculo.class.getName()).log(Level.SEVERE, null, ex);
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
