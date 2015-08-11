package br.grupointegrado.SpaceInvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
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
    private Image jogador;
    private Texture texturaJogador;
    private Texture texturaJogadorDireita;
    private Texture texturaJogadorEsquerda;
    private boolean indoDireita;
    private boolean indoEsquerda;

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
        initJogador();
    }

    private void initJogador() {
        texturaJogador = new Texture("sprites/player.png");
        texturaJogadorDireita = new Texture("sprites/player-right.png");
        texturaJogadorEsquerda = new Texture("sprites/player-left.png");

        jogador = new Image(texturaJogador);
        float x = ((camera.viewportWidth / 2) - (jogador.getWidth() / 2));
        float y = 15;
        jogador.setPosition(x, y);
        palco.addActor(jogador);
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

        lbPontuacao.setPosition(10, camera.viewportHeight - 20);
        capturaTeclas();
        atualizarJogador(delta);

        palco.act(delta);
        palco.draw();
    }

    /**
     * Atualiza a posição do Jogador
     * @param delta tempo entre um quadro e outro (em segundos)
     */
    private void atualizarJogador(float delta) {
        float velocidade = 200; //Velocidade de Movimento do Jogador

        if(indoDireita){//Movimenta jogador para direita
            if(jogador.getX() < (camera.viewportWidth - jogador.getWidth())){
                float x = (jogador.getX() + (velocidade * delta));
                float y = jogador.getY();

                jogador.setPosition(x, y);
            }
        }

        if(indoEsquerda){//Movimenta jogador para esquerda
            if(jogador.getX() > 0){
                float x = (jogador.getX() - (velocidade * delta));
                float y = jogador.getY();

                jogador.setPosition(x, y);
            }
        }

        if(indoDireita){
            //Trocar imagem Direita
            jogador.setDrawable(new SpriteDrawable(new Sprite(texturaJogadorDireita)));
        } else if(indoEsquerda){
            //Trocar imagem Esquerda
            jogador.setDrawable(new SpriteDrawable(new Sprite(texturaJogadorEsquerda)));
        } else {
            //Trocar imagem Centro
            jogador.setDrawable(new SpriteDrawable(new Sprite(texturaJogador)));
        }
    }

    /**
     * Verifica se as teclas estão pressionadas
     */
    private void capturaTeclas() {
        indoDireita = false;
        indoEsquerda = false;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            indoEsquerda = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            indoDireita = true;
        }
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
     * É chamado sempre que o jogo voltar para 1º plano (voltou a maximizar o jogo)
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
        texturaJogador.dispose();
        texturaJogadorDireita.dispose();
        texturaJogadorEsquerda.dispose();
    }
}
