package dto;

public class PessoaDTO {
    private String nome;
    private String cpf;
    private String endereco;
    private String numeroCartaoSUS;
    private String email;
    private String telefone;
    private String profissao;
    private String dataNascimento;
    private String[] comorbidades;

    public PessoaDTO(String nome, String dataNascimento, String cpf, String endereco, String numeroCartaoSUS, String email, String telefone, String profissao, String[] comorbidades) {

        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.numeroCartaoSUS = numeroCartaoSUS;
        this.email = email;
        this.telefone = telefone;
        this.profissao = profissao;
        this.comorbidades = comorbidades;
    }

    public String getDataNascimento(){
        return dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNumeroCartaoSUS() {
        return numeroCartaoSUS;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getProfissao() {
        return profissao;
    }

    public String[] getComorbidades() {
        return comorbidades;
    }
}
