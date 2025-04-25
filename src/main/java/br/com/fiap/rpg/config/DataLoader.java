package br.com.fiap.rpg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.fiap.rpg.model.Classe;
import br.com.fiap.rpg.model.Item;
import br.com.fiap.rpg.model.Personagem;
import br.com.fiap.rpg.model.Raridade;
import br.com.fiap.rpg.model.Tipo;
import br.com.fiap.rpg.repository.ItemRepository;
import br.com.fiap.rpg.repository.PersonagemRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {
        // alguns personagens iniciais
        Personagem gandalf = new Personagem();
        gandalf.setNome("Gandalf");
        gandalf.setClasse(Classe.mago);
        gandalf.setNivel(50);
        gandalf.setMoedas(1000);
        personagemRepository.save(gandalf);

        Personagem aragorn = new Personagem();
        aragorn.setNome("Aragorn");
        aragorn.setClasse(Classe.guerreiro);
        aragorn.setNivel(40);
        aragorn.setMoedas(800);
        personagemRepository.save(aragorn);

        Personagem legolas = new Personagem();
        legolas.setNome("Legolas");
        legolas.setClasse(Classe.arqueiro);
        legolas.setNivel(38);
        legolas.setMoedas(750);
        personagemRepository.save(legolas);

        // alguns itens iniciais no mercado (sem dono)
        Item espadaLonga = new Item();
        espadaLonga.setNome("Espada Longa de Fogo");
        espadaLonga.setTipo(Tipo.arma);
        espadaLonga.setRaridade(Raridade.raro);
        espadaLonga.setPreco(300);
        itemRepository.save(espadaLonga);

        Item cajadoArcanico = new Item();
        cajadoArcanico.setNome("Cajado Arcânico");
        cajadoArcanico.setTipo(Tipo.arma);
        cajadoArcanico.setRaridade(Raridade.epico);
        cajadoArcanico.setPreco(500);
        itemRepository.save(cajadoArcanico);

        Item arcoElfico = new Item();
        arcoElfico.setNome("Arco Élfico");
        arcoElfico.setTipo(Tipo.arma);
        arcoElfico.setRaridade(Raridade.raro);
        arcoElfico.setPreco(250);
        itemRepository.save(arcoElfico);

        Item armaduraPlacas = new Item();
        armaduraPlacas.setNome("Armadura de Placas");
        armaduraPlacas.setTipo(Tipo.armadura);
        armaduraPlacas.setRaridade(Raridade.incomum);
        armaduraPlacas.setPreco(200);
        itemRepository.save(armaduraPlacas);

        Item mantoArcano = new Item();
        mantoArcano.setNome("Manto Arcano");
        mantoArcano.setTipo(Tipo.armadura);
        mantoArcano.setRaridade(Raridade.raro);
        mantoArcano.setPreco(280);
        itemRepository.save(mantoArcano);

        Item pocaoCura = new Item();
        pocaoCura.setNome("Poção de Cura Maior");
        pocaoCura.setTipo(Tipo.pocao);
        pocaoCura.setRaridade(Raridade.comum);
        pocaoCura.setPreco(50);
        itemRepository.save(pocaoCura);

        Item pocaoMana = new Item();
        pocaoMana.setNome("Poção de Mana");
        pocaoMana.setTipo(Tipo.pocao);
        pocaoMana.setRaridade(Raridade.comum);
        pocaoMana.setPreco(45);
        itemRepository.save(pocaoMana);

        Item amuletoSabedoria = new Item();
        amuletoSabedoria.setNome("Amuleto da Sabedoria");
        amuletoSabedoria.setTipo(Tipo.acessorio);
        amuletoSabedoria.setRaridade(Raridade.lendario);
        amuletoSabedoria.setPreco(1000);
        itemRepository.save(amuletoSabedoria);

        Item anelProtecao = new Item();
        anelProtecao.setNome("Anel de Proteção");
        anelProtecao.setTipo(Tipo.acessorio);
        anelProtecao.setRaridade(Raridade.epico);
        anelProtecao.setPreco(600);
        itemRepository.save(anelProtecao);

        // Itens que já pertencem a personagens
        Item bastaoMagico = new Item();
        bastaoMagico.setNome("Bastão Mágico");
        bastaoMagico.setTipo(Tipo.arma);
        bastaoMagico.setRaridade(Raridade.incomum);
        bastaoMagico.setPreco(150);
        bastaoMagico.setDono(gandalf);
        itemRepository.save(bastaoMagico);

        Item espadaAcoCeleste = new Item();
        espadaAcoCeleste.setNome("Espada de Aço Celeste");
        espadaAcoCeleste.setTipo(Tipo.arma);
        espadaAcoCeleste.setRaridade(Raridade.epico);
        espadaAcoCeleste.setPreco(450);
        espadaAcoCeleste.setDono(aragorn);
        itemRepository.save(espadaAcoCeleste);

        Item arcoLongoDeFogo = new Item();
        arcoLongoDeFogo.setNome("Arco Longo de Fogo");
        arcoLongoDeFogo.setTipo(Tipo.arma);
        arcoLongoDeFogo.setRaridade(Raridade.raro);
        arcoLongoDeFogo.setPreco(300);
        arcoLongoDeFogo.setDono(legolas);
        itemRepository.save(arcoLongoDeFogo);
    }
}