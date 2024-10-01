/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Conexao;
import dao.VeiculoDAO;
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
import model.Veiculo;
import view.TelaAdVeiculo;
import view.TelaEditVeiculo;
import view.TelaVeiculos;

/**
 *
 * @author jvton
 */
public class TelaVeiculosController {
    
    private final TelaVeiculos view;

    public TelaVeiculosController(TelaVeiculos view) {
        this.view = view;
    }

    public void abrirTelaAdVeiculo(){
        TelaAdVeiculo tela_add = new TelaAdVeiculo(view);
        tela_add.setVisible(true);
    }
    
    public void abrirTelaEditVeiculo(){
        if (view.getTbVeiculos().getSelectedRow() != -1) {
            TelaEditVeiculo tela_edit = new TelaEditVeiculo(view);
            tela_edit.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Nenhum registro foi selecionado.");
        }
    }   
        
    
    public void ordenar(){
        DefaultTableModel modelo = (DefaultTableModel) view.getTbVeiculos().getModel();
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        view.getTbVeiculos().setRowSorter(sorter);
        
        sorter.setSortKeys(List.of(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
        sorter.sort();
    }
    
    public void atualizarTabelaVeiculos(){
        
        try {
            DefaultTableModel modelo = (DefaultTableModel) view.getTbVeiculos().getModel();

            modelo.setNumRows(0);

            Connection conexao = new Conexao().getConnection();

            VeiculoDAO vdao = new VeiculoDAO(conexao);

            for(Veiculo v: vdao.listar()){

                modelo.addRow(new Object[]{
                    v.getId(),
                    v.getNome(),
                    v.getAno(),
                    v.getQuilometragem(),
                    v.getValor(),
                    v.getMarca_obj().getNome()
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaVeiculos.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    
    public void excluir() throws SQLException {
        
        String[] opcoes = {"Sim", "Não"};
        
        if(view.getTbVeiculos().getSelectedRow() != -1){
        
            int resposta = JOptionPane.showOptionDialog(null,
                    "Tem certeza de que deseja excluir este registro?",
                    "Confirmação de Exclusão", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    opcoes, opcoes[0]);

            if(resposta == JOptionPane.YES_OPTION){

                int linha = view.getTbVeiculos().getSelectedRow();

                Veiculo veiculo = new Veiculo();
                
                veiculo.setId((int) view.getTbVeiculos().getValueAt(linha, 0));
                
                Connection conexao = new Conexao().getConnection();
                VeiculoDAO veiculoDao = new VeiculoDAO(conexao);
                veiculoDao.delete(veiculo);
                
                atualizarTabelaVeiculos();
            
                JOptionPane.showMessageDialog(null, "Registro excluído com sucesso.");
            }else{   
                JOptionPane.showMessageDialog(null, "A exclusão foi cancelada.");
            }
           
        }else{
            JOptionPane.showMessageDialog(null, "Nenhum registro foi selecionado.");
        }
    }
}