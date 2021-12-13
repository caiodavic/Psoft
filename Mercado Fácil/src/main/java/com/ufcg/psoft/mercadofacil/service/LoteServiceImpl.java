package com.ufcg.psoft.mercadofacil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.repository.LoteRepository;

@Service
public class LoteServiceImpl implements LoteService {
	
	@Autowired
	private LoteRepository loteRepository;
	
	public List<Lote> listarLotes() {
		return loteRepository.findAll();
	}

	public void salvarLote(Lote lote) {
		loteRepository.save(lote);		
	}

	@Override
	public int produtosTotais(long id) {
		int qtdItens = 0;
		if(loteRepository.findAll().size() > 0){
			List<Lote> lotesRepositorio = loteRepository.findAll();
			for(Lote lote : lotesRepositorio){
				if(lote.getProduto().getId() == id){
					qtdItens += lote.getNumeroDeItens();
				}
			}
		}
		return qtdItens;
	}

	private void apagaLote(long id){
		Lote lote = loteRepository.getOne(id);
		if(lote.getNumeroDeItens() == 0){
			loteRepository.delete(lote);
		}
	}

	@Override
	public void retiraProdutos(long id, int qtd) {
		int qtdRestante = qtd;
		if(loteRepository.findAll().size() > 0){
			List<Lote> lotesRepositorio = loteRepository.findAll();
			for(Lote lote : lotesRepositorio){

				if(qtdRestante > 0 && lote.getProduto().getId() == id){

					if(lote.getNumeroDeItens() >= qtdRestante){
						lote.setNumeroDeItens(lote.getNumeroDeItens() - qtdRestante);
						qtdRestante = 0;

						if(lote.getNumeroDeItens() == 0){
							apagaLote(lote.getId());
						}

					} else if(lote.getNumeroDeItens() < qtdRestante){
						qtdRestante = qtdRestante - lote.getNumeroDeItens();
						lote.setNumeroDeItens(0);
						apagaLote(lote.getId());
					}
				}
			}
		}
	}

}
