package it.marconi.catalogo.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prodotto {

    String codice;
    String nome;
    String categoria;
    String annoProduzione;
    String quantita;
}
