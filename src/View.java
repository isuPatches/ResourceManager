import java.util.*;

class View
{
    public static void all()
    {
        ArrayList<Resource> arrAllResources = Search.all();
        Display.resources(arrAllResources, false);
    }

    public static void all(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.all(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, false);
    }

    public static void bad(Integer intCustomMemUsageVal, String strCPUTime)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrBadResources = Search.bad(arrAllResources, intCustomMemUsageVal, strCPUTime);
        Display.resources(arrBadResources, false);
    }

    public static void byName(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byName(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, false);
    }

    public static void byPID(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byPID(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, false);
    }

    public static void bySessionName(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.bySessionName(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, false);
    }

    public static void bySessionNum(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.bySessionNum(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, false);
    }

    public static void byMemUsage(Integer intMemUsage, String strOperator)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byMemUsage(arrAllResources, intMemUsage, strOperator);
        Display.resources(arrResources, false);
    }

    public static void byStatus(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byStatus(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, false);
    }

    public static void byUserName(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byUserName(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, false);
    }

    public static void byCPUTime(String strTime, String strOperator)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources =  Search.byCPUTime(arrAllResources, strTime, strOperator);
        Display.resources(arrResources, false);
    }

    public static void byWindowTitle(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byWindowTitle(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, false);
    }

    public static void badStatus()
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrBadStatusResources = Search.badStatus(arrAllResources);
        Display.resources(arrBadStatusResources, false);
    }

    public static void highMemUsage(Integer intMemUsageVal)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrHighMemoryUsageResources = Search.highMemUsage(arrAllResources, intMemUsageVal);
        Display.resources(arrHighMemoryUsageResources, false);
    }

    public static void longCPUTime(String strCPUTime)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrLongCPUTimeResources = Search.longCPUTime(arrAllResources, strCPUTime);
        Display.resources(arrLongCPUTimeResources, false);
    }
}