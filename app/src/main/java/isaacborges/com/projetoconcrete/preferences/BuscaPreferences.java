package isaacborges.com.projetoconcrete.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class BuscaPreferences {

    public static final String BUSCA_PREFERENCES = "com.isaacborges.agenda.preferences.BuscaPreferences";

    public static final String ORDEM_BUSCA = "ordem";
    public static final String LINGUAGEM_BUSCA = "linguagem";

    public static final String PREFIXO_LINGUAGEM = "language:";

    private final Context context;

    public BuscaPreferences(Context context){
        this.context = context;
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(BUSCA_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void salvaInformacoes(String ordem, String linguagem) {
        SharedPreferences preferences = getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ORDEM_BUSCA, ordem);
        editor.putString(LINGUAGEM_BUSCA, PREFIXO_LINGUAGEM + linguagem);
        editor.commit();
    }

    public String getOrdemBusca(){
        SharedPreferences preferences = getSharedPreferences();
        return preferences.getString(ORDEM_BUSCA, "stars");
    }

    public String getLinguagemBusca(){
        SharedPreferences preferences = getSharedPreferences();
        return preferences.getString(LINGUAGEM_BUSCA, "language:java");
    }

    public String getLinguagemBuscaSemPrefixo(){
        String linguagemBusca = getLinguagemBusca();
        return linguagemBusca.replace(PREFIXO_LINGUAGEM, "");
    }

}
