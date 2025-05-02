public class Wave{

    private adaptiveList<Enemy> enemies;
    private double[] delays;
    private int waveDelay;
    private int numEnemies;

    /**
     *
     * @param enemyType An initiated enemy that can be used to make the rest of the enemies in the wave
     * @param enemyDelay An array of doubles that define when the next enemy in the wave spawns. In seconds that are latter converted into frames.
     * @param waveDelay How long after the game starts that the wave starts spawning
     */
    Wave(Enemy enemyType, double[] enemyDelay, int waveDelay) {
        this.waveDelay = waveDelay;

        this.enemies = new adaptiveList<>();

        this.delays = new double[enemyDelay.length];

        this.numEnemies = enemyDelay.length;

        for (int i = 0; i < delays.length; i++) {
            delays[i] = enemyDelay[i] * 100;//100 FPS times seconds
            enemies.addBack(new Enemy(enemyType.skin, enemyType.hp, enemyType.def, enemyType.atk, enemyType.speed));
        }







    }




}
