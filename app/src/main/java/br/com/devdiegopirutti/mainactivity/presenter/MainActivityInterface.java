package br.com.devdiegopirutti.mainactivity.presenter;

import java.util.List;

import br.com.devdiegopirutti.mainactivity.models.ItemsItem;

public interface MainActivityInterface {
   void apresentarDados(List<ItemsItem> list);
   void aconteceuUmErro();
}
