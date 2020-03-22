package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyBird;
import com.mygdx.game.sprites.Bird;

public class PlayState extends State {
    private Bird bird;
    private Texture bg;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, FlappyBird.HEIGHT/2);
        bg = new Texture("bg.png");
        // установим камеру по середине игрового окна
        camera.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        // делаем так, чтобы камера при постоянной отрисовки захватывала именно игровое поле
        // и в таком виде она и будет следить за птицей
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        // создав getter'ы текстуры птицы и позиции в классе Bird мы можем получить их значения
        // даже учитывая, что они являются private. Данный подход являются частью инкапсуляции(т.е. безопасности данных)
        sb.draw(bg, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
