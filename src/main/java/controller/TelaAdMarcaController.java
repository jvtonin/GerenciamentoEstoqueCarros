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
import view.TelaAdMarca;

/**
 *
 * @author jvton
 */
public class TelaAdMarcaController {
    
    private TelaAdMarca view;

    public TelaAdMarcaController(TelaAdMarca view) {
        this.view = view;
    }
    
    public void salvaMarca(){
        
        String nome = view.getTxtNome().getText();
        String pais = view.getTxtPais().getText();
        
        Marca marca = new Marca(nome, pais);

        try {
            Connection conexao = new Conexao().getConnection();
            MarcaDAO marcaDao = new MarcaDAO(conexao);
            marcaDao.insert(marca);
            
            JOptionPane.showMessageDialog(null, "O registro foi salvo com sucesso.");
            view.dispose();
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaAdMarca.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar o registro: " + ex.getMessage());
        }
    }
    
}
