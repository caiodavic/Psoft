package com.ufcg.psoft.mercadofacil.model;

import com.ufcg.psoft.mercadofacil.interfaces.Perfil;

import javax.persistence.*;

@Entity
public class Usuario {
    @Id
    private Long cpf;
    private String enderecoUsuario;
    private String nomeUsuario;
    @OneToOne(cascade = CascadeType.ALL)
    private Carrinho carrinho;
    @Enumerated(EnumType.STRING)
    private PerfilEnum perfilUsuario;


    public Usuario(){}

    public Usuario(Long cpf, String enderecoUsuario, String nomeUsuario, PerfilEnum perfilUsuario){
        this.cpf = cpf;
        this.enderecoUsuario = enderecoUsuario;
        this.nomeUsuario = nomeUsuario;
        this.perfilUsuario = perfilUsuario;
    }

    public Long getCpf() {
        return cpf;
    }

    public String getEnderecoUsuario() {
        return enderecoUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public void setEnderecoUsuario(String enderecoUsuario) {
        this.enderecoUsuario = enderecoUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    @Override
    public String toString() {
        return "Nome: " + nomeUsuario + '\n' +
                "CPF: " + cpf + '\n' +
                "Endereço: " + enderecoUsuario+ '\n' +
                "Perfil: "  + perfilUsuario.toString();

    }

    public PerfilEnum getPerfilUsuario() {
        return perfilUsuario;
    }

    public Perfil perfilUsuarioObj() {
        Perfil perfil = new PerfilNormal();

        if(perfilUsuario.toString().equals("ESPECIAL")){
            perfil = new PerfilEspecial();
        } else if(perfilUsuario.toString().equals("PREMIUM")){
            perfil = new PerfilPremium();
        }
        return perfil;
    }

    public void setPerfilUsuario(PerfilEnum perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }
}
