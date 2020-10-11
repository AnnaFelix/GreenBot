package lu.uni.bicslab.greenbot.android.ui.indicator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class IndicatorViewModel {

    String indicator_id;
    String indicator_name;
    String indicator_image;
    String indicator_data;

    public IndicatorViewModel(String name, String description) {
        this.indicator_name = name;
        this.indicator_image = String.valueOf(name.charAt(0));
        this.indicator_data = description;
    }


    public String getIndicator_id() {
        return indicator_id;
    }

    public void setIndicator_id(String indicator_id) {
        this.indicator_id = indicator_id;
    }

    public String getIndicator_name() {
        return indicator_name;
    }

    public void setIndicator_name(String indicator_name) {
        this.indicator_name = indicator_name;
    }

    public String getIndicator_image() {
        return indicator_image;
    }

    public void setIndicator_image(String indicator_image) {
        this.indicator_image = indicator_image;
    }

    public String getIndicator_data() {
        return indicator_data;
    }

    public void setIndicator_data(String indicator_data) {
        this.indicator_data = indicator_data;
    }

    public static List<IndicatorViewModel> prepareDesserts(String[] names, String[] descriptions) {
        List<IndicatorViewModel> desserts = new ArrayList<>(names.length);

        for (int i = 0; i < names.length; i++) {
            IndicatorViewModel dessert = new IndicatorViewModel(names[i], descriptions[i]);
            desserts.add(dessert);
        }

        return desserts;
    }
}