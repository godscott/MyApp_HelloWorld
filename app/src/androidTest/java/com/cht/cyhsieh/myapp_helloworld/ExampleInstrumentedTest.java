package com.cht.cyhsieh.myapp_helloworld;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.After;
import com.microsoft.appcenter.espresso.Factory;
import com.microsoft.appcenter.espresso.ReportHelper;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ReportHelper reportHelper = Factory.getReportHelper();

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.cht.cyhsieh.myapp_helloworld", appContext.getPackageName());

        reportHelper.label("myTestStepLabel");
    }

    @After
    public void TearDown(){
        reportHelper.label("Stopping App");
    }
}
