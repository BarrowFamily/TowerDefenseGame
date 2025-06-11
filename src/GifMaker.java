import java.awt.*;
import java.io.File;

public class GifMaker {

    public adaptiveList<Image> gifArray;



    public GifMaker(String filePathway){
        initGifArray(filePathway);

    }

    private void initGifArray(String filePathway){
        File directory = new File(filePathway);
        gifArray = new adaptiveList<>(directory.list());
    }

    @Override
    public String toString(){
        return gifArray.toString();
    }

}
