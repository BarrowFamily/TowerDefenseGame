public class GenericTest {

    public static void main(String[] args) {

        testGifMaker();


        //testListRemoveI();



        //robot stuff here
    }



    private static void testGifMaker(){

        GifMaker testGif = new GifMaker("C:\\Users\\josep\\IdeaProjects\\TowerDefenseGame\\src\\Images\\greenslimejump");

        System.out.println(testGif);

    }


    private static void testListRemoveI(){

        adaptiveList<Integer> testArray = new adaptiveList<>();

        for (int i = 0; i < 15; i++) {
            testArray.addBack(i);
            System.out.println(testArray);
        }
        System.out.println();

        System.out.println(testArray.popIndex(5));

        System.out.println(testArray);

    }



}
