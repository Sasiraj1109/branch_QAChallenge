package com.qa.tech.extentreportlistener;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.qa.tech.util.TestUtil;

public class TestEventListener extends TestListenerAdapter{
@Override
public void onTestFailure(ITestResult tr) {
	Throwable th = tr.getThrowable();
    if (th != null) {
        System.out.println(th.getMessage());
        
        tr.setThrowable(th);
    }
}
}
