package org.serratec.trabalhoApiIndividual.Exception;

public class ErroResposta {
	private String mensagem;
	private String detalhes;

	public ErroResposta(String mensagem, String detalhes) {
		this.mensagem = mensagem;
		this.detalhes = detalhes;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
}