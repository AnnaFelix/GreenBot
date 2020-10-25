package lu.uni.bicslab.greenbot.android.ui.indicator;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import lu.uni.bicslab.greenbot.android.ui.indicator_category.IndicatorCategoryModel;

public class IndicatorModel extends ViewModel {

    String indicator_id;
    String name;
    String icon_name;
    String indicator_description;
    String category_id;
    String id;
    String general_description;

    public IndicatorModel(String indicator_id, String name, String icon_name, String indicator_description, String category_id, String id, String general_description) {
        this.indicator_id = indicator_id;
        this.name = name;
        this.icon_name = icon_name;
        this.indicator_description = indicator_description;
        this.category_id = category_id;
        this.id = id;
        this.general_description = general_description;
    }

    public String getIndicator_idForProduct() {
        return indicator_id;
    }

    public void setIndicator_id(String indicator_id) {
        this.indicator_id = indicator_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon_name() {
        return icon_name;
    }

    public void setIcon_name(String icon_name) {
        this.icon_name = icon_name;
    }

    public String getIndicator_description() {
        return indicator_description;
    }

    public void setIndicator_description(String indicator_description) {
        this.indicator_description = indicator_description;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGeneral_description() {
        return general_description;
    }

    public void setGeneral_description(String general_description) {
        this.general_description = general_description;
    }
}