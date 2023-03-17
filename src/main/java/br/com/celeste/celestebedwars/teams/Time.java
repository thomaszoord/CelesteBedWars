package br.com.celeste.celestebedwars.teams;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Time {
    private ArrayList<Player> jogadores;
    private String nomeTime;

    public Time(String nomeTime) {
        this.nomeTime = nomeTime;
        jogadores = new ArrayList<Player>();
    }

    public void registrarJogador(Player jogador) {
        jogadores.add(jogador);
    }

    // getters e setters
    public ArrayList<Player> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Player> jogadores) {
        this.jogadores = jogadores;
    }

    public String getNomeTime() {
        return nomeTime;
    }

    public void setNomeTime(String nomeTime) {
        this.nomeTime = nomeTime;
    }
}