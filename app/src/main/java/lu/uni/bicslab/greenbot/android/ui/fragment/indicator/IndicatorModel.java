package lu.uni.bicslab.greenbot.android.ui.fragment.indicator;

import androidx.lifecycle.ViewModel;

import java.io.Serializable;

public class IndicatorModel implements Serializable {

    String indicator_id;
    String name;
    String icon_name;
    String indicator_description;
    String category_id;
    String id;
    String general_description;
    int selectionnumber = 0;

    boolean isSelected = false;

    public IndicatorModel(String indicator_id, String name, String icon_name, String indicator_description, String category_id, String id, String general_description, int selectionnumber, boolean isSelected) {
        this.indicator_id = indicator_id;
        this.name = name;
        this.icon_name = icon_name;
        this.indicator_description = indicator_description;
        this.category_id = category_id;
        this.id = id;
        this.general_description = general_description;
        this.selectionnumber = selectionnumber;
        this.isSelected = isSelected;
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

    public String getIndicator_id() {
        return indicator_id;
    }

    public int getSelectionnumber() {
        return selectionnumber;
    }

    public void setSelectionnumber(int selectionnumber) {
        this.selectionnumber = selectionnumber;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}