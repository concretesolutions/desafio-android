package isaacborges.com.projetoconcrete.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import isaacborges.com.projetoconcrete.R;
import isaacborges.com.projetoconcrete.preferences.BuscaPreferences;

public class FomularioFiltros {

    private Context context;
    private ViewGroup viewGroup;
    private View viewCriada;

    private Spinner spinnerLinguagem;
    private Spinner spinnerOrdem;

    private final BuscaPreferences buscaPreferences;
    private final RespostaPositivaDoDialog respostaPositivaDoDialog;

    private static final String TITULO = "Filter";

    public FomularioFiltros(Context context, ViewGroup viewGroup, RespostaPositivaDoDialog respostaPositivaDoDialog) {

        this.context = context;
        this.viewGroup = viewGroup;
        this.respostaPositivaDoDialog = respostaPositivaDoDialog;
        this.buscaPreferences = new BuscaPreferences(context);

        viewCriada = criaLayout();
        buscaReferenciasLayout();
    }

    public void chamaDialog(){
        carregaInformacoesDoPreferences();
        configuraFormulario();
    }

    private int buscaIndexDoSpinner(Spinner spinner, String stringInformada){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(stringInformada)){
                return i;
            }
        }
        return 0;
    }

    private void carregaInformacoesDoPreferences() {
        String ordemBusca = buscaPreferences.getOrdemBusca();
        String linguagemBusca = buscaPreferences.getLinguagemBuscaSemPrefixo();

        int indexLinguagem = buscaIndexDoSpinner(spinnerLinguagem, linguagemBusca);
        spinnerLinguagem.setSelection(indexLinguagem);

        int indexOrdem = buscaIndexDoSpinner(spinnerOrdem, ordemBusca);
        spinnerOrdem.setSelection(indexOrdem);
    }

    private void buscaReferenciasLayout() {
        spinnerLinguagem = viewCriada.findViewById(R.id.form_configuracoes_spinnerLinguages);
        spinnerOrdem = viewCriada.findViewById(R.id.form_configuracoes_spinnerOrdem);
    }

    private void configuraFormulario(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(viewCriada)
                .setTitle(TITULO)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        salvaInfomacoesNoPreferences();
                        respostaPositivaDoDialog.realizaNovaBusca();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void salvaInfomacoesNoPreferences() {
        String linguagemBusca = spinnerLinguagem.getSelectedItem().toString();
        String ordemBusca = spinnerOrdem.getSelectedItem().toString();
        buscaPreferences.salvaInformacoes(ordemBusca, linguagemBusca);
    }

    private View criaLayout(){
        return LayoutInflater.from(context).inflate(
                R.layout.form_configuracoes,
                viewGroup,
                false
        );
    }

    public interface RespostaPositivaDoDialog {
        void realizaNovaBusca();
    }

}
