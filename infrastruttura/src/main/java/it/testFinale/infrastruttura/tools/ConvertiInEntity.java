package it.testFinale.infrastruttura.tools;

import it.testFinale.infrastruttura.object.dto.DettaglioOrdineDTO;
import it.testFinale.infrastruttura.object.dto.OrdineDTO;
import it.testFinale.infrastruttura.object.model.DettaglioOrdine;
import it.testFinale.infrastruttura.object.model.Ordine;
import it.testFinale.infrastruttura.object.model.Prodotto;
import it.testFinale.infrastruttura.object.model.Utente;
import it.testFinale.infrastruttura.repository.OrdineRepository;
import it.testFinale.infrastruttura.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertiInEntity {


    private static OrdineRepository ordineRepository;
    private static ProdottoRepository prodottoRepository;

    @Autowired
    public ConvertiInEntity(ProdottoRepository prodottoRepository, OrdineRepository ordineRepository) {
        this.prodottoRepository = prodottoRepository;
        this.ordineRepository = ordineRepository;
    }

    public static Ordine convertiOrdineDTOinEntity(OrdineDTO ordineDTO, Utente utente){
        Ordine ordine = new Ordine();
        ordine.setId(ordineDTO.getId());
        ordine.setData(ordineDTO.getData());
        ordine.setTotale(ordineDTO.getTotale());
        ordine.setStato(ordineDTO.getStato());
        ordine.setUtente(utente);
        return ordine;
    }

    public static DettaglioOrdine convertiDettaglioDTOInEntity(DettaglioOrdineDTO dettaglioOrdineDTO, Ordine ordine, Prodotto prodotto) {

        Long id = dettaglioOrdineDTO.getId();
        System.out.println(id);
        return DettaglioOrdine.builder()
                .quantita(dettaglioOrdineDTO.getQuantita())
                .prodotto(prodotto)
                .ordine(ordine)
                .build();
    }
}

