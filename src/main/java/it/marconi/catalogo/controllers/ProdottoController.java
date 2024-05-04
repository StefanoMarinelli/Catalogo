package it.marconi.catalogo.controllers;

import java.lang.ProcessBuilder.Redirect;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.marconi.catalogo.domains.Prodotto;
import it.marconi.catalogo.services.ProdottoService;

@Controller
@RequestMapping("/")
public class ProdottoController {

    @Autowired
    ProdottoService prodottoService;

    @GetMapping
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }

    @GetMapping("/catalogo")
    public ModelAndView catalogue() {
        return new ModelAndView("catalogo").addObject("prodotti", prodottoService.getProdotti());
    }

    @GetMapping("/prodotto/nuovo")
    public ModelAndView newProduct() {
        return new ModelAndView("nuovo-prodotto").addObject("prodotto", new Prodotto());
    }

    @PostMapping("/prodotto/view")
    public ModelAndView handleNewProduct(@ModelAttribute Prodotto prodotto) {

        prodottoService.addProduct(prodotto);

        String code = prodotto.getCodice();

        return new ModelAndView("redirect:/prodotto/" + code);
    }

    @GetMapping("/prodotto/{code}")
    public ModelAndView productDetail(@PathVariable("code") String code) {
        Optional<Prodotto> prodotto = prodottoService.getProductByCode(code);

        if (prodotto.isPresent()) {
            return new ModelAndView("dettagli-prodotto").addObject("p", prodotto.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prodotto non trovato");
        }
    }

    @DeleteMapping("/prodotto/eliminato/{code}")
    public ModelAndView delete(@PathVariable("code") String code) {
        Optional<Prodotto> prodotto = prodottoService.getProductByCode(code);
        prodottoService.deleteProduct(prodotto);

        return new ModelAndView("prodotto-eliminato").addObject("p", prodotto.get());
    }

}
