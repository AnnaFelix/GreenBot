package lu.uni.bicslab.greenbot.android.other;

import java.util.List;

import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.IndicatorModel;

public interface UpdateFeedbackListener {
    public void updateFeedbackAction(boolean isUpdated, List<IndicatorModel> mIndicatorModel, int pos, int itemposchnged);

}
