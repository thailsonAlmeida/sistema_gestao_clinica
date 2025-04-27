package backend.clinica.services.exceptions;

public class WordMensagemResponse {
    private Boolean erro;
    private String mensagem;

    // Getters e setters
    public Boolean getErro() {
        return erro;
    }

    public void setErro(Boolean erro) {
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}