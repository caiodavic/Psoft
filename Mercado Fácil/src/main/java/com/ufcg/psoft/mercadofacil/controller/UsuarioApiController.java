package com.ufcg.psoft.mercadofacil.controller;

import com.ufcg.psoft.mercadofacil.model.PerfilEnum;
import com.ufcg.psoft.mercadofacil.model.Usuario;
import com.ufcg.psoft.mercadofacil.model.UsuarioDTO;
import com.ufcg.psoft.mercadofacil.service.CarrinhoService;
import com.ufcg.psoft.mercadofacil.service.UsuarioService;
import com.ufcg.psoft.mercadofacil.util.ErroUsuario;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PreUpdate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UsuarioApiController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value="/usuario/", method = RequestMethod.POST)
    public ResponseEntity<?> cadastraUsuario(@RequestBody UsuarioDTO dto){
        String perfil = dto.getPerfil().trim().toLowerCase();

        if(usuarioService.getUsuario(dto.getCpf()).isPresent()){
            return ErroUsuario.erroCPFJaCadastrado(dto.getCpf());
        }

        PerfilEnum perfilEnum;

        if(perfil.equals("especial")){
            perfilEnum = PerfilEnum.ESPECIAL;
        } else if(perfil.equals("premium")){
            perfilEnum = PerfilEnum.ESPECIAL;
        } else{
            perfilEnum = PerfilEnum.NORMAL;
        }

        Usuario usuarioNovo = new Usuario(dto.getCpf(),dto.getEndereco(),dto.getNome(), perfilEnum);
        usuarioService.salvaUsuario(usuarioNovo);

        return new ResponseEntity<String>("Usuário cadastrado", HttpStatus.OK);
    }

    @RequestMapping(value = "/usuario/{num_cpf}", method = RequestMethod.GET)
    public ResponseEntity<?> getUsuario(@PathVariable("num_cpf") Long cpf){

        Optional<Usuario> usuario = usuarioService.getUsuario(cpf);

        if(usuario.isEmpty()){
            return ErroUsuario.erroCPFNaoCadastrado(cpf);
        }

        String usuarioString = usuarioService.usuarioToString(usuario.get());

        return new ResponseEntity<String>(usuarioString,HttpStatus.OK);
    }

    @RequestMapping(value = "/usuario/{num_cpf}/nome", method = RequestMethod.PUT)
    public ResponseEntity<?> setNomeUsuario(@RequestBody String nome, @PathVariable("num_cpf") Long cpf){

        Optional<Usuario> optionalUsuario = usuarioService.getUsuario(cpf);

        if(optionalUsuario.isEmpty()){
            return ErroUsuario.erroCPFNaoCadastrado(cpf);
        }

        usuarioService.setNomeUsuario(nome,cpf);

        String response = "O nome do usuario %s foi alterado para: %s";

        return new ResponseEntity<String>(String.format(response,cpf,nome),HttpStatus.OK);
    }

    @RequestMapping(value = "/usuario/{num_cpf}/endereco", method = RequestMethod.PUT)
    public ResponseEntity<?> setEnderecoUsuario(@RequestBody String endereco, @PathVariable("num_cpf") Long cpf){

        Optional<Usuario> optionalUsuario = usuarioService.getUsuario(cpf);

        if(optionalUsuario.isEmpty()){
            return ErroUsuario.erroCPFNaoCadastrado(cpf);
        }

        Usuario usuario = optionalUsuario.get();

        usuarioService.setEnderecoUsuario(endereco,cpf);

        String response = "O endereço do usuario %s foi alterado para: %s";

        return new ResponseEntity<String>(String.format(response,cpf,endereco),HttpStatus.OK);
    }

    @RequestMapping(value = "/usuario/{num_cpf}/perfil", method = RequestMethod.PUT)
    public ResponseEntity<?> setPerfilUsuario(@RequestBody String perfilString, @PathVariable("num_cpf") Long cpf){
        String perfil = perfilString.trim().toLowerCase();
        Optional<Usuario> optionalUsuario = usuarioService.getUsuario(cpf);

        if(optionalUsuario.isEmpty()){
            return ErroUsuario.erroCPFNaoCadastrado(cpf);
        }

        Usuario usuario = optionalUsuario.get();

        PerfilEnum perfilAtual = usuario.getPerfilUsuario();
        PerfilEnum perfilEnum;

        if(perfil.equals("especial")){
            perfilEnum = PerfilEnum.ESPECIAL;
        } else if(perfil.equals("premium")){
            perfilEnum = PerfilEnum.PREMIUM;
        } else if(perfil.equals("normal")){
            perfilEnum = PerfilEnum.NORMAL;
        }else{
            return ErroUsuario.erroPerfilInvalido(perfilString);
        }

        usuarioService.setPerfil(perfilEnum,cpf);
        String response = "O perfil do usuário foi alterado de %s para %s";

        return new ResponseEntity<String>(String.format(response,perfilAtual.toString(),perfilEnum.toString()),HttpStatus.OK);

    }

    @RequestMapping(value = "/usuario/", method = RequestMethod.GET)
    public ResponseEntity<?> getTodosUsuarios(){
        List<Usuario> usuarios = usuarioService.listaUsuarios();

        if(usuarios.isEmpty()){
            return ErroUsuario.erroNenhumCPFCadastrado();
        }

        return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
    }

    @RequestMapping(value = "/usuario/{num_cpf}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeUsuario(@PathVariable("num_cpf") Long cpf){
        Optional<Usuario> usuario = usuarioService.getUsuario(cpf);

        if(usuario.isEmpty()){
            return ErroUsuario.erroCPFNaoCadastrado(cpf);
        }

        usuarioService.removeUsuario(cpf);

        String response = "CPF %s removido do sistema";

        return new ResponseEntity<String>(String.format(response,cpf),HttpStatus.OK);

    }

}
