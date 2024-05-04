package it.marconi.catalogo.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.marconi.catalogo.domains.Prodotto;

@Service
public class ProdottoService {

    private ArrayList<Prodotto> prodotti = new ArrayList<>();

    /**
     * Visualizza tutti i prodotti
     * 
     * @return Arraylist
     */
    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    /**
     * Aggiunta di un nuovo prodotto
     * 
     * @param p Nuovo prodotto
     */
    public void addProduct(Prodotto p) {
        prodotti.add(p);
    }

    /**
     * Visualizza il determinato prodotto in base al codice
     * 
     * @param code Codice prodotto
     * @return Il prodotto
     */
    public Optional<Prodotto> getProductByCode(String code) {
        for (Prodotto p : prodotti) {
            if (p.getCodice().equals(code)) {
                return Optional.of(p);
            }
        }

        return Optional.empty();
    }

    /**
     * Elimina il prodotto
     * 
     * @param p Il prodotto
     */
    public void deleteProduct(Optional<Prodotto> p) {
        prodotti.remove(p);
    }
}
