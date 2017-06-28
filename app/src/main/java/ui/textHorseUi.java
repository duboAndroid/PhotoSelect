package ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by
 */

public class textHorseUi extends TextView {
    public textHorseUi(Context context) {
        super(context);
    }

    public textHorseUi(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public textHorseUi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
