package br.com.celeste.celestebedwars.teams;

import org.bukkit.entity.Player;

public class BedWars {
    private Time vermelho, azul, amarelo, verde;

    public BedWars() {
        vermelho = new Time("Vermelho");
        azul = new Time("Azul");
        amarelo = new Time("Amarelo");
        verde = new Time("Verde");
    }

    public void adicionarJogador(Player jogador, String nomeTime) {
        switch(nomeTime.toLowerCase()) {
            case "vermelho":
                vermelho.registrarJogador(jogador);
                break;
            case "azul":
                azul.registrarJogador(jogador);
                break;
            case "preto":
                amarelo.registrarJogador(jogador);
                break;
            case "verde":
                verde.registrarJogador(jogador);
                break;
            default:
                // Time inválido, lançar exceção ou tratar adequadamente
                break;
        }
    }

    // getters e setters
    public Time getVermelho() {
        return vermelho;
    }

    public void setVermelho(Time vermelho) {
        this.vermelho = vermelho;
    }

    public Time getAzul() {
        return azul;
    }

    public void setAzul(Time azul) {
        this.azul = azul;
    }

    public Time getAmarelo() {
        return amarelo;
    }

    public void setAmarelo(Time amarelo) {
        this.amarelo = amarelo;
    }

    public Time getVerde() {
        return verde;
    }

    public void setVerde(Time verde) {
        this.verde = verde;
    }
}