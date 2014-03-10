
package layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import me.tatarka.sres.Bindable;

public class MainActivity
    extends LinearLayout
    implements Bindable<me.tatarka.sres.sample.MyModel>
{

    public android.widget.Button myButton;
    public android.widget.TextView bindText;
    public me.tatarka.sres.ListView bindListView;

    public MainActivity(Context context) {
        this(context, null);
    }

    public MainActivity(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainActivity(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        myButton = ((android.widget.Button) findViewById(me.tatarka.sres.sample.R.id.my_button));
        bindText = ((android.widget.TextView) findViewById(me.tatarka.sres.sample.R.id.bind_text));
        bindListView = ((me.tatarka.sres.ListView) findViewById(me.tatarka.sres.sample.R.id.bind_list_view));
    }

    public void bind(me.tatarka.sres.sample.MyModel model) {
        bindText.setText(model.getText());
        bindListView.bind(model.items());
    }

}
