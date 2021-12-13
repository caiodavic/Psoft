package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.interfaces.Perfil;
import com.ufcg.psoft.mercadofacil.model.PerfilEnum;
import com.ufcg.psoft.mercadofacil.model.Usuario;
import com.ufcg.psoft.mercadofacil.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Optional<Usuario> getUsuario(long cpf) {
        return usuarioRepository.findById(cpf);
    }

    @Override
    public void salvaUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public String usuarioToString(Usuario usuario) {
       return usuario.toString();
    }

    @Override
    public void setPerfil(PerfilEnum perfil, Long cpf) {
        Usuario usuario = usuarioRepository.findById(cpf).get();
        usuario.setPerfilUsuario(perfil);
        usuarioRepository.save(usuario);

    }

    @Override
    public Perfil getPerfil(Long cpf) {
        Usuario usuario = usuarioRepository.findById(cpf).get();
        return usuario.perfilUsuarioObj();
    }

    @Override
    public List<Usuario> listaUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void removeUsuario(Long cpf) {
        Usuario usuario = usuarioRepository.findById(cpf).get();
        usuarioRepository.delete(usuario);
    }

    @Override
    public PerfilEnum getEnumPerfil(Long cpf) {
        Usuario usuario = usuarioRepository.findById(cpf).get();
        return usuario.getPerfilUsuario();
    }

    public void setEnderecoUsuario(String endereco, Long cpf){
        Usuario usuario = usuarioRepository.findById(cpf).get();
        usuario.setEnderecoUsuario(endereco);
        usuarioRepository.save(usuario);
    }

    public void setNomeUsuario(String nome, Long cpf){
        Usuario usuario = usuarioRepository.findById(cpf).get();
        usuario.setNomeUsuario(nome);
        usuarioRepository.save(usuario);
    }
}
