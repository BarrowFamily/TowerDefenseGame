import java.awt.*;
import java.io.File;

public class GifMaker {

    public adaptiveList<Image> gifArray;

    private File directory;
    private String[] dirArray;

    public GifMaker(String filePathway){
        initGifArray(filePathway);

    }

    private void initGifArray(String filePathway){
        directory = new File(filePathway);
        dirArray = directory.list();



        gifArray = new adaptiveList<>(directory.list());
    }


    private void sortArray(String stringToCut){
        int numUntilNum = stringToCut.length();

        String[] tempDirArray = new String[dirArray.length];

        for (int i = 0; i < dirArray.length; i++) {

            String tempFilePath = dirArray[i].substring(numUntilNum);

            int digits = 0;
            while(true){
                if(Character.isDigit(tempFilePath.charAt(digits))){
                    digits++;
                }
                else{
                    break;
                }
            }






        }


    }



    @Override
    public String toString(){
        return gifArray.toString();
    }

}
