/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jvton
 */
public class Veiculo {
    
    private int id;
    private String nome;
    private int ano;
    private int quilometragem;
    private double valor;
    private int marca;
    private Marca marca_obj;
    
    public Veiculo() {
    }
    
    public Veiculo(String nome, int ano, int quilometragem, double valor, int marca) {
        this.nome = nome;
        this.ano = ano;
        this.quilometragem = quilometragem;
        this.valor = valor;
        this.marca = marca;
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

    public Marca getMarca_obj() {
        return marca_obj;
    }

    public void setMarca_obj(Marca marca_obj) {
        this.marca_obj = marca_obj;
    }

    

    
    
}
