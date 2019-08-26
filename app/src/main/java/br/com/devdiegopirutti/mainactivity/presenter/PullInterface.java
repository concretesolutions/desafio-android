package br.com.devdiegopirutti.mainactivity.presenter;

import java.util.List;

import br.com.devdiegopirutti.mainactivity.models.BasePRResponse;


public interface PullInterface {
    void apresentarDados(List<BasePRResponse> list);
    void aconteceuUmErro();
}

