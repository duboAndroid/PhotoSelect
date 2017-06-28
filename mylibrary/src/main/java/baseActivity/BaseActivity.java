package baseActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 BaseActivity extend FragmentActivtiy
 */

public class BaseActivity extends FragmentActivity {
    private BaseActivity baseActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void mainInitMethod(BaseActivity baseActivity){
        this.baseActivity = baseActivity;
        initialFunction();
        getActivtiyContentData();
    }

    private void initialFunction() {
    }

    public boolean getActivtiyContentData(Object ... objects) {
        return true;
    }
}
