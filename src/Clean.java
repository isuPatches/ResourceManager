import java.util.*;

class Clean
{
    public static void all(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.all(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, true);
        Clean.execute(arrResources);
    }

    public static void bad(Integer intCustomMemUsageVal, String strCPUTime)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrBadResources = Search.bad(arrAllResources, intCustomMemUsageVal, strCPUTime);
        Display.resources(arrBadResources, true);
        Clean.execute(arrBadResources);
    }

    public static void byName(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byName(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, true);
        Clean.execute(arrResources);
    }

    public static void byPID(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byPID(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, true);
        Clean.execute(arrResources);
    }

    public static void bySessionName(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.bySessionName(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, true);
        Clean.execute(arrResources);
    }

    public static void bySessionNum(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.bySessionNum(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, true);
        Clean.execute(arrResources);
    }

    public static void byMemUsage(Integer intMemUsage, String strOperator)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byMemUsage(arrAllResources, intMemUsage, strOperator);
        Display.resources(arrResources, true);
        Clean.execute(arrResources);
    }

    public static void byStatus(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byStatus(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, true);
        Clean.execute(arrResources);
    }

    public static void byUserName(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byUserName(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, true);
        Clean.execute(arrResources);
    }

    public static void byCPUTime(String strTime, String strOperator)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources =  Search.byCPUTime(arrAllResources, strTime, strOperator);
        Display.resources(arrResources, true);
        Clean.execute(arrResources);
    }

    public static void byWindowTitle(String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byWindowTitle(arrAllResources, strSearchCriteria, bUseRegEx, bExactMatch);
        Display.resources(arrResources, true);
        Clean.execute(arrResources);
    }

    public static void badStatus()
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrBadStatusResources = Search.badStatus(arrAllResources);
        Display.resources(arrBadStatusResources, true);
        Clean.execute(arrBadStatusResources);
    }

    public static void highMemUsage(Integer intMemUsageVal)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrHighMemUsageResources = Search.highMemUsage(arrAllResources, intMemUsageVal);
        Display.resources(arrHighMemUsageResources, true);
        Clean.execute(arrHighMemUsageResources);
    }

    public static void longCPUTime(String strCPUTime)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrLongCPUTimeResources = Search.longCPUTime(arrAllResources, strCPUTime);
        Display.resources(arrLongCPUTimeResources, true);
        Clean.execute(arrLongCPUTimeResources);
    }

    private static void execute(ArrayList<Resource> arrResources)
    {
        if (arrResources.size() > 0)
        {
            ArrayList<Integer> arrIntResourcesToKill = Input.getKillList(arrResources.size());
            ArrayList<Resource> arrResourcesToKill;

            if (arrIntResourcesToKill.size() == 1 && arrIntResourcesToKill.get(0).equals(-1))
            {
                System.out.println("");
                System.out.println("Aborting...");
            }
            else
            {
                arrResourcesToKill = ResourceManager.cleanKillList(arrIntResourcesToKill, arrResources);
                if (arrResourcesToKill.size() > 0)
                {
                    if (arrResourcesToKill.size() == arrResources.size())
                    {
                        System.out.println("Killing all resources found");
                    }
                    Boolean bConfirmation = Input.getConfirmation();
                    if (bConfirmation)
                    {
                        ResourceManager.kill(arrResourcesToKill);
                    }
                    else
                    {
                        System.out.println("You choose not to kill any resources");
                    }
                }
                else
                {
                    System.out.println("No valid resources to kill, please make sure the index entered was not out of bounds.");
                }
            }
        }
    }
}