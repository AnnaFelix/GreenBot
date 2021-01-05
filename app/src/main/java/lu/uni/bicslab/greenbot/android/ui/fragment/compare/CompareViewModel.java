package lu.uni.bicslab.greenbot.android.ui.fragment.compare;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CompareViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CompareViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}