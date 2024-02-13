package com.example.demo.services;

import com.example.demo.Exceptions.NotFoundRegisterException;
import com.example.demo.models.Movimentacoes;
import com.example.demo.dtos.MovimentacoesDto;
import com.example.demo.producers.MailProducer;
import com.example.demo.repositories.MovimentacoesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MovimentacoesService {

    @Autowired
    private MovimentacoesRepository repository;

    @Autowired
    private MailProducer mailProducer;

    @Autowired
    private MailBuilderService mailBuilderService;

    @Transactional
    public void save(MovimentacoesDto dto){

        //Construindo a Movimentação
        Movimentacoes movimentacoes = Movimentacoes.MovimentacoesBuilder
                .builder()
                .ativo(dto.ativo())
                .categoria(dto.categoria())
                .segmento(dto.segmento())
                .operacao(dto.operacao())
                .quant(dto.quant())
                .valorUnit(dto.valorUnit())
                .corretora(dto.corretora())
                .build();

        //Salva no banco e atualiza o saldo
        repository.save(movimentacoes);
        repository.flush();
        mailProducer.publishMessageMail(mailBuilderService.build(movimentacoes));
    }

    @Transactional
    public List<Movimentacoes> listarTodas(){
        List<Movimentacoes> list = repository.findAll();
        list.sort(Comparator.comparingLong(Movimentacoes::getId));
        return list;
    }

    @Transactional
    public Movimentacoes encontrarMovimentacao(Long id) throws NotFoundRegisterException{
        return repository.findById(id).orElseThrow(
                NotFoundRegisterException::new
        );
    }

    @Transactional
    public void apagar(Long id) throws NotFoundRegisterException {

       repository.findById(id).orElseThrow(
                NotFoundRegisterException::new
        );

        repository.deleteById(id);
    }

    @Transactional
    public Movimentacoes atualizar(Long id, MovimentacoesDto dto) throws NotFoundRegisterException{

        Movimentacoes atual = repository.findById(id).orElseThrow(
                NotFoundRegisterException::new
        );

        BeanUtils.copyProperties(dto, atual, "id", "data", "hora", "valorFinal");
        atual.setValorTotal(atual.getValorUnit() * atual.getQuant());

        repository.save(atual);
        repository.flush();

        return atual;
    }

}
