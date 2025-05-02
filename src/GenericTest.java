public class GenericTest {

    public static void main(String[] args) {
        testListRemoveI();
    }

    private static void testListRemoveI(){
        adaptiveList<Integer> testArray = new adaptiveList<>();

        for (int i = 0; i < 15; i++) {
            testArray.addBack(i);
            System.out.println(testArray);
        }
        System.out.println();

        System.out.println(testArray.popFront());

        System.out.println(testArray);
    }



}
