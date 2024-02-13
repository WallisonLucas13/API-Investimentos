package com.example.demo.services;

import com.example.demo.models.Mail;
import com.example.demo.models.Movimentacoes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailBuilderService {

    @Value("${mail.destination.address}")
    private String to;

    public Mail build(Movimentacoes movimentacoes){
        return Mail.MailBuilder
                .builder()
                .to(this.to)
                .titulo(createTitulo())
                .conteudo(createConteudo(movimentacoes))
                .build();
    }

    private String createTitulo(){
        return "Relátorio de Movimentações - API Investimentos";
    }

    private String createConteudo(Movimentacoes movimentacoes){
        return
                "Ativo: " + movimentacoes.getAtivo() + "\n" +
                "Categoria: " + movimentacoes.getCategoria() + "\n" +
                "Segmento: " + movimentacoes.getSegmento() + "\n" +
                "Operação: " + movimentacoes.getOperacao() + "\n" +
                "Quantidade: " + movimentacoes.getQuant() + "\n" +
                "Valor Unitário: " + movimentacoes.getValorUnit() + "\n" +
                "Valor Total da Operação: " + movimentacoes.getValorTotal() + "\n" +
                "Corretora: " + movimentacoes.getCorretora() + "\n" +
                "Data da Movimentação: " + movimentacoes.getData() + "\n" +
                "Hora da Movimentação: " + movimentacoes.getHora();
    }
}
