package com.github.api.morepopulargithubapp;

import com.github.api.morepopulargithubapp.model.vo.Repository;
import com.github.api.morepopulargithubapp.presenterImpl.RepositoryPresenterImpl;
import com.github.api.morepopulargithubapp.util.ConstantsUitl;
import com.github.api.morepopulargithubapp.view.RepositoryView;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class RepositoryPresenterTest {

    private RepositoryPresenterImpl presenterImpl;
    private RepositoryView repositoryView;
    private List<Repository> repositories;
    private boolean isChangingOrientation;
    private int recyclerViewVisibility, progressVisibility;
    private String messageLimitMaxRecords;



    @Before
    public void setUp() {
        initRepositoryViewTeste();
        presenterImpl = new RepositoryPresenterImpl();
    }

    private void initRepositoryViewTeste() {

        repositoryView = new RepositoryView() {
            @Override
            public void setIsScrolling(boolean isScrolling) {

            }

            @Override
            public void showView(int recyclerViewVisibilityParam, int progressVisibilityParam, int areaErroVisibilityParam,
                                 int fetchDataProgressVisibility) {
                recyclerViewVisibility = recyclerViewVisibilityParam;
                progressVisibility = progressVisibilityParam;
            }

            @Override
            public void showRepositories(List<Repository> repositoriesList, boolean isChangingOrientationParam) {
                repositories = repositoriesList;
                isChangingOrientation = isChangingOrientationParam;
            }

            @Override
            public void showAddMoreRepositories(List<Repository> repositoriesParam) {
                repositories.addAll(repositoriesParam);
            }

            @Override
            public void showError(Map errorMap) {

            }

            @Override
            public void showMessageLimitMaxRecords(String errorMessage, boolean isLastPage) {
                messageLimitMaxRecords = errorMessage;
            }
        };
    }

    /**
     * No método initPresenter quando a lista de repostórios não for nula (ou seja, houve uma mudança de estdo)
     * Valida envio da lista e do estado de orientação modificado para false para a View
     */
    @Test
    public void onInitPresenterWhenRepositoriesNotNullAndOrientaitionTrue_validationSendChangeOrientationAndRepositories() {
        List<Repository> repositories = RepositoryMock.getRepositoriesMock();

        // Dada uma lista de repósitórios populada para presenter
        presenterImpl.initPresenter(repositoryView, repositories);

        // Verifica se a lista foi informada para o View
        Assert.assertTrue(CollectionUtils.isEqualCollection(repositories, this.repositories));
        // Verifica se estdo foi de mudança está apresentado como false
        Assert.assertFalse(isChangingOrientation);
    }

    /**
     * Simula o adição de repositórios na busca por mais páginas
     */
    @Test
    public void onshowRepositories_validationAddRepositoriesToView() {
        this.repositories = RepositoryMock.getRepositoriesMock();

        // Inicia o presenter
        presenterImpl.setRepositoryView(repositoryView);

        List<Repository> repositories = RepositoryMock.getRepositoriesMock();

        // Clona a lista para simular o repositories existente da busca por pagina
        List<Repository> repositoriesClone = new ArrayList(this.repositories);

        // Adiciona os repositórios simulando adição da próxima página
        repositoriesClone.addAll(repositories);

        // Dada uma lista de repositórios obtida pelo request
        presenterImpl.showRepositories(repositories);

        // Valida se a lista adicionada foi enviada para a View
        Assert.assertTrue(CollectionUtils.isEqualCollection(repositoriesClone, this.repositories));
    }

    /**
     * Valida se a mensagem de limite máximos de registros foi exibido
     */

    @Test
    public void onShowError_validationMessageMaxLimit(){
        // Inicia view para ser iniciada
        presenterImpl.setRepositoryView(repositoryView);

        Map mapErrorTest = new HashMap();
        //  Simula mensagem de erro enviada pelo servidor
        String message = "Max Limit allowed";
        mapErrorTest.put(ConstantsUitl.SERVER_UNABLE_PROCESS_INSTRUCTIONS_CODE, message);

        presenterImpl.showError(mapErrorTest);
        Assert.assertEquals(message, messageLimitMaxRecords );
    }

}
