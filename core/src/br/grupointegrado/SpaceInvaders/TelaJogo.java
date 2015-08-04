package br.grupointegrado.SpaceInvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FillViewport;

/**
 * Created by jonat_000 on 03/08/2015.
 */
public class TelaJogo extends TelaBase {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage palco;
    private BitmapFont fonte;
    private Label lbPontuacao;

    /**
     * Construtor Padrão da Tela do Jogo;
     * @param game referência para a classe principal.
     */
    public TelaJogo(MainGame game) {
        super(game);
    }


    /**
     * Chamado quando a tela é exibida para o usuario;
     */
    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        palco = new Stage(new FillViewport(camera.viewportWidth, camera.viewportHeight, camera));

        initFonte();
        initInformacoes();

    }


    private void initInformacoes(){
        Label.LabelStyle lbEstilo = new Label.LabelStyle();
        lbEstilo.font = fonte;
        lbEstilo.fontColor = Color.WHITE;

        lbPontuacao = new Label("0 Pontos", lbEstilo);
        palco.addActor(lbPontuacao);
    }

    private void initFonte() {
        fonte = new BitmapFont();
    }



    /**
     * Chamado a todo quadro de atualizacao do jogo (FPS)
     * @param delta tempo entre um quadro e outro (em segundos)
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.15f, .15f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        lbPontuacao.setPosition(10, camera.viewportHeight -20);

        palco.act(delta);
        palco.draw();
    }


    /**
     * É chamado sempre quando há uma alteração no tamanho da tela
     * @param width Novo valor de largura da tela
     * @param height Novo valor de altura da tela
     */
    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }


    /**
     * É chamado sempre que o jogo for minimizado (jogado para 2º plano)
     */
    @Override
    public void pause() {

    }


    /**
     * É chamado sempre o jogo voltar para 1º plano (voltou a maximizar o jogo)
     */
    @Override
    public void resume() {

    }


    /**
     * É chamado quando nosssa tela for destruída
     */
    @Override
    public void dispose() {
        batch.dispose();
        palco.dispose();
        fonte.dispose();
    }
}
