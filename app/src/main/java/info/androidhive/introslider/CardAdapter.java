package info.androidhive.introslider;



import androidx.cardview.widget.CardView;

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 0;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
