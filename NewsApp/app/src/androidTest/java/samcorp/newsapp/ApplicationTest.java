package samcorp.newsapp;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest{
    @Rule
    public ActivityTestRule<MainActivity>
            applicationTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void TestDrawer() throws Exception{

    }
    @Test
    public void storeFrontDisplays(){
        onView(withId(R.id.recycler)).check(matches(isDisplayed()));
    }
}