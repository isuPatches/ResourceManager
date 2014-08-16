import java.util.*;

class Clean
{
    public static void all(String strSearchCriteria, Boolean bUseRegEx)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.all(arrAllResources, strSearchCriteria, bUseRegEx);
        Display.resources(arrResources, false);
        Clean.execute(arrResources);
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
                    Display.resources(arrBadResources, true);
                    Clean.execute(arrBadResources);
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
                Display.resources(arrBadResources, true);
                Clean.execute(arrBadResources);
            }
        }
        else
        {
            System.out.println("");
            System.out.println("Aborting...");
        }
    }

    public static void byName(String strSearchCriteria, Boolean bUseRegEx)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byName(arrAllResources, strSearchCriteria, bUseRegEx);
        Display.resources(arrResources, false);
        Clean.execute(arrResources);
    }

    public static void byPID(String strSearchCriteria, Boolean bUseRegEx)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byPID(arrAllResources, strSearchCriteria, bUseRegEx);
        Display.resources(arrResources, false);
        Clean.execute(arrResources);
    }

    public static void bySessionName(String strSearchCriteria, Boolean bUseRegEx)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.bySessionName(arrAllResources, strSearchCriteria, bUseRegEx);
        Display.resources(arrResources, false);
        Clean.execute(arrResources);
    }

    public static void bySessionNum(String strSearchCriteria, Boolean bUseRegEx)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.bySessionNum(arrAllResources, strSearchCriteria, bUseRegEx);
        Display.resources(arrResources, false);
        Clean.execute(arrResources);
    }

    public static void byMemUsage(Integer intMemUsage, String strOperator)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byMemUsage(arrAllResources, intMemUsage, strOperator);
        Display.resources(arrResources, false);
        Clean.execute(arrResources);
    }

    public static void byStatus(String strSearchCriteria, Boolean bUseRegEx)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byStatus(arrAllResources, strSearchCriteria, bUseRegEx);
        Display.resources(arrResources, false);
        Clean.execute(arrResources);
    }

    public static void byUserName(String strSearchCriteria, Boolean bUseRegEx)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byUserName(arrAllResources, strSearchCriteria, bUseRegEx);
        Display.resources(arrResources, false);
        Clean.execute(arrResources);
    }

    public static void byCPUTime(String strTime, String strOperator)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources =  Search.byCPUTime(arrAllResources, strTime, strOperator);
        Display.resources(arrResources, false);
        Clean.execute(arrResources);
    }

    public static void byWindowTitle(String strSearchCriteria, Boolean bUseRegEx)
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrResources = Search.byWindowTitle(arrAllResources, strSearchCriteria, bUseRegEx);
        Display.resources(arrResources, false);
        Clean.execute(arrResources);
    }

    public static void badStatus()
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrBadStatusResources = Search.badStatus(arrAllResources);
        Display.resources(arrBadStatusResources, false);
        Clean.execute(arrBadStatusResources);
    }

    public static void highMemUsage()
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrHighMemUsageResources = Search.highMemUsage(arrAllResources, 100000);
        Display.resources(arrHighMemUsageResources, false);
        Clean.execute(arrHighMemUsageResources);
    }

    public static void longCPUTime()
    {
        ArrayList<Resource> arrAllResources = Search.all();
        ArrayList<Resource> arrLongCPUTimeResources = Search.longCPUTime(arrAllResources);
        Display.resources(arrLongCPUTimeResources, false);
        Clean.execute(arrLongCPUTimeResources);
    }

    private static void execute(ArrayList<Resource> arrResources)
    {
        ArrayList<Integer> arrIntResourcesToKill = Input.getKillList(arrResources.size());
        ArrayList<Resource> arrResourcesToKill;
        if(arrIntResourcesToKill.size() == 1 && arrIntResourcesToKill.get(0).equals(-1))
        {
            System.out.println("");
            System.out.println("Aborting...");
        }
        else
        {
            arrResourcesToKill = ResourceManager.cleanKillList(arrIntResourcesToKill, arrResources);
            Boolean bConfirmation = Input.getConfirmation();
            if(bConfirmation)
            {
                ResourceManager.kill(arrResourcesToKill);
            }
            else
            {
                System.out.println("You chose not to kill any of the processes.");
            }
        }
    }
}