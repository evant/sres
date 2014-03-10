
package layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import me.tatarka.sres.Bindable;

public class ListItem
    extends TextView
    implements Bindable<me.tatarka.sres.sample.MyListItem>
{


    public ListItem(Context context) {
        this(context, null);
    }

    public ListItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void bind(me.tatarka.sres.sample.MyListItem model) {
        setText(model.text);
    }

}
