package it.testFinale.infrastruttura.tools;

import it.testFinale.infrastruttura.object.dto.DettaglioOrdineDTO;
import it.testFinale.infrastruttura.object.dto.OrdineDTO;
import it.testFinale.infrastruttura.object.dto.ProdottoDTO;
import it.testFinale.infrastruttura.object.dto.UtenteDTO;
import it.testFinale.infrastruttura.object.model.DettaglioOrdine;
import it.testFinale.infrastruttura.object.model.Ordine;
import it.testFinale.infrastruttura.object.model.Prodotto;
import it.testFinale.infrastruttura.object.model.Utente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ConvertiInDto {

    public static UtenteDTO convertiInUtenteDTO(Utente utente) {
        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setId(utente.getId());
        utenteDTO.setNome(utente.getNome());
        utenteDTO.setEmail(utente.getEmail());
        return utenteDTO;
    }

    public static OrdineDTO convertiInOrdineDTO(Ordine ordine) {
        OrdineDTO ordineDTO = new OrdineDTO();
        ordineDTO.setId(ordine.getId());
        ordineDTO.setData(ordine.getData());
        ordineDTO.setStato(ordine.getStato());
        ordineDTO.setTotale(ordine.getTotale());
        ordineDTO.setUtenteId(ordineDTO.getUtenteId());
        ordineDTO.setDettagli(new ArrayList<>());
        for (DettaglioOrdine dettaglioOrdine : ordine.getDettagli()) {

            DettaglioOrdineDTO dettaglioOrdineDTO = new DettaglioOrdineDTO();
            dettaglioOrdineDTO.setId(dettaglioOrdine.getId());
            dettaglioOrdineDTO.setQuantita(dettaglioOrdine.getQuantita());
            dettaglioOrdineDTO.setProdottoId(dettaglioOrdine.getProdotto().getId());

            ordineDTO.getDettagli().add(dettaglioOrdineDTO);
        }
        return ordineDTO;
    }

    public static DettaglioOrdineDTO convertiInDettaglioDTO(DettaglioOrdine dettaglioOrdine) {
        DettaglioOrdineDTO dettaglioOrdineDTO = new DettaglioOrdineDTO();
        dettaglioOrdineDTO.setId(dettaglioOrdineDTO.getId());
        dettaglioOrdineDTO.setQuantita(dettaglioOrdine.getQuantita());
        dettaglioOrdineDTO.setProdottoId(dettaglioOrdineDTO.getProdottoId());
        return dettaglioOrdineDTO;
    }

    public static ProdottoDTO convertiInProdottoDTO(Prodotto prodotto) {
        ProdottoDTO prodottoDTO = new ProdottoDTO();
        prodottoDTO.setId(prodotto.getId());
        prodottoDTO.setNome(prodotto.getNome());
        prodottoDTO.setPrezzo(prodotto.getPrezzo());
        return prodottoDTO;
    }

}
