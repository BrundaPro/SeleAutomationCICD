package SeleniumFrameworkDesign.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer
{
	int count = 0;
	int maxTry = 1; //specify number of times we want to re-run the test after its failure
	@Override
	public boolean retry(ITestResult result)
	{
		if(count<maxTry)
		{
			count++;
			return true;
		}
		return false;
	}

}
