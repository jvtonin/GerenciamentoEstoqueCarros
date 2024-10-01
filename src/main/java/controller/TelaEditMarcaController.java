/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Conexao;
import dao.MarcaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Marca;
import view.TelaEditMarca;

/**
 *
 * @author jvton
 */
public class TelaEditMarcaController {
    
    private TelaEditMarca view;

    public TelaEditMarcaController(TelaEditMarca view) {
        this.view = view;
    }
    
    public void abrirRegistro(){
        int linha = view.getTelaMarcas().getTbMarcas().getSelectedRow();
        
            if (linha != -1) {
                String nome = (String) view.getTelaMarcas().getTbMarcas().getValueAt(linha, 1);
                String pais = (String) view.getTelaMarcas().getTbMarcas().getValueAt(linha, 2);

                view.setTxtNome(nome);
                view.setTxtPais(pais);
            }
    }
    
    public void salvarMarca(){

        String nome = view.getTxtNome().getText();
        String pais = view.getTxtPais().getText();
        
        int linha = view.getTelaMarcas().getTbMarcas().getSelectedRow();

        
        Marca marca = new Marca(nome, pais);
        marca.setId((int) view.getTelaMarcas().getTbMarcas().getValueAt(linha, 0));
        
        try {
            Connection conexao = new Conexao().getConnection();
            MarcaDAO marcaDao = new MarcaDAO(conexao);
            marcaDao.update(marca);
            
            JOptionPane.showMessageDialog(null, "O registro foi salvo com sucesso.");
            view.dispose();
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaEditMarca.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar o registro: " + ex.getMessage());
        }

    }
    
}
