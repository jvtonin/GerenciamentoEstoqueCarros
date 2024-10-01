/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jvton
 */
public class Marca {
    
    private int id;
    private String nome;
    private String pais_de_origem;

    public Marca() {
    }

    public Marca(String nome, String pais_de_origem) {
        this.nome = nome;
        this.pais_de_origem = pais_de_origem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais_de_origem() {
        return pais_de_origem;
    }

    public void setPais_de_origem(String pais_de_origem) {
        this.pais_de_origem = pais_de_origem;
    }    

    @Override
    public String toString() {
        return nome;
    }
    
    
}

    
  