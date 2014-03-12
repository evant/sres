package me.tatarka.sres.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by evan on 3/1/14.
 */
public class MainActivity extends Activity {
    private static MyModel model = new MyModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final layout.MainActivity view = (layout.MainActivity) findViewById(R.id.content);
        view.myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.addItem("Net Item");
                model.toggle();
                view.bindText.setText(model.getText());
            }
        });
//        view.bindListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                model.items().remove(position);
//            }
//        });
        view.bind(model);
    }
}
