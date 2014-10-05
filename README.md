sres
====
**Warning! This is incomplete and not activly worked on. Instead, I'm planning or released the features of this as seperated, smaller libraries, check out [Holdr](https://github.com/evant/holdr) instead.**

Super-Duper Android Layout Preprocessor

sres simplifies Android Layout creating and usage by using a more light-weight dsl for creating
layyouts and by generating code to help use them.



Install
-------

Add the following to you're build.gradle:
```groovy
buildscript {
    dependencies {
        classpath 'me.tatarka.sres.gradle:gradle-sres:1.0-SNAPSHOT'
    }
}

apply plugin: 'android'
apply plugin: 'sres'

dependencies {
    compile project(':sres-android')
}
```

Usage
-----

### Layout Syntax

Instead of putting you layouts in `res`, you put them in `sres`.

Here is a simple layout file with a `LinearLayout` that has a `TextView` and a `Button`.

```
// sample.sres
LinearLayout(match, match) {
    orientation = vertical

    TextView(match, wrap) {
        @+id/text
        text = @string/hello_world
    }

    Button(match, 20dp) {
        @+id/button
        text = "Press Me!"
        background = @color/button_color
    }
}
```

Attributes are specified with `key = value` and nesting views is done by simply placing them inside
their parent's block. Since `layout_width` and `layout_height` are required on every view, they get
special treatment inside parenthesis following the view name. Also, since id, is so common, you can
simple say `@+id/my_id` instead of `id = @+id/my_id`.

You may notice there are no namespaces specified. The default namespace `android`. You can also
specify another namespace with `namespace:key = value`. Built-in are `tools` and `app` for res-auto.
You can still specify your own namespaces with `xmlns:my_namespace = ...`.

### In Code

This layout will not only create `sample.xml` but also `layout.Sample.java`. The later is a custom
view you can use to easily get at the children with id's without `findViewById()` calls.

For example, the above layout will generate:

```java
public class Sample extends LinearLayout {
    public android.widget.TextView text;
    public android.widget.Button button;

    public ListItem(Context context) {
        this(context, null);
    }

    public ListItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        text = ((android.widget.TextView) findViewById(R.id.text));
        button = ((android.widget.Button) findViewById(R.id.button));
    }
}
```

This way, in code you can do:
```java
public void onCreate() {
    setContentView(R.layout.sample);
    layout.Sample sampleView = (layout.Sample) findViewById(android.R.id.content);

    sampleView.text.setText("Hello, World!");
    sampleView.button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("MyApp", "Button Clicked!");
        }
    });
}
```

You can also have the layout inflate your own custom view so that you can customize it further.
```
// sample.sres
com.example.MyView<LinearLayout(match, match) {
    ...
}
```

```java
// com.example.MyView.java
public class MyView extends layout.Sample {
    public ListItem(Context context) {
        this(context, null);
    }

    public ListItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        // Do what you want
    }
}
```

### Data Binding

You can bind a model class to a layout to save you even more boilerplate. For example, if you had
a model like:

```java
// com.example.MyModel.java
public class MyModel {
    public String title;

    private String buttonText = "Press Me!";

    public String getButtonText() {
        return buttonText;
    }
}
```

You can do:

```
// sample.sres
LinearLayout(match, match) {
    bind:class = com.example.MyModel // This is required for any data-binding
    orientation = vertical

    TextView(match, wrap) {
        @+id/text
        bind:text = title
    }

    Button(match, 20dp) {
        @+id/button
        bind:text = getButtonText()
    }
}
```

You then populate the view with:

```java
MyModel myModel = new MyModel();
sampleView.bind(myModel);
```

#### Listening For Changes

For the simplest use case, you don't have to change your model object at all to participate in
data-binding. However, if you want your view to update when you change your model, there is a little
instrumentation that you must do.

```java
// com.example.MyModel.java
public class MyModel {
    private Property<String> text = new Property<String>("Hello, World!");

    public String getText() {
        return text.get();
    }

    public void setText(String newText) {
        text.set(newText);
    }
}
```

That's it!. Your public api for your model class doesn't have to change, and you view doesn't have
to change. This does, of course mean that you can only bind to methods this way, not public fields.

It even works for computed properties:

```java
// com.example.MyModel.java
public class MyModel {
    private IntProperty firstValue = new IntProperty(1);
    private IntProperty secondValue = new IntProperty(2);

    public void setFirst(int value) {
        firstValue.set(value);
    }

    public void setSecond(int value) {
        secondValue.set(value);
    }

    public String getSum() {
        return Integer.toString(firstValue.get() + secondValue.get());
    }
}
```

```
// sample.xml
TextView(match, wrap) {
    bind:class = com.example.MyModel
    bind:text = getSum() // This will be updated when either firstValue or secondValue changes.
}
```

How does this work? When the model is bound to the view, it will detect all the properties in which
`get()` is called, and then attach listeners to those properties. Keeping this in mind, you _must_
always call `get()` on all properties you expect to use even if they are not used in that particular
case.

For example:
```java
BooleanProperty isFirst = new BooleanProperty(true);
Property<String> first = new Property<String>("first");
Property<String> second = new Property<String>("second");

// Wrong!
public String getFirstOrSecond() {
    if (isFirst.get()) {
        return first.get();
    } else {
        // This will never be resisted for changes because it's not called on the first time.
        return second.get();
    }
}

// Right!
public String getFirstOrSecond() {
    String firstString = first.get();
    String secondString = second.get(); // get() is always called so were are good.

    if (isFirst.get()) {
        return firstString;
    } else {
        return secondString;
    }
}
```
