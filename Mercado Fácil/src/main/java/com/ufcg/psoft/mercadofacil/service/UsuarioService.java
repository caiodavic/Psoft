package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.interfaces.Perfil;
import com.ufcg.psoft.mercadofacil.model.PerfilEnum;
import com.ufcg.psoft.mercadofacil.model.Usuario;
import com.ufcg.psoft.mercadofacil.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public Optional<Usuario> getUsuario(long cpf);
    public void salvaUsuario(Usuario usuario);
    public String usuarioToString(Usuario usuario);
    public void setPerfil(PerfilEnum perfil, Long cpf);
    public Perfil getPerfil(Long cpf);
    public List<Usuario> listaUsuarios();
    public void removeUsuario(Long cpf);
    public PerfilEnum getEnumPerfil(Long cpf);
    public void setEnderecoUsuario(String endereco, Long cpf);
    public void setNomeUsuario(String nome, Long cpf);
}
