import com.sun.org.apache.xpath.internal.operations.Bool;

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

    public static void bad()
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrBadResources;

        Object bUseCustomMemUsage = Input.getUseCustomMemUsage();
        if(!bUseCustomMemUsage.equals("abort"))
        {
            if(bUseCustomMemUsage.equals(true))
            {
                Integer intCustomMemUsageVal = Input.getCustomMemUsageVal();
                if(!intCustomMemUsageVal.equals(-1))
                {
                    arrBadResources = Search.bad(arrAllResources, intCustomMemUsageVal);
                    Display.resources(arrBadResources, false);
                }
                else
                {
                    System.out.println("");
                    System.out.println("Aborting...");
                }
            }
            else
            {
                arrBadResources = Search.bad(arrAllResources, 100000);

                Display.resources(arrBadResources, false);
            }
        }
        else
        {
            System.out.println("");
            System.out.println("Aborting...");
        }
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

    public static void highMemUsage()
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrHighMemoryUsageResources = Search.highMemUsage(arrAllResources, 100000);
        Display.resources(arrHighMemoryUsageResources, false);
    }

    public static void longCPUTime()
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrLongCPUTimeResources = Search.longCPUTime(arrAllResources);
        Display.resources(arrLongCPUTimeResources, false);
    }
}