package com.ufcg.psoft.mercadofacil.service;

import java.util.List;

import com.ufcg.psoft.mercadofacil.model.Lote;

public interface LoteService {
	
	public List<Lote> listarLotes();

	public void salvarLote(Lote lote);

	public int produtosTotais(long id);

	public void retiraProdutos(long id, int qtd);
}
