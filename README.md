
# android-prefix-textView and editView
EditText and TextView with support for non editable prefix and image.

## Howto?
Either directly via xml:
```xml
<com.maze.prefix.PrefixView
        android:layout_margin="5dp"
        android:id="@+id/textview3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#1111"
        android:gravity="right|center_vertical"
        android:text="1398/09/23"
        android:textColorHint="#567"
        android:textSize="18sp"
        app:prefix=" تاریخ عضویت: "
        android:textColor="#D500F9"
        app:res="@drawable/term" />
```

or programmatically in code:

```kotlin

### Setting the prefix
edit_text.prefix = "$"

### Setting the color
edit_text.setHintTextColor(Color.GREEN) 

### Setting the drawable
edit_text.drawable = avatar

```
See  [sample code]

## Add as Library
Step 1. Add the JitPack repository to your build file  
Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```
	dependencies {
		implementation 'com.github.maze:android-prefix-edittext-textview:lastversion'
	}
```

