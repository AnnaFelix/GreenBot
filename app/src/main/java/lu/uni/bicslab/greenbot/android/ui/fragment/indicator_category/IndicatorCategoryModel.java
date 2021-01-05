package lu.uni.bicslab.greenbot.android.ui.fragment.indicator_category;


import java.util.ArrayList;
import java.util.List;

public class IndicatorCategoryModel {

    String id;
    String name;
    String icon_name;
    String description;


    public IndicatorCategoryModel(String name, String description) {
        this.name = name;
        this.icon_name = String.valueOf(name.charAt(0));
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndicator_name() {
        return name;
    }

    public void setIndicator_name(String indicator_name) {
        this.name = indicator_name;
    }

    public String getIcon_name() {
        return icon_name;
    }

    public void setIcon_name(String icon_name) {
        this.icon_name = icon_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<IndicatorCategoryModel> prepareDesserts(String[] names, String[] descriptions) {
        List<IndicatorCategoryModel> desserts = new ArrayList<>(names.length);

        for (int i = 0; i < names.length; i++) {
            IndicatorCategoryModel dessert = new IndicatorCategoryModel(names[i], descriptions[i]);
            desserts.add(dessert);
        }

        return desserts;
    }
}