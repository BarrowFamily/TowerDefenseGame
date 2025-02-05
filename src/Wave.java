public class Wave{
    private Enemy[] enemies;
    private int[] delays;


    Wave(Enemy[] enemies, int[] delays) {
        this.enemies = enemies;
        this.delays = new int[enemies.length];

        for (int i = 0; i < enemies.length; i++) {
            if (i < delays.length-1){
                this.delays[i] = delays[i];
            }
            else {
                this.delays[i] = 500;
            }
        }

    }




}
