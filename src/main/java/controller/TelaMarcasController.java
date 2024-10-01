/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Conexao;
import dao.MarcaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Marca;
import view.TelaAdMarca;
import view.TelaEditMarca;
import view.TelaMarcas;

/**
 *
 * @author jvton
 */
public class TelaMarcasController {
    
    private TelaMarcas view;

    public TelaMarcasController(TelaMarcas view) {
        this.view = view;
    }

    public void abrirTelaAdMarca() {
        TelaAdMarca tela_add = new TelaAdMarca(view);
        tela_add.setVisible(true);
    }
    
    public void abrirTelaEditMarca(){
        if (view.getTbMarcas().getSelectedRow() != -1) {
            TelaEditMarca tela_edit = new TelaEditMarca(view);
            tela_edit.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Nenhum registro foi selecionado.");
        }
    }

    public void ordenar(){
        DefaultTableModel modelo = (DefaultTableModel) view.getTbMarcas().getModel();
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        view.getTbMarcas().setRowSorter(sorter);
        
        sorter.setSortKeys(List.of(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
        sorter.sort();
    }
    
    public void atualizarTabelaMarcas(){
        
        try {
            DefaultTableModel modelo = (DefaultTableModel) view.getTbMarcas().getModel();

            modelo.setNumRows(0);

            Connection conexao = new Conexao().getConnection();

            MarcaDAO mdao = new MarcaDAO(conexao);

            for(Marca m: mdao.listar()){

                modelo.addRow(new Object[]{
                    m.getId(),
                    m.getNome(),
                    m.getPais_de_origem(),
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaMarcas.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    
    public void excluir() throws SQLException {
        
        String[] opcoes = {"Sim", "Não"};
        
        if(view.getTbMarcas().getSelectedRow() != -1){
        
            int resposta = JOptionPane.showOptionDialog(null,
                    "Tem certeza de que deseja excluir este registro?",
                    "Confirmação de Exclusão", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    opcoes, opcoes[0]);

            if(resposta == JOptionPane.YES_OPTION){

                int linha = view.getTbMarcas().getSelectedRow();

                Marca marca = new Marca();
                
                marca.setId((int) view.getTbMarcas().getValueAt(linha, 0));
                
                Connection conexao = new Conexao().getConnection();
                MarcaDAO marcaDao = new MarcaDAO(conexao);
                marcaDao.delete(marca);
                
                atualizarTabelaMarcas();
            
                JOptionPane.showMessageDialog(null, "Registro excluído com sucesso.");
            }else{   
                JOptionPane.showMessageDialog(null, "A exclusão foi cancelada.");
            }
           
        }else{
            JOptionPane.showMessageDialog(null, "Nenhum registro foi selecionado.");
        }
    }
    
    
}
