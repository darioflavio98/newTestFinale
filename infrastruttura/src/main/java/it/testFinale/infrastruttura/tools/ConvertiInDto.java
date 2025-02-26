package it.testFinale.infrastruttura.tools;

import it.testFinale.infrastruttura.object.dto.DettaglioOrdineDTO;
import it.testFinale.infrastruttura.object.dto.OrdineDTO;
import it.testFinale.infrastruttura.object.dto.ProdottoDTO;
import it.testFinale.infrastruttura.object.dto.UtenteDTO;
import it.testFinale.infrastruttura.object.model.DettaglioOrdine;
import it.testFinale.infrastruttura.object.model.Ordine;
import it.testFinale.infrastruttura.object.model.Prodotto;
import it.testFinale.infrastruttura.object.model.Utente;

public class ConvertiInDto {

    public static UtenteDTO convertiInUtenteDTO(Utente utente){
        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setId(utente.getId());
        utenteDTO.setNome(utente.getNome());
        utenteDTO.setEmail(utente.getEmail());
        return utenteDTO;
    }

    public static OrdineDTO convertiInOrdineDTO(Ordine ordine){
        OrdineDTO ordineDTO = new OrdineDTO();
        ordineDTO.setId(ordine.getId());
        ordineDTO.setData(ordine.getData());
        ordineDTO.setStato(ordine.getStato());
        ordineDTO.setTotale(ordine.getTotale());
        ordineDTO.setUtenteId(ordineDTO.getUtenteId());
        return ordineDTO;
    }

    public static DettaglioOrdineDTO convertiInDettaglioDTO(DettaglioOrdine dettaglioOrdine){
        DettaglioOrdineDTO dettaglioOrdineDTO = new DettaglioOrdineDTO();
        dettaglioOrdineDTO.setId(dettaglioOrdineDTO.getId());
        dettaglioOrdineDTO.setQuantita(dettaglioOrdine.getQuantita());
        dettaglioOrdineDTO.setPrezzoTotale(dettaglioOrdine.getPrezzoTotale());
        dettaglioOrdineDTO.setProdottoId(dettaglioOrdineDTO.getProdottoId());
        return dettaglioOrdineDTO;
    }

    public static ProdottoDTO convertiInProdottoDTO(Prodotto prodotto){
        ProdottoDTO prodottoDTO = new ProdottoDTO();
        prodottoDTO.setId(prodotto.getId());
        prodottoDTO.setNome(prodotto.getNome());
        prodottoDTO.setPrezzo(prodotto.getPrezzo());
        return prodottoDTO;
    }

}
