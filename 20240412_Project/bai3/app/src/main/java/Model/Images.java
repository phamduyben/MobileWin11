package Model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vanessa.bai3.R;

public class Images  {
    public Images(int imagesID) {
        this.imagesID = imagesID;
    }

    public int getImagesID() {
        return imagesID;
    }

    public void setImagesID(int imagesID) {
        this.imagesID = imagesID;
    }

    private int imagesID;


}