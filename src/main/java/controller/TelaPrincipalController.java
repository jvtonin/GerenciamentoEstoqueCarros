/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.TelaMarcas;
import view.TelaPrincipal;
import view.TelaVeiculos;

/**
 *
 * @author jvton
 */
public class TelaPrincipalController {
    
    private TelaPrincipal view;

    public TelaPrincipalController(TelaPrincipal view) {
        this.view = view;
    }
    
    
    public void abrirTelaVeiculos(){
        TelaVeiculos telaV = new TelaVeiculos();
        telaV.setVisible(true);
    }
    
    public void abrirTelaMarcas(){
        TelaMarcas telaM = new TelaMarcas();
        telaM.setVisible(true);
    }
    
    
    
}
